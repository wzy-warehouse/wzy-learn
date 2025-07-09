import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import { getCategory } from './apis/testAPI'

const app = createApp(App)

// 测试接口
getCategory().then(res => {
  console.log(res)
})

app.use(createPinia())
app.use(router)

app.mount('#app')
