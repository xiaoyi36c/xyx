<template>
    <div>
        <template  v-if="list.length">
        <div class="boxList">
            <div class="listCont" v-for="(v, i) in list" :key="i">
                <span class="item">{{v.create_time}}</span>
                <div class="ava">
                    <el-image fit="cover" :src="v.head_imager"></el-image>
                    <!-- <el-image fit="cover" :src="require('@/assets/menu3-bg.png')"></el-image> -->
                    <span>{{v.complaint_user_name}}</span>
                </div>
                <!-- <div class="content middle">
                    投诉内容DAU回复群文件覅偶家投诉内容DAU回复群文件覅偶家投诉内容DAU回复群文件覅偶家回复群文件覅偶家投诉内容DAU回复群文件覅偶家
                </div> -->
                <div class="content" v-html="v.complaint_content">
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
import {getComplaintByOrg} from '@/requestPub/api'
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
        initData(){
            getComplaintByOrg({
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
.boxList {
    background: #fff;

    .listCont {
        width: 100%;
        display: inline-flex;
        padding: 15px 0;
        margin-bottom: 5px;
        border-bottom: 1px solid #F2F2F2;
        font-size: 13px;
        align-items: center;
        .item {

            width: 200px;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
            text-align: center;
            display: inline-block;
        }

        .ava {
            display: inline-flex;
            align-items: center;
            width:200px;
            .el-image{
                width:50px;
                height:50px;
                margin-right: 5px;
                border-radius: 50%;
            }
        }

        .content {
        }
       .middle{
         margin-right:15px;
         margin-left:5px;
        }
    }
}
</style>