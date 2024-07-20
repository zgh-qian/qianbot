<template>
  <div class="appDetailPage">
    <a-card class="appCard">
      <a-row :gutter="1" style="justify-content: center; align-items: center">
        <a-col :span="isExistIcon ? 12 : 24" class="content-wrapper">
          <a-typography-title :heading="5">{{
            appVO.appName
          }}</a-typography-title>
          <a-typography-paragraph>{{ appVO.appDesc }}</a-typography-paragraph>
          <a-descriptions
            style="margin-top: 20px"
            :data="descriptionData as any"
            size="large"
            :column="1"
          />
          <div class="basicBtn">
            <a-button
              style="width: 150px; height: 50px"
              type="primary"
              :href="`/app/answer/${id}`"
            >
              开始答题
            </a-button>
            <a-button
              style="width: 150px; height: 50px"
              v-if="appVO.reviewStatus === REVIEW_STATUS_ENUM.PASS"
              @click="doShare"
              type="outline"
              >分享应用</a-button
            >
          </div>
          <a-divider
            v-if="isMy"
            :size="2"
            style="border-bottom-style: dotted"
          />
          <a-row justify="space-between" v-if="isMy">
            <a-col :span="5">
              <a-button @click="handleCheckOutQuestion" type="primary"
                >查看题目</a-button
              >
            </a-col>
            <a-col :span="5">
              <a-button @click="openAddAppQuestionModal" type="primary"
                >设置题目
              </a-button>
            </a-col>
            <a-col :span="5">
              <a-button @click="openAddAppResultModal" type="primary"
                >设置评分
              </a-button>
            </a-col>
            <a-col :span="5">
              <a-button @click="openAddAppModal" type="primary"
                >修改应用</a-button
              >
            </a-col>
            <a-col :span="2">
              <a-button @click="deleteApp" status="danger">
                <template #icon>
                  <icon-delete />
                </template>
              </a-button>
            </a-col>
          </a-row>
        </a-col>
        <a-col :span="12" v-if="appVO.appIcon">
          <a-image width="100%" :src="appVO.appIcon" />
        </a-col>
      </a-row>
    </a-card>
    <ShareModal :link="shareLink" title="应用分享" ref="shareModalRef" />
    <AppQuestionModal
      ref="appQuestionModalRef"
      :app-id="props.id"
      :app-name="appVO.appName"
    />
    <AddAppModal
      ref="addAppModal"
      :is-add="false"
      :id="props.id"
      @updateData="loadData"
    />
    <AddAppQuestionModal
      ref="addAppQuestionModal"
      :app-id="props.id"
      :app-v-o="appVO"
    />
    <AddAppResultModal
      ref="addAppResultModal"
      :app-id="props.id"
      :app-v-o="appVO"
    />
  </div>
</template>

<script setup lang="ts">
import { computed, defineProps, ref, watchEffect, withDefaults } from "vue";
import API from "@/api";
import { deleteAppUsingPost, getAppVoByIdUsingGet } from "@/api/appController";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import { useLoginUserStore } from "@/store/userStore";
import {
  APP_SCORING_STRATEGY_MAP,
  APP_TYPE_MAP,
  REVIEW_STATUS_ENUM,
} from "@/constant/app";
import ShareModal from "@/components/ShareModal.vue";
import AddAppModal from "@/views/app/components/AddAppModal.vue";
import AddAppQuestionModal from "@/views/app/components/AddAppQuestionModal.vue";
import AddAppResultModal from "@/views/app/components/AppResultModal.vue";
import AppQuestionModal from "@/views/app/components/AppQuestionModal.vue";
import { IconDelete } from "@arco-design/web-vue/es/icon";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

const appVO = ref<API.AppVO>({});
// 获取登录用户
const loginUserStore = useLoginUserStore();
let loginUserId = loginUserStore.loginUser?.id;
// 是否为本人创建
const isMy = computed(() => {
  return loginUserId && loginUserId === appVO.value.userId;
});
const isExistIcon = computed(() => {
  return !!appVO.value.appIcon;
});

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getAppVoByIdUsingGet({
    id: props.id as any,
  });
  if (res.data.code === 200) {
    appVO.value = res.data.data as any;
    // 设置描述数据
    descriptionData.value = [
      {
        label: "应用类型",
        value: APP_TYPE_MAP[appVO.value.appType],
      },
      {
        label: "评分策略",
        value: APP_SCORING_STRATEGY_MAP[appVO.value.scoringStrategy],
      },
      {
        label: "创建时间",
        value: dayjs(appVO.value.createTime).format("YYYY-MM-DD HH:mm:ss"),
      },
      {
        label: "作者",
        value: appVO.value.userVO?.userName,
      },
    ];
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

const shareModalRef = ref();
const shareLink = `${window.location.protocol}//${window.location.host}/app/detail/${props.id}`;

const doShare = (e: Event) => {
  if (shareModalRef.value) {
    shareModalRef.value.openModal();
  }
  // 阻止事件冒泡
  e.stopPropagation();
};
const descriptionData = ref([]);
const appQuestionModalRef = ref();
const handleCheckOutQuestion = () => {
  appQuestionModalRef.value.openModal();
};
const addAppModal = ref();
const openAddAppModal = () => {
  addAppModal.value.openModal();
};

const addAppQuestionModal = ref();
const openAddAppQuestionModal = () => {
  addAppQuestionModal.value.openModal();
};

const addAppResultModal = ref();
const openAddAppResultModal = () => {
  addAppResultModal.value.openModal();
};
// 删除应用
const deleteApp = async () => {
  const res = await deleteAppUsingPost({
    id: props.id as any,
  });
  if (res.data.code === 200) {
    message.success("删除应用成功，即将回到上一个页面");
    setTimeout(() => {
      window.history.back();
    }, 2000);
  } else {
    message.error("删除应用失败，" + res.data.message);
  }
};
/**
 * 监听 searchParams 变量，改变时触发数据的重新加载
 */
watchEffect(() => {
  loadData();
});
</script>

<style scoped>
.appCard {
  width: 1000px;
  height: 100%;
}
.appDetailPage .content-wrapper > * {
  margin-bottom: 5px;
}
.basicBtn {
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 100%;
  height: 100%;
}
</style>
