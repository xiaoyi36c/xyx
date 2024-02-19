<template>
  <div class="Box">
    <div class="content">
        <div class="TopBox">
        <img src="@/assets/logotxt.png" alt="">
      </div>
      <div class="ContBox">
          <!-- <div class="title">立案须知</div> -->
            <div class="Step1 Step4" >
             <img src="@/assets/step4.png" alt="">
             <p>仅支持ie版本11以上，请升级版本或更换浏览器访问</p>
          </div>
      </div>
      <!--  -->
    </div>
  </div>
</template>
<script>
import Tops from "../components/heardTop/Tops";
export default {
  components: {
    Tops,
  },
  data() {
    return {
      active: 1,
      Content:'',//内容
    }
  },
  mounted(){

  },
  methods: {

  },
};
</script>
<style lang="scss" scoped>
      .TopBox{
        margin-top: 5%;
         width:100%;
        color:#fff;
        align-items: baseline;
        display: inline-flex;
        justify-content: space-between;
        align-items: center;
        img{
          width: 40%;
        }
    }
.ContBox {
  background: #fff;
  text-align: center;
  padding: 30px;
  margin-top:15px!important;

  .title{
      font-size: 16px;
      font-weight: 500;
      color: #333333;
      text-align: left;
      margin-bottom: 20px;
      letter-spacing: 2px;
  }
  .title::before{
      content:'|   ';
      font-weight: bold;
      color:#3A4FF6;
  }
  .txt{
      text-align: left;
      p{
          color:rgba(51, 51, 51, 1);
          font-size: 15px;
          font-weight: inherit;
          padding: 2px 0;
          letter-spacing: 2px;
          margin: 10px 0;
      } 
      img{
        padding: 8% 0 ;
        width: 100%;
      }
  }
  .BtnBox{
      text-align: right;
  }
}
</style>