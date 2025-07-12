import type UserInfo from "@/types/UserInfo";
import { defineStore } from "pinia";
import { reactive } from "vue";

export const useUserStore = defineStore('user', () => {
    const userInfo: UserInfo = reactive({
        id: -1,
        loginName: '',
        username: '',
        isLogin: false
    });

    // 修改个人信息
    function updateUserInfo(info: UserInfo) {
        userInfo.id = info.id;
        userInfo.loginName = info.loginName;
        userInfo.username = info.username;
    }

    // 修改登录状态
    function updateLoginState(isLogin: boolean) {
        userInfo.isLogin = isLogin;
    }

    return { userInfo, updateUserInfo, updateLoginState };
})