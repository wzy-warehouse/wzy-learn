<template>
  <div class="bg" :style="bgStyle"></div>
  <el-main class="main-container">
    <el-row class="center-row" justify="center" align="middle">
      <el-col :xs="24" :sm="16" :md="16" :lg="7" :xl="7">
        <el-card>
          <el-form :model="form" label-width="auto" :rules="rules" ref="ruleFormRef">
            <el-form-item>
              Language:&nbsp;&nbsp;
              <el-select @change="changeLanguage" v-model="form.lang" style="width: 120px">
                <el-option
                  v-for="item in languageList"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>

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

            <el-form-item justify="space-between">
              <el-col :span="12"> </el-col>
              <el-col :span="12" align="right">
                <RouterLink to="/forget_password">{{ i18n('user.forget.text') }}</RouterLink>
              </el-col>
            </el-form-item>

            <el-form-item justify="space-between">
              <el-col :span="15">
                <el-button class="button-center" type="primary" @click="submitForm(ruleFormRef)">{{
                  i18n('user.loginBtn.text')
                }}</el-button>
              </el-col>
              <el-col :span="9" align="right">
                <RouterLink to="/register">{{ i18n('user.register.text') }}</RouterLink>
              </el-col>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>
  </el-main>
</template>

<script setup lang="ts" name="Login">
import { loginAPI } from '@/apis/users'
import { useUserStore } from '@/stores/userStore'
import type LoginInfo from '@/types/LoginInfo'
import { ElMessage, type FormInstance, type FormRules } from 'element-plus'
import { ref, onMounted, reactive, computed } from 'vue'
import { useI18n } from 'vue-i18n'
import { RouterLink, useRouter } from 'vue-router'

// 背景图片设置
const bgStyle = ref({})
// 多语言文件
const { t: i18n, locale } = useI18n()

// 语言列表
const languageList = reactive([
  { label: '简体中文', value: 'zh-CN' },
  { label: 'English', value: 'en-US' },
])
// 传递后端验证的登录数据
const form: LoginInfo = reactive({
  username: '',
  password: '',
  lang: sessionStorage.getItem('user-lang') || 'zh-CN',
})

// 表单验证
const ruleFormRef = ref<FormInstance>()
const rules = computed<FormRules<LoginInfo>>(() => {
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
  }
})

// 路由器
const router = useRouter()

// 设置随机背景
const setRandomBg = () => {
  const randomNum = Math.floor(Math.random() * 17) + 1
  bgStyle.value = { backgroundImage: `url("src/assets/login/bg/${randomNum}.jpg")` }
}
// 切换语言
const changeLanguage = () => {
  locale.value = form.lang
  sessionStorage.setItem('user-lang', form.lang)
}
// 监听页面加载
onMounted(() => {
  setRandomBg()
})
// 提交登录数据
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      // 登录
      loginAPI(form).then((res) => {
        if (res.code === 200) {
          // 保存
          useUserStore().saveUserInfo(res.data)
          // 跳转到首页
          router.replace('/home')
        }else {
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
