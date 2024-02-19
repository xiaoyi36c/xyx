<template>
  <div class="Box">
    <div class="content">
      <Tops />
      <div class="ContBox">
        <!-- 内容体 -->
        <!-- <img class="img" src="@/assets/logoBg.png"> -->
        <!-- <div class="img"></div> -->
        <div class="ItemBox">
           <h3>找回密码</h3>
           <div class="LoginBox" >
             <div class="SendBox">
                <input type="text" v-model="InfoObj.uTel"  placeholder="请输入您的手机号" />
                <span @click="GetCode">{{Timer==60?'发送':Timer+'s'}}</span>
             </div>
            <input type="text" v-model="InfoObj.uCode"  placeholder="请输入短信验证码(区分大小写)" />
            <input type="password" v-model="InfoObj.uPass"  placeholder="请输入新登录密码" />
           </div>
          <!-- 登录按钮 -->
         <div>
          <el-button class="nextBtn" style="margin-left:12px" @click="ctu()">立即找回</el-button>
        </div>
        </div>
        <div id="DemoT"></div>
      </div>
    </div>
  </div>
</template>
<script>
import Tops from "../components/heardTop/Tops";
import { UserLogin ,RetrievePasswordforSMS ,SetNewPassWord} from "../requestPub/api"; // 导入我们的api接口
 var myCaptcha;
export default {
  components: {
    Tops,
  },
  data() {
    return {
      setTimer:null,
      Timer:60,
      InfoObj: {
        uTel:'',
        uCode:'',
        uPass:'',
        token:''
      },
    };
  },
    mounted(){
    this.readyCut();
  },
  methods: {
        isTel(n){
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        return myreg.test(n);
    },
    readyCut(){
      var _this= this;
      myCaptcha = _dx.Captcha(document.getElementById('DemoT'), {
          appId: this.$appids, //appId，在控制台中“应用管理”或“应用配置”模块获取
          style: 'popup',
          success: function (token) {
            myCaptcha.hide();
            _this.InfoObj.token =token;
            _this.BtnSuc()
          }
        })
    },
    ctu(){
       if(!this.InfoObj.uTel || !this.isTel(this.InfoObj.uTel)){
            return this.$message({message:'请正确填写手机号'})
      }
        if(!this.InfoObj.uPass){
            return this.$message({message:'请填写登录密码'})
      }
      myCaptcha.reload();
      myCaptcha.show()
    },
    GetCode(){
      if(this.Timer<60){
        return this.$message({message:'请稍后在试',type:'warning'})
      }
      if(this.InfoObj.uTel && this.InfoObj.uTel.length==11){
        RetrievePasswordforSMS({
          phone:this.InfoObj.uTel
        }).then((e)=>{
          this.$message({message:'发送成功',type:'success'})
          this.setTimer = setInterval(()=>{
              if(this.Timer-- <= 0){
                this.Timer=60;
                clearInterval(this.setTimer);
              }
          },1000)
        }).catch((er)=>{

        })
      }else{
        this.$message({
          message:'请正确填写手机号',
          type: "error",
        })
      }
    },
    BtnSuc(){
        if(!this.InfoObj.uCode || !this.InfoObj.uPass || !this.InfoObj.uTel){
          return this.$message({message:'请正确填写'+(!this.InfoObj.uCode?'短信验证码':(!this.InfoObj.uTel?'手机号':(!this.InfoObj.uPass?'登录密码':''))),type:'warning'})
        }
        SetNewPassWord({
          phone:this.InfoObj.uTel,
          passWord:this.InfoObj.uPass,
          code:this.InfoObj.uCode,
          token:this.InfoObj.token
        }).then((e)=>{
          if(e.Status==200){
            this.$message({message:'找回成功',type:'success'});
            setTimeout(()=>{
              this.$router.replace('/login');
            },1000)
          }
        }).catch((er)=>{})
    }
  }
};
</script>
<style lang="scss" scoped>
.ContBox {
  text-align: center;
  height: 60%;
  width:100%;
  display: inline-flex;
//   align-items: center;
  .img{
      width:33%;    
  }
  .nextBtn {
    padding: 8px 50px;
    font-size: 14px;
    margin-top: 12px;
    background: linear-gradient(105deg, #55BAFE, #3072FF);
    color: #fff;
    border-radius: 40px;
  }
  .titleBox {
    padding: 20px 15%;
    padding-top: 5%;
  }
  .ItemBox {
    background: #fff;
    padding-top: 6%;
    text-align: center;
    flex: 1;
    h3{
        letter-spacing: 10px;
        font-size: 30px;
        margin-bottom:30px;
    }
    .tipsBox{
        width:50%;
        display: inline-flex;
        font-size: 12px;
        justify-content: space-between;
        margin-bottom:15px;
        span{
            color: #999999;
            transform: scale(0.9);
        }
        span:hover{
            cursor: pointer;
            text-decoration: underline;
        }
    }
    .LoginBox {
      width: 50%;
      margin: 0 auto;
            .SendBox{
        position: relative;
        span{
         position: absolute;
        font-size: 13px;
        color: #fff;
        background: #4ac997;
        white-space: nowrap;
        top: 13px;
        padding: 2px 10px;
        border-radius: 30px;
        right: 0;
        transform: scale(0.9);
        }
        span:hover{
          cursor: pointer;
         background:rgba(245, 166, 41, 1)
        }
      }
      input {
        border: 0px;
        width: calc(100% - 40px);
        padding: 15px 10px;
        border-bottom: 1px solid #e5e5e5;
        outline: none;
        cursor: pointer;
        margin-bottom: 20px;
      }
      input:nth-child(1) {
        padding-left: 30px;
        background: url(../assets/step2.png) left no-repeat;
        background-size: 18px;
      }
      input:nth-child(2) {
        padding-left: 30px;
        background: url(../assets/step2-2.png) left no-repeat;
        background-size: 19px;
      }
       input:nth-child(3) {
        padding-left: 30px;
        background: url(../assets/step2-3.png) left no-repeat;
        background-size: 18px;
      }
    }
  }
}
</style>