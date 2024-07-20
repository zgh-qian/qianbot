<template>
  <a-card class="appResultPage">
    <a-row>
      <a-col :span="isPic ? 12 : 24">
        <a-typography-title :heading="3">
          {{ data.appName }}
        </a-typography-title>
        <!--<a-typography-title :heading="4">
          {{ data.resultName }}
        </a-typography-title>
        <a-typography-title :heading="6">
          {{ data.resultDesc }}
        </a-typography-title>-->
        <a-descriptions size="large" :column="1">
          <a-descriptions-item label="结果名称">
            {{ data.resultName }}
          </a-descriptions-item>
          <a-descriptions-item label="结果描述">
            {{ data.resultDesc }}
          </a-descriptions-item>
          <a-descriptions-item v-if="data.resultScore" label="结果得分">
            {{ data.resultScore }}
          </a-descriptions-item>
          <a-descriptions-item label="答题时间">
            {{ dayjs(data.createTime).format("YYYY-MM-DD HH:mm:ss") }}
          </a-descriptions-item>
        </a-descriptions>
        <a-space class="bottomBtn">
          <a-button
            type="outline"
            :href="`/app/detail/${data.appId}`"
            style="width: 150px; height: 50px"
            >返回题目
          </a-button>
          <a-button
            type="primary"
            href="/app"
            style="width: 150px; height: 50px"
            >返回答题应用页
          </a-button>
          <a-button
            type="outline"
            :href="`/app/answer/${data.appId}`"
            style="width: 150px; height: 50px"
            >再次答题
          </a-button>
        </a-space>
      </a-col>
      <a-col v-if="isPic" :span="12">
        <a-image width="100%" :src="data.resultPic" alt="结果图片" />
      </a-col>
    </a-row>
  </a-card>
</template>

<script setup lang="ts">
import { defineProps, ref, watchEffect, withDefaults } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import { getAppAnswerVoByIdUsingGet } from "@/api/appAnswerController";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

const data = ref<API.AppanswerVO>({});
const isPic = ref(false);
/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getAppAnswerVoByIdUsingGet({
    id: props.id as any,
  });
  if (res.data.code === 200) {
    data.value = res.data.data as any;
    if (data.value.resultPic) {
      isPic.value = true;
    }
  } else {
    message.error("获取数据失败，" + res.data.message);
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
.appResultPage {
  width: 1000px;
  margin: 20px auto 0;
}

.appResultPage .content-wrapper > * {
  margin-bottom: 20px;
}

.bottomBtn {
  justify-content: space-around;
  width: 100%;
}
</style>
