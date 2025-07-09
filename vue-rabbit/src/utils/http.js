// 对axios做基础封装
import axios from "axios";

const httpInstance = axios.create({
  // 请求地址
  baseURL: "http://pcapi-xiaotuxian-front-devtest.itheima.net/",

  // 超时时间
  timeout: 5000,
});

// 请求拦截器
httpInstance.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// 响应拦截器
httpInstance.interceptors.response.use(
  (response) => {
    return response.data;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default httpInstance;
