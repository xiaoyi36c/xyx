<template>
  <div class="Box">
    <Tops  ref="AreaSelect" @chinaFun="chinaFun"/>
    <div class="ContBox">
      <div class="leftBox">
        <div style="height: 60%;display: inline-flex;justify-content: space-between;">
          <div style="width: 49.5%;" class="blockBox">
            <weekData ref="weekData"></weekData>
          </div>
          <div style="width: 49.5%;" class="blockBox">
            <AllData ref="AllData"></AllData>
          </div>
        </div>
        <div style="height:calc(40% - 10px);display: inline-flex;margin-top: 10px;justify-content: space-between;">
          <div style="width:49.5%;" class="blockBox">
            <areaAnlis ref="areaAnlis" v-if="funType != 'market'"></areaAnlis>
            <areaMap ref="areaMap" v-else></areaMap>
          </div>
          <div class="blockBox" style="width:49.5%;">
            <top10 ref="top10"></top10>
          </div>
        </div>
      </div>
      <div class="rightBox">
        <div class="item blockBox">
          <orgListData ref="orgListData"></orgListData>
        </div>
        <div class="item blockBox">
          <IndutryAlins ref="IndutryAlins"></IndutryAlins>
        </div>
        <div class="item blockBox">
          <PersonCount ref="PersonCount"></PersonCount>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Tops from "@/components/heardTop/Tops";
import echarts from '@/components/echarts/index'
import weekData from "./homeModules/weekData.vue";
import AreaSelect from "./homeModules/areaSelect.vue";
import AllData from "./homeModules/allData.vue";
import orgListData from "./homeModules/orgListData.vue";
import IndutryAlins from "./homeModules/indutryAlins.vue";
import PersonCount from "./homeModules/personCount.vue";
import areaAnlis from "./homeModules/areaAnlis.vue";
import top10 from "./homeModules/top10.vue";
import areaMap from "./homeModules/areaMap.vue";
export default {
  components: {
    Tops,
    echarts,
    weekData,
    AreaSelect,
    AllData,
    orgListData,
    IndutryAlins,
    PersonCount,
    areaAnlis,
    top10,
    areaMap
  },
  data() {
    return {
      funType: 'china',
      timer:null,
      refreshObj:{},
    };
  },
  computed: {

  },
  mounted() {
    //初始化，模仿点击出发省市区切换
    this.chinaFun({
      funType: this.funType,
      cityVal: '',
      provinceVal: ''
    })
  },
  methods: {
    setRefresh(){
      if(this.timer){
        clearInterval(this.timer)
      }
      //设置10秒间隔请求
      this.timer = setInterval(()=>{
        this.chinaFun(this.refreshObj)
      },60000)
    },
    chinaFun(obj) {
      /*
        provinceVal : 省份
        cityVal：市
        funType ：右上角切换的状态 
            china：全国地图数据 
            province：省份 
            market：显示地图
      */
     console.log('obj===',obj)
      this.funType = obj.funType;
      this.refreshObj = obj
      this.$nextTick(() => {
        let TypeOrg = {china:0,province:1,market:2};
        let TypeId =['china','province'].includes(obj.funType)?obj.provinceId : obj.cityId ;
        if (obj.funType == 'china') {
          this.$refs['areaAnlis'].init()
        } else if (obj.funType == 'province') {
          this.$refs['areaAnlis'].initCharts(obj.provinceVal, obj.provinceVal,TypeId,TypeOrg[obj.funType])
        } else {
          this.$nextTick(() => {
            this.$refs['areaMap'].AmapLoad(obj.cityVal,obj.cityId,TypeOrg[obj.funType])
          })
        }    
        //累计数据
        this.$refs['AllData'].initData(TypeOrg[obj.funType],TypeId)
        //机构数据
        this.$refs['orgListData'].initData(TypeOrg[obj.funType],TypeId)
        //行业数据
        this.$refs['IndutryAlins'].initData(TypeOrg[obj.funType],TypeId)
        //top10
        this.$refs['top10'].initData(TypeOrg[obj.funType],TypeId)
        //本周数据
        this.$refs['weekData'].initData(TypeOrg[obj.funType],TypeId)

        //人流量
        this.$refs['PersonCount'].initData(TypeOrg[obj.funType],TypeId)

        //定时刷新
        if(this.$store.state.token){
          this.setRefresh()
        }else{
          clearInterval(this.timer)
        }
      })
    },
  },
};
</script>
<style lang="scss" scoped>
.blockBox{
   background: #03142A;
    box-shadow: inset 0px 0px 30px 1px rgba(75,164,228,1);
    border-radius: 0px 0px 0px 0px;
    border: 1px solid #00D5F0;
}
.ContBox {
  height: calc(100% - 60px);
  padding: 0 5px;
  display: flex;
  margin-top: 5px;
  justify-content: space-between;
  .leftBox {
    // flex: 2.5;
    width: 70%;

    >div {
      width: 100%;
      // margin-right: 10px;
    }

    .item {
      padding: 10px;

      .leftTitle {
        width: 100%;
        font-size: 18px;
        font-weight: 400;
        color: #ccc;
        background: url(../assets/jian.png) right center no-repeat;
        background-size: 15%;
        letter-spacing: 2px;
      }

      .line {
        margin: 30px 0;
        width: 40%;
        height: 1px;
        background: #ffffff;
        opacity: 0.2;
      }

    }


  }

  .rightBox {
    width: 29.5%;

    .item {
      margin-bottom: 10px;
    }
    .item:last-child{
      margin-bottom: 0;
    }
  }
}
</style>