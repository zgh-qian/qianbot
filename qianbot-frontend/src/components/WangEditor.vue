<script setup lang="ts">
import "@wangeditor/editor/dist/css/style.css"; // 引入 css
import {
  defineEmits,
  defineProps,
  onBeforeUnmount,
  onMounted,
  ref,
  shallowRef,
  watchEffect,
  withDefaults,
  defineExpose,
} from "vue";
import { Editor, Toolbar } from "@wangeditor/editor-for-vue";
import { uploadFileUsingPost } from "@/api/fileController";
import { Message } from "@arco-design/web-vue";

interface Props {
  initValueHtml: string;
}

const props = withDefaults(defineProps<Props>(), {
  initValueHtml: () => {
    return "";
  },
});

// 编辑器实例，必须用 shallowRef
const editorRef = shallowRef();
const mode = "default";
// 内容 HTML
const valueHtml = ref("");

onMounted(() => {
  valueHtml.value = props.initValueHtml;
});

const toolbarConfig = {};
type InsertFnType = (url: string, alt: string, href: string) => void;
const editorConfig = {
  placeholder: "请输入内容...",
  scroll: true,
  MENU_CONF: {
    uploadImage: {
      async customUpload(file: File, insertFn: InsertFnType) {
        const res = await uploadFileUsingPost({ biz: "oj_question" }, {}, file);
        if (res.data.code === 200) {
          const alt = file.name;
          const href = "";
          insertFn(res.data.data, alt, href);
        } else {
          Message.error("上传失败，" + res.data.message);
        }
      },
    },
  },
};
// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value;
  if (editor == null) return;
  editor.destroy();
});

const handleCreated = (editor: any) => {
  editorRef.value = editor; // 记录 editor 实例，重要！
};

const emit = defineEmits(["updateValueHtml"]);

watchEffect(() => {
  emit("updateValueHtml", valueHtml.value);
});

const setValueHtml = (html: string) => {
  valueHtml.value = html;
};

defineExpose({
  setValueHtml,
});
</script>

<template>
  <div style="border: 1px solid #ccc">
    <Toolbar
      style="border-bottom: 1px solid #ccc"
      :editor="editorRef"
      :defaultConfig="toolbarConfig"
      :mode="mode"
    />
    <Editor
      style="min-height: 300px; overflow-y: hidden"
      v-model="valueHtml"
      :defaultConfig="editorConfig"
      :mode="mode"
      @onCreated="handleCreated"
    />
  </div>
</template>

<style scoped></style>
