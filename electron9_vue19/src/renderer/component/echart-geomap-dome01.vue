<template>
    <div id="echart-geomap-dome01" style="width: 1600px;height:900px;"></div>
</template>

<script>
    let echarts = require('echarts');
    require('../lib/china.js');
    function getChinaGeoOption(){
        return {
            map: 'china',
            id: 11,
            z: 1,
            left: '10',
            center: [87.98561551896913, 34.205000490896193],
            zoom: 1,
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true, //是否开启鼠标缩放和平移漫游
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        }
    }

    export default {
        name: "echart-geomap-dome01",
        mounted() {
            let option = {
                backgroundColor: '#404a59',
                animation: true,
                animationDuration: 1000,
                animationEasing: 'cubicInOut',
                animationDurationUpdate: 1000,
                animationEasingUpdate: 'cubicInOut',
                title: [{
                    text: '中国地图',
                    top: 20,
                    left: 'center',
                    fontSize: 20,
                    textStyle: {
                        color: '#fff'
                    }
                }, {
                    id: 'statistic',
                    right: 120,
                    top: 40,
                    width: 100,
                    textStyle: {
                        color: '#fff',
                        fontSize: 16
                    }
                }],
                geo:getChinaGeoOption()
            };

            let myChart = echarts.init(document.getElementById('echart-geomap-dome01'));
            myChart.setOption(option);
        }
    }
</script>

<style scoped>

</style>