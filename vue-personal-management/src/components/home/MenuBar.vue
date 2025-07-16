<template>
  <div>
    <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      unique-opened
      background-color="#304156"
      :collapse="menuStore.collapse"
      router
    >
      <MenuItem :menuList="menuList"></MenuItem>
    </el-menu>
  </div>
</template>

<script setup lang="ts" name="MenuBar">
import { computed } from 'vue'
import MenuItem from './MenuItem.vue'
import type MenuItemType from '@/types/MenuItemType'
import { getLists } from '@/apis/menus'
import { useMenuStore } from '@/stores/menuStore'
import { useRoute } from 'vue-router'

const route = useRoute()

// 收缩组件状态
const menuStore = useMenuStore()

// 菜单数据
let menuList: Array<MenuItemType> = await getLists()

// 当前激活的菜单
const activeMenu = computed(() => {
  const {path} = route
  return path
})
</script>

<style scoped lang="scss">
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 230px;
  min-height: 400px;
}
.el-menu {
  border-right: none;
}
:deep(.el-sub-menu .el-sub-menu__title) {
  color: #f4f4f4 !important;
}
:deep(.el-menu .el-menu-item) {
  color: #bfcbd9 !important;
}
// 菜单点中文字颜色
:deep(.el-menu-item.is-active) {
  color: #409eff !important;
}
// 当前打开菜单的所有子菜单颜色
:deep(.is-opened .el-menu-item) {
  color: #fff ;
}
// 鼠标移动到菜单的背景颜色
:deep(.el-menu-item:hover) {
  background-color: #001528 !important;
}
</style>

<style lang="scss">
/* 全局弹出菜单样式 */
.el-menu--popup {
  background-color: #304156 !important;
  
  .el-menu-item {
    color: #bfcbd9 !important;
  }
  
  .el-menu-item.is-active {
    color: #409eff !important;
  }
  
  .el-menu-item:hover {
    background-color: #001528 !important;
  }
  
  .el-sub-menu__title {
    color: #f4f4f4 !important;
  }
}
</style>