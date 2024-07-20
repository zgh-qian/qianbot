<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();
</script>

<template>
  <div id="userLayout">
    <a-layout style="height: 100vh">
      <a-layout-header class="header">
        <a-space @click="router.push('/')">
          <img class="logo" src="../assets/logo.png" alt="logo" />
          <div>QianBot</div>
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">By Qian</a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#userLayout {
  text-align: center;
}
#userLayout .logo {
  height: 60px;
}
#userLayout .header {
  margin-top: 30px;
  margin-bottom: 16px;
}
#userLayout .content {
  margin-bottom: 28px;
  padding: 20px;
}

#userLayout .footer {
  padding: 16px;
  text-align: center;
  background-color: #efefef;
}
</style>
