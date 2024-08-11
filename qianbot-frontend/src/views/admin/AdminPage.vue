<script setup lang="ts">
import { ref, watchEffect } from "vue";
import {
  doAppReviewUsingPost,
  getAppDetailUsingGet,
  listAppByPageUsingPost,
} from "@/api/appController";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import {
  APP_SCORING_STRATEGY_MAP,
  APP_TYPE_MAP,
  REVIEW_STATUS_ENUM,
} from "@/constant/app";
import { IconCaretLeft, IconCaretRight } from "@arco-design/web-vue/es/icon";

// 初始化搜索条件
const initSearchParams = {
  current: 1,
  pageSize: 50,
};
const searchParams = ref<API.AppQueryRequest>({
  ...initSearchParams,
  reviewStatus: REVIEW_STATUS_ENUM.REVIEWING,
});
const doSearch = (value: string) => {
  searchParams.value.searchText = value;
  loadData();
};

let index = 1;
const data = ref<API.AppVO[]>([]);
const cardData = ref<API.AppDetailVO>({});
const reviewData = ref<API.ReviewDTO>({
  reviewMessage: "",
});

const loadData = async () => {
  const res = await listAppByPageUsingPost({
    ...searchParams.value,
  });
  if (res.data.code === 200 && res.data.data?.records) {
    data.value = [];
    let first = true;
    res.data.data.records.forEach((item) => {
      if (first) {
        index = 0;
        handleClickItem(item, index);
        first = false;
      }
      data.value.push(item);
    });
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

const fetchData = () => {
  console.log("fetchData");
};

const handleClickItem = async (item: any, idx: number) => {
  const res = await getAppDetailUsingGet({ appId: item.id });
  if (res.data.code === 200) {
    index = idx;
    cardData.value = res.data.data as any;
  } else {
    message.error("查询数据失败，" + res.data.message);
  }
};

const handlePre = async () => {
  if (index > 0) {
    const preIndex = index - 1;
    handleClickItem(data.value[preIndex], preIndex);
  }
};

const handleNext = async () => {
  if (index < data.value.length - 1) {
    const nextIndex = index + 1;
    handleClickItem(data.value[nextIndex], nextIndex);
  }
};

const handleReviewPass = async (id: any) => {
  const res = await doAppReviewUsingPost({
    ...reviewData.value,
    id,
    reviewStatus: REVIEW_STATUS_ENUM.PASS,
  });
  if (res.data.code === 200) {
    loadData();
    message.success("审核通过成功");
  } else {
    message.error("审核通过失败，" + res.data.message);
  }
};

watchEffect(() => {
  loadData();
});
</script>

<template>
  <div id="adminAppPage">
    <div class="searchBar">
      <a-input-search
        :style="{ width: '500px' }"
        placeholder="搜索应用"
        button-text="搜索"
        size="large"
        search-button
        @search="doSearch"
      />
      <a-button type="primary" href="/oj/question/add">添加题目</a-button>
    </div>
    <a-row>
      <a-col :span="5" class="sider">
        <a-list :max-height="500" @reach-bottom="fetchData" scrollbar>
          <template #header> 应用列表 </template>
          <a-list-item
            v-for="(item, index) of data"
            :key="index"
            @click="handleClickItem(item, index)"
          >
            {{ item.appName }}
          </a-list-item>
        </a-list>
      </a-col>
      <a-col :span="12" class="content">
        <a-card class="card" :title="cardData.appVO?.appName">
          <template #extra>
            <a-space>
              {{ APP_TYPE_MAP[cardData.appVO?.appType] }}
              {{ APP_SCORING_STRATEGY_MAP[cardData.appVO?.scoringStrategy] }}
            </a-space>
          </template>
          {{ cardData.appVO?.appDesc }}
          <a-list
            v-if="cardData.questionAndOptionVOList"
            :virtualListProps="{
              height: 300,
            }"
            :data="cardData.questionAndOptionVOList"
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
                  {{ option.optionKey }}.{{ option.optionName }}
                </div>
              </a-list-item>
            </template>
          </a-list>
        </a-card>
        <div class="btn">
          <a-button type="primary" @click="handlePre" :style="{ width: 100 }">
            <template #icon>
              <icon-caret-left />
            </template>
          </a-button>
          <a-button type="primary" @click="handleNext">
            <template #icon>
              <icon-caret-right />
            </template>
          </a-button>
        </div>
      </a-col>
      <a-col :span="5">
        <div class="reviewBtn">
          <a-button status="danger">审核不通过</a-button>
          <a-button type="primary" @click="handleReviewPass(cardData.appVO?.id)"
            >审核通过</a-button
          >
        </div>
        <a-textarea
          placeholder="输入审核信息"
          allow-clear
          v-model="reviewData.reviewMessage"
          :auto-size="true"
        />
      </a-col>
    </a-row>
  </div>
</template>

<style scoped>
#adminAppPage {
  height: 80vh;
}
.searchBar {
  margin-bottom: 10px;
  text-align: center;
}
#adminAppPage .content {
  box-sizing: border-box;
  max-width: 1200px;
  margin: 0 auto 28px;
  padding: 20px;
  background: linear-gradient(to right, #fefefe, #fff);
}
.card {
  height: 80%;
}
.btn {
  display: flex;
  justify-content: space-around;
  margin: 0 auto;
  text-align: center;
}
.reviewBtn {
  display: flex;
  justify-content: space-around;
  margin-bottom: 30px;
}
.arco-btn-size-medium.arco-btn-only-icon {
  width: 200px;
}
</style>
