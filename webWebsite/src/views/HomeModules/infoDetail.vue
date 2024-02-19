<template>
    <div class="indexBox">
        <div class="cont">
            <div class="marb15">
                <div class="title">
                    信用动态 
                    <span v-if="info.article_special_column">/ {{info.article_special_column}}</span>
                </div>
                <div class="content">
                    <div class="info">
                        <div>{{info.article_title}}</div>
                        <div>
                            发布时间：<span>{{info.create_time?$dayjs(info.create_time).format('YYYY-MM-DD hh:mm:ss')  : '-'}} </span> 
                            <el-divider direction="vertical"></el-divider>
                            来源：<span>{{info.article_source || '-'}} </span>
                            <el-divider direction="vertical"></el-divider>
                            专栏：<span style="color:#FC9A41">{{info.article_special_column || '-'}}</span>
                        </div>
                    </div>
                    <div class="contentFull" v-html="info.article_data"></div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import listItem from "@/components/listBox/infoXinyiItem.vue";
import { getArticleDetail } from "@/requestPub/api";
export default {
    components: {
        listItem,
    },
    data() {
        return {
            info: {}
        }
    },
    mounted(){
        getArticleDetail({
            id:this.$route.query.id,
            type:this.$route.query.type
        }).then(res=>{
            this.info = res.data
        })
    }
}
</script>
<style lang="scss" scoped>
.indexBox {
    width: 100%;
    background: #fff;

    .cont {
        display: inline-flex;
        width: 100%;
        padding-bottom: 50px;
        flex-wrap: wrap;
        justify-content: space-between;

        .marb15 {
            width: 100%;
            margin-bottom: 10px;

            .title {
                width: 100%;
                border-bottom: 1px solid #f2f2f2;
                text-align: left;
                font-weight: 400;
                color: #333333;
                line-height: 50px;
                margin-bottom: 15px;
                position: relative;
                font-size: 18px;
                font-weight: 600;
                color: #4f5050;

                span {
                    color: #3994FF;
                    font-size: 14px;
                }

                i {
                    height: 3px;
                    background: #3994FF;
                    width: 30px;
                    display: inline-block;
                    position: absolute;
                    left: 0;
                    bottom: -2px;
                }
            }

            .content {
                border: 1px solid #f2f2f2;
                padding: 15px;

                .info {
                    font-size: 14px;
                    font-weight: 400;
                    color: #333333;
                    padding-bottom: 15px;
                    border-bottom: 1px dashed #F2F2F2;
                    >div:first-child{
                        margin-bottom: 10px;
                        color:#4f5050;
                        font-size: 24px;
                        font-weight: 600;
                    }
                }
                .contentFull{
                    padding-top: 20px;
                    color: #666666;
                    line-height: 2;
                    ::v-deep p{
                        font-family: "Microsoft YaHei";
                        font-size: 15px;
                        line-height: 2;
                        margin: 0px;
                    }
                }
            }
        }
    }
}
</style>