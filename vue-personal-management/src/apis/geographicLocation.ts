// 显示忻州市所有县区
export const displayXinzhou = (Cesium: any, viewer: any) => {
  
  const cityInfo = [
    {
      code: 140902,
      bgColor: '#FF0000',
      position: [112.59, 38.42],
      name: '忻府区',
    },
    {
      code: 140921,
      bgColor: '#00FF00',
      position: [112.99, 38.5],
      name: '定襄县',
    },
    {
      code: 140922,
      bgColor: '#0000FF',
      position: [113.45, 38.77],
      name: '五台县',
    },
    {
      code: 140923,
      bgColor: '#FFFF00',
      position: [113.03, 39.06],
      name: '代县',
    },
    {
      code: 140924,
      bgColor: '#FF00FF',
      position: [113.61, 39.2],
      name: '繁峙县',
    },
    {
      code: 140925,
      bgColor: '#00FFFF',
      position: [112.15, 38.89],
      name: '宁武县',
    },
    {
      code: 140926,
      bgColor: '#FFA500',
      position: [112.02, 38.37],
      name: '静乐县',
    },
    {
      code: 140927,
      bgColor: '#2f6f54ff',
      position: [112, 39.16],
      name: '神池县',
    },
    {
      code: 140928,
      bgColor: '#FF1493',
      position: [111.7, 39.01],
      name: '五寨县',
    },
    {
      code: 140929,
      bgColor: '#32CD32',
      position: [111.51, 38.72],
      name: '岢岚县',
    },
    {
      code: 140930,
      bgColor: '#1E90FF',
      position: [111.33, 39.25],
      name: '河曲县',
    },
    {
      code: 140931,
      bgColor: '#8A2BE2',
      position: [111.13, 38.89],
      name: '保德县',
    },
    {
      code: 140932,
      bgColor: '#FF4500',
      position: [111.64, 39.46],
      name: '偏关县',
    },
    {
      code: 140981,
      bgColor: '#ADFF2F',
      position: [112.71, 38.8],
      name: '原平市',
    },
  ]
  cityInfo.forEach((city) => {
    Cesium.GeoJsonDataSource.load(
      'https://geo.datav.aliyun.com/areas_v3/bound/' + city.code + '.json',
      {
        fill: Cesium.Color.fromCssColorString(city.bgColor).withAlpha(0.5),
      },
    ).then((dataSource: any) => {
      viewer.dataSources.add(dataSource)
      // 获取实体并添加自定义属性
      const entity = dataSource.entities.values[0]
      entity.countyCode = city.code // 添加县区code
    })

    // 创建文本实体
    viewer.entities.add({
      position: Cesium.Cartesian3.fromDegrees(city.position[0], city.position[1], 10),
      label: {
        text: city.name, // 文本
        font: '20px 微软雅黑', // 字体样式
        fillColor: Cesium.Color.BLACK, // 填充颜色
      },
    })
  })
}
