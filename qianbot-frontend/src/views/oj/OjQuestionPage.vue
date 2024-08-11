<script setup lang="ts">
import { ref, defineProps, watchEffect, withDefaults } from "vue";
import { Message } from "@arco-design/web-vue";
import CodeEditor from "@/components/CodeEditor.vue";
import { getOjQuestionDetailUsingGet } from "@/api/ojQuestionController";
import API from "@/api";
import {
  getOjCodeLanguageColor,
  getOjDifficultyColor,
  getOjStatusColor,
  getOjTagColor,
  OJ_DIFFICULTY_MAP,
} from "@/constant/oj";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import {
  addOjSubmitUsingPost,
  getOjSubmitVoListUsingPost,
  getOjSubmitVoUsingGet,
} from "@/api/ojSubmitController";
import { getCodeSandboxLanguageUsingGet } from "@/api/codeSandboxController";
import CodeSandboxLanguage = API.CodeSandboxLanguage;
import { useLoginUserStore } from "@/store/userStore";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => {
    return "";
  },
});

// 代码编辑器
const language = ref("java");
const code = ref("");
const theme = ref("vs-dark");
const onChange = (value: string) => {
  code.value = value;
};

const ojQuestionDetail = ref<API.OjQuestionDetailVO>({});
const languageList = ref<CodeSandboxLanguage[]>([]);

const loginUserStore = useLoginUserStore();
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
  } else {
    Message.error("获取数据失败，" + res.data.message);
  }
};

const getLanguageList = async () => {
  const res = await getCodeSandboxLanguageUsingGet();
  if (res.data.code === 200) {
    languageList.value = res.data.data as any;
  } else {
    Message.error("提交失败，" + res.data.message);
  }
};

const columns = [
  {
    title: "提交语言",
    slotName: "language",
    align: "center",
    width: 100,
  },
  {
    title: "提交状态",
    slotName: "status",
    align: "center",
    width: 100,
  },
  {
    title: "时间消耗",
    dataIndex: "timeUsed",
    align: "center",
    width: 100,
  },
  {
    title: "内存消耗",
    dataIndex: "memoryUsed",
    align: "center",
    width: 100,
  },
  {
    title: "提交时间",
    slotName: "createTime",
    align: "center",
    width: 100,
  },
];

// 初始化搜索条件
const initSearchParams = {
  current: 1,
  pageSize: 5,
};
const searchParams = ref<API.OjSubmitQueryRequest>({
  ...initSearchParams,
});
const total = ref<number>(0);
const submitHistoryData = ref([]);

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
  getSubmitHistoryData();
};

const handleTabClick = (key: number) => {
  if (key == 3) {
    getSubmitHistoryData();
  }
};

const getSubmitHistoryData = async () => {
  const res = await getOjSubmitVoListUsingPost({
    ...searchParams.value,
  });
  if (res.data.code === 200) {
    submitHistoryData.value = res.data.data?.records as any;
    total.value = res.data.data?.total as any;
  } else {
    Message.error("获取数据失败，" + res.data.message);
  }
};

const runBtn = async () => {
  console.log(code.value);
};

const submitLoading = ref(false);
const submitBtn = async () => {
  console.log(code.value);
  const res = await addOjSubmitUsingPost({
    questionId: props.id as any,
    code: code.value,
    language: language.value,
  });
  if (res.data.code === 200) {
    submitLoading.value = true;
    var timer = setInterval(async () => {
      const result = await getSubmitData(res.data.data);
      if (result) {
        clearInterval(timer);
        submitLoading.value = false;
      }
    }, 1500);
  } else {
    Message.error("提交失败，" + res.data.message);
  }
};
const submitData = ref<API.OjSubmitVO>({});
const getSubmitData = async (id: any) => {
  const res = await getOjSubmitVoUsingGet({
    id,
  });
  if (res.data.code === 200 && res.data.data.judgeStatus == 2) {
    submitData.value = res.data.data as any;
    return true;
  }
  return false;
};

watchEffect(() => {
  loadData();
  getLanguageList();
});
</script>

<template>
  <div id="ojQuestionPage">
    <a-row :gutter="24" v-if="ojQuestionDetail.id">
      <a-col :span="12">
        <a-tabs type="card" class="tab" @tabClick="handleTabClick">
          <a-tab-pane class="tabPane" key="1" title="题目描述">
            <a-scrollbar
              style="
                overflow: auto;
                margin-bottom: 10px;
                height: 50vh;
                padding: 10px;
              "
              v-if="ojQuestionDetail.content"
              class="scrollbar"
            >
              <a-space style="width: 100%; text-align: center">
                <h2>{{ ojQuestionDetail.title }}</h2>
                <a-tag
                  bordered
                  :color="getOjDifficultyColor(ojQuestionDetail.difficulty)"
                  >{{ OJ_DIFFICULTY_MAP[ojQuestionDetail.difficulty] }}</a-tag
                >
              </a-space>
              <div />
              <a-space class="tags">
                <a-tag
                  :color="getOjTagColor(tag)"
                  v-for="tag in ojQuestionDetail.tags"
                  :key="tag"
                  >{{ tag }}</a-tag
                >
              </a-space>
              <div v-html="ojQuestionDetail.content" class="content" />
              <a-descriptions :column="2" style="width: 100%">
                <a-descriptions-item label="时间限制">{{
                  ojQuestionDetail?.timeLimit
                }}</a-descriptions-item>
                <a-descriptions-item label="内存限制">{{
                  ojQuestionDetail?.memoryLimit
                }}</a-descriptions-item>
              </a-descriptions>
              <a-collapse accordion v-if="ojQuestionDetail.tips">
                <a-collapse-item
                  v-for="(tip, index) in ojQuestionDetail.tips"
                  :header="
                    ojQuestionDetail.tips.length > 1 ? `Tip${index + 1}` : 'Tip'
                  "
                  :key="index"
                  >{{ tip }}
                </a-collapse-item>
              </a-collapse>
            </a-scrollbar>
          </a-tab-pane>
          <a-tab-pane class="tabPane" key="2" title="题目答案">无</a-tab-pane>
          <a-tab-pane
            class="tabPane"
            key="3"
            title="提交记录"
            v-if="loginUserStore.loginUser.id"
          >
            <a-table
              :columns="columns as any"
              :data="submitHistoryData"
              :pagination="{
                showTotal: true,
                pageSize: searchParams.pageSize,
                current: searchParams.current,
                total,
              }"
              @page-change="onPageChange"
            >
              <template #language="{ record }">
                <a-tag :color="getOjCodeLanguageColor(record.language)">{{
                  record.language
                }}</a-tag>
              </template>
              <template #status="{ record }">
                <a-tag :color="getOjStatusColor(record.status)">{{
                  record.status
                }}</a-tag>
              </template>
              <template #createTime="{ record }">
                {{ dayjs(record.createTime).format("HH:mm:ss") }}
              </template>
            </a-table>
          </a-tab-pane>
        </a-tabs>
      </a-col>
      <a-col :span="12">
        <a-select
          v-model="language"
          style="width: 10vw; margin-bottom: 1vh"
          :bordered="false"
        >
          <a-option
            v-for="item in languageList"
            :label="item.text"
            :value="item.value"
            :key="item.value"
          />
        </a-select>
        <a-select
          v-model="theme"
          style="width: 10vw; margin-bottom: 1vh"
          :bordered="false"
        >
          <a-option label="vs" value="vs" />
          <a-option label="vs-dark" value="vs-dark" />
          <a-option label="hc-dark" value="hc-dark" />
        </a-select>
        <div>
          <CodeEditor
            style="height: 50vh"
            :language="language"
            :value="code"
            :theme="theme"
            :handle-change="onChange"
          />
        </div>
        <a-space class="btn">
          <a-button @click="runBtn" type="outline">运行</a-button>
          <a-button @click="submitBtn" type="primary" :loading="submitLoading"
            >提交</a-button
          >
        </a-space>
      </a-col>
    </a-row>
  </div>
</template>

<style scoped>
.tab {
  width: 38vw;
  height: 60vh;
  padding: 10px;
}
.tabPane {
  height: 60vh;
}
.tags {
  margin-bottom: 10px;
}
.scrollbar {
  height: 500px;
  overflow: auto;
}
.arco-space-align-center {
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}
.content {
  margin-bottom: 10px;
}
.btn {
  justify-content: flex-end;
  width: 100%;
  margin-top: 1vh;
}
</style>
