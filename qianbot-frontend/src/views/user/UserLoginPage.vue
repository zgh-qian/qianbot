<template>
  <div id="userLoginPage">
    <a-typography-title :heading="2"> 用户登录 </a-typography-title>
    <a-form
      :model="form"
      :style="{ width: '480px', margin: '0 auto' }"
      label-align="left"
      @submit="handleSubmit"
    >
      <a-form-item label="账号" validate-trigger="input" required>
        <a-input v-model="form.userAccount" placeholder="请输入长度大于等于4" />
      </a-form-item>
      <a-form-item label="密码" validate-trigger="input" required>
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入长度大于等于6"
        />
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
          >登录</a-button
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
        <a-link href="/user/register">用户注册</a-link>
      </a-space>
      <UserRetryPasswordModal ref="userRetryPasswordModal" />
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import API from "@/api";
import { userLoginUsingPost } from "@/api/userController";
import { useLoginUserStore } from "@/store/userStore";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import UserRetryPasswordModal from "@/views/user/components/UserRetryPasswordModal.vue";

const form = ref({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
} as API.UserLoginRequest);

const loginUserStore = useLoginUserStore();
const router = useRouter();

const handleSubmit = async () => {
  const res = await userLoginUsingPost(form.value);
  if (res.data.code === 200) {
    await loginUserStore.fetchLoginUser();
    message.success("登录成功");
    router.push({
      path: "/",
      replace: true,
    });
  } else {
    message.error("登录失败，" + res.data.message);
  }
};
const userRetryPasswordModal = ref();
</script>
<style scoped>
#userLoginPage {
  text-align: center;
}
</style>
