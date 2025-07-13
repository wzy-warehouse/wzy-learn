import type LoginInfo from "@/types/LoginInfo";
import type RegisterInfo from "@/types/RegisterInfo";
import httpInstance from "@/utils/http";
import { toRaw } from "vue";

// 注册
export async function registerAPI(datas: RegisterInfo) {
    const result = await httpInstance.post('/users/register', toRaw(datas))
    return result.data
}

// 登录
export async function loginAPI(datas: LoginInfo) {
    const result = await httpInstance.post('/users/login', toRaw(datas))
    return result.data
}

// 检测登录状态
export async function checkLoginAPI() {
    const result = await httpInstance.post('/users/login_status')
    return result.data
}