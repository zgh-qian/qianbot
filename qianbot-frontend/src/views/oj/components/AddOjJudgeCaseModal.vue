<template>
  <div class="share-modal">
    <a-modal
      v-model:visible="visible"
      @cancel="closeModal"
      @ok="handleOk"
      width="auto"
    >
      <template #title>
        {{ id === "" || id === null ? "添加用例" : "修改用例" }}
      </template>
      <a-form :model="oldOjJudgeCase" :style="{ width: '600px' }">
        <a-form-item label="输入">
          <a-input v-model="oldOjJudgeCase.input" placeholder="请输入输入" />
        </a-form-item>
        <a-form-item label="输出">
          <a-input v-model="oldOjJudgeCase.output" placeholder="请输入输出" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { defineExpose, ref, defineEmits } from "vue";
import message from "@arco-design/web-vue/es/message";
import API from "@/api";
import {
  addOjJudgecaseUsingPost,
  getOjJudgecaseByIdUsingGet,
  updateOjJudgecaseUsingPost,
} from "@/api/ojJudgecaseController";
/**
 * 定义组件属性类型
 */
interface Props {
  questionId: string;
}

/**
 * 给组件指定初始值
 */
// eslint-disable-next-line no-undef
const props = withDefaults(defineProps<Props>(), {
  questionId: () => "",
});

const id = ref("");
const visible = ref(false);

const openModal = (ojJudgeCaseId: any) => {
  id.value = ojJudgeCaseId;
  loadData();
  visible.value = true;
};

const closeModal = () => {
  visible.value = false;
};

defineExpose({
  openModal,
});

const oldOjJudgeCase = ref<API.OjJudgecaseVO>({});
/**
 * 加载数据
 */
const loadData = async () => {
  if (!id.value || id.value === "") {
    oldOjJudgeCase.value = {};
    return;
  }
  const res = await getOjJudgecaseByIdUsingGet({ id: id.value as any });
  if (res.data.code === 200 && res.data.data) {
    oldOjJudgeCase.value = res.data.data;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

const emit = defineEmits(["updateData"]);

const handleOk = async () => {
  // 如果是修改
  if (id.value !== "") {
    const res = await updateOjJudgecaseUsingPost({
      id: id.value as any,
      ...oldOjJudgeCase.value,
    });
    if (res.data.code === 200) {
      closeModal();
      message.success("修改成功");
    } else {
      message.error("修改失败，" + res.data.message);
    }
  } else {
    // 创建
    const res = await addOjJudgecaseUsingPost({
      ...oldOjJudgeCase.value,
      questionId: props.questionId as any,
    });
    if (res.data.code === 200) {
      message.success("添加成功");
      closeModal();
    } else {
      message.error("添加失败，" + res.data.message);
    }
  }
  emit("updateData");
};
</script>
