import { defineStore } from "pinia";
import { ref } from "vue";
import { getLoginUserUsingGet } from "@/api/userController";

export const useLoginUserStore = defineStore("loginUser", () => {
  const loginUser = ref<API.LoginUserVO>({
    userName: "未登录",
  });

  function setLoginUser(newLoginUser: API.LoginUserVO) {
    loginUser.value = newLoginUser;
  }
  function removerLoginUser() {
    loginUser.value = {
      userName: "未登录",
    };
  }
  async function fetchLoginUser() {
    const res = await getLoginUserUsingGet();
    if (res.data.code === 200 && res.data.data) {
      loginUser.value = res.data.data;
    } else {
      loginUser.value = {
        userName: "未登录",
      };
    }
  }
  return { loginUser, removerLoginUser, setLoginUser, fetchLoginUser };
});
