<script setup lang="ts">
import { defineExpose, ref } from "vue";
import API from "@/api";
import * as echarts from "echarts";

const chartData = ref<API.ChartVO>({});
const visible = ref(false);

const openModal = (data: API.ChartVO) => {
  chartData.value = data;
  visible.value = true;
  initChart();
};

const closeModal = () => {
  visible.value = false;
};

defineExpose({
  openModal,
});

const initChart = () => {
  if (!chartData.value.genData) {
    return;
  }
  var myChart = echarts.init(document.getElementById("detailChart"));
  myChart.setOption(JSON.parse(chartData.value.genData.replace(/'/g, '"')));
};
</script>
<template>
  <div id="chartDetailModal">
    <a-modal
      v-model:visible="visible"
      @cancel="closeModal"
      :footer="false"
      fullscreen
      :title="chartData.chartName"
    >
      <a-descriptions size="large" :column="1">
        <a-descriptions-item label="图表类型">{{
          chartData.chartType
        }}</a-descriptions-item>
        <a-descriptions-item label="图表目标">{{
          chartData.chartGoal
        }}</a-descriptions-item>
        <a-descriptions-item label="图表结果">
          <a-typography-title
            :heading="6"
            copyable
            v-if="chartData.genResult"
            >{{ chartData.genResult }}</a-typography-title
          >
        </a-descriptions-item>
        <a-descriptions-item label="生成图表">
          <div id="detailChart" />
        </a-descriptions-item>
      </a-descriptions>
    </a-modal>
  </div>
</template>
<style scoped>
#chartDetailModal {
  width: 100%;
  height: 100%;
  margin: 0 auto;
}
#detailChart {
  width: 600px;
  height: 500px;
}
</style>
