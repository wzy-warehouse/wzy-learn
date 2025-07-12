<template>
  <div class="bg" :style="bgStyle"></div>
  <el-main class="main-container">
    <el-row class="center-row" justify="center" align="middle">
      <el-col :xs="24" :sm="16" :md="16" :lg="7" :xl="7">
        <el-card>
          <el-form :model="form" label-width="auto" :rules="rules" ref="ruleFormRef">
            <el-form-item :label="i18n('user.username.label')" prop="username">
              <el-input v-model="form.username" :placeholder="i18n('user.username.placeholder')" />
            </el-form-item>

            <el-form-item :label="i18n('user.password.label')" prop="password">
              <el-input
                v-model="form.password"
                :placeholder="i18n('user.password.placeholder')"
                type="password"
              />
            </el-form-item>

            <el-form-item :label="i18n('user.confirmPassword.label')" prop="confirmPassword">
              <el-input
                v-model="form.confirmPassword"
                :placeholder="i18n('user.confirmPassword.placeholder')"
                type="password"
              />
            </el-form-item>

            <el-form-item>
              <el-button class="button-center" type="primary" @click="submitForm(ruleFormRef)">{{
                i18n('user.registerBtn.text')
              }}</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </el-main>
</template>

<script setup lang="ts" name="Login">
import { registerAPI } from '@/apis/users'
import type RegisterInfo from '@/types/RegisterInfo'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { ref, onMounted, reactive, computed } from 'vue'
import { useI18n } from 'vue-i18n'
import { useRouter } from 'vue-router'

// 背景图片设置
const bgStyle = ref({})
// 多语言文件
const { t: i18n, locale } = useI18n()

// 传递后端验证的登录数据
const form: RegisterInfo = reactive({ username: '', password: '', confirmPassword: '', lang: sessionStorage.getItem('user-lang') || 'zh-CN'})

// 表单验证
const ruleFormRef = ref<FormInstance>()
const rules = computed<FormRules<RegisterInfo>>(() => {
  return {
    username: [
      { required: true, message: i18n('user.username.rules.required'), trigger: 'blur' },
      { min: 3, message: i18n('user.username.rules.min', [3]), trigger: 'blur' },
      { max: 10, message: i18n('user.username.rules.max', [10]), trigger: 'blur' },
    ],
    password: [
      { required: true, message: i18n('user.password.rules.required'), trigger: 'blur' },
      { min: 8, message: i18n('user.password.rules.min', [8]), trigger: 'blur' },
      { max: 16, message: i18n('user.password.rules.max', [16]), trigger: 'blur' },
    ],
    confirmPassword: [
      { required: true, message: i18n('user.confirmPassword.rules.required'), trigger: 'blur' },
      { min: 8, message: i18n('user.confirmPassword.rules.min', [8]), trigger: 'blur' },
      { max: 16, message: i18n('user.confirmPassword.rules.max', [16]), trigger: 'blur' },
      {
        validator: (rule, value, callback) => {
          if (form.password !== value) {
            callback(new Error(i18n('user.confirmPassword.rules.equalTo')))
          } else {
            callback()
          }
        },
      },
    ],
  }
})

// 全局路由
const router = useRouter()

// 设置随机背景
const setRandomBg = () => {
  const randomNum = Math.floor(Math.random() * 17) + 1
  bgStyle.value = { backgroundImage: `url("src/assets/login/bg/${randomNum}.jpg")` }
}

// 监听页面加载
onMounted(() => {
  setRandomBg()
})
// 提交注册数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid) => {
    if (valid) {
      // 注册
      registerAPI(form).then((res) => {
        if (res.code === 200) {
          ElMessage({
            type: 'success',
            message: res.message,
            duration: 3000,
          })
          setTimeout(() => {
            router.push('/login')
          }, 3000)
        } else {
          ElMessage.error(res.message)
        }
      })
    }
  })
}
</script>

<style scoped>
.bg {
  width: 100%;
  height: 100vh;
  margin: 0;
  padding: 0;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  position: absolute;
  top: 0;
  left: 0;
  z-index: -1;
}

.main-container {
  min-height: 100vh;
  padding: 0;
}

.center-row {
  min-height: 100vh;
}

.button-center {
  display: block;
  margin: auto;
  width: 100%;
}
</style>
