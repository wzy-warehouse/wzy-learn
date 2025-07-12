import { createRouter, createWebHistory } from "vue-router";
import Login from "@/views/Login/index.vue"
import ForgetPassword from "@/views/ForgetPassword/index.vue";
import Register from "@/views/Register/index.vue"

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            redirect: '/login'
        },
        {
            name: 'login',
            path: '/login',
            component: Login
        },
        {
            name: 'forgetPassword',
            path: '/forget_password',
            component: ForgetPassword

        },
        {
            name: 'register',
            path: '/register',
            component: Register
        },
    ]
})