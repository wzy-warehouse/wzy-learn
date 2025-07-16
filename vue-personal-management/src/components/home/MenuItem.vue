<template>
  <template v-for="menu in menuList" :key="menu.id">
    <el-sub-menu v-if="menu.children && menu.children.length > 0" :index="menu.path == null ? '#' : menu.path">
      <template #title>
        <el-icon>
          <component :is="menu.icon"></component>
        </el-icon>
        <span>{{ menu.menuName }}</span>
      </template>
      <!-- 生成下级菜单 -->
      <menu-item :menuList="menu.children"></menu-item>
    </el-sub-menu>
    <el-menu-item v-else :index="menu.path == null ? '#' : menu.path">
      <el-icon>
        <component :is="menu.icon"></component>
      </el-icon>
      <template #title>{{ menu.menuName }}</template>
    </el-menu-item>
  </template>
</template>

<script setup lang="ts" name="MenuItem">
import MenuItem from './MenuItem.vue'
defineProps(['menuList'])
</script>

<style scoped></style>
