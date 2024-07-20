<template>
  <div class="share-modal">
    <a-modal v-model:visible="visible" @cancel="closeModal" :footer="false">
      <template #title>
        {{ title }}
      </template>
      <h4>复制分享链接</h4>
      <a-typography-paragraph copyable>{{ link }}</a-typography-paragraph>
      <img alt="codeImg" :src="codeImg" />
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { defineExpose, withDefaults, defineProps, ref } from "vue";
//@ts-ignore
import QRCode from "qrcode";
import message from "@arco-design/web-vue/es/message";
/**
 * 定义组件属性类型
 */
interface Props {
  // 分享链接
  link: string;
  // 分享标题
  title: string;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  link: () => "http://localhost:8080/",
  title: () => "分享",
});

const codeImg = ref("");

QRCode.toDataURL(props.link)
  .then((url) => {
    codeImg.value = url;
  })
  .catch((err) => {
    console.error(err);
    message.error("生成二维码失败，" + err.message);
  });
const visible = ref(false);

const openModal = () => {
  visible.value = true;
};

const closeModal = () => {
  visible.value = false;
};
defineExpose({
  openModal,
});
</script>
