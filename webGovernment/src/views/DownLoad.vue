<template>
  <div class="Box">
    <div class="content">
      <Tops />
      <div class="CurrWeiZhi">当前位置：首页 > 文书下载</div>
      <div class="ContBox">
          <div class="title">文书下载</div>
          <div class="txt">
               <table>
                   <thead>
                       <tr><th>序号</th><th>名称</th><th>更新时间</th><th>操作</th></tr>
                   </thead>
                   <tbody>
                 <template>
                <tr v-for="(v, i) in listArr" :key="i">
                  <td>{{ i * currPage + 1 }}</td>
                  <td>{{ v.Name }}</td>
                  <td>{{ v.UpdateTime}}</td>
                  <td @click="getDown(v.FileUrl)">下载</td>
                </tr>
              </template>
                   </tbody>
               </table>
          </div>
        <div class="BtnBox">
          <el-button class="nextBtn" style="background:rgba(245, 166, 41, 1);color:#fff;" @click="BtnNext(1)">上一页</el-button>
          <el-button class="nextBtn" style="background:rgba(245, 166, 41, 1);color:#fff;" @click="BtnNext(2)">下一页</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Tops from "../components/heardTop/Tops";
import { GetDocumentTemplate } from "../requestPub/api"; // 导入我们的api接口
export default {
  components: {
    Tops,
  },
  data() {
    return {
       currPage:1,//当前页
       currTotal:0,//当前记录总数
       listArr:[]
    }
  },
  mounted(){
    this.GetInfoList();
  },
  methods: {
    getDown(e){
      window.open(e)
    },
    GetInfoList(){
      GetDocumentTemplate({
        limit:10,
        page:this.currPage
      }).then((e)=>{
          this.listArr = e.Data.data;
          this.currTotal=e.Data.total;
      }).catch((er)=>{

      })
    },
        BtnNext(n) {
      if (n == 1) {
        //上翻
        this.currPage -- 
        if(this.currPage<=0){
          this.currPage=1;
        };
        this.GetInfoList()
      }
      if (n == 2) {
        //下翻
        if(this.currTotal>0){
           this.currPage++;
          if( this.currPage>Math.ceil((this.currTotal / 10))){
            this.currPage=Math.ceil((this.currTotal / 10));
          }
        }  else{
          this.currPage=1;
        }  
         this.GetInfoList()
      }
    },
  },
};
</script>
<style lang="scss" scoped>
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
      text-align: center;
        table{
          border-collapse: collapse;
          width:100%;
          thead{
              background: rgba(58, 79, 246,.12);
              tr th{
                padding: 10px 0;               
                font-size: 14px;
                font-weight: 500;
                color: #333333; 
              }
          }
          tbody{
              tr {
                border-bottom: 1px solid  #EEEEEE;
                td{
                    padding: 15px 0;               
                    font-size: 13px;
                    color: #333333; 
                }
                td:nth-child(2):hover,  td:nth-child(3):hover, td:nth-child(4):hover{ 
                   cursor: pointer;
                }
                td:nth-child(4){ 
                    font-size: 13px;
                    font-weight: 500;
                    color: #3A4FF6;  
                }
                td:nth-child(4)::before{ 
                  content:'';
                  padding-left:25px ;
                  background: url(../assets/down.png) no-repeat left;
                  background-size: 18px;
                }
              }
               
          }
      }
      
  }
  .BtnBox{
      margin-top:30px;
      text-align: center;
      .nextBtn{
         padding: 6px 35px;
        font-size: 14px;
        background: rgba(74, 201, 151, 1);
        color: #fff;
        border-radius: 40px;
      }
  }
}
</style>