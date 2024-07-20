<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import routes from "@/router/router";
import { computed } from "vue";
import { useLoginUserStore } from "@/store/userStore";
import checkAccess from "@/access/checkAccess";
import { userLogoutUsingGet } from "@/api/userController";
import message from "@arco-design/web-vue/es/message";
import ACCESS_ENUM from "@/access/accessEnum";

const route = useRoute();
const router = useRouter();
const selectedKeys = computed(() => [route.path]);
const loginUserStore = useLoginUserStore();

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};

const visibleRoutes = computed(() => {
  return routes
    .filter((item) => item?.meta?.showInMenu)
    .filter((item) =>
      checkAccess(loginUserStore.loginUser, item.meta?.access as string)
    )
    .sort((a, b) => (a?.meta?.index as number) - (b?.meta?.index as number));
});

const handleSelect = (value: string) => {
  router.push({
    path: value,
  });
};
// 退出登录
const handleLogout = async () => {
  const res = await userLogoutUsingGet();
  if (res.data.code === 200) {
    loginUserStore.removerLoginUser();
    message.success("退出登录成功");
  } else {
    message.error("退出登录失败，" + res.data.message);
  }
};
</script>

<template>
  <a-row id="globalHeader" align="center" :wrap="false" justify="space-between">
    <a-col :span="12">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="title-bar">
            <img class="logo" src="../assets/logo.png" alt="logo" />
            <div class="title">QianBot</div>
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path"
          >{{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col :span="3">
      <template v-if="!loginUserStore.loginUser.id">
        <a-button type="primary" href="/user/login">登录</a-button>
      </template>
      <a-dropdown v-else position="bl" @select="handleSelect as any">
        <a-space>
          <a-avatar
            v-if="loginUserStore.loginUser.userAvatar"
            :size="50"
            class="avatar"
          >
            <img alt="avatar" :src="loginUserStore.loginUser.userAvatar" />
          </a-avatar>
          <p>{{ loginUserStore.loginUser.userName }}</p>
        </a-space>
        <template #content>
          <a-doption
            value="/admin"
            v-if="loginUserStore.loginUser.userRole === ACCESS_ENUM.ADMIN"
            >管理页面</a-doption
          >
          <a-doption value="/user/center">个人中心</a-doption>
          <a-doption value="/app/history">答题历史</a-doption>
          <a-doption value="/chart/history">图表历史</a-doption>
          <a-doption value="/app/my">我的应用</a-doption>
          <a-doption value="/" @click="handleLogout">退出登录</a-doption>
        </template>
      </a-dropdown>
    </a-col>
  </a-row>
</template>

<style scoped>
#globalHeader {
  padding: 10px;
}
#globalHeader .title-bar {
  display: flex;
  align-items: center;
}
#globalHeader .title-bar .logo {
  height: 50px;
}
#globalHeader .title-bar .title {
  margin-left: 16px;
  color: black;
}
#globalHeader .avatar {
  cursor: pointer;
}
</style>
