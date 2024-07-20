<template>
  <div id="userLoginPage">
    <a-typography-title :heading="2"> 用户注册 </a-typography-title>
    <a-form
      :model="form"
      :style="{ width: '480px', margin: '0 auto' }"
      label-align="left"
      @submit="handleSubmit"
    >
      <a-form-item label="账号" validate-trigger="input" required>
        <a-input
          v-model="form.userAccount"
          placeholder="请输入长度大于等于4的账号"
        />
      </a-form-item>
      <a-form-item label="密码" validate-trigger="input" required>
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入长度大于等于6的密码"
        />
      </a-form-item>
      <a-form-item label="确认密码" validate-trigger="input" required>
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请输入长度大于等于6的密码"
        />
      </a-form-item>
      <a-form-item label="昵称" validate-trigger="input" required>
        <a-input v-model="form.userName" placeholder="请输入昵称..." />
      </a-form-item>
      <a-form-item label="邮箱" validate-trigger="input" required>
        <a-input v-model="form.userEmail" placeholder="请输入邮箱..." />
      </a-form-item>
      <a-form-item label="验证码" validate-trigger="input" required>
        <a-input v-model="form.code" placeholder="请输入验证码...">
          <template #append>
            <a-button type="primary" @click="sendCode" :loading="loading">{{
              loading ? `${countDown}` + "后重试" : "发送验证码"
            }}</a-button>
          </template>
        </a-input>
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
          >注册</a-button
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
        <a-link href="/user/login">用户登录</a-link>
      </a-space>
      <UserRetryPasswordModal ref="userRetryPasswordModal" />
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import API from "@/api";
import {
  getRegisterCodeUsingGet,
  userRegisterUsingPost,
} from "@/api/userController";
import { useLoginUserStore } from "@/store/userStore";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import UserRetryPasswordModal from "@/views/user/components/UserRetryPasswordModal.vue";

const router = useRouter();
const loginUserStore = useLoginUserStore();

const countDownInit = 60;
const countDown = ref(countDownInit);
const loading = ref(false);

const form = ref<API.UserRegisterRequest>({});

const sendCode = async () => {
  if (!form.value.userEmail) {
    message.error("请输入邮箱");
    return;
  }
  const res = await getRegisterCodeUsingGet({
    email: form.value.userEmail,
  });
  if (res.data.code === 200) {
    countDown.value = countDownInit;
    loading.value = true;
    var timer = setInterval(() => {
      countDown.value--;
      if (countDown.value == 0) {
        clearInterval(timer);
        loading.value = false;
      }
    }, 1000);
    message.success("发送验证码成功，请在邮箱中查看");
  } else {
    message.error("发送验证码失败，" + res.data.message);
  }
};

const handleSubmit = async () => {
  const res = await userRegisterUsingPost(form.value);
  if (res.data.code === 200) {
    await loginUserStore.fetchLoginUser();
    message.success("注册成功");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("注册失败，" + res.data.message);
  }
};
const userRetryPasswordModal = ref();
</script>
<style scoped>
#userLoginPage {
  text-align: center;
}
</style>
