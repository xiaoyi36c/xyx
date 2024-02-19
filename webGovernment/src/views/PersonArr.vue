<template>
  <div class="Box">
    <div class="content">
      <Tops />
      <div class="CurrWeiZhi">当前位置：首页 > 仲裁费名册</div>
      <div class="ContBox">
          <div class="title">仲裁费名册</div>
          <div class="txt">
                <!-- <div class="inlineBox">
                  <el-input v-model="intTxt" placeholder="请输入姓名"></el-input>
                  <el-button type="primary" icon="el-icon-search" @click="btnSearch()">搜索</el-button>
                </div> -->

                <el-table
                  :data="listArr"
                  height="100%"
                  border
                  style="width: 100%">
                  <el-table-column
                    prop="姓名"
                    label="姓名"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="居住地"
                    label="居住地"
                    width="180">
                  </el-table-column>
                  <el-table-column
                    prop="擅长类型"
                    label="擅长类型">
                  </el-table-column>
                   <el-table-column
                    prop="职业分类"
                    label="职业分类">
                  </el-table-column>
                   <el-table-column
                    prop="职称"
                    label="职称">
                  </el-table-column>
                </el-table>
          </div>
        <div class="BtnBox">
          <!-- <el-button class="nextBtn" style="background:rgba(245, 166, 41, 1);color:#fff;" @click="BtnNext">下一步</el-button> -->
            <el-pagination
              @current-change="handleCurrentChange"
              :current-page.sync="currentPage"
              :page-size="PageSize"
              layout="prev, pager, next, jumper"
              :total="PageTotal"
              >
            </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Tops from "../components/heardTop/Tops";
import { GetArbitrators } from '../requestPub/api'
export default {
  components: {
    Tops,
  },
  data() {
    return {
      PageTotal:0,//总条数
      PageSize:10, //每页条数
      intTxt:'',//搜索框 值
      currentPage:1,
      listArr:[]
    }
  },
  mounted(){
    this.GetInfo();
  },
  methods: {
    btnSearch(){
      this.currentPage=1;
      this.GetInfo();
    },
    GetInfo(){
      GetArbitrators({
        page:1,
        limit:10,
        name:this.intTxt
      }).then((e)=>{
        console.log(e,e.total)
        this.listArr = e.Data.data;
        this.PageTotal= e.Data.total;
      }).catch((er)=>{

      })
    },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      }
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
      .inlineBox{
        padding-bottom:15px;
        display: inline-flex;
        .el-button{
          margin-left:10px ;
        }
      }
  }
  .BtnBox{
    padding-top:20px;
    text-align: center;
  }
}
</style>