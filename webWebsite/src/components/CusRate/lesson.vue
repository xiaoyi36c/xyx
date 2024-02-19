<template>
    <div>
        <template  v-if="list.length">
            <div class="box">
                <div class="boxItem" v-for="(v, i) in list" :key="i">
                    <div class="item">
                        <el-image fit="cover" :src="v.course_cover_img"></el-image>
                        <!-- <el-image fit="cover" :src="require('@/assets/menu3-bg.png')"></el-image> -->
                    </div>
                    <div class="right">
                        <div class="title">
                            <span>{{v.course_name}}</span>
                            <span v-if="v.course_feature_tags">{{v.course_feature_tags}}</span>
                        </div>
                        <div class="info">{{v.course_category}}</div>
                        <div class="content">
                            <div>
                                {{v.course_describe}}
                            </div>
                            <span @click="toPath">
                                去咨询
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <el-pagination class="pagination" v-if="list.length" v-bind="pagination" @current-change="currentChange">  </el-pagination>
        </template>
        <template v-else>
            <el-empty></el-empty>
        </template>
    </div>
</template>
<script>
import {
getCourse} from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins:[ListMinxins],
    data() {
        return {
            list: []
        }
    },
    mounted(){
        this.initData()
    },
    methods:{
        toPath(){
            this.$message.info('暂未配置')
        },
        initData(){
            getCourse({
                orgId:this.$route.query.id,                
                pageNum:this.pagination.currentPage,
                pageSize:this.pagination.pageSize,
            }).then(res=>{
                this.list = res.rows
                this.pagination.total = res.total
            })
        }
    }
}
</script>
<style lang="scss" scoped>
$h:110px;
$w:180px;
.box {
    display: inline-flex;
    width:100%;
    justify-content: space-between;
    flex-wrap: wrap;
    padding: 0 0px;
    margin-top: 20px;
    .boxItem {
       width: calc(50% - 20px);
        display: inline-flex;
        margin-bottom: 15px;
        .item{
            position: relative;
            .el-image{
                width: $w;
                height: $h;
            }
        }
        .right{
            padding:0 15px;
            width:100%;
            .title{
                font-size: 16px;
                font-weight: bold;
                color: #333333;
                line-height: 26px;
                span{
                    background: #FFEDED;
                    color:#F86868;
                    padding:3px 8px;
                    font-size:12px;
                    margin-right: 10px;
                }
                span:nth-child(1){
                    font-size: 15px;
                    background: transparent;
                    color: #333333;
                    padding:0;
                }
                span:last-child{
                    margin-right: 0px;
                }
            }
            .info{
                font-size: 12px;
                color: #6D7387;
                line-height: 26px;
            }
            .content{
                color: #333333;
                font-size: 13px;
                width: 100%;
                display: inline-flex;
                position: relative;
                >div{
                    width:calc(100% - 50px);
                    text-overflow: ellipsis;
                    overflow: hidden;
                    font-size: 14px;
                    line-height: 18px;
                    display: -webkit-box;
                    -webkit-box-orient: vertical;
                    -webkit-line-clamp: 3;
                    overflow: hidden;
                }
                span{
                    color:#3994FF;
                    font-size: 12px;
                    position: absolute;
                    bottom:0;
                    right: 0;
                }
            }
        }
    }
}
</style>