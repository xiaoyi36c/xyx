<template>
    <div class="box">
        <div class="title">行业分析</div>
        <div class="tabBox">
            <span @click.stop="activeName=1,handleClick()" :class="activeName==1?'act':''">健康指数</span>
            <span @click.stop="activeName=2,handleClick()" :class="activeName==2?'act':''">客诉率</span>
            <span @click.stop="activeName=3,handleClick()" :class="activeName==3?'act':''">客诉数</span>
            <span @click.stop="activeName=4,handleClick()" :class="activeName==4?'act':''">评分</span>
        </div>
        <div class="content">
            <echarts height="240px" width="100%" :baseOptions="options"></echarts>
        </div>
    </div>
</template>
<script>
import echarts from '@/components/echarts/index'
import { getIndutryList } from "@/requestPub/api";
export default {
    components: {
        echarts
    },
    computed: {

    },
    data() {
        return {
            activeName: '1',
            options: {
                color: [
                {
								type: 'linear',
								// x2=1,y=0,柱子的颜色在水平方向渐变
								x2: 0,
								y2: 1,
								colorStops: [
									// 0%处的颜色
									{
										offset: 0,
										color: '#32B5FF',
									},
									// 50%处的颜色
									// 100%处的颜色
									{
										offset: 1,
										color: '#1774FF ',
									},
								],
								global: false // 缺省为 false
							},
                ],
                legend: {
                    // x: "left", //可设定图例在左、右、居中
                    y: "bottom", //可设定图例在上、下、居中
                    align: "left", //字在图例的左边或右边【left/right】
                    orient: "horizontal", //图例方向【horizontal/vertical】
                    icon: "roundRect", //图例形状【circle\rect\roundRect\triangle\diamond\pin\arrow\none】
                    itemGap: 5,
                    itemHeight:10,
                    itemWidth:11,
                    textStyle: {
                        color:'#596E88',
                    },
                    data:['行业分析'],
                },
                // backgroundColor: '#031a40',
                grid: {
                    left: "2%",
                    right: "5%",
                    bottom: "10%",
                    top: "5%",
                    containLabel: true,
                },
                tooltip: {
                    show: true,
                    formatter: "{b}<br/>{c}",
                    textStyle: {
                        fontWeight: "bold",
                        fontSize: 12,
                    },
                },
                yAxis: [{
                    type: "category",
                    inverse: true,
                    barCategoryGap:5,
                    axisLabel: {
                        show: true,
                        color: "#3F89AE",
                        fontSize: 12,
                        textStyle: {
                            color: "#3F89AE",
                            fontSize: 12,
                        },
                    },
                    splitLine: {
                        show: false,
                        lineStyle: {
                            color: "#3F89AE",
                        },
                    },
                    axisLine: {
                        show: true,
                        lineStyle: {
                            color: "#707070",
                            // width: 1,
                        },
                    },
                    axisTick: {
                        show: false,
                    },
                    data: []
                },],
                xAxis: [{
                    type: "value",
                    axisTick: {
                        show: false,
                    },
                    max: 100,

                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: "#035B73",
                            width: 1,
                        },
                    },
                    axisTick: {
                        show: true,
                        lineStyle: {
                            color: "#035B73",
                            width: 1,
                        },
                    },
                    splitLine: {
                        show: true,
                        lineStyle: {
                            type:'dashed',
                            color: "#3F89AE",
                        },
                        
                    },
                    nameTextStyle: {
                        color: 'red',
                    },
                    // nameLocation: "end",
                    name: "",
                    axisLabel: {
                        inside: false,
                        textStyle: {
                            color: "#3F89AE",
                            fontSize: 12,
                        },
                        interval: 0,
                        // rotate: 25,
                        formatter: '{value}'
                    },
                },],
                series: [ {
                    name:'行业分析',
                    type: "bar",
                    stack: "1",
                    barWidth: 12,
                    barMargin:5,
                    // barBorderRadius: 30,
                    label: {
                            normal: {
                                show: true,
                                position: "right",
                                formatter: function (data) {
                                    return '{a1|' + data.value + '}';
                                },
                                rich: {
                                    a1: {
                                        color: '#A6CDFF',
                                        fontSize: 12,
                                        fontFamily: 'DIN',
                                        fontWeight: 'bold'
                                    },
                                }
                            },
                        },
                    // zlevel: 2,
                    data: []
                },
                ],
            },
            type:'',
            regionId:'',
        }
    },
    mounted() {
        // this.initData()
    },

    methods: {
        initData(type = 1,regionId=0){
            this.type=type;
            this.regionId=regionId;
            getIndutryList({
                type,
                regionId,
                kind:this.activeName
            }).then(e=>{
                this.options.yAxis[0]['data']=e.data.title
                this.options.series[0]['data']=e.data.count
                this.options.legend.data=[['健康指数(%)','客诉率(%)','客诉数','评分'][this.activeName-1]]
                this.options.series[0]['name']=['健康指数(%)','客诉率(%)','客诉数','评分'][this.activeName-1]
            })
        },
        handleClick(tab, event) {
            this.initData( this.type, this.regionId)
        }
    }
}
</script>
<style lang="scss" scoped>

.tabBox {
        border: 1px solid #2F778D;
        width: fit-content;
        margin: 10px 0;
        margin-left: 5px;
        span {
            display: inline-block;
            width:100px;
            margin-right: 10px;
            padding:1px 5px;
            font-size: 12px;
            line-height: 20px;
            color:#2F778D;
            text-align: center;
            cursor: pointer;
        }
        .act{
            transition: all .3s;
            background: linear-gradient(0, #145C77 0%, rgba(9,54,85,0) 100%);
            color:#84F2FF;
            font-size: 14px;
        }
    }

.box {
    border: 1px solid transparent;
    // padding: 5px;
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

    .content {
        width: calc(100% - 10px);
        padding: 5px;

        .contTable {
            overflow: hidden;
            // overflow-x: hidden;
            /* 设置溢出滚动 */
            white-space: nowrap;
            // overflow-y: scroll;
            /* 隐藏滚动条 */
            scrollbar-width: none;
            /* firefox */
            -ms-overflow-style: none;
            /* IE 10+ */


        }

        .contTable::-webkit-scrollbar,
        .el-table__body-wrapper::-webkit-scrollbar {
            display: none;
            overflow: hidden;
            /* Chrome Safari */
        }
    }

    .item {
        padding: 8px;
    }
}
</style>