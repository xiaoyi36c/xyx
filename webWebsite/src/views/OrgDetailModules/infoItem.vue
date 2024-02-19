<template>
    <div class="bannerContent">
        <el-carousel>
            <el-carousel-item v-for="item in info.frontDoorPhoto" :key="item">
                <el-image :src="item" fit="concovertain">
                    <div slot="error" class="image-slot" style="height: 100%;">
                        <el-image src="/defaultBanner.png" fit="cover"></el-image>
                    </div>
                </el-image>
            </el-carousel-item>
        </el-carousel>
        <div class="bannerArt">
            <div class="info">
                <span>统一社会信用代码：</span>
                <span>{{ info.credit_code || '-' }}</span>
                <span style="color:#409EFF;cursor: pointer;" @click="tolook">查看营业执照</span>
            </div>
            <div class="info">
                <span>成立日期：</span>
                <div>{{ info.register_data }}</div>
            </div>
            <div class="info">
                <span>行业：</span>
                <div>{{ info.industryName || '-' }}</div>
            </div>
            <div class="info">
                <span>校区：</span>
                <div>{{ info.campusName || '-' }}</div>
            </div>
            <div class="info">
                <span>地址：</span>
                <div>{{ info.area_detail || '-' }}</div>
            </div>
            <div class="info">
                <span class="dretitle">简介：</span>
                <div class="dre">{{ info.org_synopsis }}</div>
            </div>
            <div class="detail" v-if="info.jk_pc_url && info.is_push_jk == 1">
                <el-button type="primary" size="small" @click="toBaoFun">
                    去报名&nbsp;<i class="el-icon-right"></i>
                </el-button>
            </div>
        </div>
        <!-- 营业执照 -->
        <el-dialog title="查看营业执照" :visible.sync="dialogVisible" width="800px" @close="dialogVisible = false" append-to-body>
            <el-image v-if="info?.business_license" :src="info?.business_license" fit="cover"></el-image>
            <template v-else>
                <el-empty description="暂时还没有营业执照"></el-empty>
            </template>
        </el-dialog>
    </div>
</template>
<script>
export default {
    props: ['info'],
    data() {
        return {
            dialogVisible: false,
            activeName: 'first',
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
        }
    },
    methods: {
        handleClick(e) {
            console.log(e)
        },
        toBaoFun() {
            if (!this.info.jk_pc_url || this.info.is_push_jk != 1) return this.$message.info('暂未配置')
            window.open(this.info.jk_pc_url)
        },
        tolook() {
            // if(this.info.businessLicense) return this.$message.info('暂时还没有营业执照')
            this.dialogVisible = true
        }
    }
}
</script>
<style lang="scss" scoped>
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
        width: 85%;
        padding: 0 15px;
        margin-left: 10px;

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
            font-size: 13px;
            color: #333333;
            line-height: 25px;

            span {
                margin-right: 5px;
                min-width: 45px;
                // font-weight: bold;
            }

            .dretitle,
            .dre {
                line-height: 25px;
            }

            .dre {
                text-overflow: ellipsis;
                overflow: hidden;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp: 3;
                overflow: hidden;
            }
        }

        .detail {
            text-align: left;
            font-size: 14px;
            color: #3994FF;
            margin-top: 5px;
        }
    }
}
</style>