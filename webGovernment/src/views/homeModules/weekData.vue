<template>
    <div class="box">
        <div class="title">本周数据</div>
        <div class="content">
            <div class="left">
                <div class="item">
                    <div class="leftTitle">投诉情况</div>
                    <echartsBox height="230px" width="100%" :baseOptions="options"></echartsBox>
                </div>
                <div class="item">
                    <div class="leftTitle">各信用平台数据推送量</div>
                    <echartsBox height="230px" width="100%" :baseOptions="optionsLine"></echartsBox>
                </div>
            </div>
            <div class="right">
                <div class="CountItem" v-for="(e, idx) in weekData" :key="idx">
                    <span>{{ idx }}</span>
                    <span>{{ e }}</span>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import echartsBox from '@/components/echarts/index'
import { tousuApi,tuisongApi,weekTotalApi } from '@/requestPub/api'
export default {
    data() {
        return {
            //本周数据抓取统计
            weekData: [],
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
                            {
								type: 'linear',
								// x2=1,y=0,柱子的颜色在水平方向渐变
								x2: 0,
								y2: 1,
								colorStops: [
									// 0%处的颜色
									{
										offset: 0,
										color: '#FFD685',
									},
									// 50%处的颜色
									// 100%处的颜色
									{
										offset: 1,
										color: '#FC951E',
									},
								],
								global: false // 缺省为 false
							}
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
                        rich: {
                            name: {
                                color: "#ffff",
                                fontSize: 9,
                            },
                            value: {
                                color: "#596E88",
                                fontSize: 9,
                            },
                        },
                    },
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                grid: {
                    top: '15%',
                    left: '2%',
                    right: '2%',
                    bottom: '13%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                        boundaryGap: true,
                        splitLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLine: {
                            show: false,
                            lineStyle: {
                                color: "#3F89AE"
                            }
                        },
                        axisLabel: {
                            margin: 10,
                            textStyle: {
                                fontSize: 12,
                                color: "#3F89AE"
                            }
                        },
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        nameTextStyle: {
                            color: '#fff',
                            fontSize: 12,
                            padding: [0, 0, 0, -60],
                        },
                        splitLine: {
                            show: true,
                            lineStyle: {
                                color: ['rgba(255,255,255,0.2)']
                            }
                        },
                        axisLine: {
                            show: false,
                            lineStyle: {
                                color: "rgba(255,255,255,0.3)"
                            }
                        },
                        nameTextStyle: {
                            color: "#fff",
                            fontSize: 12,
                            lineHeight: 0
                        },
                        axisLabel: {
                            margin: 10,
                            textStyle: {
                                fontSize: 12,
                                color: "#3F89AE"
                            }
                        },
                    }
                ],
                series: [
                    {
                        name: '新增投诉',
                        type: 'bar',
                        barWidth: 20,
                        label: {
                            normal: {
                                show: true,
                                position: "top",
                                formatter: function (data) {
                                    return '{a0|' + data.value + '}';
                                },
                                rich: {
                                    a0: {
                                        color: '#A6CDFF',
                                        fontSize: 12,
                                        fontFamily: 'DIN',
                                        fontWeight: 'bold'
                                    },
                                }
                            },
                        },
                      data: []
                    },
                    {
                        name: '处理投诉',
                        type: 'bar',
                        barWidth: 20,
                        label: {
                            normal: {
                                show: true,
                                position: "top",
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
                        data: []
                    }
                ]
            },
            optionsLine: {
                color:['#279BFF'],
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
                    data:['推送量'],
                },
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        lineStyle: {
                            color: "#3333"
                        }
                    },
                    backgroundColor: "rgba(255,255,255,1)",
                    padding: [5, 10],
                },
                grid: {
                    top: '15%',
                    left: '2%',
                    right: '2%',
                    bottom: '15%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                    axisLabel: {
                        margin: 10,
                        textStyle: {
                                fontSize: 12,
                                color: "#3F89AE"
                            }
                    }
                },
                yAxis: {
                    type: 'value',
                    splitLine: {
                        show: true,
                        lineStyle: {
                            color: ['rgba(255,255,255,0.2)']
                        }
                    },
                    axisLine: {
                        show: false,
                        lineStyle: {
                            color: "rgba(255,255,255,0.3)"
                        }
                    },
                    nameTextStyle: {
                        color: "#DEEBFF",
                        fontSize: 12,
                        lineHeight: 0
                    },
                    axisLabel: {
                        margin: 10,
                        textStyle: {
                                fontSize: 12,
                                color: "#3F89AE"
                            }
                    }
                },
                series: [
                    {
                        name:'推送量',
                        data: [],
                        type: 'line'
                    }
                ]
            }
        }
    },
    components: {
        echartsBox
    },
    methods:{
        initData(type=1,regionId = 0){
            tousuApi({
                type,
                regionId
            }).then((e)=>{
                this.options.series[0]['data'] = e.data.new
                this.options.series[1]['data'] = e.data.over
                
            })
            tuisongApi({
                type,
                regionId
            }).then((e)=>{
                this.optionsLine.series[0]['data'] = e.data
                
            })
            weekTotalApi({
                type,
                regionId
            }).then((e)=>{
                this.weekData = e.data
            })
        }
    }
}
</script>
<style lang="scss" scoped>
.box {
    border: 1px solid transparent;
    position: relative;
    margin: 5px;
    top: 5px;

    .title {
        font-size: 15px;
        font-weight: 500;
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
        display: inline-flex;
        padding: 5px;

        .left {
            flex: 3;
            height: 100%;

            .leftTitle {
                font-weight: 400;
                color: #84F2FF;
                background: linear-gradient(82deg, #1A406D 0%, rgba(128,128,128,0) 100%);
                border-radius: 0px 0px 0px 0px;
                font-size: 15px;
                padding: 5px 0 5px 10px;
            }
        }

        .right {
            flex: 1;
        }
    }

    .item {
        padding: 8px;
    }
}

.CountItem {
    margin-top: 10px;
    // border: 1px solid #ccc;
    text-align: center;
    margin-bottom: 11px;
    padding: 10px 0;
    color: #fff;
    background: #0D243E;
    border-radius: 4px 4px 4px 4px;
    border: 1px solid #3488AC;
    margin-bottom:20px;
    span {
        display: block;
        font-size: 13px;
        font-weight: 400;
        color: #3F89AE;
    }

    span:last-child {

        font-weight: bold;
        margin-top: 5px;
        font-size: 20px;
        font-weight: 500;
        color: #279BFF;
    }
}
</style>