<template>
  <el-container class="layout">
    <el-aside class="aside" width="auto" style="background-color: #304156">
      <Suspense>
        <template #default>
          <MenuBar></MenuBar>
        </template>
      </Suspense>
    </el-aside>
    <el-container>
      <el-header class="header">
        <Header></Header>
      </el-header>
      <el-main class="main">
        <RouterView />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts" name="Home">
import Header from '@/components/home/Header.vue'
import MenuBar from '@/components/home/MenuBar.vue'
import { RouterView } from 'vue-router';
</script>
<style scoped lang="scss">
.layout {
  height: 100%;
  .header {
    background-color: #009688;
    display: flex;
    align-items: center;
  }
  .main {
    margin: 0;
    padding: 0;
  }
}
</style>
