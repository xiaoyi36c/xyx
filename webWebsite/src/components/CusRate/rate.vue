<template>
<div>
    <div>
        <div class="box" v-if="avgAppraise">
            <div class="left">
                <div class="title">综合评分</div>
                <div class="rate">
                    <span>{{avgAppraise}}</span>
                    <i v-if="avgAppraise>=0">{{avgAppraise>4.5?'超赞':avgAppraise>3 && avgAppraise<4.5?'一般':(avgAppraise>=0 && avgAppraise<3?'较差':'')}}</i>
                </div>
            </div>
            <div style="width: 1px;background:#EEEEEE;"></div>
            <div class="right">
                <div class="title">学员认为</div>
                <div class="info">
                    <span v-for="(v,i) in rateInfo" :key="i">{{ v.student_appraise_lable }}({{v.appeaiseCount}})</span>
                </div>
            </div>
        </div>
        <div class="boxList">
            <div class="industyBox" v-if="avgAppraise">
                <div class="industyItem" :class="actKey===i?'act':''" v-for="(item, i) in options" :key="i" @click="tap(i,item)">
                    {{ item.title }}
                </div>
            </div>
            <template v-if="list.length">
                <div class="listCont" v-for="(v,i) in list" :key="i">
                <el-image class="ava" fit="cover" :src="v.head_imager"></el-image>
                <div class="right">
                    <div class="info">
                        <span>{{v.created_by_name}}</span>
                        <span>{{v.phone}}</span>
                    </div>
                    <div class="rate">
                        <span>
                            <el-rate :value="v.appraise_num" disabled></el-rate>
                        </span>
                        <span>
                            环境:{{v.environment_appraise}}  
                            师资：{{v.teachers_appraise}}   
                            服务{{v.server_appraise}}   
                            效果：{{v.result_appraise}}  
                        </span>
                    </div>
                    <div class="cont">
                         {{v.appraise_data}}
                    </div>
                    <div class="other">
                        <el-image fit="cover"  v-for="(item,i) in v.fileList" :key="i" :src="item.file_url"></el-image>
                    </div>
                </div>
            </div>
            </template>
            <template v-else>
                <el-empty></el-empty>
            </template>

        </div>
    </div>
    <el-pagination class="pagination" v-if="list.length" v-bind="pagination" @current-change="currentChange">  </el-pagination>
    </div>
</template>
<script>
import {getOrgEvaluate,
} from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins:[ListMinxins],
    data() {
        return {
            options:[{title:'全部'},{title:'好评'}],
            actKey:0,
            list: [],
            rateInfo:[],
            avgAppraise:''
        }
    },
    mounted(){
        this.initData();
        // getOrgEvaluate({id:this.$route.query.id}).then(res=>{
        //         this.rateInfo = res.data
        // })
    },
    methods:{
        tap(i){
            this.actKey=i;
            this.pagination.currentPage=1;
            this.initData();
        },
        initData(){
            getOrgEvaluate({
                appraise_type:this.actKey,
                orgId:this.$route.query.id,                
                pageNum:this.pagination.currentPage,
                pageSize:this.pagination.pageSize,
            }).then(res=>{
                this.list = res.data.appraiseList.rows
                this.rateInfo= res.data.appraiseCount
                this.avgAppraise= res.data.avgAppraise
                this.pagination.total = res.data.appraiseList.total
            })
        }
    }
}
</script>
<style lang="scss" scoped>
.box{
    background: #FAFBFC;
    border-radius: 0px 0px 0px 0px;
    display: inline-flex;
    width: 100%;
    padding:10px 0  20px 20px;
    margin-top: 20px;
    .left{
        padding:0px 30px 0 0;
        .title{
            margin-right: 20px;
            font-size: 14px;
            font-weight: 500;
            color: #333333;
            text-align: left;
        }
        .rate{
            margin-top: 30px;
            position: relative;
            text-align: center;
            span{
                font-size: 44px;
                font-weight: 700;
                color: #3994FF;
            }
            i{
                background: url('~@/assets/other/rateBg.png');
                color:#fff;
                font-size: 12px;
                padding: 1px 3px;
                background-repeat: no-repeat;
                background-size: 100% 100%;
                position: absolute;
                top: -15px;
                width: 30px;
                text-align: center;
                right:-20px;
            }
        }
    }
    .right{
        padding:0px 30px ;
        .title{
            font-size: 14px;
            font-weight: 500;
            color: #333333;
        }
        .info{
            padding-top: 50px;
            span{
                font-size: 12px;
                background: #F4F9F9;
                margin-right: 10px;
                padding: 5px 15px;
                border-radius: 4px 4px 4px 4px;
            }
        }
    }
}
.boxList{
    background: #fff;
    .industyBox{
    display: inline-flex;
    width:100%;
    position: relative;
  
    border-bottom: 1px solid #f2f2f2;
    .industyItem{
        margin-right:30px;
        padding: 10px 0 5px 0;
        border-bottom: 2px solid transparent;
        span{
            font-size: 14px;
            font-weight: bold;
            color: #333333;
            display: block;
            text-align: center;
            padding-bottom: 5px;
            padding: 2px 0 10px 0;
        }
        &:last-child{
            margin-right:0;
        }
        &:hover{
            cursor: pointer;
        }
    }
    .act{
        transition: all .5s;
        border-bottom: 2px solid #3994FF;
    }
    }
    .listCont{
        width: 100%;
        display: inline-flex;
        margin: 15px 0;
        margin-bottom: 5px;
        .ava{
            width: 55px;
            height: 55px;
            border-radius: 50%;
        }
        .right{
            margin-left: 15px;
            flex:1;
            .info{
                span{
                    font-size: 12px;
                    color:#999999;
                }
                span:nth-child(1){
                    font-weight: 700;
                    font-size: 15px;
                    color:#333333;
                }
            }
            .rate{
                padding-bottom: 3px;
                display: inline-flex;
                width: 100%;
                span{
                    font-size: 12px;
                    line-height: 20px;
                    color:#C7C7C7;
                }
            }
            .cont{
                padding-bottom: 3px;
                font-size: 14px;
                color: #333333;
            }
            .other{
                .el-image{
                    width: 80px;
                    height: 80px;
                    margin-right: 10px;
                }
                span{
                    font-size: 15px;
                }
            }
        }
    }
}
</style>