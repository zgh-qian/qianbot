<template>
  <div id="userCenter">
    <a-typography-title :heading="2"> 用户中心 </a-typography-title>
    <a-form
      :model="form"
      :style="{ width: '480px', margin: '0 auto' }"
      label-align="left"
      @submit="handleSubmit"
    >
      <a-form-item label="账号" validate-trigger="input">
        <a-input v-model="form.userAccount" placeholder="请输入账号..." />
      </a-form-item>
      <a-form-item label="昵称" validate-trigger="input">
        <a-input v-model="form.userName" placeholder="请输入昵称..." />
      </a-form-item>
      <a-form-item label="头像">
        <PictureUploader
          :biz="FILE_BIZ.USER_AVATAR"
          :value="form.userAvatar"
          :onChange="(value) => (form.userAvatar = value)"
        />
      </a-form-item>
      <a-form-item label="简介" validate-trigger="input">
        <a-input v-model="form.userProfile" placeholder="请输入简介..." />
      </a-form-item>
      <div
        style="
          display: flex;
          width: 100%;
          align-items: center;
          justify-content: center;
          margin-top: 20px;
        "
      >
        <a-button type="primary" html-type="submit" style="width: 120px"
          >修改</a-button
        >
        <a-button status="danger" @click="handleLogout" style="width: 120px"
          >退出登录</a-button
        >
      </div>
      <a-space
        style="
          display: flex;
          width: 100%;
          align-items: center;
          justify-content: center;
          margin-top: 10px;
        "
      >
        <a-link @click="userRetryPasswordModal.openModal()">找回密码</a-link>
        <a-link @click="router.push('/')">返回首页</a-link>
      </a-space>
      <UserRetryPasswordModal ref="userRetryPasswordModal" />
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { ref, watchEffect } from "vue";
import API from "@/api";
import {
  getLoginUserUsingGet,
  userLogoutUsingGet,
  userUpdateUsingPost,
} from "@/api/userController";
import message from "@arco-design/web-vue/es/message";
import UserRetryPasswordModal from "@/views/user/components/UserRetryPasswordModal.vue";
import PictureUploader from "@/components/PictureUploader.vue";
import { FILE_BIZ } from "@/constant/file";
import { useRouter } from "vue-router";
import { useLoginUserStore } from "@/store/userStore";

const router = useRouter();
const form = ref({
  userAccount: "",
  userName: "",
  userAvatar: "",
  userProfile: "",
} as API.UserUpdateRequest);

/**
 * 加载数据
 */
const loadData = async () => {
  const res = await getLoginUserUsingGet();
  if (res.data.code === 200) {
    form.value = res.data.data as any;
  } else {
    message.error("登录失败，" + res.data.message);
  }
};

/**
 * 修改
 */
const handleSubmit = async () => {
  const res = await userUpdateUsingPost(form.value);
  if (res.data.code === 200) {
    message.success("修改成功");
  } else {
    message.error("修改失败，" + res.data.message);
  }
};

const loginUserStore = useLoginUserStore();
/**
 * 退出登录
 */
const handleLogout = async () => {
  const res = await userLogoutUsingGet();
  if (res.data.code === 200) {
    loginUserStore.removerLoginUser();
    message.success("退出登录成功");
  } else {
    message.error("退出登录失败，" + res.data.message);
  }
};

const userRetryPasswordModal = ref();

watchEffect(() => {
  loadData();
});
</script>
<style scoped>
#userCenter {
  text-align: center;
}
</style>
