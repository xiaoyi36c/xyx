<template>
  <div class="LogoTitleBox">
    <div class="left">
      <span @click.stop="chinaFun('china')">全国</span>
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
          <el-select 
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
    <div class="center">信易学机构行业数据大屏</div>
    <el-image class="bg" :src="require('@/assets/home/title_logo_bg.png')"></el-image>
    <div class="right">
      <!-- <div class="two">
        <span>7°C</span>
        <span>多云</span>
      </div>
      <el-divider direction="vertical"></el-divider> -->
      <div class="two">
        <span>{{CurrTime}}</span>
        <span>{{NowDate}}</span>
      </div>
      <el-divider direction="vertical"></el-divider>
      <div class="ava">
         <el-image :src="require('@/assets/home/logo_ava.png')"></el-image>
         <span>{{uName}}</span>
      </div>
      <el-divider direction="vertical"></el-divider>
      <div @click="Exit">
         <el-image :src="require('@/assets/home/loginout.png')"></el-image>
      </div>
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
            return `${dateFun.getFullYear()}年${dateFun.getMonth() + 1}月${dateFun.getDate()}日`
        },
        NowTime(){
            return ` 星期${weekList[new Date().getDay()] }`
        },
        uName(){
          return window.sessionStorage.getItem('uName')
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
  methods: {
        time(){
            setInterval(() => {
                this.CurrTime =  ` ${new Date().getHours()+':'+new Date().getMinutes()+':'+new Date().getSeconds()} 星期${weekList[new Date().getDay()]}`
                // console.log( this.CurrTime)
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
        },
    Exit() {
      this.$confirm('是否退出登录？').then((res)=>{
        if(res){
            this.$store.commit('LoginOut');
            this.$router.push('/login')
        }
      })

    },
  }
}
</script>
<style lang="scss" scoped>
.right{
  color:#fff;
  position: absolute;
  right:100px;
  top:20px;
  font-size: 15px;
  display: inline-flex;
  align-items: center;
  width: fit-content;
  padding-right: 15px;
  .el-divider{
    background:#84F2FF;
    width: 2px;
  }
  .el-image{
    width:35px;
    height: 35px;
  }
  >div{
    display: inline-flex;
    align-items: center;
    margin-left:15px;
    font-size: 16px;
    cursor: pointer;
  }
  .two{
    flex-direction: column;
    color: #84F2FF;
    span:nth-child(2){
      font-size: 12px;
     
    }
  }
  .ava{
    span{
      color: #84F2FF;
      font-size: 12px;
      margin-left: 8px;
    }
  }
}
 .selectArea{
    width:200px;
    display: inline-block; 
    line-height: 32px;
    ::v-deep .el-input__suffix{
        display: none;
    }
    ::v-deep .el-input__inner{
        padding: 0;
        text-align: center;
        background: transparent;
        background: url('~@/assets/home/tab_bg.png');
        color:#fff;
        border:0;
        font-weight: 600;
        color: #FFFFFF;
        background-size: cover;
        background-position-y:-1px;
    }
    ::v-deep .el-input__inner::placeholder{
      color:#fff;
    }
 }
.left{
  color:#fff;
  position: absolute;
  left: 0;
  top:18px;
  font-size: 15px;
  display: inline-flex;
  align-items: center;
  span{
    width:200px;
    display: inline-block; 
    cursor: pointer;
    height: 33px;
    font-weight: 600;
    color: #FFFFFF;
    line-height: 35px;
    text-align: center;
    background-position-y:-1px;
    background: url('~@/assets/home/tab_bg_r.png') no-repeat;
    background-size: cover;
  }
}
.LogoTitleBox {
  height: 60px;
  position: relative;

  .bg {
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    z-index: -1;
  }

  .center {
    color: #fff;
    text-align: center;
    font-weight: bold;
    font-size: 30px;
    line-height: 45px;
    // word-spacing: 5px;
    letter-spacing: 8px;
  }
}
</style>