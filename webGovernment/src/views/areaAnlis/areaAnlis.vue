<template>
    <div style="background-color: #fff;">
        <div :style="{ height: '400px', width: '600px' }" ref="charts"></div>
    </div>
</template>
<script>
// import echarts from '@/components/echarts/index'
const geoJson = require('../../../public/quanguo.json');
const HKJSON = require('../../../public/HK.json');
const dataList = [{
    name: '北京',
    value: 81
},
{
    name: '天津',
    value: 60
},
{
    name: '上海',
    value: 54
},
{
    name: '重庆',
    value: 390
},
{
    name: '河北',
    value: 1741
},
{
    name: '河南',
    value: 2634

},
{
    name: '云南',
    value: 755
},
{
    name: '辽宁',
    value: 826
},
{
    name: '黑龙江',
    value: 225
},
{
    name: '湖南',
    value: 969
},
{
    name: '安徽',
    value: 818
},
{
    name: '山东',
    value: 3011
},
{
    name: '新疆',
    value: 1528
},
{
    name: '江苏',
    value: 929
},
{
    name: '浙江',
    value: 746
},
{
    name: '江西',
    value: 667
},
{
    name: '湖北',
    value: 957
},
{
    name: '广西',
    value: 1810
},
{
    name: '甘肃',
    value: 655
},
{
    name: '山西',
    value: 843
},
{
    name: '内蒙古',
    value: 310
},
{
    name: '陕西',
    value: 1926
},
{
    name: '吉林',
    value: 149
},
{
    name: '福建',
    value: 741
},
{
    name: '贵州',
    value: 252
},
{
    name: '广东',
    value: 1594
},
{
    name: '青海',
    value: 4
},
{
    name: '西藏',
    value: 1
},
{
    name: '四川',
    value: 971
},
{
    name: '宁夏',
    value: 255
},
{
    name: '海南',
    value: 406
},]
export default {
    components: {
        // echarts
    },
    data() {
        return {
            baseOptions: {}
        }
    },
    computed: {
        scrollerHeight: function () {
            return (document.documentElement.clientHeight - 250) + 'px'; //自定义高度
        },
        scrollerWeight: function () {
            return (document.documentElement.clientWeight - 550) + 'px'; //自定义宽度
        }
    },
    created() {
        this.$nextTick(() => {
            this.initCharts();
        })
    },
    methods: {
        initCharts() {
            const charts = this.$echarts.init(this.$refs["charts"]);
            const option = {
                // 背景颜色
                backgroundColor: "#fff",
                // 提示浮窗样式
                tooltip: {
                    // show: true,
                    trigger: 'item', //坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用
                    triggerOn: "mousemove",
                    enterable: true, //鼠标是否可进入提示框浮层中
                    formatter: function (params) {
                        let str = '';
                        str = `<div> ` + params.name + `:` + (params.value || 0) + `</div>`
                        return str
                    },
                },

                visualMap: { //分段型视觉映射组件
                    show: true,
                    type: 'piecewise',
                    left: 10,
                    bottom: 20,
                    showLabel: true,
                    itemWidth: 20,
                    itemHeight: 10,
                    inverse: true,
                    // lt:小于; lte:小于等于; gt:大于; gte:大于等于;
                    pieces: [  
                        {
                            gte: 0,
                            lte: 20,
                            label: "0-20家",
                            color: "#55BB8A"
                        },
                        {
                            gt: 20,
                            lte: 50,
                            label: "20-50家",
                            color: "#20A162"
                        },
                        {
                            gt: 50,
                            lte: 80,
                            label: "50-80家",
                            color: "#9ABEFA"
                        },
                        {
                            gt: 80,
                            lte: 100,
                            label: '80-100家',
                            color: "#78A9F9"
                        },
                        {
                            gt: 100,
                            label: '100家以上',
                            color: "#5693F7"
                        }
                    ]
                },
                // 地图配置
                geo: {
                    map: "china",
                    show:false,//是否打开背景地图
                    aspectScale:1, //长宽比,0.75的含义为宽是高的0.75,假如高为100,宽就只有75;0.5的含义就是宽只有高的一半,假如高为100,宽就只有50
                    // zoom: 1.2, //觉比例大小,1.2即为原有大小的1.2倍
                    roam: false, //是否开启鼠标缩放和平移漫游。默认不开启。可以不用设置,如果只想要开启缩放或者平移，可以设置成 'scale' 或者 'move'。
                    // top: '0%',
                    label: {
                        // 通常状态下的样式
                        normal: {
                            show: true,
                            textStyle: {
                                color: "#fff",
                            },
                        },
                        // 鼠标放上去的样式
                        emphasis: {
                            textStyle: {
                                color: "#fff",
                            },
                        },
                    },
                    // 地图区域的样式设置
                    itemStyle: {
                        normal: {
                            borderColor: "rgba(0, 0, 0, 0.1)",
                            borderWidth: 1,
                        },
                        // 鼠标放上去高亮的样式
                        emphasis: {
                            // areaColor: "rgba(0, 0, 0, 0.1)",
                            borderWidth: 0,
                        },
                    },
                },
                series: [
                    {
                        name: '香港18区人口密度',
                        type: 'map',
                        map: 'china',
                        label: {
                            show: true
                        },
                        data: dataList,
                        // 自定义名称映射
                        // nameMap: {
                        //     'Central and Western': '中西区',
                        //     Eastern: '东区',
                        //     Islands: '离岛',
                        //     'Kowloon City': '九龙城',
                        //     'Kwai Tsing': '葵青',
                        //     'Kwun Tong': '观塘',
                        //     North: '北区',
                        //     'Sai Kung': '西贡',
                        //     'Sha Tin': '沙田',
                        //     'Sham Shui Po': '深水埗',
                        //     Southern: '南区',
                        //     'Tai Po': '大埔',
                        //     'Tsuen Wan': '荃湾',
                        //     'Tuen Mun': '屯门',
                        //     'Wan Chai': '湾仔',
                        //     'Wong Tai Sin': '黄大仙',
                        //     'Yau Tsim Mong': '油尖旺',
                        //     'Yuen Long': '元朗'
                        // }
                    }
                ],
            };
            // 地图注册，第一个参数的名字必须和option.geo.map一致
            this.$echarts.registerMap("china", geoJson)
            // this.$echarts.registerMap("HK", HKJSON)
            charts.setOption(option);
            charts.on("mouseover", function () { //取消鼠标移入地图区域高亮
                charts.dispatchAction({
                    type: 'legendUnSelect'
                });
            });
        },
    }
}
</script>