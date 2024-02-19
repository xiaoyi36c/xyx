<template>
    <div class="indexBox">
        <div class="cont">
            <div class="marb15">
                <div class="title">
                    信用动态 /
                    <span>文章标题XXXX</span>
                </div>

                <div class="content">
                    <div class="info">
                        <div>标题XXXXXXXXXX</div>
                        <div>
                            发布时间：<span>2023年5月8日 | </span>
                            来源：<span>信用中国</span>
                            专栏：<span style="color:#FC9A41">行业</span>
                        </div>
                    </div>
                    <div>富文本内容富文本内容富文本内容富文本内容富文本内容富文本内容</div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import listItem from "@/components/listBox/infoXinyiItem.vue";
export default {
    components: {
        listItem,
    },
    data() {
        return {
            list: [{}, {}, {}, {}, {}, {}, {}]
        }
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
                color: #333333;

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
                }
            }
        }
    }

}</style>