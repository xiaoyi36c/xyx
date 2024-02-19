<template>
    <div>
        <div  :ref="ids" :style="styles"></div>
    </div>
</template>
<script>

export default {
    props: {
        ids: { type: String, default: 'echartsMain' },
        width:{type: String, default: '600px' },
        height:{type: String, default: '400px' },
        baseOptions: { type: Object, default: () => { } }
    },
    computed: {
        styles() {
            return {
                width: this.width,
                height: this.height
            }
        },
        defaultOptions() {
            return {
                // title: {
                //     text: "ECharts 入门示例",
                // },
                tooltip: {},
                // legend: {
                //     data: ["销量"],
                // },
                xAxis: {
                    data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"],
                },
                yAxis: {},
                series: [
                    {
                        name: "销量",
                        type: "bar",
                        data: [5, 20, 36, 10, 10, 20],
                    },
                ],
            };
        }
    },
    data() {
        return {
            myChart:null
        }
    },
    watch:{
        baseOptions:{
            handler(){
                this.init()
            },
            deep:true
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.init()
        });
    },
    methods: {
        resize(){
            this.myChart.setOption(this.baseOptions)
        },
        init() {
            // 基于准备好的dom，初始化echarts实例  这个和上面的main对应
            // console.log(this.$refs[this.id],'===',echarts)
            this.myChart = this.$echarts.init(this.$refs[this.ids]);
            this.setOptions()
            window.addEventListener('resize', () => {
                this.myChart.resize();
            });
        },
        setOptions(){
            // 使用刚指定的配置项和数据显示图表。
            this.myChart.setOption(this.baseOptions || this.defaultOptions);
        }
    }
}
</script>