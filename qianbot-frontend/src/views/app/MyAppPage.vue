<template>
  <div id="myAppPage">
    <div class="searchBar">
      <a-input-search
        :style="{ width: '500px' }"
        placeholder="搜索应用"
        button-text="搜索"
        size="large"
        search-button
        @search="doSearch"
      />
    </div>
    <div class="searchBar">
      <a-button type="primary" @click="openAddAppModal">
        点击制作应用
        <template #icon>
          <icon-plus />
        </template>
      </a-button>
    </div>
    <a-list
      class="list-demo-action-layout"
      :grid-props="{ gutter: [20, 20], sm: 24, md: 12, lg: 8, xl: 6 }"
      :bordered="false"
      :data="dataList"
      :pagination-props="{
        showTotal: true,
        showPageSize: true,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
      }"
      @page-change="onPageChange"
      @page-size-change="onPageSizeChange"
    >
      <template #item="{ item }">
        <MyAppCard :app="item" />
      </template>
    </a-list>
    <AddAppModal ref="addAppModal" :is-add="true" />
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import { listMyAppVoByPageUsingPost } from "@/api/appController";
import message from "@arco-design/web-vue/es/message";
import { IconPlus } from "@arco-design/web-vue/es/icon";
import AddAppModal from "@/views/app/components/AddAppModal.vue";
import MyAppCard from "@/components/MyAppCard.vue";

// 初始化搜索条件
const initSearchParams = {
  current: 1,
  pageSize: 10,
};

const searchParams = ref<API.AppQueryRequest>({
  ...initSearchParams,
});
const dataList = ref<API.AppVO[]>([]);
const total = ref<number>(0);

/**
 * 加载数据
 */
const loadData = async () => {
  const params = {
    ...searchParams.value,
  };
  const res = await listMyAppVoByPageUsingPost(params);
  if (res.data.code === 200) {
    dataList.value = res.data.data?.records || [];
    total.value = res.data.data?.total || 0;
  } else {
    message.error("获取数据失败，" + res.data.message);
  }
};
const doSearch = (value: string) => {
  searchParams.value.searchText = value;
  loadData();
};

/**
 * 当分页变化时，改变搜索条件，触发数据加载
 * @param page
 */
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
/**
 * 监听 searchParams 变量，改变时触发数据的重新加载
 */
watchEffect(() => {
  loadData();
});
const addAppModal = ref();
const openAddAppModal = () => {
  addAppModal.value.openModal();
};
</script>

<style scoped>
#myAppPage {
}

.searchBar {
  display: flex;
  align-content: center;
  justify-content: space-around;
  margin-bottom: 28px;
  text-align: center;
}

.list-demo-action-layout .image-area {
  width: 183px;
  height: 119px;
  overflow: hidden;
  border-radius: 2px;
}

.list-demo-action-layout .list-demo-item {
  padding: 20px 0;
  border-bottom: 1px solid var(--color-fill-3);
}

.list-demo-action-layout .image-area img {
  width: 100%;
}

.list-demo-action-layout .arco-list-item-action .arco-icon {
  margin: 0 4px;
}
</style>
