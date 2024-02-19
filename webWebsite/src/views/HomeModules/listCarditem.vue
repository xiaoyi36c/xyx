<template>
    <div style="width: calc(50% - 20px);margin-bottom: 20px;" class="listItem">
        <div class="list">
            <div class="left">
                <el-image :src="item.enterprise_logo" fit="contain">
                    <div slot="error" class="image-slot" style="width: 100%;">
                        <el-image src="/step4.png" fit="cover"></el-image>
                    </div>
                </el-image>
                <div class="bannerArt">
                    <div class="content">
                        {{ item.organization_name }}
                    </div>
                    <!-- v-if="item.credit_score>650 && item.org_type" -->
                    <div class="title" v-if="item.org_type == 2">
                        <!-- <span v-if="item.credit_score>650">信用健康分：{{item.credit_score || '-'}} 分</span>
                        <span v-if="item.credit_score>650">信用评级：{{ item.credit_rank || '-'}}</span> -->
                        <span v-if="item.org_type">{{ item.org_type == 2 ? 'k12机构' : '' }}</span>
                    </div>
                    <!-- <div class="info">
                        <i class="el-icon-notebook-2"></i>
                        <span>课程：{{item.courseName || '暂无'}}</span>
                    </div> -->
                    <div class="info">
                        <i class="el-icon-location-outline"></i>
                        <span>{{ item.area_detail || '暂无' }}</span>
                    </div>
                </div>
            </div>
            <!-- <div class="line"></div>
            <div class="rate">
                <el-rate v-model="item.appraiseNum" disabled show-score ></el-rate>
                <div>{{item.evaluateCount || 0}}条评价<i class="el-icon-right"></i></div>
                <div>{{item.complaintCount || 0}}条投诉<i class="el-icon-right"></i></div>
                <div>{{item.campusCount || 0}}条校区<i class="el-icon-right"></i></div>
            </div> -->
        </div>
        <el-divider v-if="len > ItemIndex"></el-divider>
    </div>
</template>
<script>
export default {
    props: {
        ItemIndex: {
            type: [String, Number],
            default: ''
        },
        len: {
            type: [String, Number],
            default: ''
        },
        item: {
            type: Object,
            default: () => { }
        }
    },
    data() {
        return {

        }
    },
    methods: {

    }
}
</script>
<style lang="scss" scoped>
.el-divider {
    background-color: #F2F2F2;
    padding: 0 5px;
    width: calc(100% - 30px);
    margin: 15px auto;
}

.listItem:nth-child(2n+2) {
    margin-left: 40px;
}

.list {
    display: inline-flex;
    width: 100%;
    padding: 10px 0;
    cursor: pointer;

    .left {
        display: inline-flex;
        width: 95%;
    }

    .el-image {
        width: 120px;
        height: 90px;
    }

    .bannerArt {
        padding: 0 0 0 15px;
        width: calc(100% - 150px);
        display: flex;
        flex-direction: column;
        justify-content: center;

        .title {
            width: 100%;
            margin-bottom: 0px;
            height: 30px;

            span {
                background: rgba($color: #FFF2E6, $alpha: .8);
                font-size: 12px;
                text-align: center;
                display: inline-block;
                padding: 3px 5px;
                color: #FA8C24;
                border-radius: 5px;
                height: 20px;
                line-height: 20px;
                margin-right: 10px;
            }

            span:nth-child(2) {
                color: #43CA47;
                background: #cce9cd;
            }

            span:nth-child(3) {
                color: #3994FF;
                background: #EDF5FF;
            }
        }

        .info {
            // height:30px;
            line-height: 30px;
            width: 100%;
            font-size: 12px;
            color: #999999;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;

            span {
                margin-left: 5px;
            }
        }

        .content {
            height: 30px;
            margin-bottom: 3px;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
            width: 100%;
            font-size: 18px;
            font-weight: bold;
            color: #333333;

        }

        .detail {
            text-align: right;
            font-size: 14px;
            color: #3994FF;

        }
    }

    .line {
        width: 1px;
        background: #F2F2F2;
    }

    .rate {
        font-size: 14px;
        font-weight: 500;
        color: #333333;
        line-height: 22px;
        margin-left: 20px;

        >div {
            padding-left: 5px;
            margin-top: 7px;
        }

        >div:nth-child(2) {
            margin-top: 10px;
        }
    }
}</style>