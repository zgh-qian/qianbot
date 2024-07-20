<script setup lang="ts">
import { defineExpose, defineProps, ref, withDefaults } from "vue";
import message from "@arco-design/web-vue/es/message";
import {
  addAndUpdateAppQuestionAndOptionUsingPost,
  getAppQuestionAndOptionUsingGet,
} from "@/api/appController";
import { IconPlus } from "@arco-design/web-vue/es/icon";
import API from "@/api";
import AIGenerateQuestionDrawer from "@/views/app/components/AIGenerateQuestionDrawer.vue";
import { getUserRemainingCountUsingPost } from "@/api/userUsageController";

/**
 * 定义组件属性类型
 */
interface Props {
  appId: string;
  appVO: API.AppVO;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  appId: () => "",
  appVO: () => null,
});

const visible = ref(false);

const openModal = () => {
  loadData();
  getRemainingCount();
  visible.value = true;
  handleTabClick(1);
};

const closeModal = () => {
  tabsData.value = [];
  visible.value = false;
};

defineExpose({
  openModal,
});

const tabActiveKey = ref(1);
const questionOptionData = ref<API.AppQuestionOptionUpdateDTO[]>([]);
const tabsData = ref([]);

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
  if (res.data.code === 200 && res.data.data) {
    questionOptionData.value = res.data
      .data as API.AppQuestionOptionUpdateDTO[];
    tabsData.value = [];
    if (questionOptionData.value.length <= 0) {
      handleTabsAdd();
      return;
    }
    questionOptionData.value?.forEach((item) => {
      tabsData.value = tabsData.value.concat({
        key: `${tabsData.value.length + 1}`,
        title: `Question ${tabsData.value.length + 1}`,
        data: item,
      } as any);
    });
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

const handleTabClick = (key: any) => {
  tabActiveKey.value = key as number;
};

const handleTabsAdd = () => {
  tabsData.value = tabsData.value.concat({
    key: `${tabsData.value.length + 1}`,
    title: `Question ${tabsData.value.length + 1}`,
    data: {
      question: {
        questionName: "",
        questionPic: "",
      },
      optionList: [
        {
          optionKey: "",
          optionName: "",
          optionResult: "",
        },
      ],
    },
  });
  tabActiveKey.value += 1;
};

const handleTabsDelete = (key: any) => {
  tabActiveKey.value -= 1;
  tabsData.value = tabsData.value.filter((item) => item.key !== key);
  tabsData.value.forEach((item, index) => {
    item.key = index + 1;
    item.title = `Question ${index + 1}`;
  });
};

const handleOptionAdd = (item: API.AppQuestionOptionUpdateDTO) => {
  item.optionList.push({
    optionKey: "",
    optionName: "",
    optionResult: "",
  });
};

const handleOptionDelete = (
  item: API.AppQuestionOptionUpdateDTO,
  index: number
) => {
  item.optionList?.splice(index);
};
/**
 * 提交问题和选项
 */
const submitQuestionOptionData = async () => {
  let data = [] as API.AppQuestionOptionUpdateDTO[];
  tabsData.value.forEach((item) => {
    if (
      item.data.question.questionName === "" ||
      item.data.optionList.length === 0
    ) {
      return;
    }
    data.push(item.data);
  });
  const res = await addAndUpdateAppQuestionAndOptionUsingPost({
    appId: props.appId,
    appQuestionAndOptionList: data,
  });
  if (res.data.code === 200) {
    message.success("提交成功");
    closeModal();
  } else {
    message.error("提交失败，" + res.data.message);
  }
};
// AI 生成题目成功后执行
const onSuccess = (result: API.AppAIGenerateVO[]) => {
  result.forEach((item) => {
    tabsData.value = tabsData.value.concat({
      key: `${tabsData.value.length + 1}`,
      title: `Question ${tabsData.value.length + 1}`,
      data: {
        question: {
          questionName: item.questionName,
          questionPic: "",
        },
        optionList: item.optionList,
      },
    });
  });
  message.success(`AI 生成题目成功，生成 ${result.length} 道题目`);
};

const onSSESuccess = (result: API.AppQuestionOptionUpdateDTO) => {
  tabsData.value = tabsData.value.concat({
    key: `${tabsData.value.length + 1}`,
    title: `Question ${tabsData.value.length + 1}`,
    data: {
      question: {
        questionName: result.questionName,
        questionPic: "",
      },
      optionList: result.optionList,
    },
  });
};

const onSSEStart = () => {
  message.success(`AI 开始生成题目`);
};

const onSSEClose = () => {
  message.success(`AI 生成题目结束`);
};

const remainingCount = ref<number>(0);
/**
 * 获取剩余调用次数
 */
const getRemainingCount = async () => {
  const res = await getUserRemainingCountUsingPost({
    usageType: "app_ai",
  });
  if (res.data.code === 200) {
    remainingCount.value = res.data.data as number;
  } else {
    message.error("获取调用次数失败，" + res.data.message);
  }
};
</script>
<template>
  <div id="addAppQuestionPage">
    <a-modal
      v-model:visible="visible"
      @cancel="closeModal"
      :footer="false"
      fullscreen
    >
      <template #title> {{ appVO.appName }} </template>
      <a-tabs
        v-if="tabsData.length > 0"
        type="card"
        :editable="true"
        show-add-button
        class="tabs"
        :auto-switch="true"
        default-active-key="1"
        :active-key="tabActiveKey"
        @tabClick="handleTabClick"
        @add="handleTabsAdd"
        @delete="handleTabsDelete"
      >
        <a-tab-pane
          v-for="item of tabsData"
          :key="item.key"
          :title="item.title"
        >
          <a-form :model="item" class="tabsForm">
            <a-form-item label="题目标题" v-if="item.data.question">
              <a-input
                v-model="item.data.question.questionName"
                placeholder="请输入题目标题"
              />
            </a-form-item>
            <a-row
              :gutter="30"
              v-for="(optionItem, optionIndex) of item.data.optionList"
              :key="optionIndex"
            >
              <a-col :span="4"></a-col>
              <a-col :span="6">
                <a-form-item label="选项Key" label-col-flex="60px">
                  <a-input
                    v-model="optionItem.optionKey"
                    placeholder="请输入选项Key"
                  />
                </a-form-item>
              </a-col>
              <a-col :span="6">
                <a-form-item label="选项内容" label-col-flex="60px">
                  <a-input
                    v-model="optionItem.optionName"
                    placeholder="请输入选项内容"
                  />
                </a-form-item>
              </a-col>
              <a-col :span="6">
                <a-form-item label="选项结果" label-col-flex="60px">
                  <a-input
                    v-model="optionItem.optionResult"
                    placeholder="请输入选项结果"
                  />
                </a-form-item>
              </a-col>
              <a-col :span="1">
                <a-button
                  status="danger"
                  @click="handleOptionDelete(item.data, optionIndex)"
                  >-</a-button
                >
              </a-col>
            </a-row>
            <a-form-item field="questionName" label="添加选项">
              <a-button type="primary" @click="handleOptionAdd(item.data)">
                <template #icon> <icon-plus /> </template
              ></a-button>
            </a-form-item>
          </a-form>
        </a-tab-pane>
      </a-tabs>
      <a-space class="btn">
        <!-- AI 生成抽屉 -->
        <AIGenerateQuestionDrawer
          :appId="appId"
          :remaining-count="remainingCount"
          :onSuccess="onSuccess"
          :onSSESuccess="onSSESuccess"
          :onSSEStart="onSSEStart"
          :onSSEClose="onSSEClose"
          @updateRemainCount="getRemainingCount"
        />
        <a-button type="primary" @click="submitQuestionOptionData">
          提交
        </a-button>
        <a-button status="danger" @click="closeModal"> 取消 </a-button>
      </a-space>
    </a-modal>
  </div>
</template>
<style scoped>
#addAppQuestionPage {
  width: 100%;
  margin: 0 auto;
}
.tabs {
  width: 100%;
}
.tabsForm {
  width: 80%;
}
.btn {
  justify-content: center;
  width: 100%;
  margin-top: 20px;
}
</style>
