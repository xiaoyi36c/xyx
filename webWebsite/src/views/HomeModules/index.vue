<template>

    <div class="indexBox">
        <div class="left">
            <div class="tagBox">
                <!-- <span class="span" @click="ChhooiseFun(3)" :class="ChooiseTag ==3?'act':''" >市动态</span>
                <span class="span" @click="ChhooiseFun(2)" :class="ChooiseTag ==2?'act':''" >省动态</span>  -->
                <span class="span" @click="ChhooiseFun(1)" :class="ChooiseTag ==1?'act':''">信用动态</span>
            </div>
            <template v-if="list.length">            
                <listItem v-for="(v,i) in list" :item="v" :len="list.length" :ItemIndex="i+1"  :key="i" @click.native="$router.push('/infoDetail?id='+v.id)"/>
                <el-pagination 
                    class="pagination"
                    v-if="list.length" 
                    v-bind="pagination"
                    @current-change="currentChange"
                >
                </el-pagination>
            </template>
            <template v-else>
                <el-empty></el-empty>
            </template>
        </div>
        <div class="right">
            <!-- <div class="marb15">
               <InfoPub></InfoPub>
            </div> -->
            <div class="marb15">
                <xiniyongPub></xiniyongPub>
            </div>
            <!-- <div class="marb15">
                <noSort></noSort>
            </div> -->
            <div class="marb15">
                
                <orgSort></orgSort>
            </div>
        </div>
    </div>
</template>
<script>
import listItem  from "@/components/listBox/listItem.vue";
import InfoPub from "@/components/cardItem/infoPub.vue";
import noSort from "@/components/cardItem/noSort.vue";
import orgSort from "@/components/cardItem/orgSort.vue";
import xiniyongPub from "@/components/cardItem/xiniyongPub.vue";
import { ListMinxins } from '@/mixins/ListMinxins'
import {getArticleList,getArticleAdvertisingList } from '@/requestPub/api'
export default {
    mixins:[ListMinxins],
    components:{
    listItem,
    InfoPub,
    noSort,
    orgSort,
    xiniyongPub
},
    data(){
        return{
            ChooiseTag:1,
            list:[]
        }
    },
    watch:{
        '$route.query':{
            handler(){
                if(this.$route.query?.cityVal){
                    this.initData()
                }
            }
        }
    },
    mounted(){
        this.initData()
    },
    methods:{
        ChhooiseFun(i){
            this.ChooiseTag = i
            this.initData()
        },
        initData(){
            let regionId=0
            if(this.ChooiseTag==2){
                regionId = this.$route.query?.cityVal?this.$route.query?.cityVal.split(',')[0]:0
            }
            if(this.ChooiseTag==3){
                regionId = this.$route.query?.cityVal?this.$route.query?.cityVal.split(',')[1]:0
            }
            //文章列表
            getArticleList({
                type:this.ChooiseTag,
                regionId:regionId,
                pageNum: this.pagination.currentPage,
                pageSize: 13
            }).then(res=>{
                this.list = res.rows
                this.pagination.total = res.total
            })
        },
    }
}
</script>
<style lang="scss" scoped>
.indexBox{
    width: 100%;
    display: inline-flex;
    margin-top: 15px;
    .left{
        background: #fff;
        border-radius: 5px;
        padding: 15px;
        width: 63%;
        padding-bottom:0;
        margin-bottom: 15px;
        .tagBox{
            margin-bottom: 10px;
            padding-left: 15px;
            .span{
                display: inline-block;
                width: 90px;
                font-size: 13px;
                line-height: 30px;
                text-align: center;
                color: #333333;
                box-sizing: border-box;
                border: 1px solid #EEEEEE;
                cursor: pointer;
            }
            .act{
                color:#3994FF;
                font-weight: 600;
                // background: rgba($color: #3994FF, $alpha: .3);
            }   
        }
    }
    .right{
        background:#F9F9F9;
        border-radius: 5px;
        // padding: 15px;
        margin-left: 20px;
        width: 37%;
.marb15{
    margin-bottom: 15px;
}
    }
}
</style>