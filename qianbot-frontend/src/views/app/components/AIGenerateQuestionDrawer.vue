<template>
  <a-button type="outline" @click="handleClick"
    >AI 生成题目(剩余{{ props.remainingCount }}次机会)
  </a-button>
  <a-drawer
    :width="340"
    :visible="visible"
    @ok="handleOk"
    @cancel="handleCancel"
    unmountOnClose
  >
    <template #title>AI 生成题目</template>
    <div>
      <a-form
        :model="form"
        label-align="left"
        auto-label-width
        @submit="handleSubmit"
      >
        <a-form-item field="questionNumber" label="题目数量">
          <a-input-number
            :min="0"
            :max="10"
            v-model="form.questionNumber"
            placeholder="请输入题目数量"
          />
        </a-form-item>
        <a-form-item field="optionNumber" label="选项数量">
          <a-input-number
            :min="0"
            :max="10"
            v-model="form.optionNumber"
            placeholder="请输入选项数量"
          />
        </a-form-item>
        <a-form-item>
          <a-space>
            <a-button
              :loading="submitting"
              type="primary"
              html-type="submit"
              style="width: 120px"
              :disabled="submitting || sseSubmitting"
            >
              {{ submitting ? "生成中" : "一键生成" }}
            </a-button>
            <a-button
              :loading="sseSubmitting"
              type="primary"
              style="width: 120px"
              @click="handleSSESubmit"
              :disabled="submitting || sseSubmitting"
            >
              {{ submitting ? "生成中" : "实时生成" }}
            </a-button>
          </a-space>
        </a-form-item>
      </a-form>
    </div>
  </a-drawer>
</template>

<script setup lang="ts">
import { defineEmits, defineProps, reactive, ref, withDefaults } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { getAiGenerateQuestionSyncUsingPost } from "@/api/appController";

interface Props {
  appId: string;
  remainingCount: number;
  onSuccess?: (result: API.AppQuestionAndOptionVO[]) => void;
  onSSESuccess?: (result: API.AppQuestionAndOptionVO) => void;
  onSSEStart?: (event: any) => void;
  onSSEClose?: (event: any) => void;
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return "";
  },
  remainingCount: () => {
    return 0;
  },
});

const form = reactive({
  optionNumber: 2,
  questionNumber: 10,
} as API.AppAIGenerateRequest);

const visible = ref(false);
const submitting = ref(false);
const sseSubmitting = ref(false);

const handleClick = () => {
  visible.value = true;
};

const handleOk = () => {
  visible.value = false;
};

const handleCancel = () => {
  visible.value = false;
};
const emit = defineEmits(["updateRemainCount"]);
/**
 * 提交
 */
const handleSubmit = async () => {
  if (!props.appId) {
    return;
  }
  submitting.value = true;
  const res = await getAiGenerateQuestionSyncUsingPost({
    appId: props.appId as any,
    ...form,
  });
  if (res.data.code === 200 && res.data.data.length > 0) {
    if (props.onSuccess) {
      props.onSuccess(res.data.data);
    } else {
      message.success("生成题目成功");
    }
    emit("updateRemainCount");
    // 关闭抽屉
    handleCancel();
  } else {
    message.error("操作失败，" + res.data.message);
  }
  submitting.value = false;
};

const handleSSESubmit = async () => {
  if (!props.appId) {
    return;
  }
  sseSubmitting.value = true;
  // 建立 SSE 连接
  // 手动填写完整的后端地址
  var baseUrl = "http://localhost:9000/api/app/ai/generate/sse";
  if (process.env.NODE_ENV === "development") {
    baseUrl =
      "https://qianbot-backend-115575-5-1317982725.sh.run.tcloudbase.com";
  }
  const eventSource = new EventSource(
    `${baseUrl}?appId=${props.appId}&optionNumber=${form.optionNumber}&questionNumber=${form.questionNumber}`,
    {
      withCredentials: true,
    }
  );
  visible.value = false;
  let first = true;
  //  接收消息
  eventSource.onmessage = (event) => {
    if (first) {
      props.onSSEStart?.(event);
      handleCancel();
      first = !first;
    }
    props.onSSESuccess?.(JSON.parse(event.data));
  };
  // 监听连接关闭
  eventSource.onerror = (event) => {
    if (event.eventPhase === EventSource.CLOSED) {
      eventSource.close();
      sseSubmitting.value = false;
      props.onSSEClose?.(event);
      // console.log("SSE 连接结束");
    } else {
      // console.error("SSE 连接错误:", event);
      eventSource.close();
    }
  };
  // 监听连接打开
  eventSource.onopen = (event) => {
    // console.log("SSE 连接成功:", event);
    emit("updateRemainCount");
  };
};
</script>
