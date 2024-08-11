<script setup lang="ts">
import { useRoute } from "vue-router";
import BasicLayout from "@/layouts/BasicLayout.vue";
import { onMounted } from "vue";

const route = useRoute();

// 设置网页标题
onMounted(() => {
  document.title = "QianBot";
});
</script>
<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else-if="route.path.startsWith('/admin')">
      <router-view />
    </template>
    <template v-else>
      <BasicLayout />
    </template>
  </div>
</template>

<style></style>
