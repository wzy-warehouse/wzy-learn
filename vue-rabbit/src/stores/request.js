import { getCategoryAPI } from "@/apis/layout"
import { defineStore } from "pinia"
import { ref } from "vue"

export const useRequestStore = defineStore("repeatRequest", () => {
    const requestList = ref([])

    async function setRequestList() {
        const res = await getCategoryAPI()
        requestList.value = res.result
    }

    function clearRequestList() {
        requestList.value = []
    }

    return { requestList, setRequestList, clearRequestList }
})