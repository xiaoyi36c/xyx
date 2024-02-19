<template>
    <div class="indexBox">
        <!-- <div class="title">
            <span @click="toRules">
                <i class="el-icon-edit"></i>
                信用评价体系</span>
        </div> -->
        <div class="cont" style="flex-direction: column;">
            <!-- justify-content: center -->
            <div class="marb15" v-if="list.goods.length" style="padding-top: 20px;width: 80%;margin-left: 13%;border:0;display: inline-flex;flex-wrap: wrap;justify-content: flex-start;">
                <!-- <div class="title">
                    <el-image :src="require('@/assets/other/icon_xy_good.png')"></el-image>
                    优秀信用企业（{{list.goods.length}}）
                </div> -->
                <template >
                    <listCarditem v-for="(v, i ) in list.goods" :key="i" :len="list.length" :ItemIndex="i + 1" :item="v"  @click.native="toDetail(v)"/>
                    <!-- <listItem v-for="(v,i) in list.goods" :item="v" :len="list.length" :ItemIndex="i+1"  :key="i" @click.native="toDetail(v)"/> -->
                </template>
            </div>
            <div class="marb15"  v-else style="width: 80%;margin-left: 10%;border:0;display: inline-flex;flex-wrap: wrap;justify-content: center;">
                    <el-empty></el-empty>
            </div>
            <el-pagination 
                        class="pagination"
                        v-if="list.goods.length" 
                        v-bind="pagination1"
                        @current-change="currentChange1"
                        >
                        </el-pagination>
            <!-- <div class="marb15">
                <div class="title">
                    <el-image :src="require('@/assets/other/icon_xy_middle.png')"></el-image>
                    良好信用企业（{{list.middle.length}}）
                </div>
                <template v-if="list.middle.length">
                    <listItem v-for="(v,i) in list.middle" :item="v" :len="list.length" :ItemIndex="i+1"  :key="i" @click.native="toDetail(v)"/>
                    <el-pagination 
                        class="pagination"
                        v-if="list.middle.length" 
                        v-bind="pagination2"
                        @current-change="currentChange2"
                        >
                        </el-pagination>
                </template>
                <template v-else>
                    <el-empty></el-empty>
                </template>
            </div>
            <div class="marb15">
                <div class="title">
                    <el-image :src="require('@/assets/other/icon_xy_bad.png')"></el-image>
                    一般信用企业（{{list.bad.length}}）
                </div>
                <template v-if="list.bad.length">
                    <listItem v-for="(v,i) in list.bad" :item="v" :len="list.length" :ItemIndex="i+1"  :key="i" @click.native="toDetail(v)"/>
                    <el-pagination 
                        class="pagination"
                        v-if="list.bad.length" 
                        v-bind="pagination3"
                        @current-change="currentChange3"
                        >
                    </el-pagination>
                </template>
                <template v-else>
                    <el-empty></el-empty>
                </template>
            </div> -->
        </div>
    </div>
</template>
<script>
import listCarditem from './listCarditem'
import listItem from "@/components/listBox/infoPubList.vue";
import {getOrgShowList} from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins:[ListMinxins],
    components: {
        listItem,listCarditem
    },
    data() {
        return {
            pagination1:{
                pageSize:20,
                total:0,
                currentPage:1,
                pageSizes:[10],
                layout:"prev, pager, next" 
            },
            pagination2:{
                pageSize:10,
                total:0,
                currentPage:1,
                pageSizes:10,
                layout:"prev, pager, next" 
            },
            pagination3:{
                pageSize:10,
                total:0,
                currentPage:1,
                pageSizes:10,
                layout:"prev, pager, next" 
            },
            list:{
                goods:[],
                middle:[],
                bad:[]
            }
        }
    },
    mounted(){
       this.initData()
    },
    methods:{
        toDetail(item){
            console.log(1)
            this.$router.push('/infoOrgDetail?id='+item.id)
        },
        toRules(){
            this.$router.push('/InfoCountRules')
            // window.open('/评价指标体系.xls')
            // this.$router.push('/infoOrgDetail?id='+item.id)
        },
        currentChange1(e){
            this.pagination1.currentPage= e
            this.initData_Good()
        },
        currentChange2(e){
            this.pagination2.currentPage= e
            this.initData_Middle()
        },
        currentChange3(e){
            this.pagination3.currentPage= e
            this.initData_Bad()
        },
        initData(){
            // creditStatus：信用状态1：良好，2：一般，3：轻微失信，4：严重失信
            this.initData_Good()
            this.initData_Middle()
            this.initData_Bad()
        },
        initData_Good(){
            getOrgShowList({
                creditStatus:1,
                pageNum:this.pagination1.currentPage,
                pageSize:this.pagination1.pageSize,
            }).then(res=>{
                this.list['goods']= res.rows
                this.pagination1.total = res.total
            })
        },
        initData_Middle(){
            getOrgShowList({
                creditStatus:2,
                pageNum:this.pagination2.currentPage,
                pageSize:this.pagination2.pageSize,
            }).then(res=>{
                this.list['middle']= res.rows
                this.pagination2.total = res.total
            })
        },
        initData_Bad(){
            getOrgShowList({
                creditStatus:3,
                pageNum:this.pagination3.currentPage,
                pageSize:this.pagination3.pageSize,
            }).then(res=>{
                this.list['bad']= res.rows
                this.pagination3.total = res.total
            })
        }
    }
}
</script>
<style lang="scss" scoped>
.indexBox {
    width: 100%;
    // margin-top: 15px;
    .title{
        text-align: right;
        font-size: 14px;
        font-weight: 400;
        color: #3994FF;
        line-height: 20px;
        width: 100%;
        line-height: 50px;
        cursor: pointer;
    }
    .cont{
        display: inline-flex;
        width:100%;
        padding-bottom: 50px;
        justify-content: space-around;
        .marb15{
            // flex:1;
            width: calc(33.3333% - 15px);
            border: 1px solid #3994FF;
            .title{
                width:100%;
                background: #F2F8FF;
                text-align: center;
                border-top: 2px solid #3994FF;
                font-size: 16px;
                font-weight: 400;
                color: #333333;
                line-height: 50px;
                display: inline-flex;
                align-items: center;
                justify-content:center;
                .el-image{
                    margin-right: 5px;
                    width:25px;
                    height:25px;
                }
            }
        }
        >div:nth-child(2){
         margin: 0 10px;
        }
    }

}
</style>