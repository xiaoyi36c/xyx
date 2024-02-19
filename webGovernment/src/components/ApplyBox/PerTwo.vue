<template>
  <div>
    <div class="addPerOne">
      <div class="addItem">
        <el-collapse accordion>
          <el-collapse-item>
            <template slot="title">
              <div class="titlebox">
                <span>申请人{{objIndex + 1}}：（法人）</span> 
                <span class="btnDel" @click.stop="CurrDel(CurrIndex,objIndex)">删除</span>
              </div>
            </template>
            <div class="addLine" style="border-top: 1px solid #f5f5f5">
              <span> <i>*</i> 统一社会信用代码</span>
              <input
                type="text"
                v-model="objItem.统一社会信用代码"
                placeholder="请输入统一社会信用代码"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 名称</span>
              <input type="text" v-model="objItem.名称"  placeholder="请输入姓名" />
            </div>
            <div class="addLine">
              <span> <i>*</i> 姓名</span>
              <input
                type="text"
                v-model="objItem.姓名"
                placeholder="请输入姓名"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 法人代表姓名</span>
              <input
                type="text"
                v-model="objItem.法人代表姓名"
                placeholder="法人代表姓名"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 国家或地区</span>
              <el-select v-model="objItem.国家或地区" placeholder="请选择">
                <el-option
                  v-for="item in CityOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div>
            <div class="addLine">
              <span> <i>*</i> 证件号码</span>
              <input
                type="text"
                v-model="objItem.证件号码"
                placeholder="请输入证件号码"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 营业执照</span>
              <!-- <img
                src="@/assets/addImg.png"
                v-for="(n, m) in objItem.营业执照"
                :key="m"
                class="ImgArrBox"
              />
              <img src="@/assets/addImg.png" class="defaultImg" /> -->
              <img
               v-if="objItem.营业执照"
                :src="objItem.营业执照"
                class="ImgArrBox"
                @click="delObjImg(m)"
              />
              <el-upload
                style="height: 52px"
                class="avatar-uploader"
                action="http://47.97.3.24:8083/api/UserInfoAPI/UpDataFile"
                :show-file-list="false"
                :limit="1"
                :before-upload="beforeAvatarUpload"
                :on-error="upEorror"
                :on-change="ChangeFiles"
                :http-request="uploadSectionFile"
                :file-list="fileList"
              >
                <img src="@/assets/addImg.png" class="defaultImg" />
              </el-upload>
            </div>
            <div class="addLine">
              <span> <i></i> 法定代表人证件类型</span>
              <el-select v-model="objItem.法定代表人证件类型" placeholder="请选择">
                <el-option
                  v-for="item in w_CardOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                >
                </el-option>
              </el-select>
            </div>
            <div class="addLine">
              <span> <i>*</i> 法定代表人证件号码</span>
              <input
                type="text"
                v-model="objItem.法定代表人证件号码"
                placeholder="请输入法定代表人证件号码"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 法定代表人证明书</span>
              <el-upload
                    class="avatar-uploader"
                    action="http://47.97.3.24:8083/api/UserInfoAPI/UpDataFile"
                    :show-file-list="false"
                    :limit="1"
                    :on-error="upEorror"
                    :on-change="ChangeFilesWord_3"
                    :http-request="uploadFileWord_3"
                    :file-list="fileListWord_3"
                  >
                    <span  class="btnup">{{
                      objItem.法定代表人证明书 ? "已上传" : "上传"
                    }}</span>
                </el-upload>
              <p @click.stop="CurrDown2()"  class="p">【证明书模板下载】</p>
            </div>
            <div class="addLine">
              <span> <i>*</i> 手机号码</span>
              <input
                type="number"
                v-model="objItem.手机号码"
                placeholder="请输入手机号码"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 联系电话</span>
              <input
                type="number"
                v-model="objItem.联系电话"
                placeholder="请输入联系电话"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 电子邮箱</span>
              <input
                type="text"
                v-model="objItem.电子邮箱"
                placeholder="请输入电子邮箱"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 住所地</span>
              <input
                type="text"
                v-model="objItem.住所地"
                placeholder="请输入住所地"
              />
            </div>
            <div class="addLine">
              <span> <i>*</i> 仲裁文书送达地</span>
              <input
                type="text"
                v-model="objItem.仲裁文书送达地"
                placeholder="请输入仲裁文书送达地"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 邮政编码</span>
              <input
                type="text"
                v-model="objItem.邮政编码"
                placeholder="请输入邮政编码"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 银行账号</span>
              <input
                type="text"
                v-model="objItem.银行账号"
                placeholder="请输入银行账号"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 开户行</span>
              <input
                type="text"
                v-model="objItem.开户行"
                placeholder="请输入开户行"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 企业地址</span>
              <input
                type="text"
                v-model="objItem.企业地址"
                placeholder="请输入企业地址"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 企业联系电话</span>
              <input
                type="text"
                v-model="objItem.企业联系电话"
                placeholder="请输入企业联系电话"
              />
            </div>
            <div class="addLine">
              <span> <i></i> 委托代理人</span>
              <span class="btnadd" @click.stop="AddWeituo(CurrIndex,objIndex)">添加</span>
            </div>
             <!-- 委托代理人 -->
            <el-collapse
              accordion
              v-for="(v, i) in objItem.委托代理人列表"
              :key="i"
            >
              <el-collapse-item>
                <template slot="title">
                  <div class="titlebox">
                    <span>代理人{{i + 1}}</span>
                    <span class="btnDel" @click.stop="CurrWeiTuoDel(CurrIndex,objIndex,i)">删除</span>
                  </div>
                </template>
                <div class="addLine" style="border-top: 1px solid #f5f5f5">
                  <span> <i>*</i> 代理人姓名</span>
                  <input
                    type="text"
                    v-model="v.代理人姓名"
                    placeholder="请输入代理人姓名"
                  />
                </div>
                <div class="addLine">
                  <span> <i></i> 工作单位</span>
                  <input
                    type="text"
                    v-model="v.工作单位"
                    placeholder="请输入工作单位"
                  />
                </div>
                <div class="addLine">
                  <span> <i></i> 关系</span>
                  <el-select v-model="v.关系" placeholder="请选择">
                    <el-option
                      v-for="item in RelationOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    >
                    </el-option>
                  </el-select>
                </div>
                <div class="addLine">
                  <span> <i></i> 关系证明文件</span>
                  <el-upload
                    class="avatar-uploader"
                    action="http://47.97.3.24:8083/api/UserInfoAPI/UpDataFile"
                    :show-file-list="false"
                    :limit="1"
                    :on-error="upEorror"
                    :on-change="ChangeFilesWord"
                    :http-request="uploadFileWord"
                    :file-list="fileListWord"
                    :data="{i}"
                  >
                    <span  class="btnup">{{
                      v.关系证明文件 ? "已上传" : "上传"
                    }}</span>
                  </el-upload>

                  <p>【员工证明、劳动合同、律师函、亲属证明、公证书等】</p>
                </div>
                <div class="addLine">
                  <span> <i></i> 职业</span>
                  <input
                    type="text"
                    v-model="v.职业"
                    placeholder="请输入职业"
                  />
                </div>
                <div class="addLine">
                  <span> <i></i> 证件类型</span>
                  <el-select v-model="v.证件类型" placeholder="请选择">
                    <el-option
                      v-for="item in w_CardOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    >
                    </el-option>
                  </el-select>
                </div>
                <div class="addLine">
                  <span> <i></i> 证件号码</span>
                  <input
                    type="text"
                    v-model="v.证件号码"
                    placeholder="请输入证件号码"
                  />
                </div>
                <div class="addLine">
                  <span> <i></i> 联系电话</span>
                  <input
                    type="text"
                    v-model="v.联系电话"
                    placeholder="请输入联系电话"
                  />
                </div>
                <div class="addLine">
                  <span> <i></i> 电子邮箱</span>
                  <input
                    type="text"
                    v-model="v.电子邮箱"
                    placeholder="请输入电子邮箱"
                  />
                </div>
                <div class="addLine">
                  <span> <i></i> 通讯地址</span>
                  <input
                    type="text"
                    v-model="v.通讯地址"
                    placeholder="请输入通讯地址"
                  />
                </div>
                <div class="addLine">
                  <span> <i>*</i> 授权委托书</span>
                <el-upload
                    class="avatar-uploader"
                    action="http://47.97.3.24:8083/api/UserInfoAPI/UpDataFile"
                    :show-file-list="false"
                    :limit="1"
                    :on-error="upEorror"
                    :on-change="ChangeFilesWord_2"
                    :http-request="uploadFileWord_2"
                    :file-list="fileListWord_2"
                    :data="{i}"
                  >
                    <span  class="btnup">{{
                      v.授权委托书 ? "已上传" : "上传"
                    }}</span>
                  </el-upload>
                    <p @click.stop="CurrDown()">【委托书模板下载】</p>
                </div>
                <div class="addLine" style="border-bottom: 0">
                  <span> <i></i> 代理人证件</span>
                  <img
                    :src="n"
                    v-for="(n, m) in v.代理人证件"
                    :key="m"
                    class="ImgArrBox"
                    @click="delImg(i,m)"
                  />
                  <el-upload
                    style="height: 52px"
                    class="avatar-uploader"
                    action="http://47.97.3.24:8083/api/UserInfoAPI/UpDataFile"
                    :show-file-list="false"
                    :limit="1"
                    :on-error="upEorror"
                    :before-upload="beforeAvatarUpload"
                    :on-change="ChangeFiles_2"
                    :http-request="uploadSectionFile_2"
                    :file-list="fileList_2"
                    :data="{i}"
                  >
                    <img src="@/assets/addImg.png" class="defaultImg" />
                  </el-upload>
                </div>
              </el-collapse-item>
            </el-collapse>
            <!-- 委托代理人结束  -->
          </el-collapse-item>
        </el-collapse>
      </div>
    </div>
  </div>
</template>
<script>
import { UpDataFile } from "../../requestPub/api";
export default {
  props:['objIndex','CurrIndex','objItem','CityOptions','CardOptions','RelationOptions','w_CardOptions'] ,
  data() {
    return {
      fileList: [],
      fileList_2:[],
      fileListWord: [],
      fileListWord_2:[],
      fileListWord_3:[],
    };
  },
  methods: {
    delObjImg(n){
      this.objItem.证件扫描件.splice(n,1);
    },
    delImg(act,n){
      this.objItem.委托代理人列表[act].代理人证件.splice(n,1);
    },
    uploadSectionFile_2(param) {
      var fileObj = param.file;
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      this.$axios({
        method: "post",
        url: this.$upFileAddr,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: form,
      })
        .then((res) => {
          if (res.data.Status == 200) {
            this.$message({
              message: "上传成功",
              type: "success",
            });
            this.objItem.委托代理人列表[param.data.i].代理人证件.push(res.data.Data);
          }
        })
        .catch((er) => {});
    },
    uploadFileWord_3(param) {
      //法定代表人证明书
      var fileObj = param.file;
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      this.$axios({
        method: "post",
        url: this.$upFileAddr,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: form,
      })
        .then((res) => {
          if (res.data.Status == 200) {
            this.$message({
              message: "上传成功",
              type: "success",
            });
            this.objItem.法定代表人证明书=res.data.Data;
          }
        })
        .catch((er) => {});
    },
    uploadFileWord_2(param) {
      //授权委托书
      var fileObj = param.file;
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      this.$axios({
        method: "post",
        url: this.$upFileAddr,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: form,
      })
        .then((res) => {
          if (res.data.Status == 200) {
            this.$message({
              message: "上传成功",
              type: "success",
            });
            this.objItem.委托代理人列表[param.data.i].授权委托书=res.data.Data;
          }
        })
        .catch((er) => {});
    },
    uploadFileWord(param) {
      //关系证明文件
      var fileObj = param.file;
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      this.$axios({
        method: "post",
        url: this.$upFileAddr,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: form,
      })
        .then((res) => {
          console.log(param);
          if (res.data.Status == 200) {
            this.$message({
              message: "上传成功",
              type: "success",
            });
            this.objItem.委托代理人列表[param.data.i].关系证明文件=res.data.Data;
          }
        })
        .catch((er) => {});
    },
    beforeAvatarUpload(file) {
      var isType='';
      if (file.type==="image/png" || file.type==="image/jpg" || file.type==="image/jpeg") {
         isType=true;
      }else{
        isType=false;
         this.$message.error("上传图片只能是 JPG、PNG、JPEG格式!");
      }
      return isType;
    },
    ChangeFiles_2(file, fileList) {
      this.fileList_2 = [];
    },
    ChangeFilesWord_3(file, fileList) {
      this.fileListWord_3 = [];
    },
    ChangeFilesWord_2(file, fileList) {
      this.fileListWord_2 = [];
    },
    ChangeFilesWord(file, fileList) {
      this.fileListWord = [];
    },
    ChangeFiles(file, fileList) {
      this.fileList = [];
    },
    upEorror(err, file, fileList) {
      this.$message({
        message: "上传失败，请重试",
        type: "error",
      });
      this.fileList = [];
    },
    uploadSectionFile(param) {
      var fileObj = param.file;
      console.log(param);
      // FormData 对象
      var form = new FormData();
      // 文件对象
      form.append("file", fileObj);
      this.$axios({
        method: "post",
        url: this.$upFileAddr,
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: form,
      })
        .then((res) => {
          console.log(res);
          if (res.data.Status == 200) {
            this.$message({
              message: "上传成功",
              type: "success",
            });
            // this.objItem.营业执照.push(res.data.Data);
            this.objItem.营业执照=res.data.Data;
          }
        })
        .catch((er) => {});
    },
    AddWeituo(CurrIndex, e) {
      this.$emit("BtnAddWei", {
        active: CurrIndex,
        nindex: e,
      });
    },
    CurrDel(CurrIndex,e) {  //申请人删除
      this.$emit("BtnDel", {
          active: CurrIndex,
        nindex: e,
      });
    },
    CurrWeiTuoDel(CurrIndex,itemAct,e){  //委托代理人删除
      this.$emit("WeiTuoDel", {
        active: CurrIndex,
        itemAct: itemAct,
        nindex:e
      });
    },
    CurrDown(){
       this.$emit("BtnDown");
    },
    CurrDown2(){
       this.$emit("BtnDown2");
    }
  },
};
</script>
<style lang="scss" scoped>
.addPerOne {
  margin-left: 150px;
  .titlebox {
    margin-left: 10px;
    width: 100%;
    span:nth-child(1) {
      min-width: 150px;

      display: inline-block;
    }
  }
  .addItem {
    .btnup {
      width: 60px !important;
      color: #fff;
      font-size: 12px;
      text-align: center;
      background: #3a4ff6;
      border-radius: 2px;
    }
    .btnup:hover {
      cursor: pointer;
    }
    .btnadd {
      width: 60px !important;
      color: #3a4ff6;
      margin-left: 10px;
      font-size: 12px;
      text-align: center;
      border: 1px solid #3a4ff6;
      border-radius: 8px;
    }
    .btnDel {
      // margin-left: 10px;
      font-size: 12px;
      padding: 2px 10px;
      color: #e60012;
      border: 1px solid #e60012;
      border-radius: 8px;
    }
    .addLine {
      padding: 10px 0;
      align-items: center;
      width: 100%;
      display: inline-flex;
      border-bottom: 1px solid #f5f5f5;
      .p{
        color:#3a4ff6;
      }
      .p:hover{
        cursor: pointer;
        text-decoration: underline;
      }
      span {
        display: inline-block;
        width: 150px;
        i {
          display: inline-block;
          width: 5px;
          font-size: 9px;
          color: #e00000;
        }
      }
      .defaultImg {
        border: 1px solid #ccc;
        border-radius: 5px;
      }
            .ImgArrBox {
        border: 1px solid #ccc;
        border-radius: 5px;
        margin-right: 10px;
      }
      img {
        width: 50px;
      }
      input {
        padding: 5px 0px;
        border: 0;
        outline: none;
      }
    }
  }
}
</style>