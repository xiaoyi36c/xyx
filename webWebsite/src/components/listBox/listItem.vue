<template>

<div>
    <div class="list">
        <el-image lazy v-if="item.file_url" :src="item.file_url" fit="cover"></el-image>
        <div class="bannerArt" :style="
        {
            width: `${item.file_url?'calc(100% - 255px)':'100%'}`
        }">
                <div class="title">
                    <span>专栏</span>
                    <p> {{item.article_title}}</p>
                </div>
            <div class="content">
                {{item.article_synopsis}}
            </div>
            <div class="info">
                <span>{{$dayjs(item.create_time).format("YYYY-MM-DD HH:mm:ss")}}</span>
                <span>来源：{{item.article_source}}</span>
                <div>
                    <!-- <span>部委</span> -->
                    <!-- <span>信用状态</span> -->
                </div>
            </div>
        </div>
    </div>
    <el-divider v-if="len>ItemIndex"></el-divider>
</div>
</template>
<script>
export default{
    props:['ItemIndex','len','item'],
    data() {
        return {
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
        }
    },
}
</script>
<style lang="scss" scoped>
.el-divider {
    background-color: #F2F2F2;
    margin: 20px 0;
}
.list{
    display: inline-flex;
    width: calc(100% - 15px);
    cursor: pointer;
    .el-image{
        width: 240px;
        height: 145px;
    }
    .bannerArt {
           
            padding:0 0 0 15px;

            .title {
                display: inline-flex;
                width: 100%;
                margin-bottom: 0px;

                span {
                    background: rgba($color: #FFF2E6, $alpha: .8);
                    font-size: 13px;
                    width: 50px;
                    text-align: center;
                    display: inline-block;
                    padding: 3px 0px;
                    color: #FA8C24;
                    border-radius: 5px;
                    height: fit-content;
                }

                p {
                    width: 100%;
                    margin: 0;
                    margin-left: 8px;
                    font-size: 15px;
                    font-weight: 400;
                    color: #333333;
                    line-height: 25px;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                }
            }

            .info {
                display: inline-flex;
                width: 100%;
                // justify-content: space-between;
                font-size: 13px;
                color: #999999;
                margin-top: 3px;
                span{
                    margin-right: 30px;
                }
                >div{
                    span{
                        background: #F2F2F2;
                        border-radius: 2px;
                        border: 1px solid #CCCCCC;
                        color:#999999;
                        padding:2px 5px;
                        margin-left: 10px;
                        font-size: 12px;
                      
                    }
                }
            }

            .content {
                margin: 5px 0;
                text-overflow: ellipsis;
                overflow: hidden;
                font-size: 14px;
                line-height: 22px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp:4;
                overflow: hidden;
                color:#938e8e;
            }

            .detail {
                text-align: right;
                font-size: 14px;
                color: #3994FF;

            }
        }
}

</style>