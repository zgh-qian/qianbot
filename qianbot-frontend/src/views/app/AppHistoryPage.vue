<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import {
  deleteAppAnswerUsingPost,
  getAppAnswerPageVoUsingPost,
} from "@/api/appAnswerController";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";

const columns = [
  {
    title: "应用名称",
    slotName: "appName",
    align: "center",
    width: 350,
  },
  {
    title: "结果名称",
    slotName: "resultName",
    align: "center",
    width: 350,
  },
  {
    title: "答题时间",
    slotName: "createTime",
    align: "center",
    width: 200,
  },
  {
    title: "操作",
    slotName: "optional",
    align: "center",
    width: 200,
  },
];
// 初始化搜索条件
const initSearchParams = {
  current: 1,
  pageSize: 10,
};
const searchParams = ref<API.AppanswerQueryRequest>({
  ...initSearchParams,
});
const total = ref<number>(0);
const historyData = ref<API.AppanswerVO[]>();
const loadData = async () => {
  const res = await getAppAnswerPageVoUsingPost({
    ...searchParams.value,
  });
  if (res.data.code === 200) {
    historyData.value = res.data.data?.records as any;
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
const handleDelete = async (id: string) => {
  const res = await deleteAppAnswerUsingPost({
    id: id as any,
  });
  if (res.data.code === 200) {
    loadData();
    message.success("删除成功");
  } else {
    message.error("删除失败，" + res.data.message);
  }
};
watchEffect(() => {
  loadData();
});
</script>

<template>
  <div id="appHistory">
    <a-table
      :columns="columns"
      :data="historyData"
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
      <template #appName="{ record }">
        <a-link :href="`/app/detail/${record.appId}`">
          {{ record.appName }}
        </a-link>
      </template>
      <template #resultName="{ record }">
        <a-link :href="`/app/result/${record.id}`" :hoverable="false">
          {{ record.resultName }}
        </a-link>
      </template>
      <template #createTime="{ record }">
        {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
      </template>
      <template #optional="{ record }">
        <a-button type="primary" :href="`/app/result/${record.id}`"
          >查看</a-button
        >
        <a-button status="danger" @click="handleDelete(record.id)"
          >删除</a-button
        >
      </template>
    </a-table>
  </div>
</template>

<style scoped></style>
