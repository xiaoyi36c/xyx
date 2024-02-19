<template>
    <div>
        <template  v-if="list.length">
            <div class="box">
                    <div class="boxItem" v-for="(v, i) in list" :key="i">
                        <div class="item">
                            <span></span>
                            <el-image fit="cover" :src="v.profile_photo"></el-image>
                        </div>
                    <div class="right">
                        <span class="title">{{ v.teacher_name }}</span>
                        <div class="info">{{ v.responsible_courses }}</div>
                        <div class="content" v-html="v.intro"></div>
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
    getComplaintByOrg,
    getCourse,
    getTeachersPower,
    getCreditEvaluate
} from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins: [ListMinxins],
    data() {
        return {
            list: []
        }
    },
    mounted() {
        this.initData()
    },
    methods: {
        initData() {
            getTeachersPower({
                orgId: this.$route.query.id,
                pageNum: this.pagination.currentPage,
                pageSize: this.pagination.pageSize,
            }).then(res => {
                res.rows.map(e=>{
                    if(e.intro){
                        e.intro = e.intro.replaceAll('&lt;','<').replaceAll('&gt;','>')
                    }
                })
                this.list = res.rows
                this.pagination.total = res.total
            })
        }
    }
}
</script>
<style lang="scss" scoped>
$h: 110px;

.box {
    display: inline-flex;
    width: calc(100% - 40px);
    justify-content: space-between;
    flex-wrap: wrap;
    padding: 0 20px;
    margin-top: 20px;

    .boxItem {
        width: calc(50% - 20px);
        display: inline-flex;
        margin-bottom: 40px;

        .item {
            position: relative;

            span {
                border: 1px dashed pink;
                border-radius: 50%;
                display: inline-block;
                width: $h;
                height: $h;
                position: absolute;
                left: -10px;
                top: -5px;
            }

            .el-image {
                width: $h;
                height: $h;
                border-radius: 50%;
            }
        }

        .right {
            background: linear-gradient(199deg, #EAEFF9 0%, #F7FBFE 100%);
            padding: 15px;
            margin-top: 40px;
            width: 100%;
            margin-left: calc($h / -2);
            padding-left: calc($h / 2 + 10px);
            ;

            .title {
                font-size: 16px;
                font-weight: bold;
                color: #333333;
                line-height: 26px;
            }

            .info {
                font-size: 12px;
                color: #6D7387;
                line-height: 26px;
            }

            .content {
                color: #333333;
                font-size: 13px;
                line-height: 26px;
                text-overflow: ellipsis;
                overflow: hidden;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 4;
            }
        }
    }
}</style>