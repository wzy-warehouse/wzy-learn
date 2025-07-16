import { defineStore } from "pinia";
import { ref } from "vue";

export const useMenuStore = defineStore('menu', () => {
    let collapse = ref(false)

    return { collapse }
})