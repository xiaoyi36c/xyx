<template>
    <div class="box">
        <div class="title">累计数据</div>
        <div class="content">
            <div class="left">
                <div class="CountItem" v-for="(e, idx) in weekData" :key="idx">
                    <span>{{ idx }}</span>
                    <span>{{ e }}</span>
                </div>
            </div>
            <div class="right">
                <div class="item">
                    <div class="leftTitle">信用分分布情况</div>
                    <echarts height="223px" width="100%" :baseOptions="optionsLevel"></echarts>
                </div>
                <div class="item">
                    <div class="leftTitle">评价分布情况</div>
                    <echarts height="223px" ids="optionsScore" width="100%" :baseOptions="optionsScore"></echarts>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import echarts from '@/components/echarts/index'
import {getCreditGroup,getUserAppraise,getOrgCreditCount} from '@/requestPub/api'
export default {
    data() {
        return {
            //累计数据抓取统计
            weekData: [],
            optionsLevel: {
                color: ["#944EFF", "#01B3FF", "#FFD201", "#236BD7","#FE5454"],
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c}家",
                },
                toolbox: {
                    show: true,
                },
                legend: {
                    x: "left", //可设定图例在左、右、居中
                    y: "bottom", //可设定图例在上、下、居中
                    align: "left", //字在图例的左边或右边【left/right】
                    orient: "vertical", //图例方向【horizontal/vertical】
                    icon: "circle", //图例形状【circle\rect\roundRect\triangle\diamond\pin\arrow\none】
                    itemGap: 10,
                    itemWidth:10,
                    textStyle: {
                        color:'#fff',
                        rich: {
                            name: {
                                color: "#ffff",
                                fontSize: 9,
                            },
                            value: {
                                color: "#ffff",
                                fontSize: 9,
                            },
                        },
                    },
                },
                series: [ {
                        name: "信用分",
                        type: "pie",
                        radius: ['60%', '85%'],
                        center: ["50%", "50%"],
                        label: {
                            normal: {
                                show: true,
                                position: "inside",
                                formatter: "{c}",
                                textStyle: {
                                    align: "center",
                                    baseline: "middle",
                                    fontSize: 12,
                                    fontWeight: "100",
                                    color: "#fff",
                                },
                            },
                        },
                        labelLine: {
                            show: true,
                        },
                        data: [],
                    }],
            },
            optionsScore: {
                color: ["#FE5454", "#944EFF", "#01B3FF", "#FFD201", "#236BD7"],
                tooltip: {
                    trigger: "item",
                    formatter: "{a} <br/>{b} : {c}条",
                },
                toolbox: {
                    show: true,
                },
                legend: {
                    x: "left", //可设定图例在左、右、居中
                    y: "bottom", //可设定图例在上、下、居中
                    align: "left", //字在图例的左边或右边【left/right】
                    orient: "vertical", //图例方向【horizontal/vertical】
                    icon: "circle", //图例形状【circle\rect\roundRect\triangle\diamond\pin\arrow\none】
                    itemGap: 10,
                    itemWidth:10,
                    textStyle: {
                        color:'#fff',
                        rich: {
                            name: {
                                color: "#ffff",
                                fontSize: 9,
                            },
                            value: {
                                color: "#ffff",
                                fontSize: 9,
                            },
                        },
                    },
                },
                series: [ {
                        name: "评价分布",
                        type: "pie",
                        radius: ['60%', '85%'],
                        center: ["50%", "50%"],
                        label: {
                            normal: {
                                show: true,
                                position: "inside",
                                formatter: "{c}",
                                textStyle: {
                                    align: "center",
                                    baseline: "middle",
                                    fontSize: 12,
                                    fontWeight: "100",
                                    color: "#fff",
                                },
                            },
                        },
                        labelLine: {
                            show: true,
                        },
                        data: [],
                    }],
            }
        }
    },
    components: {
        echarts
    },
    methods: {
        initData(type=1,regionId = 0){
            getOrgCreditCount({
                type,
                regionId
            }).then(e=>{
                this.weekData = e.data
            })
            // type[类型:1,2,3: 省市区]，regionId[地区id 
            getCreditGroup({
                type,
                regionId
            }).then(e=>{
                this.optionsLevel['series'][0]['data'] = e.data
               
            })
            getUserAppraise({
                type,
                regionId
            }).then(e=>{
                 this.optionsScore['series'][0]['data'] = e.data
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
    top: 10px;

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

        .left {
            height: 100%;
            display: inline-flex;
            flex-wrap: wrap;
            width: 100%;

            .CountItem {
                width: calc(100% / 3 - 12px);
                margin:3px 5px 10px 5px;
                // border: 1px solid #ccc;
                background-repeat: no-repeat;
                background-size: 100% 100%;
                // background: #03142A;
                background-image: url('~@/assets/itemBg.png');
                // box-shadow: inset 0px 0px 30px 1px rgba(75,164,228,1);
                // border: 1px solid #00D5F0;
                text-align: center;
                padding: 15px 0;
                color: #fff;
                span {
                    display: block;
                    color: #3F89AE;
                    font-size: 13px;
                }

                span:last-child {
                    color:#00DDFF;
                    font-weight: bold;
                    margin-top: 5px;
                    font-size: 20px;
                }
            }

            .CountItem:nth-child(1),
            .CountItem:nth-child(2),
            .CountItem:nth-child(3) {
                margin-top: 8px;
            }
        }

        .right {
            width: 100%;
            display: inline-flex;
            text-align: center;
            margin-top: 2px;
            .item {
                flex: 1;
                .leftTitle {
                    font-size: 14px;
                    font-weight: bold;
                    color:#fff;
                }
            }
        }
    }


}
</style>