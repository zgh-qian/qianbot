<!-- eslint-disable @typescript-eslint/no-unused-vars -->
<!-- eslint-disable @typescript-eslint/no-empty-function -->
<!-- eslint-disable no-undef -->
<!-- 使用示例
import CodeEditor from '@/components/CodeEditor.vue'
import { ref } from 'vue'
const value = ref('')
const onChange = (value: string) => {
  console.log(value)
}

<CodeEditor :value="value" :handle-change="onChange" />
-->
<script setup lang="ts">
import * as monaco from "monaco-editor";
import { onMounted, ref, toRaw, watch } from "vue";

const codeEditorRef = ref();
const codeEditor = ref();

/**
 * 定义组件属性类型
 */
interface Props {
  value: string;
  language?: string;
  theme?: string;
  handleChange: (v: string) => void;
}
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
  language: () => "",
  theme: () => "",
  handleChange: () => {},
});

watch(
  () => props.language,
  async () => {
    if (!codeEditor.value) {
      return;
    }
    if (codeEditor.value) {
      monaco.editor.setModelLanguage(
        toRaw(codeEditor.value).getModel(),
        props.language
      );
    }
  },
  { immediate: true }
);

watch(
  () => props.value,
  async () => {
    if (!codeEditor.value) {
      return;
    }
    if (codeEditor.value) {
      const newValue = props.value;
      const currentValue = toRaw(codeEditor.value).getValue();
      if (newValue !== currentValue) {
        toRaw(codeEditor.value).setValue(newValue);
      }
    }
  },
  { immediate: true }
);

watch(
  () => props.theme,
  async () => {
    if (!codeEditor.value) {
      return;
    }
    if (codeEditor.value) {
      monaco.editor.setTheme(props.theme);
    }
  },
  { immediate: true }
);

onMounted(() => {
  if (!codeEditorRef.value) {
    return;
  }
  codeEditor.value = monaco.editor.create(codeEditorRef.value, {
    value: props.value,
    language: props.language || "java",
    automaticLayout: true,
    colorDecorators: true,
    minimap: {
      enabled: true,
    },
    theme: props.theme || "vs-dark",
    readOnly: false,
  });
  codeEditor.value.onDidChangeModelContent(() => {
    props.handleChange(toRaw(codeEditor.value).getValue());
  });
});
</script>
<template>
  <div id="code-editor" ref="codeEditorRef" />
</template>
<style scoped>
#code-editor {
  min-width: 50px;
  min-height: 50px;
}
</style>
