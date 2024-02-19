<template>
  <div id="app" class="Box">
    <!-- <Tops/> -->
    <router-view />
  </div>
</template>

<script>
function testJson(obj) {
  console.log(obj,'==============')
      sessionStorage.setItem('pro', obj.pro)
      sessionStorage.setItem('city', obj.city)
}
import Tops from "@/components/heardTop/Tops";
export default {
  name: 'app',
  components:{
    Tops
  },
  mounted(){
    console.log(window.innerHeight)
  },
}
</script>

<style lang="scss">
// 去掉地图logo
.amap-logo,.amap-copyright,.BMap_cpyCtrl,.anchorBL{
    display: none!important;
}
.BMap_bubble_content,.BMap_bubble_center{
  height:35px!important;
}
.BMap_bubble_pop>img{
    height: 45px!important;
    left: 88px!important;
    bottom: -30px!important;
    top: 67px!important;
}
.BMap_bubble_title{
  text-overflow: ellipsis!important;
  width:190px!important;
}
body{
    margin: 0 auto;
    padding: 0;
    font-family: "Microsoft YaHei";
    height: 1080px;
    display: inline-flex;
    align-items: center;
    width: 100vw;
    background: url('~@/assets/home/bg.png');
}
#app {
  height: 100%;
  // width:100vw;
  // max-height: 1080px;
  max-height: 1080px;
  margin: 0 auto;
  overflow: hidden;
  overflow-y: auto;
  min-width: 1920px;

}
// ----------修改elementui表格的默认样式-----------
.el-table__body-wrapper {
  &::-webkit-scrollbar { // 整个滚动条
    width: 0; // 纵向滚动条的宽度
    background: rgba(213,215,220,0.3);
    border: none;
  }
  &::-webkit-scrollbar-track { // 滚动条轨道
    border: none;
  }
}

// --------------------隐藏table gutter列和内容区右侧的空白 start
.el-table th.gutter{
  display: none;
  width:0
}
.el-table colgroup col[name='gutter']{
  display: none;
  width: 0;
}

.el-table__body{
  width: 100% !important;
}

// --------------------隐藏table gutter列和内容区右侧的空白 end
</style>
