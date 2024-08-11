<script setup lang="ts">
import { defineExpose, defineProps, ref, withDefaults } from "vue";
import message from "@arco-design/web-vue/es/message";
import { IconPlus, IconEdit, IconDelete } from "@arco-design/web-vue/es/icon";
import API from "@/api";
import {
  deleteOjJudgecaseUsingPost,
  getOjJudgecaseListUsingPost,
} from "@/api/ojJudgecaseController";
import AddOjJudgeCaseModal from "@/views/oj/components/AddOjJudgeCaseModal.vue";

/**
 * 定义组件属性类型
 */
interface Props {
  id: string;
}

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  id: () => "",
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
    title: "输入",
    dataIndex: "input",
    align: "center",
  },
  {
    title: "输出",
    dataIndex: "output",
    align: "center",
  },
  {
    title: "操作",
    slotName: "optional",
    align: "center",
  },
];

const tableData = ref<API.OjJudgecaseVO[]>();
// 初始化搜索条件
const initSearchParams = {
  current: 1,
  pageSize: 10,
};
const searchParams = ref<API.OjJudgecaseQueryRequest>({
  ...initSearchParams,
});
const total = ref<number>(0);

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
  loadData();
};
/**
 * 加载数据
 */
const loadData = async () => {
  if (!props.id) {
    return;
  }
  const res = await getOjJudgecaseListUsingPost({
    ...searchParams.value,
    questionId: props.id as any,
  });
  if (res.data.code === 200 && res.data.data) {
    tableData.value = (res.data.data.records as any) || [];
    total.value = res.data.data.total || 0;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};

const addOjJudgeCaseModalRef = ref();
const openAddOjJudgeCaseModal = (id: any) => {
  addOjJudgeCaseModalRef.value.openModal(id);
};

const handleDelete = async (id: any) => {
  const res = await deleteOjJudgecaseUsingPost({
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
  <div id="ojJudgeCaseModal">
    <a-modal
      v-model:visible="visible"
      @cancel="closeModal"
      :footer="false"
      fullscreen
    >
      <a-button type="primary" @click="openAddOjJudgeCaseModal('')">
        添加用例
        <template #icon> <icon-plus /> </template
      ></a-button>
      <a-table
        :columns="tableColumns as any"
        :data="tableData"
        :pagination="{
          showTotal: true,
          pageSize: searchParams.pageSize,
          current: searchParams.current,
          total,
        }"
        @page-change="onPageChange"
      >
        <template #optional="{ record }">
          <a-button type="primary" @click="openAddOjJudgeCaseModal(record.id)">
            <template #icon> <icon-edit /> </template>
          </a-button>
          <a-button status="danger" @click="handleDelete(record.id)">
            <template #icon> <icon-delete /> </template>
          </a-button>
        </template>
      </a-table>
      <AddOjJudgeCaseModal
        ref="addOjJudgeCaseModalRef"
        :question-id="id"
        @updateData="loadData"
      />
    </a-modal>
  </div>
</template>
<style scoped>
#ojJudgeCaseModal {
  width: 100%;
  margin: 0 auto;
}
</style>
