<template>
    <div class="Box">
        <h3 style="padding: 20px 0 20px;text-align: center;font-weight: 500;">{{ $route.query.type == 1 ? '机构入驻申请' : '机构认领'
        }}</h3>
        <el-form ref="form" :model="form" :rules="rules" label-width="110px" style="width: 78%;margin: 0 auto;">
            <el-row :gutter="24">
                <el-col :span="12">
                    <el-form-item label="机构名称" prop="orgName">
                        <el-input size="small" v-model="form.orgName" v-if="$route.query.type == 1"
                            placeholder="请填写机构名称"></el-input>
                        <template v-else>
                            {{ form.orgName }}
                        </template>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="法人" v-if="$route.query.type != 1">
                        {{ form.legal_person }}
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="机构类型" prop="orgType">
                        <el-select style="width:100%;" size="small" v-model="form.orgType" placeholder="请选择机构类型">
                            <el-option label="职教机构" value="1"></el-option>
                            <el-option label="K12非学科教培机构" value="2"></el-option>
                            <el-option label="其他" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="您的姓名" prop="userName">
                        <el-input size="small" placeholder="请输入您的姓名" v-model="form.userName"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="邮箱" prop="email">
                        <el-input size="small" placeholder="请输入邮箱" v-model="form.email"></el-input>
                        <div style="line-height: 20px;color:red;font-size: 12px;">审核通过后会发默认密码到指定邮箱中，请务必正确填写！</div>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="身份证号" prop="idCard">
                        <el-input size="small" placeholder="请输入法人身份证号" v-model="form.idCard"></el-input>
                    </el-form-item>
                </el-col>

                <el-col :span="13"></el-col>
                <el-col :span="12">
                    <el-form-item label="上传证件" prop="fileList">
                        <el-upload class="avatar-uploader" :action="ProUrl" :show-file-list="false"
                            :on-success="handleAvatarSuccess1" :before-upload="beforeAvatarUpload">
                            <el-image v-if="imageUrl[0]" :src="imageUrl[0]" class="avatar"></el-image>
                            <el-image v-else :src="require('@/assets/other/id1.png')" class="avatar"></el-image>
                            <div class="mask" v-if="imageUrl[0]">
                                <i class="el-icon-zoom-in" @click.stop="handlePictureCardPreview(imageUrl[0])"></i>
                                <i class="el-icon-delete" @click.stop="imageUrl[0] = ''; $forceUpdate()"></i>
                            </div>
                        </el-upload>
                        <el-upload style="margin-left: 20px;" class="avatar-uploader" :action="ProUrl"
                            :show-file-list="false" :on-success="handleAvatarSuccess2" :before-upload="beforeAvatarUpload">
                            <el-image v-if="imageUrl[1]" :src="imageUrl[1]" class="avatar"></el-image>
                            <el-image v-else :src="require('@/assets/other/id2.png')" class="avatar"></el-image>
                            <div class="mask" v-if="imageUrl[1]">
                                <i class="el-icon-zoom-in" @click.stop="handlePictureCardPreview(imageUrl[1])"></i>
                                <i class="el-icon-delete" @click.stop="imageUrl[1] = ''; $forceUpdate()"></i>
                            </div>
                        </el-upload>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="上传营业执照" prop="businessLicense">
                        <el-upload class="avatar-uploader" :action="ProUrl" :show-file-list="false"
                            :on-success="handleAvatarSuccess3" :before-upload="beforeAvatarUpload">
                            <el-image v-if="form.businessLicense" :src="form.businessLicense" class="avatar"></el-image>
                            <el-image v-else :src="require('@/assets/other/iddea_1.png')" class="avatar"></el-image>
                            <div class="mask" v-if="form.businessLicense">
                                <i class="el-icon-zoom-in" @click.stop="handlePictureCardPreview(form.businessLicense)"></i>
                                <i class="el-icon-delete" @click.stop="form.businessLicense = ''"></i>
                            </div>
                        </el-upload>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="统一信用码" prop="creditCode">
                        <el-input size="small" placeholder="请输入社会统一信用代码" v-model="form.creditCode"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="手机号" prop="phone">
                        <el-input size="small" placeholder="请输入手机号" v-model="form.phone"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="验证码" prop="smsCode" class="code-item">
                        <el-input size="small" placeholder="请输入验证码" v-model="form.smsCode"></el-input>
                        <el-button size="small" type="primary" @click="sendSMS" :loading="codeSendLoading"
                            v-if="!isCodeSend">获取验证码</el-button>
                        <el-button size="small" type="primary" key="disabled" disabled v-else>{{ timeLeft }}秒后重发</el-button>
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                    <el-form-item>
                        <div style="text-align: center;margin-left: -110px;">
                            <el-button size="small" type="primary" @click="submitForm">提交申请</el-button>
                            <el-button size="small" @click="resetForm">重置</el-button>
                        </div>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <!-- 提交成功提示 -->
        <el-dialog width="400px" :visible.sync="offerApplyRet" append-to-body @close="back">
            <div class="suc">
                <el-image :src="require('@/assets/other/suc.png')"></el-image>
                <div>提交成功</div>
                <div>感谢您的申请，我们会尽快审核！审核通过后会发默认密码到指定邮箱中，请注意查收！</div>

            </div>
            <!-- <div style="text-align: center;margin-top: 20px;">
                <el-button size="small" type="primary" @click="resetForm">确定</el-button>
            </div> -->
        </el-dialog>

        <!-- 预览 -->
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogVisibleUrl" alt="">
        </el-dialog>
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
import { getOrgDetail, getSmsCode } from '@/requestPub/api'
import { orgClaim } from "../requestPub/api";
export default {
    components: {
        bannerTab, bannerSearch, InfoCusRate, infoDrector, infoItem, infoAll, infoXinyong, InfoOperate
    },
    data() {
        return {
            dialogVisibleUrl: '',
            dialogVisible: false,
            info: {},//机构详情
            imageUrl: ['', ''],//证件
            list: [{}, {}, {}, {}, {}],
            tagList: [{}, {}, {}],
            typeIdx: '',
            renlingFun: false,
            offerApplyRet: false,//提交成功的提示
            form: {
                id: null,//机构id
                phone: null,
                smsCode: null,
                userName: null,
                email: null,
                idCard: null,
                orgType: null,
                orgName: null,
                source: null,
                businessLicense: null,
                fileList: [
                    // {
                    //     file_url:'',
                    //     file_name:''
                    // }
                ],
                isUseLearnNowPayLater: false,
                channelUserAccount: null,
                creditCode: null
            },
            rules: {
                idCard: [
                    { required: true, message: '请正确输入身份证号', trigger: 'blur', pattern: /(^\d{8}(0\d|10|11|12)([0-2]\d|30|31)\d{3}$)|(^\d{6}(18|19|20)\d{2}(0[1-9]|10|11|12)([0-2]\d|30|31)\d{3}(\d|X|x)$)/ }
                ],
                orgType: [
                    { required: true, message: '请选择机构', trigger: 'change' },
                ],
                orgName: [
                    { required: true, message: '请输入机构名称', trigger: 'blur' },
                ],
                userName: [
                    { required: true, message: '请输入您的姓名', trigger: 'blur' },
                ],
                phone: [
                    {
                        required: true, message: '请正确输入手机号', trigger: 'blur', pattern: /^1[3-9]\d{9}$/
                    },
                ],
                smsCode: [
                    { required: true, message: '请输入验证码', trigger: 'blur' },
                ],
                email: [
                    { required: true, message: '请输入邮箱', trigger: 'blur', pattern: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/ },
                ],
                fileList: [
                    {
                        required: true, message: '请上传证件', trigger: 'change', validator: (rule, value, callback) => {
                            console.log(value);
                            if (value.length != 2) {
                                callback('请上传证件')
                            }
                            callback()
                        }
                    },
                ],
                businessLicense: [
                    { required: true, message: '请上传营业执照', trigger: 'change' },
                ],
                creditCode: [{ required: true, message: '请输入统一社会信用代码', trigger: 'blur' }, { message: '请输入正确统一社会信用代码', pattern: /^[0-9A-HJ-NPQRTUWXY]{18}$/, trigger: 'blur' }]

            },
            codeSendLoading: false,
            isCodeSend: false,
            timeLeft: 0
        };
    },
    computed: {
        ProUrl() {
            return window.origin + process.env.VUE_APP_BASE_API + '/org/officialWebsite/uploadFile'
        },
    },
    watch: {
        '$route.query'(val) {
            this.$emit('resetTimer')
            this.$refs.form.resetFields();
            this.imageUrl = ['', '']
            let source = '机构申请';
            if (this.$route.query.type != 1) {
                source = '机构认领';
                this.rules['orgName'][0]['required'] = false
                getOrgDetail({ id: this.$route.query.id }).then(res => {
                    this.info = res.data;
                    this.form.orgName = res.data.organization_name;
                    this.form.legal_person = res.data.legal_person;
                    this.$forceUpdate()
                })
            }
            this.form.source = source;
        }
    },
    mounted() {
        console.log(process.env.VUE_APP_BASE_API)
        this.$refs.form.resetFields();
        this.imageUrl = ['', '']
        let source = '机构申请';
        if (this.$route.query.type != 1) {
            source = '机构认领';
            this.rules['orgName'][0]['required'] = false
            getOrgDetail({ id: this.$route.query.id }).then(res => {
                this.info = res.data;
                this.form.orgName = res.data.organization_name;
                this.form.legal_person = res.data.legal_person;
                this.$forceUpdate()
            })
        }
        if (this.$route.query.creditCode) {
            this.form.creditCode = this.$route.query.creditCode
        }
        this.form.source = source;
    },
    methods: {
        back() {
            this.$router.back()
        },
        sendSMS() {
            this.$refs['form'].validateField(['phone'], valid => {
                if (!valid) {
                    this.codeSendLoading = true
                    getSmsCode({ phone: this.form.phone }).then(() => {
                        this.codeSendLoading = false
                        this.isCodeSend = true
                        this.timeLeft = 60
                        this.$message.success('验证码已发送')
                        const timer = setInterval(() => {
                            this.timeLeft--;
                            if (this.timeLeft <= 0) {
                                this.isCodeSend = false
                                clearInterval(timer);
                            }
                        }, 1000);
                        this.$on('resetTimer', () => {
                            clearInterval(timer);
                            this.codeSendLoading = false
                            this.isCodeSend = false
                            this.timeLeft = 0
                        })
                    }).catch(() => {
                        this.codeSendLoading = false
                    })
                }
            })
        },
        handlePictureCardPreview(dialogVisibleUrl) {
            this.dialogVisibleUrl = dialogVisibleUrl;
            this.dialogVisible = true;
        },
        handleAvatarSuccess1(res, file) {
            console.log(res)
            if (res.code == 200) {
                this.imageUrl[0] = res.data.fileUrl
                this.form.fileList[0] = res.data
                this.$forceUpdate()
            }
        },
        handleAvatarSuccess2(res, file) {
            if (res.code == 200) {
                this.imageUrl[1] = res.data.fileUrl
                this.form.fileList[1] = res.data
                this.$forceUpdate()
            }
        },
        handleAvatarSuccess3(res, file) {
            if (res.code == 200) {
                this.form.businessLicense = res.data.fileUrl
                this.$forceUpdate()
            }
        },
        beforeAvatarUpload(file) {
            const isJPG = ['image/jpeg', 'image/png'].includes(file.type);
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG || !isLt2M) {
                this.$message.error('请上传jpg或png格式图片,大小不能超过 2MB');
            }
            // if (!isLt2M) {
            //     this.$message.error('上传图片大小不能超过 2MB!');
            // }
            return isJPG && isLt2M;
        },
        resetForm(formName) {
            this.$refs['form'].resetFields();
            this.renlingFun = false;
        },
        submitForm(formName) {
            this.$refs['form'].validate((valid) => {
                console.log(this.form.fileList);
                if (valid) {
                    this.form.id = this.$route.query.id || null
                    this.form.type = this.$route.query.type || null
                    // this.form.industry=this.$route.query.industry
                    // this.form.topIndustry=this.$route.query.topIndustry
                    orgClaim(this.form).then(res => {
                        this.renlingFun = false;
                        this.offerApplyRet = true;
                        this.$refs['form'].resetFields();
                        this.imageUrl = ['', ''];
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
.el-form-item {
    margin-bottom: 35px;
}

.avatar-uploader {
    position: relative;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;

    .mask {
        opacity: 1;
        transition: all 0.3s;
    }
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 100px;
    height: 95px;
    text-align: center;
}

.avatar {
    width: 100px;
    height: 95px;
    display: block;
}
</style>
<style lang="scss" scoped>
.mask {
    position: absolute;
    top: 0;
    opacity: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    background: rgba(0, 0, 0, .4);
    line-height: 95px;
    font-size: 26px;
    border-radius: 5px;
    color: #fff;
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

.content {
    width: 1200px;
    margin: 0 auto;
}

::v-deep .el-dialog__body {
    padding-top: 10px;
}

::v-deep .code-item {
    .el-form-item__content {
        display: flex;
        align-items: center;

        .el-button {
            margin-left: 8px;
            width: 90px;
            padding-left: 0;
            padding-right: 0;
            text-align: center;
            flex-shrink: 0;
        }
    }
}

.avatar-uploader {
    float: left;
    border: 1px dashed #d9d9d9;
    cursor: pointer;
    margin-right: 10px;
    width: 100px;
    height: 95px;
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
