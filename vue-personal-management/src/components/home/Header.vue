<template>
  <div class="header-container">
    <Collapse />
  </div>
</template>

<script setup lang="ts" name="Header">
import Collapse from './header/Collapse.vue'
</script>

<style scoped>
.header-container {
  display: flex;
  align-items: center;
}
</style>
