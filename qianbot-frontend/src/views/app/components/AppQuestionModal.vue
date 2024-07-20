<script setup lang="ts">
import { defineExpose, defineProps, ref, withDefaults } from "vue";
import API from "@/api";
import { getAppQuestionAndOptionUsingGet } from "@/api/appController";
import message from "@arco-design/web-vue/es/message";

/**
 * 定义组件属性类型
 */
interface Props {
  appId: string;
  appName: string;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  appId: () => "",
  appName: () => "",
});

const visible = ref(false);
const openModal = () => {
  loadData();
  visible.value = true;
};

const closeModal = () => {
  visible.value = false;
};
defineExpose({
  openModal,
});
const appQuestionOption = ref<API.AppQuestionAndOptionVO[]>();
/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.appId) {
    return;
  }
  const res = await getAppQuestionAndOptionUsingGet({
    appId: props.appId as any,
  });
  if (res.data.code === 200) {
    appQuestionOption.value = res.data.data as any;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};
</script>
<template>
  <div id="appQuestionModal">
    <a-modal
      v-model:visible="visible"
      @cancel="closeModal"
      :footer="false"
      fullscreen
      :title="appName"
    >
      <template v-if="appQuestionOption">
        <a-list
          v-if="appQuestionOption"
          :data="appQuestionOption"
          :bordered="false"
        >
          <template #item="{ item, index }">
            <a-list-item :key="index">
              <a-list-item-meta
                :title="item.question.questionName"
              ></a-list-item-meta>
              <div
                v-for="(option, optionIndex) of item.optionList"
                :key="optionIndex"
              >
                <a-space>
                  {{ option.optionKey }}. {{ option.optionName }}
                  {{ option.optionResult }}
                </a-space>
              </div>
            </a-list-item>
          </template>
        </a-list>
      </template>
    </a-modal>
  </div>
</template>
<style scoped>
#appQuestionModal {
  width: 100%;
  margin: 0 auto;
}
.arco-list-item-meta-title {
  font-weight: 1000;
  font-size: larger;
}
.arco-list-item-meta {
  justify-content: center;
}
.arco-list-item {
  text-align: center;
}
</style>
