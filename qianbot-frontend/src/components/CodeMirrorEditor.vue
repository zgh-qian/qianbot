<script setup lang="ts">
import { ref, onUnmounted, withDefaults, defineProps } from "vue";
import Codemirror from "codemirror-editor-vue3";
import "codemirror/mode/javascript/javascript.js";

interface Props {
  language: string;
  code: string;
  height: string;
  width: string;
}

const props = withDefaults(defineProps<Props>(), {
  language: () => {
    return "";
  },
  code: () => {
    return "";
  },
  height: () => {
    return "400";
  },
  width: () => {
    return "600";
  },
});

const code = ref("");

const cmRef = ref();
const cmOptions = ref({
  mode: "text/javascript",
});
const onChange = (val: string, cm: any) => {
  console.log("onChange:" + val);
  console.log("cm:" + cm.getValue());
};

const onInput = (val: string) => {
  console.log("onInput:" + val);
};

const onReady = (cm: any) => {
  // console.log(cm.focus());
};

onUnmounted(() => {
  cmRef.value?.destroy();
});
</script>
<template>
  <div style="width: 100%">
    <div>
      <a-select v-model="cmOptions.mode" placeholder="请输入编程语言">
        <a-option label="Java" value="text/java" />
        <a-option label="JavaScript" value="text/javascript" />
        <a-option label="Python" value="python" />
      </a-select>
    </div>
    <Codemirror
      v-model:value="code"
      :options="cmOptions"
      border
      ref="cmRef"
      :height="height"
      :width="width"
      @change="onChange"
      @input="onInput"
      @ready="onReady"
    >
    </Codemirror>
  </div>
</template>
