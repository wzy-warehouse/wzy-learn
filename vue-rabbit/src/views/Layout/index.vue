<template>
<LayoutNav />
<LayoutHeader />
<RouterView />
<Layoutfooter />
</template>

<script setup lang="ts" name="Layout">
import { RouterView } from 'vue-router';
import LayoutNav from './components/LayoutNav.vue';
import LayoutHeader from './components/LayoutHeader.vue';
import Layoutfooter from './components/Layoutfooter.vue';
</script>

<style scoped>

</style>