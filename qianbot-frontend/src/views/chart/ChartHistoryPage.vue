<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import message from "@arco-design/web-vue/es/message";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import {
  deleteChartUsingPost,
  getChartVoPageUsingPost,
} from "@/api/chartController";
import ChartDetailModal from "@/views/chart/components/ChartDetailModal.vue";
import { CHART_STATUS_MAP, getChartColor } from "@/constant/chart";

const columns = [
  {
    title: "分析名称",
    slotName: "chartName",
    align: "center",
    width: 250,
  },
  {
    title: "图表类型",
    dataIndex: "chartType",
    align: "center",
    width: 200,
  },
  {
    title: "图表状态",
    slotName: "chartStatus",
    align: "center",
    width: 100,
  },
  {
    title: "分析时间",
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
const searchParams = ref<API.ChartQueryRequest>({
  ...initSearchParams,
});
const total = ref<number>(0);
const historyData = ref<API.ChartVO[]>();

const loadData = async () => {
  const res = await getChartVoPageUsingPost({
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
  const res = await deleteChartUsingPost({
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
const chartDetailModal = ref();
const handleDetail = (data: API.ChartVO) => {
  chartDetailModal.value.openModal(data);
};
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
      <template #chartName="{ record }">
        <a-link @click="handleDetail(record)">
          {{ record.chartName }}
        </a-link>
      </template>
      <template #createTime="{ record }">
        {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
      </template>
      <template #chartStatus="{ record }">
        <a-tag :color="getChartColor(record.chartStatus)">{{
          CHART_STATUS_MAP[record.chartStatus]
        }}</a-tag>
      </template>
      <template #optional="{ record }">
        <a-button type="primary" @click="handleDetail(record)">查看</a-button>
        <a-button status="danger" @click="handleDelete(record.id)"
          >删除</a-button
        >
      </template>
    </a-table>
    <ChartDetailModal ref="chartDetailModal" />
  </div>
</template>

<style scoped></style>
