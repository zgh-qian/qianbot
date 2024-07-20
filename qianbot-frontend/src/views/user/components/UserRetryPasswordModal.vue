<script setup lang="ts">
import { defineExpose, ref } from "vue";
import message from "@arco-design/web-vue/es/message";
import API from "@/api";
import {
  getPasswordCodeUsingGet,
  userRetryPasswordUsingPost,
} from "@/api/userController";

const visible = ref(false);

const openModal = () => {
  visible.value = true;
};

const closeModal = () => {
  visible.value = false;
};

defineExpose({
  openModal,
});

const form = ref({
  userEmail: "",
  userPassword: "",
  checkPassword: "",
  code: "",
} as API.UserUpdateRequest);

const countDownInit = 60;
const countDown = ref(countDownInit);
const loading = ref(false);
/**
 * 发送验证码
 */
const sendCode = async () => {
  if (!form.value.userEmail) {
    message.error("请输入邮箱");
    return;
  }
  const res = await getPasswordCodeUsingGet({
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

/**
 * 提交
 */
const handleSubmit = async () => {
  const res = await userRetryPasswordUsingPost({
    ...form.value,
  });
  if (res.data.code === 200) {
    message.success("修改密码成功");
  } else {
    message.error("修改密码失败，" + res.data.message);
  }
};
</script>
<template>
  <div class="userRetryPasswordModal">
    <a-modal v-model:visible="visible" @cancel="closeModal" :footer="false">
      <template #title> 找回密码 </template>
      <a-form
        :model="form"
        :style="{ width: '480px' }"
        label-align="left"
        auto-label-width
        @submit="handleSubmit"
      >
        <a-form-item field="userEmail" label="邮箱" required>
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
        <a-form-item field="userPassword" label="密码" required>
          <a-input-password
            v-model="form.userPassword"
            placeholder="请输入密码..."
          />
        </a-form-item>
        <a-form-item field="checkPassword" label="确认密码" required>
          <a-input-password
            v-model="form.checkPassword"
            placeholder="请输入确认密码..."
          />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit" style="width: 120px">
            提交
          </a-button>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
