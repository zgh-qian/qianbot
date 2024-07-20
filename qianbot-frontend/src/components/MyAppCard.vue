<template>
  <a-card class="appCard" hoverable @click="doCardClick">
    <template #cover v-if="app.appIcon">
      <div
        :style="{
          overflow: 'hidden',
        }"
      >
        <img
          :style="{ width: '100%', transform: 'translateY(-20px)' }"
          :alt="app.appName"
          :src="app.appIcon"
        />
      </div>
    </template>
    <a-card-meta :title="app.appName"> </a-card-meta>
    <template #actions>
      <span>
        <a-tag :color="getAppColor(app.reviewStatus as number)">{{
          REVIEW_STATUS_MAP[app.reviewStatus]
        }}</a-tag>
      </span>
      <span
        v-if="app.reviewStatus === REVIEW_STATUS_ENUM.PASS"
        class="icon-hover"
        @click="doShare"
      >
        <IconShareInternal />
      </span>
    </template>
  </a-card>
  <ShareModal :link="shareLink" title="应用分享" ref="shareModalRef" />
</template>

<script setup lang="ts">
import { IconShareInternal } from "@arco-design/web-vue/es/icon";
import API from "@/api";
import { defineProps, ref, withDefaults } from "vue";
import { useRouter } from "vue-router";
import ShareModal from "@/components/ShareModal.vue";
import {
  getAppColor,
  REVIEW_STATUS_ENUM,
  REVIEW_STATUS_MAP,
} from "@/constant/app";

interface Props {
  app: API.AppVO;
}

const props = withDefaults(defineProps<Props>(), {
  app: () => {
    return {};
  },
});

const router = useRouter();

const doCardClick = () => {
  router.push(`/app/detail/${props.app.id}`);
};

const shareModalRef = ref();

const shareLink = `${window.location.protocol}//${window.location.host}/app/detail/${props.app.id}`;

const doShare = (e: Event) => {
  if (shareModalRef.value) {
    shareModalRef.value.openModal();
  }
  // 阻止事件冒泡
  e.stopPropagation();
};
</script>
<style scoped>
.appCard {
  cursor: pointer;
}

.icon-hover {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 24px;
  height: 24px;
  border-radius: 50%;
  transition: all 0.1s;
}

.icon-hover:hover {
  background-color: rgb(var(--gray-2));
}
</style>
