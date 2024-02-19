<template>
    <div class="chooiseBox">
        <div class="cityBox">
            <span class="title">{{$route.query.cityName || '全国'}}</span> <el-divider direction="vertical"></el-divider>
            <span class="dre">选择城市</span>
            <!-- <el-select v-model="value" placeholder="请选择" size="small" style="width: 200px;margin:0 10px 0 30px;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select> -->
            <!-- <el-select v-model="value" placeholder="请选择" size="small" style="width: 200px;margin:0 10px 0 30px;">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select> -->
            <el-cascader
                size="small"
                style="width: 200px;margin:0 10px 0 30px;"
                v-model="cityVal"
                :options="options"
                :props="{
                label:'name',
                value:'id',
                }"
                ref="cascader"
                @change="handleCityChange"
            ></el-cascader>
        </div>
        <div class="cityHot">
            <span class="title">热门城市：</span>
            <div>
                <span v-for="(item,i) in hotCity" :key="i" @click="hotCityChange(item)">{{item.name}}</span>
            </div>
        </div>
    </div>
</template>
<script>
import {getRegionMap } from '@/requestPub/api'
export default {
    data() {
        return {
            options: [
                // {
                //   value: 'zhinan',
                //   label: '四川省',
                //   children: [
                //     {
                //       value: 'shejiyuanze',
                //       label: '成都市',
                //     }
                //   ]
                // }
            ],
            hotCity:[
                    {
                        name:'成都',
                        cityVal:[510000, 510100]
                    },
                    {
                        name:'上海',
                        cityVal:[310000, 310100]
                    },
                    {
                        name:'深圳',
                        cityVal:[440000, 440300]
                    },
                    {
                        name:'广州',
                        cityVal:[440000, 440100]
                    },
                    {
                        name:'天津',
                        cityVal:[120000, 120100]
                    },
                    {
                        name:'北京',
                        cityVal:[110000, 110100]
                    }
            ],
            cityVal:[],
            cityName:'',
        }
    },
    mounted(){
        //城市列表
        getRegionMap().then(res=>{
            this.options= res.rows;
            if(this.$route.query?.cityVal){
                console.log(this.$route.query?.cityVal.split(','))
                this.$nextTick(()=>{

                    this.cityVal = this.$route.query?.cityVal.split(',').map(e=>Number(e))
                })
                console.log('cityVal======',this.cityVal)
            }
        })
    },
    methods:{
        hotCityChange(item){
            this.cityVal= item.cityVal;
            let query = JSON.parse(JSON.stringify( this.$route.query))
            const queryParam = Object.assign(query,{
                    cityName:item.name,
                    cityVal:item.cityVal.join(),
                    hotCity:true,
            })
            this.changeParam(queryParam)
        },
        changeParam(queryParam){
            let ret = '';
            Object.keys(queryParam).map((v)=>{
                ret += v+'='+queryParam[v] +'&'
            })
            console.log(this.$route.path)
            const newUrl = '/#'+this.$route.path + `?${ret}`
            window.history.replaceState('', '', newUrl);
            this.$emit('change',queryParam)
        },
        handleCityChange(){
            let getCheckedNodes = this.$refs.cascader.getCheckedNodes()[0];
            console.log(getCheckedNodes)
            // let Link =  this.$router.push({ path:'/infoOrgSearch', query: {
            //         cityName:getCheckedNodes.data.name,
            //         cityVal:this.cityVal.join(),
            //     }
            // });
            let query = JSON.parse(JSON.stringify( this.$route.query))
            const queryParam = Object.assign(query,{
                    cityName:getCheckedNodes.data.name,
                    cityVal:this.cityVal.join(),
                    hotCity:'',
            })
            this.changeParam(queryParam)
        }
    }
}
</script>
<style lang="scss" scoped>
.chooiseBox {
    background: #fff;
    border-radius: 5px;
    padding: 15px;
    position: relative;
    z-index: 1;
    margin-top: -80px;

    .cityBox {
        .title {
            font-size: 15px;
            font-weight: 600;
            color: #333333;
            line-height: 25px;
        }

        .dre {
            font-size: 14px;
        }
    }

    .cityHot {
        display: inline-flex;
        width: 100%;
        align-items: flex-start;
        margin-top: 10px;

        .title {
            font-size: 15px;
            font-weight: 600;
            color: #333333;
            line-height: 25px;
            width:90px;
        }

        >div {
            >span {
                font-size: 13px;
                font-weight: 400;
                color: #666666;
                line-height: 22px;
                margin-right: 20px;

                &:hover {
                    cursor: pointer;
                    text-decoration: underline;
                }
            }

            .act {
                color: #3994FF;
            }

        }
    }
}</style>