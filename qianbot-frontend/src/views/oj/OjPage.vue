<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { getOjQuestionVoListUsingPost } from "@/api/ojQuestionController";
import {
  getOjDifficultyColor,
  getOjTagColor,
  OJ_DIFFICULTY_MAP,
} from "@/constant/oj";

const columns = [
  {
    title: "题目",
    slotName: "title",
    align: "center",
    width: 250,
  },
  {
    title: "标签",
    slotName: "tags",
    align: "center",
    width: 300,
  },
  {
    title: "难度",
    slotName: "difficulty",
    align: "center",
    width: 100,
  },
];
// 初始化搜索条件
const initSearchParams = {
  current: 1,
  pageSize: 10,
};
const searchParams = ref<API.OjQuestionQueryRequest>({
  ...initSearchParams,
});
const total = ref<number>(0);
const ojData = ref<API.ChartVO[]>();

const loadData = async () => {
  const res = await getOjQuestionVoListUsingPost({
    ...searchParams.value,
  });
  if (res.data.code === 200) {
    ojData.value = res.data.data?.records as any;
    total.value = res.data.data?.total as any;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};
const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};
const onPageSizeChange = (pageSize: number) => {
  searchParams.value = {
    ...searchParams.value,
    pageSize,
  };
};
const doSearch = (value: string) => {
  searchParams.value.title = value;
  loadData();
};
watchEffect(() => {
  loadData();
});
</script>

<template>
  <div id="ojPage">
    <a-form :model="searchParams" layout="inline" class="searchBar">
      <a-form-item label="题目">
        <a-input-search
          v-model="searchParams.title"
          :style="{ width: '300px' }"
          placeholder="搜索题目"
          button-text="搜索"
          size="large"
          search-button
          @search="doSearch"
        />
      </a-form-item>
      <a-form-item label="标签">
        <a-input-tag
          v-model="searchParams.tags"
          :style="{ width: '320px' }"
          placeholder="请输入标签"
          :max-tag-count="5"
          allow-clear
        />
      </a-form-item>
      <a-form-item label="难度">
        <a-select
          v-model="searchParams.difficulty"
          :style="{ width: '150px' }"
          placeholder="请选择难度"
        >
          <a-option value="">-</a-option>
          <a-option value="0">简单</a-option>
          <a-option value="1">中等</a-option>
          <a-option value="2">困难</a-option>
        </a-select>
      </a-form-item>
    </a-form>
    <a-table
      :columns="columns as any"
      :data="ojData"
      :pagination="{
        showTotal: true,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
        showPageSize: true,
      }"
      @page-change="onPageChange"
      @page-size-change="onPageSizeChange"
    >
      <template #title="{ record }">
        <a-link :href="`/oj/${record.id}`" :hoverable="false">{{
          record.title
        }}</a-link>
      </template>
      <template #tags="{ record }">
        <a-space>
          <a-tag
            :color="getOjTagColor(tag)"
            v-for="tag in record.tags"
            :key="tag"
            >{{ tag }}</a-tag
          >
        </a-space>
      </template>
      <template #difficulty="{ record }">
        <a-tag bordered :color="getOjDifficultyColor(record.difficulty)">{{
          OJ_DIFFICULTY_MAP[record.difficulty]
        }}</a-tag>
      </template>
    </a-table>
  </div>
</template>

<style scoped>
.searchBar {
  margin-bottom: 28px;
}
</style>
