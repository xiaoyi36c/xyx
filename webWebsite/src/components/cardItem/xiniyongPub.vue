<template>
    <div class="box">
        <div class="title">城市信用指数排名
            <!-- <span>2022.4.1-2023.3.31</span> -->
            <span @click="$router.push('/infoOrgSearch')">更多<i class="el-icon-d-arrow-right"></i></span>
        </div>
        <div class="cont">
            <ul>
                <!-- 标题 -->
                <li class="titleHead">
                    <span>排名</span>
                    <span>地区</span>
                    <span>信用指数</span>
                </li>

                <template v-if="list.length">
                    <li v-for="(v, i) in list" :key="i">
                        <span>{{ i + 1 }}</span>
                        <span>{{ v.regionName }}</span>
                        <span>{{ v.rankCount }}</span>
                    </li>
                </template>
                <template v-else>

                    <el-empty></el-empty>
                </template>
            </ul>
        </div>
    </div>
</template>
<script>
import { orgRegionCreditOrderBy } from '@/requestPub/api'
export default {
    data() {
        return {
            list: []
        }
    },
    mounted() {
        orgRegionCreditOrderBy().then(res => {
            this.list = res.data
        })
    }
}
</script>
<style lang="scss" scoped>
.box {
    padding: 15px;
    background: #fff;

    .title {
        font-size: 18px;
        font-weight: 600;
        color: #333333;
        line-height: 20px;
        border-bottom: 1px solid #F2F2F2;
        padding-bottom: 10px;

        span {
            font-size: 14px;
            font-weight: 400;
            color: #999999;
        }

        >span:last-child {
            float: right;

            &:hover {
                cursor: pointer;
                text-decoration: underline;
            }
        }
    }

    .cont {
        background: #F2F8FF;
        width: 100%;
        margin-top: 15px;
        display: inline-flex;
        justify-content: space-between;

        ul {
            padding: 0;
            margin: 0;
            width: 100%;

            .titleHead {
                background: #3994FF !important;
                color: #fff;
                font-size: 15px !important;
            }

            li {
                list-style: none;
                height: 50px;
                line-height: 50px;
                width: 100%;
                font-size: 14px;
                display: inline-flex;
                justify-content: space-around;
                background: #FFFFFF;
                span {
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    overflow: hidden;
                    flex: 1;
                    text-align: center;
                }
            }

            >li:nth-child(2n + 1) {
                background: #F5F5F5;
            }
        }
    }
}
</style>