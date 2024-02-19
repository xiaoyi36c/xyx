<template>
  <div class="Box">
    <div class="contentLogin">
      <div class="ContBox">
        <!-- 内容体 -->
        <!-- <img class="img" src="@/assets/logoBg.png"> -->
        <!-- <div class="img"></div> -->
        <div class="ItemBox">
          <h3>信易学数据中心</h3>
          <div class="LoginBox">
            <el-input placeholder="登录账号" prefix-icon="el-icon-phone" v-model="InfoObj.uTel">
            </el-input>
            <el-input show-password placeholder="登录密码" prefix-icon="el-icon-key" v-model="InfoObj.uPass"
              @keyup.enter.native="BtnSuc()">
            </el-input>
            <!-- <input type="text" v-model="InfoObj.uTel"  placeholder="手机号" />
             <input type="password" @confirm.enter="BtnSuc()" v-model="InfoObj.uPass"  placeholder="登录密码" /> -->
          </div>
          <!-- <div class="tipsBox">
              <span @click="toResetPass()">忘记密码</span>
              <span @click="toResiger()">没有账号？立即注册</span>
          </div> -->
          <!-- 登录按钮 -->
          <div>
            <el-button class="nextBtn" style="margin-left:12px" @click="BtnSuc()">立即登录</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Tops from "../components/heardTop/Tops";
import { sysLogin } from "@/requestPub/api"; // 导入我们的api接口
export default {
  components: {
    Tops,
  },
  data() {
    return {
      InfoObj: {
        uTel: "",
        uPass: '',
        token: '',
      },
    };
  },
  mounted() {
    console.log(process.env.NODE_ENV,process.env.VUE_APP_BASE_API)
  },
  methods: {
    isTel(n) {
      var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
      return myreg.test(n);
    },
    ctu() {
      if (!this.InfoObj.uTel || !this.isTel(this.InfoObj.uTel)) {
        return this.$message({ message: '请正确填写手机号' })
      }
      if (!this.InfoObj.uPass) {
        return this.$message({ message: '请填写登录密码' })
      }
    },
    toResetPass() {
      this.$router.replace('/resetPass')
    },
    toResiger() {
      this.$router.replace('/reginer')
    },
    BtnSuc() {
      if (!this.InfoObj.uTel) {
        return this.$message({ message: '请填写账号' })
      }
      if (!this.InfoObj.uPass) {
        return this.$message({ message: '请填写登录密码' })
      }
      // this.$message({ message: '登录成功!', type: 'success' });
      // window.sessionStorage.setItem('isLogin', true);
      // window.sessionStorage.setItem('uName', this.InfoObj.uTel);
      // window.sessionStorage.setItem('utoken', this.InfoObj.uTel);
      // this.$store.commit('UpLoginStates');
      // this.$router.replace('/');
      const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      sysLogin({
        type:1,
        username: this.InfoObj.uTel,
        password: this.InfoObj.uPass,
      }).then((e)=>{
          this.$message({message:'登录成功!',type:'success'});
          window.sessionStorage.setItem('isLogin',true);
          window.sessionStorage.setItem('uName',this.InfoObj.uTel);
          window.sessionStorage.setItem('utoken',e.token);
      }).then((er)=>{
          this.$store.commit('UpLoginStates');
          this.$router.replace('/');
      }).then(()=>{
          loading.close();
      }).catch(()=>{
        loading.close();
      })
    }
  }
};
</script>
<style lang="scss" scoped>
.contentLogin {
  display: inline-flex;
  width: 100%;
  align-items: center;
  height: 100vh;
  justify-content: center;
  background: #4072dd;
  // background-image: url('~@/assets/login_bg.svg');
  background-image: url('~@/assets/login_bg.png');
  background-size: cover;
}

.ContBox {
  // text-align: center;
  // height: 60%;
  // width:100%;
  // display: inline-flex;
  // align-items: center;
  // margin-right: 5%;

  // height: 50vh;
  // box-shadow: 0 0px 10px 3px #CCC;
  img {
    height: 100%;
    width: 300px;
  }

  .nextBtn {
    padding: 10px 50px;
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
    padding: 6% 0;
    text-align: center;
    box-shadow: 0 0px 10px 3px #CCC;
    flex: 1;
    padding-bottom: 12%;
    height: calc(100% - 18%);

    h3 {
      letter-spacing: 10px;
      font-size: 30px;
    }

    .tipsBox {
      width: 50%;
      display: inline-flex;
      font-size: 12px;
      justify-content: space-between;
      margin-bottom: 15px;

      span {
        color: #999999;
        transform: scale(0.9);
      }

      span:hover {
        cursor: pointer;
        text-decoration: underline;
      }
    }

    .LoginBox {
      width: 70%;
      margin: 0 auto;
      margin-bottom: 0px;

      .el-input {
        // border: 0px;
        // width: calc(100% - 40px);
        // padding: 15px 10px;
        // border-bottom: 1px solid #e5e5e5;
        // outline: none;
        // cursor: pointer;
        margin-bottom: 30px;
      }

      // input:nth-child(1) {
      //   padding-left: 30px;
      //   background: url(../assets/step2.png) left no-repeat;
      //   background-size: 18px;
      // }
      // input:nth-child(2) {
      //   padding-left: 30px;
      //   background: url(../assets/step2-3.png) left no-repeat;
      //   background-size: 18px;
      //   margin-bottom: 10px;
      // }
    }
  }
}</style>