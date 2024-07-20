<template>
  <div class="share-modal">
    <a-modal
      v-model:visible="visible"
      @cancel="closeModal"
      @ok="handleOk"
      width="auto"
    >
      <template #title>
        {{ id === "" || id === null ? "添加结果" : "修改结果" }}
      </template>
      <a-form :model="appResult" :style="{ width: '600px' }">
        <a-form-item label="结果名称">
          <a-input
            v-model="appResult.resultName"
            placeholder="请输入结果名称"
          />
        </a-form-item>
        <a-form-item label="结果描述">
          <a-input
            v-model="appResult.resultDesc"
            placeholder="请输入结果描述"
          />
        </a-form-item>
        <a-form-item label="结果图片">
          <PictureUploader
            :biz="FILE_BIZ.APP_RESULT"
            :value="appResult.resultPic"
            :onChange="(value) => (appResult.resultPic = value)"
          />
        </a-form-item>
        <template v-if="appType == 0">
          <a-form-item label="结果得分">
            <a-input-number
              v-model="appResult.resultScore"
              placeholder="请输入结果得分"
            />
          </a-form-item>
        </template>
        <template v-else>
          <a-form-item
            v-for="(prop, index) of resultProp"
            :label="`结果属性 ${index + 1}`"
            :key="index"
          >
            <a-input v-model="prop.value" placeholder="请输入结果属性" />
            <a-button @click="handleDelete(index)" status="danger"
              >Delete</a-button
            >
          </a-form-item>
        </template>
        <div v-if="appType == 1">
          <a-button @click="handleAdd" type="primary">增加结果属性</a-button>
        </div>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup lang="ts">
import { defineExpose, ref, withDefaults, defineProps, defineEmits } from "vue";
import message from "@arco-design/web-vue/es/message";
import API from "@/api";
import {
  addAppResultUsingPost,
  getAppResultByIdUsingGet,
  updateAppResultUsingPost,
} from "@/api/appResultController";
import { FILE_BIZ } from "@/constant/file";
import PictureUploader from "@/components/PictureUploader.vue";
/**
 * 定义组件属性类型
 */
interface Props {
  appId: string;
  appType: number;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  appId: () => "",
  appType: () => 0,
});

const visible = ref(false);

const id = ref("");

const openModal = (appResultId: any) => {
  id.value = appResultId;
  loadData();
  visible.value = true;
};

const closeModal = () => {
  appResult.value = {};
  resultProp.value = [{ value: "" }];
  visible.value = false;
};

defineExpose({
  openModal,
});

const oldAppResult = ref<API.AppresultVO>();
const appResult = ref<API.AppresultAddRequest>({
  resultProp: [],
});
const resultProp = ref([{ value: "" }]);

/**
 * 加载数据
 */
const loadData = async () => {
  if (!id.value || id.value === "") {
    return;
  }
  const res = await getAppResultByIdUsingGet({ id: id.value as any });
  if (res.data.code === 200 && res.data.data) {
    oldAppResult.value = res.data.data;
    appResult.value = res.data.data;
    if (
      appResult.value.resultProp != null &&
      appResult.value.resultProp.length > 0
    ) {
      resultProp.value = appResult.value.resultProp.map((item) => {
        return {
          value: item,
        };
      });
    }
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

const handleAdd = () => {
  resultProp.value.push({ value: "" });
};

const handleDelete = (index: number) => {
  resultProp.value.splice(index, 1);
};

const emit = defineEmits(["updateData"]);

const handleOk = async () => {
  appResult.value.appId = props.appId as any;
  if (resultProp.value.length > 0) {
    appResult.value.resultProp = resultProp.value.map((item) => item.value);
  }
  if (props.appType == 0) {
    // 如果是得分类
    appResult.value.resultProp = null as any;
  } else {
    // 如果是测评类
    appResult.value.resultScore = null as any;
  }
  // 如果是修改
  if (id.value) {
    const res = await updateAppResultUsingPost({
      id: id as any,
      ...appResult.value,
    });
    if (res.data.code === 200) {
      closeModal();
      message.success("修改成功");
    } else {
      message.error("修改失败，" + res.data.message);
    }
  } else {
    // 创建
    const res = await addAppResultUsingPost(appResult.value);
    if (res.data.code === 200) {
      message.success("添加成功");
      closeModal();
    } else {
      message.error("添加失败，" + res.data.message);
    }
  }
  emit("updateData");
};
</script>
