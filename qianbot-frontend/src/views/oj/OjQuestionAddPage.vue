<script setup lang="ts">
import WangEditor from "@/components/WangEditor.vue";
import { defineProps, ref, watchEffect, withDefaults } from "vue";
import OjQuestionDetailAddRequest = API.OjQuestionDetailAddRequest;
import {
  addOjQuestionDetailUsingPost,
  deleteOjQuestionDetailUsingPost,
  getOjQuestionDetailUsingGet,
  updateOjQuestionDetailUsingPost,
} from "@/api/ojQuestionController";
import API from "@/api";
import { Message } from "@arco-design/web-vue";
import OjJudgeCaseModal from "@/views/oj/components/OjJudgeCaseModal.vue";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

const ojQuestionDetail = ref<OjQuestionDetailAddRequest>({});
const tips = ref<string[]>([]);
const wangEditorRef = ref();

const updateValueHtml = (value: string) => {
  ojQuestionDetail.value.content = value;
};

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getOjQuestionDetailUsingGet({
    id: props.id as any,
  });
  if (res.data.code === 200) {
    ojQuestionDetail.value = res.data.data as any;
    tips.value = ojQuestionDetail.value.tips as any;
    wangEditorRef.value.setValueHtml(ojQuestionDetail.value.content);
  } else {
    Message.error("获取数据失败，" + res.data.message);
  }
};

const deleteOjQuestion = async () => {
  const res = await deleteOjQuestionDetailUsingPost({
    id: props.id as any,
  });
  if (res.data.code === 200) {
    Message.success("删除成功");
  } else {
    Message.error("删除失败，" + res.data.message);
  }
};

const addOjQuestion = async () => {
  if (!props.id) {
    // 添加题目
    const res = await addOjQuestionDetailUsingPost({
      ...ojQuestionDetail.value,
      ...tips.value,
    });
    if (res.data.code === 200) {
      Message.success("添加成功");
    } else {
      Message.error("添加失败，" + res.data.message);
    }
  } else {
    // 修改题目
    const res = await updateOjQuestionDetailUsingPost({
      ...ojQuestionDetail.value,
      ...tips.value,
      id: props.id as any,
    });
    if (res.data.code === 200) {
      Message.success("修改成功");
    } else {
      Message.error("修改失败，" + res.data.message);
    }
  }
};

const ojJudgeCaseModalRef = ref();
const showOjJudgeCase = () => {
  ojJudgeCaseModalRef.value.openModal();
};

watchEffect(() => {
  loadData();
});
</script>

<template>
  <div id="ojQuestionAddPage">
    <h1 style="text-align: center">
      {{ id === "" ? "添加题目" : "编辑题目" }}
    </h1>
    <a-form :model="ojQuestionDetail">
      <a-form-item label="题目标题" required>
        <a-input
          v-model.number="ojQuestionDetail.title"
          placeholder="请输入标题"
        />
      </a-form-item>
      <a-form-item label="题目难度" required>
        <a-select
          v-model="ojQuestionDetail.difficulty"
          placeholder="请输入难度"
        >
          <a-option label="简单" :value="0" />
          <a-option label="中等" :value="1" />
          <a-option label="困难" :value="2" />
        </a-select>
      </a-form-item>
      <a-form-item label="题目标签" required>
        <a-input-tag v-model="ojQuestionDetail.tags" placeholder="请输入标签" />
      </a-form-item>
      <a-form-item label="时间限制" required>
        <a-input
          v-model="ojQuestionDetail.timeLimit"
          :style="{ width: '250px' }"
          placeholder="请输入时间限制"
        />
      </a-form-item>
      <a-form-item label="内存限制" required>
        <a-input
          v-model="ojQuestionDetail.memoryLimit"
          :style="{ width: '250px' }"
          placeholder="请输入内存限制"
        />
      </a-form-item>
      <a-form-item label="题目内容" required>
        <WangEditor
          ref="wangEditorRef"
          @updateValueHtml="updateValueHtml"
          :init-value-html="ojQuestionDetail.content"
        />
      </a-form-item>
      <!--      <a-form-item label="答案代码">-->
      <!--        <a-button-group>-->
      <!--          <a-button> java </a-button>-->
      <!--        </a-button-group>-->
      <!--      </a-form-item>-->
      <a-form-item label="题目提示">
        <a-collapse style="width: 100%" :default-active-key="[1]">
          <a-collapse-item header="Tip1" key="1">
            <a-input v-model="tips[0]" placeholder="请输入" />
          </a-collapse-item>
          <a-collapse-item header="Tip2" key="2">
            <a-input v-model="tips[1]" placeholder="请输入" />
          </a-collapse-item>
          <a-collapse-item header="Tip3" key="3">
            <a-input v-model="tips[2]" placeholder="请输入" />
          </a-collapse-item>
        </a-collapse>
      </a-form-item>
      <a-form-item label="题目用例" v-if="id !== ''">
        <a-button type="primary" @click="showOjJudgeCase">点击</a-button>
      </a-form-item>
    </a-form>
    <div class="btn">
      <a-space>
        <a-button @click="deleteOjQuestion" status="danger" style="width: 200px"
          >删除题目</a-button
        >
        <a-button @click="addOjQuestion" type="primary" style="width: 200px">{{
          id === "" ? "添加题目" : "修改题目"
        }}</a-button>
      </a-space>
    </div>
    <OjJudgeCaseModal ref="ojJudgeCaseModalRef" :id="id" />
  </div>
</template>

<style scoped>
#ojQuestionAddPage {
}
.btn {
  margin-bottom: 10px;
  text-align: center;
}
</style>
