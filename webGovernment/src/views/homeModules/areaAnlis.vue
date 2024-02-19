<template>
    <div class="box">
        <div class="title">机构分布</div>
        <div :style="{ height: '328px', width: '100%' }" ref="charts"></div>
    </div>
</template>
<script>
import china from "@/assets/json/china.json";
import fujian from "@/assets/json/fujian.json";
import anhui from "@/assets/json/anhui.json";
import aomen from "@/assets/json/aomen.json";
import beijing from "@/assets/json/beijing.json";
import chongqing from "@/assets/json/chongqing.json";
import gansu from "@/assets/json/gansu.json";
import guangdong from "@/assets/json/guangdong.json";
import guangxi from "@/assets/json/guangxi.json";
import guizhou from "@/assets/json/guizhou.json";
import hainan from "@/assets/json/hainan.json";
import hebei from "@/assets/json/hebei.json";
import heilongjiang from "@/assets/json/heilongjiang.json";
import henan from "@/assets/json/henan.json";
import hubei from "@/assets/json/hubei.json";
import hunan from "@/assets/json/hunan.json";
import jiangsu from "@/assets/json/jiangsu.json";
import jiangxi from "@/assets/json/jiangxi.json";
import jilin from "@/assets/json/jilin.json";
import liaoning from "@/assets/json/liaoning.json";
import neimenggu from "@/assets/json/neimenggu.json";
import ningxia from "@/assets/json/ningxia.json";
import qinghai from "@/assets/json/qinghai.json";
import shandong from "@/assets/json/shandong.json";
import shanghai from "@/assets/json/shanghai.json";
import shanxi2 from "@/assets/json/shanxi2.json";
import shanxi from "@/assets/json/shanxi.json";
import sichuan from "@/assets/json/sichuan.json";
import taiwan from "@/assets/json/taiwan.json";
import tianjin from "@/assets/json/tianjin.json";
import xianggang from "@/assets/json/xianggang.json";
import xinjiang from "@/assets/json/xinjiang.json";
import xizang from "@/assets/json/xizang.json";
import yunnan from "@/assets/json/yunnan.json";
import zhejiang from "@/assets/json/zhejiang.json";
const provinces = {
    全国:china,
    台湾省: taiwan,
    河北省: hebei,
    山西省: shanxi,
    辽宁省: liaoning,
    吉林省: jilin,
    黑龙江省: heilongjiang,
    江苏省: jiangsu,
    浙江省: zhejiang,
    安徽省: anhui,
    福建省: fujian,
    江西省: jiangxi,
    山东省: shandong,
    河南省: henan,
    湖北省: hubei,
    湖南省: hunan,
    广东省: guangdong,
    海南省: hainan,
    四川省: sichuan,
    贵州省: guizhou,
    云南省: yunnan,
    陕西省: shanxi2,
    甘肃省: gansu,
    青海省: qinghai,
    新疆维吾尔自治区: xinjiang,
    广西壮族自治区: guangxi,
    内蒙古自治区: neimenggu,
    宁夏回族自治区: ningxia,
    西藏自治区: xizang,
    北京: beijing,
    天津: tianjin,
    上海: shanghai,
    重庆: chongqing,
    香港特别行政区: xianggang,
    澳门特别行政区: aomen,
}
import {getAllOrgMap} from '@/requestPub/api'
export default {
    components: {
        // echarts
    },
    data() {
        return {
            charts:null,
            dataList:[],//数据源
            option: {
                // 背景颜色
                // backgroundColor: "#fff",
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
                    itemWidth: 10,
                    itemHeight: 10,
                    inverse: true,
                    // lt:小于; lte:小于等于; gt:大于; gte:大于等于;
                    textStyle: {
                        color: '#fff'
                    },
                    pieces: [
                        {
                            gte: 0,
                            lte: 20,
                            label: "0-20家",
                            color: "#1C5C99",
                        },
                        {
                            gt: 20,
                            lte: 50,
                            label: "20-50家",
                            color: "#1D82FF"
                        },
                        {
                            gt: 50,
                            lte: 80,
                            label: "50-80家",
                            color: "#1DB2FF"
                        },
                        {
                            gt: 80,
                            lte: 100,
                            label: '80-100家',
                            color: "#EDAE5D"
                        },
                        {
                            gt: 100,
                            label: '100家以上',
                            color: "#2E50FF"
                        }
                    ]
                },
                geo: [
                    {
                        // 指定图标为地图
                        type: "map",
                        // 设置数据
                        map: '',
                        aspectScale: 0.75,
                        zoom: 1.8, // 改变这个值的大小就可以了
                        top: '30%',
                        // 开启缩放
                        roam: true,
                        // 设置缩放的大小
                        scaleLimit: {
                            min: 1,
                            max: 1.8,
                        },
                        label: {
                            // 通常状态下的样式
                            normal: {
                                show: false,

                                // textStyle: {
                                //     color: "#fff",
                                // },
                            },
                            // 鼠标放上去的样式
                            emphasis: {
                                textStyle: {
                                    color: "#333",
                                },
                            },
                        },
                        // 地图区域的样式设置
                        itemStyle: {
                            normal: {
                                areaColor:'#4BA4E4',//区域颜色
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
                ],
                series: [
                    {
                        type: "map",
                        // 跟geo关联
                        geoIndex: 0,
                        // 设置人口的数据  用于显示在地图上
                        data: [],
                    },
                ],
            }
        }
    },
    computed: {

    },
    created() {
        // this.$nextTick(() => {
        //     this.init()
        // })
    },
    methods: {
        init() {
            this.initCharts('china', '全国',0,0);
        },
        initCharts(name, JsonName,areaId=0,type) {
            this.charts = this.$echarts.init(this.$refs["charts"]);
            console.log(name, JsonName)
            this.option.geo[0].map =  name;
            this.option.geo[0].center = undefined;
            getAllOrgMap({
                type:type,
                areaId:areaId
            }).then((e)=>{
                this.dataList= e.data;
            }).then(()=>{
                this.option.series[0].data=this.dataList;
                // 地图注册，第一个参数的名字必须和option.geo.map一致
                this.$echarts.registerMap(name, provinces[JsonName])
                this.charts.setOption(this.option);
                this.charts.on("mouseover", function () { //取消鼠标移入地图区域高亮
                });
                this.charts.on("click", function (e) { //取消鼠标移入地图区域高亮
                });
            })
        },
    }
}
</script>
<style lang="scss" scoped>
.box {
    border: 1px solid transparent;
    // padding-top: 5px;
    margin: 5px;
    padding-top: 10px;
    .title {
        font-size: 15px;
        font-weight: 500;
        padding-left: 10px;
        color: #FFFFFF;
        &::before{
            display: inline-block;
            content:'';
            width: 8px;
            height: 8px;
            transform: rotate(45deg);
            margin-right: 10px;
            border: 2px solid #8BD2E6;
        }
    }
}
</style>
