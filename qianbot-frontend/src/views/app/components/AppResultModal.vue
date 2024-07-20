<script setup lang="ts">
import { defineExpose, defineProps, ref, withDefaults } from "vue";
import message from "@arco-design/web-vue/es/message";
import { IconPlus, IconEdit, IconDelete } from "@arco-design/web-vue/es/icon";
import API from "@/api";
import {
  deleteAppResultUsingPost,
  getAppResultByAppIdUsingGet,
} from "@/api/appResultController";
import { dayjs } from "@arco-design/web-vue/es/_utils/date";
import AddAppResultModal from "@/views/app/components/AddAppResultModal.vue";

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
  visible.value = true;
};

const closeModal = () => {
  visible.value = false;
};
defineExpose({
  openModal,
});
const tableColumns = [
  {
    title: "结果名称",
    dataIndex: "resultName",
    align: "center",
  },
  {
    title: "结果描述",
    dataIndex: "resultDesc",
    align: "center",
  },
  {
    title: "结果图片",
    slotName: "resultPic",
    align: "center",
  },
  {
    title: "结果",
    slotName: "result",
    align: "center",
  },
  {
    title: "创建时间",
    slotName: "createTime",
    align: "center",
  },
  {
    title: "更新时间",
    slotName: "updateTime",
    align: "center",
  },
  {
    title: "操作",
    slotName: "optional",
    align: "center",
  },
];
const tableData = ref();
/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.appId) {
    return;
  }
  const res = await getAppResultByAppIdUsingGet({
    appId: props.appId as any,
  });
  if (res.data.code === 200 && res.data.data) {
    tableData.value = res.data.data;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};
const addAppResultModalRef = ref();
const openAddAppResultModal = (id: any) => {
  addAppResultModalRef.value.openModal(id);
};
const handleDelete = async (id: any) => {
  const res = await deleteAppResultUsingPost({
    id,
  });
  if (res.data.code === 200 && res.data.data) {
    message.success("删除成功");
  } else {
    message.error("删除失败，" + res.data.message);
  }
  loadData();
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
      <a-button type="primary" @click="openAddAppResultModal('')">
        添加结果
        <template #icon> <icon-plus /> </template
      ></a-button>
      <a-table
        :columns="tableColumns"
        :data="tableData"
        :pagination="false"
        :stripe="true"
      >
        <template #result="{ record }">
          {{
            record.resultScore === null ? record.resultProp : record.resultScore
          }}
        </template>
        <template #resultPic="{ record }">
          <template v-if="record.resultPic">
            <a-image
              width="100"
              :src="record.resultPic"
              :alt="record.resultName"
            />
          </template>
        </template>
        <template #createTime="{ record }">
          {{ dayjs(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
        </template>
        <template #updateTime="{ record }">
          {{ dayjs(record.updateTime).format("YYYY-MM-DD HH:mm:ss") }}
        </template>
        <template #optional="{ record }">
          <a-button type="primary" @click="openAddAppResultModal(record.id)">
            <template #icon> <icon-edit /> </template>
          </a-button>
          <a-button status="danger" @click="handleDelete(record.id)">
            <template #icon> <icon-delete /> </template>
          </a-button>
        </template>
      </a-table>
      <AddAppResultModal
        ref="addAppResultModalRef"
        :app-id="props.appId"
        :app-type="props.appVO.appType as number"
        @updateData="loadData"
      />
    </a-modal>
  </div>
</template>
<style scoped>
#addAppQuestionPage {
  width: 100%;
  margin: 0 auto;
}
</style>
