<template>
  <a-card class="appCard" hoverable @click="doCardClick">
    <template #actions>
      <!--      <span class="icon-hover"> <IconThumbUp /> </span>-->
      <span class="icon-hover" @click="doShare"> <IconShareInternal /> </span>
    </template>
    <template #cover v-if="app.appIcon">
      <div
        :style="{
          overflow: 'hidden',
        }"
      >
        <img
          :style="{
            width: '100%',
            transform: 'translateY(-20px)',
            // height: '200px',
            height: 'auto',
          }"
          :alt="app.appName"
          :src="app.appIcon"
        />
      </div>
    </template>
    <!--<a-card-meta :title="app.appName" :description="app.appDesc">-->
    <a-card-meta :title="app.appName">
      <template #avatar>
        <div
          :style="{ display: 'flex', alignItems: 'center', color: '#1D2129' }"
        >
          <a-avatar
            :size="24"
            :image-url="app.userVO?.userAvatar"
            :style="{ marginRight: '8px' }"
          />
          <a-typography-text>{{ app.userVO?.userName }} </a-typography-text>
        </div>
      </template>
    </a-card-meta>
  </a-card>
  <ShareModal :link="shareLink" title="应用分享" ref="shareModalRef" />
</template>

<script setup lang="ts">
import { IconShareInternal } from "@arco-design/web-vue/es/icon";
import API from "@/api";
import { defineProps, ref, withDefaults } from "vue";
import { useRouter } from "vue-router";
import ShareModal from "@/components/ShareModal.vue";

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
