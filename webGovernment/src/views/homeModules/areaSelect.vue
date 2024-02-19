<template>
    <div class="selectBox">
        <div class="selectBoxChooise">
          <el-button size="small" :type="funType == 'china' ?'primary' :''" @click.stop="chinaFun('china')">全国</el-button>
          <!-- <el-button size="small" :type="funType == 'province' ? 'primary' :''" @click.stop="chinaFun('province')">省</el-button> -->
          <el-select 
                size="small"
                @change="chinaFun('province')" 
                class="selectArea" 
                v-model="provinceVal" 
                filterable 
                placeholder="请选择"
                :class="funType=='province'?'selectAreaAct':''"
            >
                <el-option
                v-for="item in mapData"
                :key="item.id"
                :label="item.name"
                :value="item.name">
                </el-option>
          </el-select>
          <!-- <el-button size="small" :type="funType == 'market' ? 'primary' :''" v-if="funType =='china' || funType=='market'" @click.stop="chinaFun('market')">市</el-button> -->
          <el-select 
                v-if="provinceVal" 
                size="small" 
                class="selectArea" 
                @change="chinaFun('market')" 
                :class="funType=='market'?'selectAreaAct':''"
                style="margin: 0;" 
                v-model="cityVal" 
                filterable 
                placeholder="请选择"
            >
                <el-option
                v-for="(item,val) in cityJson"
                :key="item.id"
                :label="item.name"
                :value="item.sname">
                </el-option>
          </el-select>
        </div>
        <div class="selectBoxTime">
            <div>信易学数据可视化平台</div>
            <div>{{NowDate + CurrTime }}</div>
        </div>
    </div>
</template>
<script>
const dateFun = new Date()
const weekList =['天','一','二','三','四','五','六']
import {getAreaList} from '@/requestPub/api'
export default{
    computed:{
        NowDate(){
            return `${dateFun.getFullYear()}年${dateFun.getMonth()}月${dateFun.getDay()}日`
        },
        NowTime(){
            return ` 星期${weekList[new Date().getDay()] }`
        }
    },
    data() {
        return {
            cityJson:[],
            mapData:[],
            funType:'china',
            cityVal:'',
            provinceVal: '',
            CurrTime:''
        }
    },
    mounted(){
        //获取省份
        getAreaList({
                type:1,
                regionId:0
            }).then(e=>{
                this.mapData = e.data;
            })
            this.time();
    },
    methods:{
        time(){
            setInterval(() => {
                this.CurrTime =  ` ${new Date().getHours()+':'+new Date().getMinutes()+':'+new Date().getSeconds()} 星期${weekList[new Date().getDay()]}`
                console.log( this.CurrTime)
            }, 1000);
        },
        getcityInfo(regionId){
            getAreaList({
                type:2,
                regionId:regionId
            }).then(e=>{
                this.cityJson = e.data;
            })
        },
        chinaFun(e){
            let cityId='',provinceId='';
            this.funType =e;
            if(e=='china'){
                this.cityVal=''
                this.provinceVal= ''
                this.cityJson=[]
            }
            if(e=='province'){
                this.cityVal='';
                provinceId = this.mapData.filter(e=>e.name == this.provinceVal)[0]['id'];
                this.getcityInfo(provinceId)
            }
            if(e=='market'){
                cityId = this.cityJson.filter(e=>e.sname == this.cityVal)[0]['id'];
            }
            this.$emit('chinaFun',{
                funType:e,
                provinceId:provinceId || '0',
                cityId:cityId || '0',
                cityVal:this.cityVal,
                provinceVal:this.provinceVal
            })
        }
    }
}
</script>
 <style lang="scss" scoped>
 .selectArea{
    width:80px;
    margin: 0 5px;
    border-radius: 3px;
    ::v-deep .el-input__suffix{
        display: none;
    }
    ::v-deep .el-input__inner{
        padding: 0;
        text-align: center;
        background: #fff;
        color:#333;
        border:0;
        border-radius: 3px;
    }
 }
 .selectAreaAct{
    background-color: #409EFF;
    color:#fff;
    ::v-deep .el-input__inner{
        padding: 0;
        background-color: #409EFF;
        text-align: center;
        color:#fff;
        border:0;
        border-radius: 3px;
    }
 }
.selectBox{
    display: inline-flex;
    width:100%;
    padding: 5px;
    .selectBoxChooise{
        width: 350px;
    }
    >.selectBoxTime{
        text-align: center;
        width: 100%;
        color:#fff;
        >div{
            font-weight: bold;
            // margin-top: 10px;
        }
        div:first-child{
            font-size:18px;
            color:rgba(250, 212, 0, 1);
        }
        div:last-child{
            padding-top: 10px;
            font-size:18px;
        }
    }
}
</style>