import type RegisterInfo from "@/types/RegisterInfo";
import axios from "axios";
import { toRaw } from "vue";

// 注册
export async function registerAPI(datas: RegisterInfo) {
    const result = await axios.post('/api/users/register', toRaw(datas))
    return result.data
}