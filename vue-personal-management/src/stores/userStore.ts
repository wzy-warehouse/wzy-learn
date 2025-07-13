import { defineStore } from 'pinia'
import type UserInfo from '@/types/UserInfo'
import { reactive } from 'vue'

export const useUserStore = defineStore('user', () => {
  const localUserInfo = localStorage.getItem('person_management_userInfo')
  const user: UserInfo = reactive(
    localUserInfo
      ? JSON.parse(localUserInfo)
      : {
          uid: -1,
          username: '',
          fullName: '',
        },
  )

  // 保存用户信息
  function saveUserInfo(info: UserInfo) {
    user.uid = info.uid
    user.username = info.username
    user.fullName = info.fullName

    // 保存到本地存储
    localStorage.setItem('person_management_userInfo', JSON.stringify(info))
  }

  // 登出操作
  function logout() {
    // 清除 localStorage
    localStorage.removeItem('person_management_userInfo')
  }

  return { user, saveUserInfo, logout }
})
