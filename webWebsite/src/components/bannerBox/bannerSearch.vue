<template>
    <div class="bannerSearch">
        <div class="logoTitle">
            <img src="@/assets/slogen.png">
        </div>
        <div class="searchType" v-if="searchType && !searchOrg" style="margin-top: -75px;">
            <span v-for="(v, i) in tablist" :key="i" :class="i === act ? 'act' : ''" @click="tap(i)">{{ v }}</span>
        </div>
        <div class="searchType" v-if="searchOrg" style="margin-top: -75px;">
            <el-image :src="require('@/assets/other/logoTitle.png')"></el-image>
            <div style="margin-top: 10px;">收录{{ info.orgCount || 0 }}个机构、{{ info.courseCount || 0 }}门课程</div>
        </div>
        <div class="searchInt">
            <el-input :placeholder="act === 0 ? '请输入机构名/统一社会信用代码' : '请输入标题'" v-model="keyWords" @keyup.enter.native="search">
                <el-button slot="append" icon="el-icon-search" type="warning" @click.stop="search">搜索</el-button>
            </el-input>
        </div>
    </div>
</template>
<script>
import { getAllOrgAndCourse } from '@/requestPub/api'
export default {
    props: {
        tablist: {
            type: Array,
            default: () => ['信用信息']
            // default: () => ['信用信息', '文章']
        },
        searchType: {
            type: Boolean,
            default: true
        },
        searchOrg: {
            type: Boolean,
            default: false
        },
    },
    data() {
        return {
            act: 0,
            keyWords: '',
            info: {}
        }
    },
    watch: {

    },
    mounted() {

        if (this.$route.query?.keyWords) {
            this.keyWords = this.$route.query?.keyWords;
            //     this.$nextTick(()=>{
            //         this.search()
            //    })
        }
        getAllOrgAndCourse().then(res => {
            this.info = res.data
        })
    },
    methods: {
        tap(i) {
            this.act = i
        },
        search() {
            if (this.$route.path == '/infoArtSearch') {
                return this.$emit('search', this.keyWords)
            }
            if (this.$route.path == '/infoOrgSearch') {
                return this.$emit('search', this.keyWords)
            }
            if (this.act === 0) {
                this.$router.push(`/infoOrgSearch?keyWords=${this.keyWords}`)
            } else {
                this.$router.push(`/infoArtSearch?keyWords=${this.keyWords}`)
            }
            console.log('1111', this.keyWords)
        }
    }
}
</script>
<style lang="scss" scoped>
$h: 50px;

.bannerSearch {
    background: url('~@/assets/bg/banner.png');
    height: 450px;
    background-repeat: no-repeat;
    width: 100vw;
    background-size: cover;
    margin-left: calc(calc(-100vw + 1200px) / 2);
    display: inline-flex;
    align-items: center;
    // justify-content: center;
    flex-direction: column;
    min-width: 1200px;
    margin: 0 auto;

    .logoTitle {
        padding: 80px 0 125px 0;
        color: #fff;
        font-size: 24px;
        display: inline-flex;
        align-items: center;

        img {
            width: 405px;
            height: 45px;
        }
    }

    .searchType {
        // width: 1000px;
        text-align: center;
        margin-bottom: 30px;

        span {
            font-size: 18px;
            color: #FFFFFF;
            margin: 0 30px;
            width: 80px;
            padding: 10px 15px;
            padding-bottom: 15px;
        }

        div {
            color: #fff;
            font-size: 20px;
            font-weight: 400;
            color: #FFFFFF;
        }

        .act {
            color: #3994FF;
            background: url('~@/assets/bg/searchAct.png');
            background-size: 100% 100%;
            background-repeat: no-repeat;
            transition: all .5s;

        }

        span:hover {
            cursor: pointer;
        }

    }

    .searchInt {
        height: $h;
        width: 50%;

        .el-input {
            height: 100%;

            ::v-deep input {
                height: 100%;
                border: 0;
                font-size: 16px;
            }
        }

        ::v-deep .el-input-group__append {
            background: #FC9A41;
            color: #fff;
            border: 0;
        }
    }
}</style>