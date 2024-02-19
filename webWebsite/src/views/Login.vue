<template>
  <div class="Box">
    <div class="content">
      <Tops />
      <div class="ContBox">
        <!-- 内容体 -->
        <img class="img" src="@/assets/logoBg.png">
        <!-- <div class="img"></div> -->
        <div class="ItemBox">
           <h3>信易学数据中心</h3>
           <div class="LoginBox">
             <input type="text" v-model="InfoObj.uTel"  placeholder="账号/机构名" />
             <input type="password" v-model="InfoObj.uPass"  placeholder="登录密码" />
          </div>
          <div class="tipsBox">
              <span @click="toResetPass()">忘记密码</span>
              <span @click="toResiger()">没有账号？立即注册</span>
          </div>
          <!-- 登录按钮 -->
         <div>
          <el-button class="nextBtn" style="margin-left:12px" @click="ctu()">立即登录</el-button>
        </div>
        </div>
        <div id="DemoT"></div>
      </div>
    </div>
  </div>
</template>
<script>
import Tops from "../components/heardTop/Tops";
import { UserLogin } from "../requestPub/api"; // 导入我们的api接口
 var myCaptcha;
export default {
  components: {
    Tops,
  },
  data() {
    return {
      InfoObj: {
        uTel: "",
        uPass:'',
        token:'',
      },
    };
  },
  mounted(){
    this.readyCut();
  },
  methods: {
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
    isTel(n){
        var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
        return myreg.test(n);
    },
    ctu(){
       if(!this.InfoObj.uTel || !this.isTel(this.InfoObj.uTel)){
            return this.$message({message:'请正确账号/机构名'})
      }
        if(!this.InfoObj.uPass){
            return this.$message({message:'请填写登录密码'})
      }
      myCaptcha.reload();
      myCaptcha.show()
    },
    toResetPass(){
        this.$router.replace('/resetPass')
    },
    toResiger(){
        this.$router.replace('/reginer')
    },
    BtnSuc(){
      UserLogin({
        loginName: this.InfoObj.uTel,
        passWord: this.InfoObj.uPass,
        token:this.InfoObj.token
      }).then((e)=>{
          myCaptcha.hide();
          this.$message({message:'登录成功!',type:'success'});
          window.sessionStorage.setItem('isLogin',true);
          window.sessionStorage.setItem('uName',e.Data.UserName);
          window.sessionStorage.setItem('utoken',e.Data.Token);
          setTimeout(()=>{
             this.$store.commit('UpLoginStates');
             this.$router.replace('/');
          },500)
      }).catch((er)=>{

      })
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
        margin-bottom:50px;
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
        background: url(../assets/step2-3.png) left no-repeat;
        background-size: 18px;
        margin-bottom: 10px;
      }
    }
  }
}
</style>