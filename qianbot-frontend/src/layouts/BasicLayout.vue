<script setup lang="ts">
import GlobalFooter from "@/components/GlobalFooter.vue";
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>

<template>
  <a-layout id="baseLayout">
    <a-layout-header class="header"><GlobalHeader /></a-layout-header>
    <a-layout-content class="content"><router-view /></a-layout-content>
    <a-layout-footer><GlobalFooter /></a-layout-footer>
  </a-layout>
</template>

<style scoped>
#baseLayout {
  height: 100vh;
}
#baseLayout .header {
  width: 100%;
  margin-bottom: 16px;
  box-shadow: #eee 1px 1px 5px;
}
#baseLayout .content {
  box-sizing: border-box;
  max-width: 1200px;
  margin: 0 auto 28px;
  padding: 20px;
  background: linear-gradient(to right, #fefefe, #fff);
}
</style>
