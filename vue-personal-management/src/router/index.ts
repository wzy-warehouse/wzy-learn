import { createRouter, createWebHistory } from 'vue-router'
import Login from '@/views/Login/index.vue'
import ForgetPassword from '@/views/ForgetPassword/index.vue'
import Register from '@/views/Register/index.vue'
import Home from '@/views/Home/index.vue'
import { checkLoginAPI } from '@/apis/users'
import { useUserStore } from '@/stores/userStore'
import type ReturnType from '@/types/Return'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      redirect: '/login',
    },
    {
      name: 'login',
      path: '/login',
      component: Login,
      meta: { public: true }, // 标记为公开页面
    },
    {
      name: 'forgetPassword',
      path: '/forget_password',
      component: ForgetPassword,
      meta: { public: true }, // 标记为公开页面
    },
    {
      name: 'register',
      path: '/register',
      component: Register,
      meta: { public: true }, // 标记为公开页面
    },
    {
      name: 'home',
      path: '/home',
      component: Home,
      meta: { requiresAuth: true }, // 需要登录
    },
  ],
})

// 添加全局前置守卫
router.beforeEach(async (to, from, next) => {
  // 1. 检查目标路由是否需要登录
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    // 2. 验证用户是否登录
    const isAuthenticated = await checkAuthStatus()

    if (!isAuthenticated) {
      // 3. 未登录则重定向到登录页
      next({
        name: 'login',
      })
    } else {
      // 加载pinia中数据
      useUserStore()
      next() // 已登录，放行
    }
  }
  // 公开页面直接放行
  else if (to.matched.some((record) => record.meta.public)) {
    next()
  }
  // 其他未标记的路由默认需要登录（根据需求调整）
  else {
    next('/login') // 或者保持原有逻辑
  }
})

/**
 * 检查用户认证状态
 * @returns {Promise<boolean>} 是否已认证
 */
async function checkAuthStatus() {
  try {
    // 向验证接口发送请求
    const response: ReturnType = await checkLoginAPI()
    if (response && response.code === 200) {
      return true
    }
    return false
  } catch (error) {
    console.error('认证检查失败:', error)
    return false
  }
}

export default router
