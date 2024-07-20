<template>
  <div class="share-modal">
    <a-modal v-model:visible="visible" @cancel="closeModal" :footer="false">
      <template #title>
        {{ isAdd ? "添加应用" : "修改应用" }}
      </template>
      <a-form
        :model="form"
        :style="{ width: '480px' }"
        label-align="left"
        auto-label-width
        @submit="handleSubmit"
      >
        <a-form-item field="appName" label="应用名称">
          <a-input v-model="form.appName" placeholder="请输入应用名称" />
        </a-form-item>
        <a-form-item field="appDesc" label="应用描述">
          <a-textarea
            auto-size
            v-model="form.appDesc"
            placeholder="请输入应用描述"
          />
        </a-form-item>
        <a-form-item field="appIcon" label="应用图标">
          <PictureUploader
            :biz="FILE_BIZ.APP_ICON"
            :value="form.appIcon"
            :onChange="(value) => (form.appIcon = value)"
          />
        </a-form-item>
        <a-form-item field="appType" label="应用类型">
          <a-select
            v-model="form.appType"
            :style="{ width: '320px' }"
            placeholder="请选择应用类型"
          >
            <a-option
              v-for="(value, key) of APP_TYPE_MAP"
              :value="Number(key)"
              :key="key"
              :label="value"
            />
          </a-select>
        </a-form-item>
        <a-form-item field="scoringStrategy" label="评分策略">
          <a-select
            v-model="form.scoringStrategy"
            :style="{ width: '320px' }"
            placeholder="请选择评分策略"
          >
            <a-option
              v-for="(value, key) of APP_SCORING_STRATEGY_MAP"
              :value="Number(key)"
              :key="key"
              :label="value"
            />
          </a-select>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit" style="width: 120px">
            {{ isAdd ? "创建" : "修改" }}
          </a-button>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { defineExpose, withDefaults, defineProps, ref, defineEmits } from "vue";
import message from "@arco-design/web-vue/es/message";
import { APP_SCORING_STRATEGY_MAP, APP_TYPE_MAP } from "@/constant/app";
import { useRouter } from "vue-router";
import {
  addAppUsingPost,
  editAppUsingPost,
  getAppVoByIdUsingGet,
} from "@/api/appController";
import API from "@/api";
import PictureUploader from "@/components/PictureUploader.vue";
import { FILE_BIZ } from "@/constant/file";

/**
 * 定义组件属性类型
 */
interface Props {
  id: string;
  isAdd: boolean;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  id: () => "",
  isAdd: () => false,
});

const visible = ref(false);

const openModal = () => {
  loadData();
  visible.value = true;
};

const closeModal = () => {
  visible.value = false;
};
defineExpose({
  openModal,
});

const router = useRouter();

const form = ref({
  appDesc: "",
  appIcon: "",
  appName: "",
  appType: 0,
  scoringStrategy: 0,
} as API.AppAddRequest);

const oldApp = ref<API.AppVO>();

/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getAppVoByIdUsingGet({
    id: props.id as any,
  });
  if (res.data.code === 200 && res.data.data) {
    oldApp.value = res.data.data;
    form.value = res.data.data;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};
const emit = defineEmits(["updateData"]);
/**
 * 提交
 */
const handleSubmit = async () => {
  // 如果是修改
  if (props.id) {
    const res = await editAppUsingPost({
      id: props.id as any,
      ...form.value,
    });
    if (res.data.code === 200) {
      emit("updateData");
      closeModal();
      message.success("修改成功");
    } else {
      message.error("修改失败，" + res.data.message);
    }
  } else {
    // 创建
    const res = await addAppUsingPost(form.value);
    if (res.data.code === 200) {
      message.success("操作成功，即将跳转到应用详情页");
      setTimeout(() => {
        router.push(`/app/detail/${props.id || res.data.data}`);
      }, 3000);
    } else {
      message.error("操作失败，" + res.data.message);
    }
  }
};
</script>
