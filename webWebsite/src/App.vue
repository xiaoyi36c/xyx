<template>
  <div id="app">
    <Tops />
    <FunBox v-if="$route.path!='/' && $route.path!='/index'"></FunBox>
    <div class="appContent">
      <router-view />
    </div>
     <FooterBox />
  </div>
</template>

<script>
import Tops from "@/components/heardTop/Tops";
import FunBox from "@/components/heardTop/FunBox";
import FooterBox from "@/components/heardTop/FooterBox";
export default {
  name: 'app',
  components: { Tops ,FunBox,FooterBox}
}
</script>

<style lang="scss">

body {
  margin: 0;
  padding: 0;
}

#app {
  // height: 100vh;
  width: 100%;
  overflow: hidden auto;
  background-color: #F9F9F9;
  min-width: 1200px;
  font-family: 'Microsoft YaHei';
  .appContent{
    margin: 0 auto;
    // width:1200px;
  }
}
$h:32px;
.pagination{
    text-align:center;margin-top: 50px;
}
.el-pagination {
    .btn-prev, .btn-next{
        line-height:$h;
        height: $h;
    }
}
.el-pager li.active{
    color: #409EFF;
    cursor: default;
 
}
.el-pager li{
    margin: 0 5px;
    font-weight: 500;
    line-height:$h;
    height: $h;
}
.el-tabs__item.is-active{
  color: #333333;
  font-weight: 600;
}
.el-tabs__item{
  color:#999999;
}
</style>
