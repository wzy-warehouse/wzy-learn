<template>
  <div>
    <el-icon color="#FFF" size="30" class="icon" @click="setCollapse">
      <component :is="menuStore.collapse ? Fold : Expand"></component>
    </el-icon>
  </div>
</template>

<script setup lang="ts" name="Collapse">
import { useMenuStore } from '@/stores/menuStore'
import { Fold, Expand } from '@element-plus/icons-vue'
const menuStore = useMenuStore()

// 切换图标点击事件
const setCollapse = () => {
  menuStore.collapse = !menuStore.collapse
}
</script>

<style scoped>
.icon {
  cursor: pointer;
}
</style>
