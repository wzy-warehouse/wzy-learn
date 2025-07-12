import { createApp } from 'vue'
import { createPinia } from 'pinia'
import i18n from '@/locales'

import App from './App.vue'
import router from '@/router'

// 引入element-plus样式
import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(i18n)

app.mount('#app')
