<template>
    <div class="bannerContent">
        <el-carousel :autoplay="false">
            <el-carousel-item class="" v-for="(item, i) in bannerList" :key="i">
                <div class="left">
                    <el-image :src="item.title_images_url" fit="cover">
                        <div slot="error" class="image-slot" style="width: 100%;">
                            <el-image src="/step4.png" fit="cover"></el-image>
                        </div>
                    </el-image>
                </div>
                <div class="bannerArt">
                    <div class="title">
                        <span>专栏</span>
                        <p>{{ item['article_title'] }}</p>
                    </div>
                    <div class="info">
                        <span>{{ $dayjs(item.create_time).format("YYYY-MM-DD HH:mm:ss") }}</span>
                        <span>来源：{{ item.article_source || '-'}}</span>
                    </div>
                    <div class="content">
                        {{ item.article_synopsis }}
                    </div>
                    <div class="detail" @click="toArtDetail('/infoDetail?type='+(item.type || '')+'&id=' + item['id'])">
                        查看详情&nbsp;<i class="el-icon-right"></i>
                    </div>
                </div>
            </el-carousel-item>
        </el-carousel>

    </div>
</template>
<script>
import { getArticleAdvertisingList } from '@/requestPub/api'
export default {
    data() {
        return {
            bannerList: [{
                article_title: '',
                article_data: ''
            }],
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
        }
    },
    methods: {
        handleClick(e) {
            console.log(e)
        }
    },
    mounted() {
        //banner文章+广告
        getArticleAdvertisingList().then(res => {
            this.bannerList = res.data;
        })
    },
    methods: {
        toArtDetail(v) {
            this.$router.push(v)
        }
    }
}
</script>
<style lang="scss" scoped>
.bannerContent {
    height: 240px;
    display: inline-flex;
    width: 100%;
    margin-top: 20px;

    .el-carousel {
        width: 100%;
        height: 100%;

        ::v-deep .el-carousel__item {
            display: inline-flex;
        }

        ::v-deep .el-carousel__indicators--horizontal {
            left: 35%;
        }

        .left {
            width: 70%;
            height: 100%;
        }

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
        width: 30%;
        padding: 15px;
        padding-top: 5px;
        position: relative;
        .title {
            display: inline-flex;
            width: 100%;
            margin-bottom: 3px;

            span {
                background: rgba($color: #FFF2E6, $alpha: .8);
                font-size: 13px;
                width: 50px;
                height: 21px;
                line-height: 21px;
                text-align: center;
                display: inline-block;
                color: #FA8C24;
                border-radius: 5px;
                flex-shrink: 0;
            }

            p {
                margin: 0;
                margin-left: 8px;
                font-size: 15px;
                font-weight: 400;
                color: #333333;
                word-break: break-all;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
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
            font-size: 14px;
            line-height: 22px;
            text-overflow: ellipsis;
            overflow: hidden;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 7;
            color:#938e8e;
        }

        .detail {
            text-align: right;
            font-size: 14px;
            color: #3994FF;
            position: absolute;
            bottom: 0;
            right: 0
        }
    }
}</style>
