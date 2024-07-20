<script setup lang="ts">
import {
  computed,
  defineProps,
  reactive,
  ref,
  watchEffect,
  withDefaults,
} from "vue";
import API from "@/api";
import { useRouter } from "vue-router";
import message from "@arco-design/web-vue/es/message";
import {
  addAppAnswerUsingPost,
  generateIdUsingGet,
  getAppAnswerVoByIdUsingGet,
} from "@/api/appAnswerController";
import {
  getAppQuestionAndOptionUsingGet,
  getAppVoByIdUsingGet,
} from "@/api/appController";

interface Props {
  appId: string;
}

const props = withDefaults(defineProps<Props>(), {
  appId: () => {
    return "";
  },
});

const router = useRouter();

const app = ref<API.AppVO>({});
// 题目列表
const questionContent = ref<API.AppQuestionAndOptionVO[]>([]);
// 当前题目的序号（从 1 开始）
const current = ref(1);
// 当前题目
const currentQuestion = ref<API.AppQuestionAndOptionVO>({});
// 当前题目选项
const questionOptions = computed(() => {
  return currentQuestion.value?.optionList
    ? currentQuestion.value.optionList.map((option) => {
        return {
          label: `${option.optionKey}. ${option.optionName}`,
          value: option.id,
        };
      })
    : [];
});
// 当前答案
const currentAnswer = ref<string>();
// 回答列表
const answerList = reactive<string[]>([]);
// 是否正在提交结果
const submitting = ref(false);
// 唯一 id
const id = ref<string>();
// 生成唯一 id
const generateId = async () => {
  const res = await generateIdUsingGet();
  if (res.data.code === 200) {
    id.value = res.data.data as any;
  } else {
    message.error("获取唯一 id 失败，" + res.data.message);
  }
};
watchEffect(() => {
  generateId();
});
/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.appId) {
    return;
  }
  // 获取 app
  const appRes = await getAppVoByIdUsingGet({
    id: props.appId as any,
  });
  if (appRes.data.code === 200) {
    app.value = appRes.data.data as any;
  } else {
    message.error("获取应用失败，" + appRes.data.message);
  }
  // 获取题目
  const res = await getAppQuestionAndOptionUsingGet({
    appId: props.appId as any,
  });
  if (res.data.code === 200 && res.data.data) {
    questionContent.value = res.data.data;
  } else {
    message.error("获取题目失败，" + res.data.message);
  }
};

// 获取旧数据
watchEffect(() => {
  loadData();
});

// 改变 current 题号后，会自动更新当前题目和答案
watchEffect(() => {
  currentQuestion.value = questionContent.value[current.value - 1];
  currentAnswer.value = answerList[current.value - 1];
});

/**
 * 选中选项后，保存选项记录
 * @param value
 */
const doRadioChange = (value: string) => {
  answerList[current.value - 1] = value;
};

/**
 * 提交
 */
const doSubmit = async () => {
  if (!props.appId || !answerList) {
    return;
  }
  submitting.value = true;
  const res = await addAppAnswerUsingPost({
    id: id.value as any,
    appId: props.appId as any,
    userAnswer: answerList as any,
  });
  if (res.data.code === 200 && res.data.data) {
    message.success("提交成功，即将跳转结果页面");
    let fetchAnswer = false;
    while (!fetchAnswer) {
      const answerRes = await getAppAnswerVoByIdUsingGet({
        id: res.data.data as any,
      });
      if (answerRes.data.code === 200) {
        fetchAnswer = true;
        submitting.value = false;
      }
    }
    router.push(`/app/result/${res.data.data}`);
  } else {
    message.error("提交答案失败，" + res.data.message);
    submitting.value = false;
  }
};
</script>
<template>
  <div>
    <a-card class="doAnswerPage">
      <template #title>
        {{ app.appName }}
      </template>
      <a-typography-title :heading="4">
        {{ current }}、{{ currentQuestion?.question?.questionName }}
      </a-typography-title>
      <div class="radioGroup">
        <a-radio-group
          v-model="currentAnswer"
          :options="questionOptions as any"
          @change="doRadioChange"
        />
      </div>
      <a-space size="large" class="bottomBtn">
        <a-button
          :disabled="current <= 1"
          circle
          @click="current -= 1"
          style="width: 150px; height: 50px"
        >
          上一题
        </a-button>
        <a-button
          type="primary"
          circle
          v-if="current < questionContent.length"
          :disabled="!currentAnswer"
          @click="current += 1"
          style="width: 150px; height: 50px"
        >
          下一题
        </a-button>
        <a-button
          type="primary"
          v-if="current === questionContent.length"
          :loading="submitting"
          circle
          :disabled="!currentAnswer"
          @click="doSubmit"
          style="width: 150px; height: 50px"
        >
          {{ submitting ? "评分中" : "查看结果" }}
        </a-button>
      </a-space>
    </a-card>
    <a-progress :percent="current / questionContent.length">
      <template v-slot:text="scope">
        进度 {{ scope.percent * questionContent.length }}/{{
          questionContent.length
        }}
      </template>
    </a-progress>
  </div>
</template>
<style scoped>
.doAnswerPage {
  width: 1000px;
  margin: 20px auto 0;
  margin-bottom: 50px;
  text-align: center;
}

.radioGroup {
  display: flex;
  justify-content: center;
  width: 100%;
  margin-top: 50px;
}

.bottomBtn {
  justify-content: center;
  width: 100%;
  margin-top: 50px;
}
</style>
