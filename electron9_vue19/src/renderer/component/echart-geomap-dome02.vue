<template>
    <div id="echart-geomap-dome02" style="width: 1600px;height:900px;"></div>
</template>

<script>
    let echarts = require('echarts');
    require('../lib/china.js');
    function getChinaGeoOption() {
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

    function getChinaCoordMap() {
        var chinaGeoCoordMap = {
            '黑龙江': [127.9688, 45.368],
            '内蒙古': [110.3467, 41.4899],
            "吉林": [125.8154, 44.2584],
            '北京市': [116.4551, 40.2539],
            "辽宁": [123.1238, 42.1216],
            "河北": [114.4995, 38.1006],
            "天津": [117.4219, 39.4189],
            "山西": [112.3352, 37.9413],
            "陕西": [109.1162, 34.2004],
            "甘肃": [103.5901, 36.3043],
            "宁夏": [106.3586, 38.1775],
            "青海": [101.4038, 36.8207],
            "新疆": [87.9236, 43.5883],
            "西藏": [91.11, 29.97],
            "四川": [103.9526, 30.7617],
            "重庆": [108.384366, 30.439702],
            "山东": [117.1582, 36.8701],
            "河南": [113.4668, 34.6234],
            "江苏": [118.8062, 31.9208],
            "安徽": [117.29, 32.0581],
            "湖北": [114.3896, 30.6628],
            "浙江": [119.5313, 29.8773],
            "福建": [119.4543, 25.9222],
            "江西": [116.0046, 28.6633],
            "湖南": [113.0823, 28.2568],
            "贵州": [106.6992, 26.7682],
            "云南": [102.9199, 25.4663],
            "广东": [113.12244, 23.009505],
            "广西": [108.479, 23.1152],
            "海南": [110.3893, 19.8516],
            '上海': [121.4648, 31.2891]
        };
        return chinaGeoCoordMap;
    }
    function getChinaGeoCoordMapEffectScatter(){
        var chinaGeoCoordMap=getChinaCoordMap();
        var data=[];
        for (var key in chinaGeoCoordMap) {
            var value=chinaGeoCoordMap[key];
            if(key=="西藏"){
                value.push(2);
            }
            else{
                value.push(0);
            }
            data.push({"name":key,"value":value});
        }
        //console.log(data);
        return [{
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: { //涟漪特效
                period: 4, //动画时间，值越小速度越快
                brushType: 'stroke', //波纹绘制方式 stroke, fill
                scale: 4 //波纹圆环最大限制，值越大波纹越大
            },
            label: {
                normal: {
                    show: true,
                    position: 'right', //显示位置
                    offset: [5, 0], //偏移设置
                    formatter: function (params) {//圆环显示文字
                        return params.data.name;
                    },
                    fontSize: 13
                },
                emphasis: {
                    show: true
                }
            },
            symbol: 'circle',
            symbolSize: function (val) {
                return 5 + val[2] * 5; //圆环大小
            },
            itemStyle: {
                normal: {
                    show: false,
                    color: '#ffba63'
                }
            },
            data: data.filter(function (element, index, array) { return element.name!="西藏" })
        },{
            type: 'effectScatter',
            coordinateSystem: 'geo',
            zlevel: 2,
            rippleEffect: { //涟漪特效
                period: 4, //动画时间，值越小速度越快
                brushType: 'stroke', //波纹绘制方式 stroke, fill
                scale: 4 //波纹圆环最大限制，值越大波纹越大
            },
            label: {
                normal: {
                    show: true,
                    position: 'right', //显示位置
                    offset: [5, 0], //偏移设置
                    formatter: function (params) {//圆环显示文字
                        return params.data.name;
                    },
                    fontSize: 13
                },
                emphasis: {
                    show: true
                }
            },
            symbol: 'circle',
            symbolSize: function (val) {
                return 5 + val[2] * 5; //圆环大小
            },
            itemStyle: {
                normal: {
                    show: false,
                    color: '#2bffb3'
                }
            },
            data: data.filter(function (element, index, array) { return element.name=="西藏" })
        }]
    }

    export default {
        name: "echart-geomap-dome02",
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
                geo: getChinaGeoOption(),
                series: [
                    getChinaGeoCoordMapEffectScatter()[0],
                    getChinaGeoCoordMapEffectScatter()[1]
                ]
            };

            let myChart = echarts.init(document.getElementById('echart-geomap-dome02'));
            myChart.setOption(option);

            myChart.on('click', function (params) {

                let mouseEvent = params.event;
                let event = mouseEvent.event;
                let pageX = event.pageX;
                let pageY = event.pageY;
                let arr = [pageX, pageY];

                let data = myChart.convertFromPixel('geo', arr);
                console.log(data);
            });
        }
    }
</script>

<style scoped>

</style>