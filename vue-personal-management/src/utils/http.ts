// 对axios做基础封装
import axios from 'axios'

const httpInstance = axios.create({
  baseURL: 'http://localhost:9999',
  // 超时时间
  timeout: 5000,
  // 携带cookie
  withCredentials: true,
})

// 请求拦截器
httpInstance.interceptors.request.use(
  (config) => {
    return config
  },
  (error) => {
    return Promise.reject(error)
  },
)

// 响应拦截器
httpInstance.interceptors.response.use(
  (response) => {
    return response
  },
  (error) => {
    return Promise.reject(error)
  },
)

export default httpInstance
