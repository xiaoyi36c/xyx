<template>
    <div class="bannerTab" >
        <div style="position: relative;">
            <el-tabs :value="$route.path" @tab-click="handleClick" v-if="$route.path !='/infoOrgDetail'">
                <el-tab-pane :label="v.title" :name="v.path" :itemInfo="v" v-for="(v,i) in tabList" :key="i"></el-tab-pane>
            </el-tabs>
            <div class="bannerTabTitle" v-if="$route.path =='/infoPubList'">
                <span @click="toRules">
                    <i class="el-icon-edit"></i>
                    信用评价体系</span>
            </div>
        </div>
        <slot name="content"></slot>
    </div>
</template>
<script>
export default {
    data() {
        return {
            activeName: 'first',
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
            tabList:[
                {
                    title:'首页',
                    path:'/index',
                },
                {
                    title:'信息公示',
                    path:'/infoPubList',
                },
                {
                    title:'信易学+',
                    path:'/infoXinyi',
                },
            ]
        }
    },
    methods: {
        toRules(){
            this.$router.push('/InfoCountRules')
            // window.open('/评价指标体系.xls')
        },
        handleClick(e) {
            this.$router.push(e.name)
        }
    }
}
</script>
<style lang="scss" scoped>
    .bannerTabTitle{
        text-align: right;
        font-size: 14px;
        font-weight: 400;
        color: #3994FF;
        line-height: 20px;
        width: 100%;
        line-height: 50px;
        cursor: pointer;
        position: absolute;
        top:-10px;
    }
::v-deep .el-tabs__header{
    margin: 0;
}
.bannerTab {
    background: #fff;
    border-radius: 5px;
    padding: 15px;
    position: relative;
    z-index: 1;
    margin-top: -80px;
    .bannerContent {
        height: 240px;
        display: inline-flex;
        width: 100%;

        .el-carousel {
            width: 100%;
            height: 100%;

            ::v-deep .el-carousel__container {
                width: 100%;
                height: 240px;

                .el-image {
                    width: 100%;
                    height: 100%;
                }
            }
        }

        .bannerArt {
            width: 45%;
            padding: 15px;

            .title {
                display: inline-flex;
                width: 100%;
                margin-bottom: 3px;

                span {
                    background: rgba($color: #FFF2E6, $alpha: .8);
                    font-size: 13px;
                    width: 50px;
                    text-align: center;
                    display: inline-block;
                    padding: 3px 0px;
                    color: #FA8C24;
                    border-radius: 5px;
                }

                p {
                    width: 100%;
                    margin: 0;
                    margin-left: 8px;
                    font-size: 15px;
                    font-weight: 400;
                    color: #333333;
                    line-height: 25px;
                }
            }

            .info {
                display: inline-flex;
                width: 100%;
                justify-content: space-between;
                font-size: 13px;
                color: #999999;
            }

            .content {
                margin: 5px 0;
                text-overflow: ellipsis;
                overflow: hidden;
                font-size: 14px;
                line-height: 22px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 7;
                overflow: hidden;
            }

            .detail {
                text-align: right;
                font-size: 14px;
                color: #3994FF;

            }
        }
    }
}
</style>