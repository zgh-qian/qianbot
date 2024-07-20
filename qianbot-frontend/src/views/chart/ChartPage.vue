<script setup lang="ts">
import { computed, ref, watch, watchEffect } from "vue";
import {
  CHART_STATUS_ENUM,
  CHART_TYPE_BAR_ENUM,
  CHART_TYPE_LINE_ENUM,
  CHART_TYPE_PIE_ENUM,
  CHART_TYPE_SCATTER_ENUM,
} from "@/constant/chart";
import API from "@/api";
import {
  addChartByFileUsingPost,
  getChartVoByIdUsingGet,
} from "@/api/chartController";
import message from "@arco-design/web-vue/es/message";
import * as echarts from "echarts";
import { IconLeft, IconRight } from "@arco-design/web-vue/es/icon";
import {
  getDefaultRemainingCountUsingPost,
  getUserRemainingCountUsingPost,
} from "@/api/userUsageController";
import { useLoginUserStore } from "@/store/userStore";

const form = ref<API.ChartAddRequest>({
  chartName: "",
  chartGoal: "",
  chartType: "",
});
const fileData = ref();
// 0-未生成 1-生成中 2-生成成功 3-生成失败
const isGenChart = ref(0);
const chartId = ref();
const chartData = ref<API.ChartVO>({});
let intervalChart: any;

const current = ref(1);
const minStep = 1;
const maxStep = 5;
const isCanNext = ref([false, false, false, true, false]);

const onPrev = () => {
  current.value = Math.max(minStep, current.value - 1);
};

const onNext = () => {
  current.value = Math.min(maxStep, current.value + 1);
  if (current.value === 5) {
    chartData.value = {};
  }
};

const setCurrent = (cur: number) => {
  current.value = cur;
};

const fileInput = ref();

const handleInputClick = () => {
  fileInput.value.click();
};

const getFile = (event: any) => {
  fileData.value = event.target.files[0];
  isCanNext.value[current.value - 1] = true;
};

const handleChartTypeClick = (value: string) => {
  form.value.chartType = value;
  isCanNext.value[current.value - 1] = true;
};

watch([form.value], () => {
  if (form.value.chartName != "" && form.value.chartGoal != "") {
    isCanNext.value[current.value - 1] = true;
  }
});

const descData = ref([
  {
    label: "图表名称",
    value: computed(() => form.value.chartName),
  },
  {
    label: "图表目标",
    value: computed(() => form.value.chartGoal),
  },
  {
    label: "图表类型",
    value: computed(() => form.value.chartType),
  },
  {
    label: "上传文件",
    value: computed(() => fileData.value.name),
  },
]);

const handleSubmit = async () => {
  const res = await addChartByFileUsingPost(
    { ...form.value },
    {},
    fileData.value
  );
  if (res.data.code === 200) {
    getRemainingCount();
    chartId.value = res.data.data;
    isGenChart.value = 1;
    intervalChart = setInterval(() => {
      getChartData();
    }, 3000);
  } else {
    chartData.value = {};
    isGenChart.value = 3;
    message.error("生成图表失败，" + res.data.message);
  }
};

// 图表相关
const getChartData = async () => {
  if (!chartId.value) {
    return;
  }
  const res = await getChartVoByIdUsingGet({
    id: chartId.value as any,
  });
  if (res.data.code === 200) {
    if (res.data.data.chartStatus == CHART_STATUS_ENUM.SUCCESS) {
      clearInterval(intervalChart);
      chartData.value = res.data.data as any;
      chartChange();
    }
  } else {
    isGenChart.value = 3;
    message.error("获取数据失败，" + res.data.message);
  }
};

const chartChange = () => {
  var myChart = echarts.init(document.getElementById("mainChart"));
  // 绘制图表
  let validJsonString = chartData.value.genData.replace(/'/g, '"');
  myChart.setOption(JSON.parse(validJsonString));
  isGenChart.value = 2;
  message.success("生成图表成功");
};

const loginUserStore = useLoginUserStore();
const remainingCount = ref<number>(0);
/**
 * 获取剩余调用次数
 */
const getRemainingCount = async () => {
  if (!loginUserStore.loginUser.id) {
    const res = await getDefaultRemainingCountUsingPost({
      usageType: "chart_ai",
    });
    if (res.data.code === 200) {
      remainingCount.value = res.data.data as number;
    } else {
      message.error("获取调用次数失败，" + res.data.message);
    }
  } else {
    const res = await getUserRemainingCountUsingPost({
      usageType: "chart_ai",
    });
    if (res.data.code === 200) {
      remainingCount.value = res.data.data as number;
    } else {
      message.error("获取调用次数失败，" + res.data.message);
    }
  }
};

watchEffect(() => {
  getRemainingCount();
});
</script>

<template>
  <a-card id="chartPage">
    <template #title>
      <h1 style="text-align: center">智能分析图表</h1>
    </template>
    <template #extra> 剩余{{ remainingCount }}次机会 </template>
    <a-steps :changeable="false" :current="current" @change="setCurrent">
      <a-step>导入图表数据</a-step>
      <a-step>选择图表类型</a-step>
      <a-step>填写图表信息</a-step>
      <a-step>确认图表信息</a-step>
      <a-step>导出图表数据</a-step>
    </a-steps>
    <div class="stepsContent">
      <div class="stepContent" v-if="current === 1">
        <a-space direction="vertical" fill>
          <template v-if="fileData">
            <div>已上传文件：{{ fileData.name }}</div>
          </template>
          <a-button @click="handleInputClick" type="primary">
            点击上传
            <input
              type="file"
              style="display: none"
              ref="fileInput"
              @change="getFile($event)"
            />
          </a-button>
          <div>请上传数据文件，文件格式是：.xlsx/.xls</div>
        </a-space>
      </div>
      <div class="stepContent" v-if="current === 2">
        <a-space direction="vertical">
          <a-radio-group v-model="form.chartType" type="button" size="large">
            <a-radio
              v-for="(value, index) in CHART_TYPE_LINE_ENUM"
              :key="index"
              :value="value"
              @click="handleChartTypeClick(value)"
              >{{ value }}</a-radio
            >
          </a-radio-group>
          <a-radio-group v-model="form.chartType" type="button" size="large">
            <a-radio
              v-for="(value, index) in CHART_TYPE_BAR_ENUM"
              :key="index"
              :value="value"
              @click="handleChartTypeClick(value)"
              >{{ value }}</a-radio
            >
          </a-radio-group>
          <a-radio-group v-model="form.chartType" type="button" size="large">
            <a-radio
              v-for="(value, index) in CHART_TYPE_PIE_ENUM"
              :key="index"
              :value="value"
              @click="handleChartTypeClick(value)"
              >{{ value }}</a-radio
            >
          </a-radio-group>
          <a-radio-group v-model="form.chartType" type="button" size="large">
            <a-radio
              v-for="(value, index) in CHART_TYPE_SCATTER_ENUM"
              :key="index"
              :value="value"
              @click="handleChartTypeClick(value)"
              >{{ value }}</a-radio
            >
          </a-radio-group>
        </a-space>
      </div>
      <div class="stepContent" v-if="current === 3">
        <a-form :model="form" class="form" @submit="getChartData">
          <a-form-item label="图表名称">
            <a-input v-model="form.chartName" placeholder="请输入图表名称" />
          </a-form-item>
          <a-form-item label="图表目标">
            <a-textarea
              style="max-height: 200px"
              :max-length="{ length: 300, errorOnly: true }"
              v-model="form.chartGoal"
              placeholder="请输入图表目标"
              auto-size
              allow-clear
              show-word-limit
            />
          </a-form-item>
        </a-form>
      </div>
      <div class="stepContent" v-if="current === 4">
        <a-descriptions
          style="margin-top: 20px"
          :data="descData"
          size="large"
          title="图表数据"
          :column="1"
        />
      </div>
      <div class="stepContent" v-if="current === 5">
        <a-row style="height: 100%; margin-top: 30px">
          <a-col
            :span="24"
            v-if="isGenChart === 1"
            style="text-align: center; margin-top: 100px"
          >
            <a-spin :loading="isGenChart === 1" tip="生成中..." />
          </a-col>
          <a-col
            :span="24"
            v-if="isGenChart === 3"
            style="justify-content: center; align-items: center"
          >
            <a-result status="warning" title="生成失败"> </a-result>
          </a-col>
          <a-col :span="12">
            <div id="mainChart" style="width: 500px; height: 280px" />
          </a-col>
          <a-col :span="12" v-if="isGenChart === 2">
            <a-typography-paragraph copyable>{{
              chartData.genResult
            }}</a-typography-paragraph>
          </a-col>
        </a-row>
      </div>
      <a-space class="stepBtn" size="large">
        <a-button
          type="secondary"
          :disabled="current <= minStep"
          @click="onPrev"
        >
          <IconLeft /> 上一步
        </a-button>
        <a-button
          v-if="current === maxStep"
          type="outline"
          @click="handleSubmit"
          >生成图表</a-button
        >
        <a-button
          type="primary"
          :disabled="current >= maxStep || !isCanNext[current - 1]"
          @click="onNext"
        >
          下一步 <IconRight />
        </a-button>
      </a-space>
    </div>
  </a-card>
</template>

<style scoped>
#chartPage {
  justify-content: center;
  height: 450px;
  margin: 0 auto;
}
.stepsContent {
  width: 1000px;
  height: 300px;
}
.stepContent {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 300px;
  margin: 0 auto;
}
.stepBtn {
  display: flex;
  justify-content: center;
}
.arco-space-fill {
  align-items: center;
}
.arco-card-header-extra {
  color: rgb(255 22 48);
}
</style>
