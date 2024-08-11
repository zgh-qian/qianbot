<script setup lang="ts">
import CodeEditor from "@/components/CodeEditor.vue";
import { ref, watchEffect } from "vue";
import {
  executeUsingPost,
  getCodeSandboxLanguageUsingGet,
} from "@/api/codeSandboxController";
// eslint-disable-next-line no-undef
import CodeSandboxLanguage = API.CodeSandboxLanguage;
import { Message } from "@arco-design/web-vue";

const languageList = ref<CodeSandboxLanguage[]>([]);
const language = ref("java");
const code = ref("");
const theme = ref("vs-dark");

const isRunning = ref<boolean>(false);
const isCollapse = ref<number>(1);
const result = ref({});

const onCodeChange = (value: string) => {
  code.value = value;
};

const getLanguageList = async () => {
  const res = await getCodeSandboxLanguageUsingGet();
  if (res.data.code === 200) {
    languageList.value = res.data.data as any;
  } else {
    Message.error("提交失败，" + res.data.message);
  }
};

const onMockCode = () => {
  languageList.value.forEach((lang) => {
    if (lang.value === language.value) {
      code.value = lang.templateCode as string;
    }
  });
};

const onRunCode = async () => {
  isRunning.value = true;
  result.value = {};
  const res = await executeUsingPost({
    code: code.value,
    language: language.value,
  });
  if (res.data.code === 200) {
    result.value = res.data.data as any;
    isCollapse.value = 1;
    isRunning.value = false;
  } else {
    Message.error("提交失败，" + res.data.message);
  }
};

watchEffect(() => {
  getLanguageList();
});
</script>

<template>
  <div id="codesandboxPage">
    <a-space class="top">
      <a-select v-model="language" style="margin-bottom: 1vh" :bordered="false">
        <a-option
          v-for="item in languageList"
          :label="item.text"
          :value="item.value"
          :key="item.value"
        />
      </a-select>
      <a-select v-model="theme" style="margin-bottom: 1vh" :bordered="false">
        <a-option label="vs" value="vs" />
        <a-option label="vs-dark" value="vs-dark" />
        <a-option label="hc-dark" value="hc-dark" />
      </a-select>
    </a-space>
    <a-space class="top">
      <a-button @click="onMockCode" type="text"> Hello World </a-button>
      <a-button @click="onRunCode" type="outline" v-if="false"> 保存 </a-button>
      <a-button @click="onRunCode" :loading="isRunning" type="primary">
        运行
      </a-button>
    </a-space>
    <CodeEditor
      style="width: 70vw; height: 60vh"
      :language="language"
      :value="code"
      :theme="theme"
      :handle-change="onCodeChange"
    />
    <a-collapse class="result" v-model="isCollapse" :default-active-key="[1]">
      <a-collapse-item
        :header="`执行结果(退出码：${result?.exitCode})`"
        key="1"
        v-if="Object.keys(result).length > 0"
      >
        <a-typography-paragraph
          v-if="result.message && result.message != ''"
          copyable
        >
          {{ result?.message }}
        </a-typography-paragraph>
      </a-collapse-item>
    </a-collapse>
  </div>
</template>

<style scoped>
#codesandboxPage {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.top {
  justify-content: flex-end;
  width: 100%;
  margin-bottom: 1vh;
}
.result {
  width: 100%;
}
</style>
