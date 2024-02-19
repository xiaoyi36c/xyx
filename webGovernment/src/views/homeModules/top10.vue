<template>
    <div class="box">
        <div class="title">信用良好top10</div>
        <div class="content">
            <template v-if="opitons['series'][0]['data'].length">
                <echarts height="318px" :baseOptions="opitons"></echarts>
            </template>
            <template v-else>
                <el-empty></el-empty>
            </template>
        </div>
    </div>
</template>
<script>
import echarts from '@/components/echarts/index'
import { getTop10 } from '@/requestPub/api'
const colorList =[
    '#ffd201', '#fe5454', '#944eff', '#32cd32', '#01b3ff',
    '#ba55d3', '#10aab4', '#137dff', '#1c5c99', '#1c5c99',
];
export default {
    components: {
        echarts
    },
    data() {
        return {
            opitons: {
                
                // 图表标题
                title: {
                    show: false,//显示策略，默认值true,可选为：true（显示） | false（隐藏）
                    // text: '"2020年11月份第1周各单品被特采次数',//主标题文本，'\n'指定换行
                    x: 'center',        // 水平安放位置，默认为左对齐，可选为：
                    // 'center' ¦ 'left' ¦ 'right'
                    // ¦ {number}（x坐标，单位px）
                    y: 'bottom',             // 垂直安放位置，默认为全图顶端，可选为：
                    // 'top' ¦ 'bottom' ¦ 'center'
                    // ¦ {number}（y坐标，单位px）
                    //textAlign: null          // 水平对齐方式，默认根据x设置自动调整
                    backgroundColor: 'rgba(0,0,0,0)',
                    borderColor: '#ccc',    // 标题边框颜色
                    borderWidth: 0,         // 标题边框线宽，单位px，默认为0（无边框）
                    padding: 0,             // 标题内边距，单位px，默认各方向内边距为5，
                    // 接受数组分别设定上右下左边距，同css
                    itemGap: 10,            // 主副标题纵向间隔，单位px，默认为10，
                    textStyle: {
                        fontSize: 12,
                        fontWeight: 'bolder',
                        color: '#fff'        // 主标题文字颜色
                    },
                    subtextStyle: {
                        color: '#fff'        // 副标题文字颜色
                    }
                },
                // backgroundColor: '#fff',
                tooltip: {},
                animationDurationUpdate: function (idx) {
                    // 越往后的数据延迟越大
                    return idx * 2;
                },
                animationEasingUpdate: 'bounceIn',
                color: ['#fff', '#fff', '#fff'],
                series: [{
                    type: 'graph',
                    layout: 'force',
                    force: {
                        repulsion: 200,
                        edgeLength: 10
                    },
                    roam: true,
                    label: {
                        normal: {
                            show: true,
                            color:'#fff'
                        }
                    },
                    data: []
                }]
            },
            type:'',
            regionId:'',
            dataList:[],    
        }
    },
    computed: {

    },
    mounted() {

    },

    methods: {
        initData(type = 1, regionId = 0) {
            this.type = type;
            this.regionId = regionId;
            getTop10({
                type,
                regionId
            }).then(e => {
                let len = e.data.length
                let ret =[]
                 e.data.map((res,i)=>{
                    ret.push({
                        "name": `No.${i+1} ${res.organization_name}`,
                        "value": res.credit_score,
                        "fixed":false,
                        "symbolSize":60 + 10 * (len - i),
                        "draggable": true,
                        "itemStyle": {
                            format:()=>{
                                return '123'
                            },
                            color:'#fff',
                            "normal": {
                                "shadowBlur": 10,
                                "shadowColor": colorList[i],
                                "color": colorList[i]
                            },
                            textStyle: {
                                color:'#fff',
                            },
                        },


                    })
                })
                this.dataList= ret
            }).then(()=>{
                console.log(this.dataList)
                this.opitons['series'][0]['data'] = this.dataList;
            })
        },
    }
}
</script>
<style lang="scss" scoped>
.box {
    border: 1px solid transparent;
    padding-top: 10px;
    margin: 5px;

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

    .item {
        padding: 8px;
    }
}
</style>