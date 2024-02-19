<template>
    <div class="box">
        <div class="title">人流量</div>
        <div class="content">
            <echarts height="223px" width="100%" :baseOptions="options"></echarts>
        </div>
    </div>
</template>
<script>
import echarts from '@/components/echarts/index'
import {  peopleTotalApi } from "@/requestPub/api";
export default {
    components: {
        echarts
    },
    data() {
        return {
            options: {
                // backgroundColor: 'black',
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
                grid: {
                    left: '2%',
                    right: '2%',
                    top: '15%',
                    bottom: '5%',
                    containLabel: true
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
                    // textStyle: {
                    //     color: "#3333"
                    // }
                },
                xAxis: {
                    type: "category",
                    data: [],
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
                            color: "rgba(255,255,255,0.5)"
                        }
                    },
                    axisLabel: {
                        margin: 10,
                        textStyle: {
                            color: "#3F89AE",
                            fontSize: 12,
                        },
                    }
                },
                yAxis: [
                    {
                        type: "value",
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
                                color: "#3F89AE",
                                fontSize: 12,
                            },
                        }
                    }],
                series: []
            }
        }
    },
    computed: {

    },
    mounted() {

    },

    methods: {
        initData(type = 1,regionId=0){
            this.type=type;
            this.regionId=regionId;
            peopleTotalApi({
                type,
                regionId
            }).then(e=>{
                console.log('e===',e)
                this.options.xAxis['data'] = e.data.month;
                this.options.series=[
                {
                        name: "人流量",
                        type: "line",
                        smooth: true,
                        showSymbol: false,
                        data: e.data.data,
                        itemStyle: {
                            normal: {
                                color: "#00859A"
                            }
                        },
                        areaStyle: {
                            opacity: 0.6,
                        },
                        lineStyle: {
                            width: 0
                        },
                        smooth: true,
                        areaStyle: {}          
                    }
                ]
            })
        },
        handleClick(tab, event) {
            console.log(tab, event);
        }
    }
}
</script>
<style lang="scss" scoped>
.box {
    border: 1px solid transparent;
    // padding: 5px;
    margin: 5px;
    padding-top: 10px;

    .title {
        font-size: 15px;
        font-weight: 500;
        color: #FFFFFF;
        padding-left: 10px;
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

    .tabBox {
        span {
            font-size: 15px;
        }
    }

    .content {
        width: calc(100% - 10px);
        padding: 5px;
    }
}
</style>