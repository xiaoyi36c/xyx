<template>
  <div style="text-align:center;background-color: #fff;">
    <div class="TopBox">
      <div class="logo">
        <router-link to="/"><img src="@/assets/logo.png" alt="" /></router-link>
        <template v-if="!['/infoOrgSearch', '/infoOrgDetail'].includes($route.path)">
          <!-- <el-cascader size="small" style="width: 200px;margin:0 10px 0 20px;" v-model="cityVal" :options="options"
            :props="{
              label: 'name',
              value: 'id',
            }" ref="cascader" @change="handleCityChange"></el-cascader> -->
          <!-- <el-link type="primary" @click="toPath('/infoOrgSearch')">查K12机构</el-link> <el-divider
            direction="vertical"></el-divider> -->
          <el-link style="margin-left: 20px;" type="primary" @click="toPath('/infoOrgSearch')">职教培训</el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link type="primary" @click="toPath('/infoOrgSearch')">全部机构</el-link>
        </template>
      </div>

      <!-- 右边 -->
      <div class="right">
        <!-- <el-link type="primary" size="small" plain @click="toApply">机构入驻申请</el-link>
        <el-divider direction="vertical"></el-divider> -->
        <!-- <el-link type="primary" size="small" plain @click="toLoginOrg">机构登录</el-link> -->
        <!-- <el-divider direction="vertical"></el-divider>
        <el-link type="primary" size="small" plain @click="offerApply = true">订阅</el-link> -->
      </div>
      <!-- 订阅 -->
      <el-dialog @close="resetForm" width="600px" title="订阅申请" :visible.sync="offerApply" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="订阅内容" prop="subscriptionTopic">
            <el-select multiple collapse-tags style="width:100%;" size="small" v-model="form.subscriptionTopic"
              placeholder="请选择订阅内容">
              <el-option :label="v.value" :value="v.key" v-for="(v, i) in TypeList" :key="i"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="您的身份" prop="identityType">
            <el-select style="width:100%;" size="small" v-model="form.identityType" placeholder="请选择身份">
              <el-option :label="v.value" :value="v.key" v-for="(v, i) in TypeCardList" :key="i"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位名称" prop="unitName">
            <el-input size="small" v-model="form.unitName" placeholder="请输入单位名称"></el-input>
          </el-form-item>
          <el-form-item label="您的称呼" prop="nickName">
            <el-input size="small" v-model="form.nickName" placeholder="请输入您的称呼"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="phone">
            <el-input size="small" v-model="form.phone" placeholder="请输入手机号"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input size="small" v-model="form.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item label="推送方式" prop="pushType">
            <el-radio-group v-model="form.pushType">
              <el-radio label="1">邮箱</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <div style="text-align: center;margin-left: -80px;">
              <el-button size="small" type="primary" @click="submitForm">提交申请</el-button>
              <el-button size="small" @click="resetForm">取消</el-button>
            </div>
          </el-form-item>
        </el-form>
      </el-dialog>
      <!-- 订阅成功提示 -->
      <el-dialog width="400px" :visible.sync="offerApplyRet" append-to-body>
        <div class="suc">
          <el-image :src="require('@/assets/other/suc.png')"></el-image>
          <div>提交成功</div>
          <div>感谢您的订阅，我们会尽快审核您的订阅申请!</div>
        </div>
      </el-dialog>
    </div>
  </div>
</template>
<script>
import { getRegionMap, subscriptionApply, getPushType, getIdentityType } from '@/requestPub/api'
export default {
  props: {

  },
  data() {
    return {
      offerApply: false,
      offerApplyRet: false,
      form: {
        subscriptionTopic: [],
        identityType: '',
        unitName: '',
        nickName: '',
        phone: '',
        email: '',
        pushType: '',
      },
      rules: {
        subscriptionTopic: [
          { required: true, message: '请选择订阅内容', trigger: 'change' },
        ],
        identityType: [
          { required: true, message: '请选择身份', trigger: 'change' },
        ],
        unitName: [
          { required: true, message: '请输入单位名称', trigger: 'blur' },
        ],
        nickName: [
          { required: true, message: '请输入您的姓名', trigger: 'blur' },
        ],
        phone: [
          {
            required: true, message: '请正确输入手机号', trigger: 'blur', pattern: /^1[3-9]\d{9}$/
          },
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'blur', pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ },
        ],
        pushType: [
          { required: true, message: '请选择推送方式', trigger: 'change' },
        ],

      },
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
      cityVal: [],
      cityName: '',
      TypeList: [],
      TypeCardList: [],
    }
  },
  computed: {

  },
  mounted() {
    if (!['/infoOrgSearch', '/infoOrgDetail'].includes(this.$route.path)) {
      //城市列表
      getRegionMap().then(res => {
        console.log(res)
        this.options = res.rows
      })
    }
    //订阅类型
    getPushType().then(res => {
      this.TypeList = res.data

    })
    //身份类型
    getIdentityType().then(res => {
      this.TypeCardList = res.data
    })
  },
  methods: {
    toApply(){
      this.$router.push('/infoOrgApply?type=1')
    },
    toLoginOrg(){
      window.open('http://op.lorisai.cn/')
    },
    handleCityChange(e) {
      let getCheckedNodes = this.$refs.cascader.getCheckedNodes()[0];
      this.cityName = getCheckedNodes['data']['name']
      this.toPath('/index')
      console.log(getCheckedNodes)
    },
    toPath(v) {
      this.$router.push(`${v}?cityName=${this.cityName}&cityVal=` + this.cityVal.join())
    },
    resetForm(formName) {
      this.$refs['form'].resetFields();
      this.offerApply = false;
    },
    submitForm(formName) {

      this.$refs['form'].validate((valid) => {
        if (valid) {
          subscriptionApply(this.form).then(res => {
            this.offerApply = false;
            this.$refs['form'].resetFields();
            this.offerApplyRet = true;
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    offerApplyFun() {
      this.$message.info('订阅')
    }
  }
}
</script>
<style lang="scss" scoped>
.suc {
  text-align: center;

  .el-image {
    width: 80px;
    height: 80px;
  }

  div {
    font-size: 20px;
    font-weight: 600;
    color: rgba(0, 0, 0, 0.85);
    line-height: 32px;
  }

  >div:last-child {
    font-size: 14px;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.45);
    line-height: 22px;
  }
}

.TopBox {
  height: 60px;
  // margin-top: 5%;
  width: 1200px;
  background: #fff;
  color: #333;
  align-items: baseline;
  display: inline-flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;

  .logo {
    display: inline-flex;
    align-items: center;

    img {
      width: 100px;
    }
  }

  .right {}
}
</style>
