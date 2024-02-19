<template>
    <div class="Box">
        <bannerSearch :searchOrg="true" />
        <div class="content">
            <!-- banner搜索 -->
            <bannerTab>
                <template slot="content">
                    <!-- 机构信息 -->
                    <div class="orgInfo">
                        <div class="orgInfoBox">
                            <div>
                                <span class="title">{{info.organization_name}}</span>
                                <span v-if="info.business_status">{{  info.business_status}}</span>
                            </div>
                            <!-- <el-button type="primary" size="small" @click="renlingFun = true">申请认领</el-button> -->
                            <!-- <router-link v-if="info.isApply != 1" :to="'/infoOrgApply?id='+$route.query.id+'&creditCode='+info.credit_code"><el-button type="primary" size="small" > 申请认领</el-button></router-link> -->
                           
                        </div>
                        <div class="info" v-if="info.allStudentCount>=10">
                            该机构为教育的典范机构，信用培训学员已累计 （{{info.allStudentCount}}）人
                        </div>
                        <infoItem :info="info"></infoItem>
                    </div>
                    <el-dialog 
                        @close="resetForm"
                        width="600px" 
                        title="机构认领" 
                        :visible.sync="renlingFun" 
                        append-to-body
                    >
                        <el-form ref="form" :model="form" :rules="rules" label-width="110px">
                            <el-form-item label="机构名称">
                              {{form.orgName}}
                            </el-form-item>
                            <el-form-item label="法人" >
                              {{form.legal_person}}
                            </el-form-item>
                            <el-form-item label="机构类型" prop="orgType">
                                <el-select style="width:100%;" size="small" v-model="form.orgType" placeholder="请选择机构类型">
                                    <el-option label="普通机构" value="1" ></el-option>
                                    <el-option label="k12机构" value="2" ></el-option>
                                </el-select>
                            </el-form-item>
                            <el-form-item label="您的姓名" prop="userName">
                                <el-input size="small" placeholder="请输入您的姓名" v-model="form.userName"></el-input>
                            </el-form-item>
                            <el-form-item label="手机号" prop="phone">
                                <el-input size="small" placeholder="请输入手机号" v-model="form.phone"></el-input>
                            </el-form-item>
                            <el-form-item label="法人身份证号" prop="idCard">
                                <el-input size="small" placeholder="请输入法人身份证号" v-model="form.idCard"></el-input>
                            </el-form-item>
                            <el-form-item label="邮箱" prop="email">
                                <el-input size="small" placeholder="请输入邮箱" v-model="form.email"></el-input>
                            </el-form-item>
                            <el-form-item label="上传营业执照" prop="businessLicense">
                                <el-upload
                                    class="avatar-uploader"
                                    :action="ProUrl"
                                    :show-file-list="false"
                                    :on-success="handleAvatarSuccess3"
                                    :before-upload="beforeAvatarUpload">
                                    <el-image  v-if="form.businessLicense" :src="form.businessLicense" class="avatar"></el-image>
                                    <el-image  v-else :src="require('@/assets/other/iddea_1.png')" class="avatar"></el-image>
                                    <div class="mask" v-if="form.businessLicense">
                                        <i class="el-icon-zoom-in" @click.stop="handlePictureCardPreview(form.businessLicense)"></i>
                                        <i class="el-icon-delete"  @click.stop="form.businessLicense=''"></i>
                                    </div>
                                </el-upload>
                            </el-form-item>
                            <el-form-item label="上传法人证件" prop="fileList">
                                <el-upload
                                    class="avatar-uploader"
                                    :action="ProUrl"
                                    :show-file-list="false"
                                    :on-success="handleAvatarSuccess1"
                                    :before-upload="beforeAvatarUpload">
                                    <el-image  v-if="imageUrl[0]" :src="imageUrl[0]" class="avatar"></el-image>
                                    <el-image  v-else :src="require('@/assets/other/id1.png')" class="avatar"></el-image>
                                    <div class="mask" v-if="imageUrl[0]">
                                        <i class="el-icon-zoom-in" @click.stop="handlePictureCardPreview(imageUrl[0])"></i>
                                        <i class="el-icon-delete"  @click.stop="imageUrl[0]='';$forceUpdate()"></i>
                                    </div>
                                </el-upload>
                                <el-upload
                                    class="avatar-uploader"
                                    :action="ProUrl"
                                    :show-file-list="false"
                                    :on-success="handleAvatarSuccess2"
                                    :before-upload="beforeAvatarUpload">
                                    <el-image  v-if="imageUrl[1]" :src="imageUrl[1]" class="avatar"></el-image>
                                    <el-image  v-else :src="require('@/assets/other/id2.png')" class="avatar"></el-image>
                                    <div class="mask" v-if="imageUrl[1]">
                                        <i class="el-icon-zoom-in" @click.stop="handlePictureCardPreview(imageUrl[1])"></i>
                                        <i class="el-icon-delete"  @click.stop="imageUrl[1]='';$forceUpdate()"></i>
                                    </div>
                                </el-upload>
                            </el-form-item>
                            <el-form-item>
                                <div style="text-align: center;margin-left: -110px;">
                                    <el-button size="small" type="primary" @click="submitForm">认领并注册</el-button>
                                    <el-button size="small" @click="resetForm">取消</el-button>
                                </div>
                            </el-form-item>
                        </el-form>
                    </el-dialog>
                    <!-- 提交成功提示 -->
                    <el-dialog width="400px" :visible.sync="offerApplyRet" append-to-body>
                        <div class="suc">
                        <el-image :src="require('@/assets/other/suc.png')"></el-image>
                        <div>提交成功</div>
                        <div>感谢您的认领申请，我们会尽快审核!</div>
                        </div>
                    </el-dialog>
                </template>
            </bannerTab>
            <infoAll @change="changeType">
                <template slot="cont">
                    <infoDrector v-if="typeIdx == 0" name="简介"></infoDrector>
                    <InfoCusRate v-if="typeIdx == 1" name="服务水平"></InfoCusRate>
                    <infoXinyong :info="info" v-if="typeIdx == 2" name="信用"></infoXinyong>
                    <InfoOperate v-if="typeIdx == 3" name="荣誉"></InfoOperate>
                </template>
            </infoAll>
            <!-- 预览 -->
            <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogVisibleUrl" alt="">
            </el-dialog>
        </div>
    </div>
</template>
<script>
import bannerTab from "@/components/bannerBox/bannerTab";
import bannerSearch from "@/components/bannerBox/bannerSearch"
import infoItem from './OrgDetailModules/infoItem'
import infoAll from './OrgDetailModules/infoAll'
//机构下各维度数据
import InfoCusRate from './OrgDetailModules/InfoCusRate' //服务水平
import infoDrector from './OrgDetailModules/infoDrector' //简介
import InfoOperate from './OrgDetailModules/InfoOperate' //经营状况
import infoXinyong from './OrgDetailModules/infoXinyong' //信用信息
import {getOrgDetail} from '@/requestPub/api'
import { orgClaim } from "../requestPub/api";
export default {
    components: {
        bannerTab, bannerSearch, InfoCusRate, infoDrector, infoItem, infoAll, infoXinyong, InfoOperate
    },
    data() {
        return {
            dialogVisibleUrl:'',
            dialogVisible:false,
            info:{},//机构详情
            imageUrl:['',''],//证件
            list: [{}, {}, {}, {}, {}],
            tagList: [{}, {}, {}],
            typeIdx: '',
            renlingFun: false,
            offerApplyRet:false,//提交成功的提示
            form: {
                id:'',//机构id
                phone:'',
                userName: '',
                email:'',
                idCard:'',
                orgType:'',
                businessLicense:'',
                fileList:[
                    // {
                    //     file_url:'',
                    //     file_name:''
                    // }
                ]
            },
            rules: {
                idCard: [
                    { required: true, message: '请正确输入身份证号', trigger: 'blur' ,pattern:/(^\d{8}(0\d|10|11|12)([0-2]\d|30|31)\d{3}$)|(^\d{6}(18|19|20)\d{2}(0[1-9]|10|11|12)([0-2]\d|30|31)\d{3}(\d|X|x)$)/}
                ],
                orgType: [
                    { required: true, message: '请选择机构', trigger: 'change' },
                ],
                orgName: [
                    { required: false, message: '请输入单位名称', trigger: 'blur' },
                ],
                userName: [
                    { required: true, message: '请输入您的姓名', trigger: 'blur' },
                ],
                phone: [
                    { required: true, message: '请正确输入手机号', trigger: 'blur' , pattern:/^1[3-9]\d{9}$/
                    },
                ],
                email: [
                    { required: true, message: '请输入邮箱', trigger: 'blur',pattern:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ },
                ],
                fileList: [
                    { required: true, message: '请上传证件', trigger: 'change',validator:(rule,value,callback)=>{
                        console.log(value);
                        if(value.length!=2){
                            callback('请上传证件')
                        }
                        callback()
                    }},
                ],
                businessLicense: [
                    { required: true, message: '请上传营业执照', trigger: 'change'},
                ],

            },
        };
    },
    computed: {
        ProUrl(){
            return window.origin + process.env.VUE_APP_BASE_API +'/org/officialWebsite/uploadFile'
        },
    },
    mounted(){
        console.log(process.env.VUE_APP_BASE_API)
        getOrgDetail({id:this.$route.query.id}).then(res=>{
            this.info = res.data;
            this.form.orgName = res.data.organization_name
            this.form.legal_person = res.data.legal_person
            
        })
    },
    methods: {
      handlePictureCardPreview(dialogVisibleUrl) {
        this.dialogVisibleUrl = dialogVisibleUrl;
        this.dialogVisible = true;
      },
      handleAvatarSuccess1(res, file) {
        console.log(res)
        if(res.code == 200){
            this.imageUrl[0] =res.data.fileUrl
            this.form.fileList[0]=res.data
            this.$forceUpdate()
        }
      },
      handleAvatarSuccess2(res, file) {
        if(res.code == 200){
            this.imageUrl[1] = res.data.fileUrl
            this.form.fileList[1]=res.data
            this.$forceUpdate()
        }
      },
      handleAvatarSuccess3(res, file) {
        if(res.code == 200){
            this.form.businessLicense=res.data.fileUrl
            this.$forceUpdate()
        }
      },
      beforeAvatarUpload(file) {
          const isJPG = ['image/jpeg' ,'image/png'].includes(file.type);
          const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 、png格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
        resetForm(formName) {
            this.$refs['form'].resetFields();
            this.renlingFun = false;
        },
        submitForm(formName) {
            this.$refs['form'].validate((valid) => {
                if (valid) {
                    this.form.id=this.$route.query.id
                    // this.form.industry=this.$route.query.industry
                    // this.form.topIndustry=this.$route.query.topIndustry
                    orgClaim(this.form).then(res=>{
                        this.renlingFun=false;
                        this.offerApplyRet = true;
                        this.$refs['form'].resetFields();
                        this.imageUrl=['',''];
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        changeType(idx) {
            this.typeIdx = idx
        }
    }
};
</script>
<style>
.avatar-uploader {
    position: relative;
}
.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
    .mask{
        opacity: 1;
        transition:all 0.3s;
    }
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height:  95px;
    text-align: center;
  }
  .avatar {
    width: 100px;
    height:  95px;
    display: block;
  }
</style>
<style lang="scss" scoped>
.mask{
  position: absolute;
  top:0;
  opacity: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
  background: rgba(0, 0, 0, .4);
  line-height:95px;
  font-size: 26px;
  border-radius: 5px;
  color:#fff;
}

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
.content{
  width: 1200px;
  margin: 0 auto;
}
::v-deep .el-dialog__body{
    padding-top: 10px;
}
 .avatar-uploader {
    float: left;
    border: 1px dashed #d9d9d9;
    cursor: pointer;
    margin-right: 10px;
    width: 100px;
    height:  95px;
 }
.content {
    .orgInfo {
        background: #fff;
        border-radius: 5px;
        // padding: 15px;
        position: relative;
        z-index: 1;
        // margin-top: -80px;

        .orgInfoBox {
            display: inline-flex;
            width: 100%;
            align-items: center;
            margin-top: 15px;
            margin-bottom: 10px;
            justify-content: space-between;

            .title {
                font-size: 20px;
                font-weight: 600;
                color: #333333;
                line-height: 25px;
                background: transparent !important;
            }

            >div {
                width: 100%;
                display: inline-flex;
                align-items: center;

                >span {
                    font-size: 12px;
                    color: #fff;
                    padding: 2px 5px;
                    margin-right: 20px;
                    background: #409EFF;
                }
            }
        }
    }

    .info {
        line-height: 50px;
        background: #EDF5FF;
        color: #3994FF;
        font-size: 18px;
        text-align: center;
        margin: 20px 0;
        border: 1px solid #3994FF;
    }
}
</style>