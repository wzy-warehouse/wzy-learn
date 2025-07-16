<template>
  <div id="cesium_container"></div>

  <!-- 提示框 -->
  <div class="tips" :style="{
    position: 'absolute',
    top: tipTop,
    left: tipLeft
  }" v-if="showTip">
    <div>城市：{{ city }}</div>
    <div>温度：{{ temperatureFloat }}℃</div>
    <div>天气：{{ weather }}</div>
    <div>风向：{{ winddirection }}</div>
    <div>风力：{{ windpower }}</div>
  </div>
</template>

<script setup lang="ts" name="Weather">
import { displayXinzhou } from '@/apis/geographicLocation'
import axios from 'axios'
import * as Cesium from 'cesium'
import { onMounted, ref } from 'vue'

const city = ref('')
const temperatureFloat = ref('')
const weather = ref('')
const winddirection = ref('')
const windpower = ref('')
const tipTop = ref('0')
const tipLeft = ref('0')
const showTip = ref(false)

onMounted(() => {
  // 设置token
  Cesium.Ion.defaultAccessToken =
    'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIyMmRjMzA3Mi1kOTQ3LTRjMDYtOGFmOC05YWMwZmYxN2RlYWYiLCJpZCI6MzIxMzI2LCJpYXQiOjE3NTI0OTIyNDh9.6MQTMjCxtSTFMaE6KmNIdMWdX_eBfNT8CeZVzxwm6fE'

  // 设置视图与属性
  const viewer = new Cesium.Viewer('cesium_container', {
    // 用来控制页面的各种控件
    geocoder: false, // 搜索
    homeButton: false, // 回家
    infoBox: false, // 信息框
    sceneModePicker: false, // 视角切换
    baseLayerPicker: false, // 底图切换
    navigationHelpButton: false, // 帮助
    animation: false, // 动画
    timeline: false, // 时间轴
    fullscreenButton: false, // 全屏
  })

  // 隐藏默认logo
  const creditContainer = viewer.cesiumWidget.creditContainer as HTMLElement
  if (creditContainer) {
    creditContainer.style.display = 'none'
  }

  // 使用高德底图
  const gaodeImageProvider = new Cesium.UrlTemplateImageryProvider({
    url: 'https://webst01.is.autonavi.com/appmaptile/?lang=zh_cn&size=1&scale=1&style=6&x={x}&y={y}&z={z}',
    credit: '高德地图——影像地图',
  })
  // const gaodeImageProvider = new Cesium.UrlTemplateImageryProvider({
  //   url: 'https://webrd01.is.autonavi.com/appmaptile/?lang=zh_cn&size=1&scale=1&style=8&x={x}&y={y}&z={z}',
  //   credit: '高德地图——矢量地图',
  // })
  viewer.imageryLayers.addImageryProvider(gaodeImageProvider)

  // 定位到山西省
  viewer.camera.flyTo({
    destination: Cesium.Cartesian3.fromDegrees(111, 39, 10000),
  })

  // 监听相机移动结束事件，用于突出显示数据
  viewer.camera.moveEnd.addEventListener(() => {
    // 隐藏提示框
    showTip.value = false
    const updatedCartographic = Cesium.Cartographic.fromCartesian(viewer.camera.position)
    const updatedHeight = Math.round(updatedCartographic.height)
    // 小于60万米显示忻州市所有县
    if (updatedHeight < 600000) {
      displayXinzhou(Cesium, viewer)
    }
  })

  const handler = new Cesium.ScreenSpaceEventHandler(viewer.scene.canvas)
  handler.setInputAction((event: any) => {
    showTip.value = false
    const pick = viewer.scene.pick(event.position)
    // 选中图源进行交互
    if (pick && pick.id && pick.id.countyCode) {
      // 获取数据
      axios.get(`https://restapi.amap.com/v3/weather/weatherInfo?key=d7677776fa2f372515b3ce002f32aec2&city=${ pick.id.countyCode }&extensions=base`).then((res) => {
        showTip.value = true
        tipTop.value = event.position.y + 20 + 'px'
        tipLeft.value = event.position.x + 60 + 'px'
        city.value = res.data.lives[0]['city']
        temperatureFloat.value = res.data.lives[0]['temperature_float']
        weather.value = res.data.lives[0]['weather']
        winddirection.value = res.data.lives[0]['winddirection']
        windpower.value = res.data.lives[0]['windpower']
      })
    }
  }, Cesium.ScreenSpaceEventType.LEFT_CLICK)

  // 拖拽
  handler.setInputAction(() => {
    showTip.value = false
  }, Cesium.ScreenSpaceEventType.LEFT_DOWN)
})
</script>

<style scoped>
#cesium_container {
  width: 100%;
  height: 100%;
}
.tips {
  background: antiquewhite;
  padding: 20px;
  border-radius: 10px;
}
</style>
