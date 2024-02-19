<template>
    <div class="Box">
        <div class="industyBox">
            <span class="industyItem"
                style="color: #333!important;font-weight:bold;font-size:15px;margin-right: 5px;">行业分类：</span>
            <div class="industyItem" :class="actKey == i ? 'act' : ''" v-for="(item, i) in options" :key="i"
                @click="actKeyFun(i, item)">
                <!-- <el-image :src="require('@/assets/menu2-bg.png')"></el-image> -->
                <span>{{ item.industryName }}</span>
            </div>
        </div>
        <div class="chooiseBox" v-if="options.length && actKey != '-' && options[actKey]['children']">
            <span class="title">细分行业：</span>
            <div style="width:100%">
                <span :key="i" v-for="(item, i) in options[actKey]['children']" :class="typeIndusty == i ? 'act' : ''"
                    @click="typeIndustyFun(i, item)">
                    {{ item.industryName }}
                </span>
            </div>
        </div>
        <div class="chooiseBox" v-if="optionsArea.length">
            <span class="title">区域：</span>
            <div style="width:100%">
                <span v-for="(item, i) in optionsArea" :key="i" :class="areaKey == i ? 'act' : ''" @click="areaKeyFun(i, item)">
                    {{ item.name }}
                </span>
            </div>
        </div>
        <slot name="cont"></slot>
    </div>
</template>
<script >
import { getIssueindustryTree, getCurrArea } from "@/requestPub/api";
export default {
    data() {
        return {
            actKey: this.$route.query?.actKey || '-',
            typeIndusty: this.$route.query?.typeIndusty || "-",//行业选择
            areaKey: this.$route.query?.areaKey || "-",//选中的区域
            options: [],
            optionsArea: [],
            query: '',
            actKeyId: '',
        }
    },
    mounted() {
        this.query = JSON.parse(JSON.stringify(this.$route.query))
        this.initIntry()
    },
    methods: {
        //区域分类
        areaKeyFun(i, item) {
            this.areaKey = i;
            this.query.areaKey = i;
            this.query.areaId = item.id;
            this.changeUrlData()
        },
        //切换行业分类
        actKeyFun(i, item) {
            this.actKey = i
            this.query.actKey = i;
            this.query.actKeyId = item.id;
            this.actKeyId = item.id;
            this.typeIndusty = '-'
            this.query.typeIndusty = '';
            this.query.typeIndustyId = '';
            this.areaKey = '-'
            this.query.areaId = ''
            this.changeUrlData()
        },
        //细分行业
        typeIndustyFun(i, item) {
            this.typeIndusty = i;
            this.query.typeIndusty = i;
            this.query.typeIndustyId = item.id;
            this.areaKey = '-'
            this.actKeyId = ''
            this.query.areaId = ''
            this.changeUrlData()
        },
        changeUrlData() {

            this.$emit('change', this.query)
            // if(JSON.stringify(this.query) == JSON.stringify(this.$route.query)) {
            //     this.$emit('change')
            // };
            // console.log('this.query===',this.query)
            // let Link =  this.$router.push(
            //     { 
            //         path:'/infoOrgSearch', 
            //         query: this.query
            //     }
            // );
            //  window.open(Link.href, '_blank')
        },
        initCity(obj) {
            if(obj?.cityVal != this.query.cityVal){
                getCurrArea({
                    pid: obj?.cityVal.split(',')[1]
                }).then(res => {
                    this.optionsArea = res.data;
                })
            }
                //切换城市，初始化行业分类
                this.actKey = 0;
                obj.actKey =0;
                //初始化细分行业
                this.typeIndusty = '-';
                obj.typeIndusty = '';
                obj.typeIndustyId = '';
                obj.actKeyId = '';
                //初始化区域
                this.areaKey = '-';
                obj.areaKey = '';
                obj.areaId = '';
                //重新赋值query
                this.query = obj;
                this.$forceUpdate()
                this.changeUrlData()
        },
        initIntry() {
            getIssueindustryTree().then(res => {
                this.options = res.data;
            });
            if (this.$route.query?.actKeyId) {
                this.actKeyId = this.$route.query?.actKeyId
            }
            if (this.$route.query?.cityVal) {
                this.initCity(this.$route.query)
            }
        },
    }
}

</script>
<style lang="scss" scoped>
.Box {
    background: #fff;
    border-radius: 5px;
    padding: 15px;
}

.industyBox {
    display: inline-flex;
    width: 100%;
    position: relative;
    border-bottom: 1px solid #f2f2f2;

    .industyItem {
        margin-right: 30px;
        border-bottom: 2px solid transparent;
        text-align: center;
        color: #999999;

        .el-image {
            width: 50px;
            height: 50px;
        }

        span {
            font-size: 14px;

            display: block;
            text-align: center;
            padding-bottom: 5px;
        }

        &:last-child {
            margin-right: 0;
        }

        &:hover {
            cursor: pointer;
        }
    }

    .act {
        font-weight: bold;
        color: #333 !important;
        transition: all .3s;
        border-bottom: 2px solid #3994FF;
    }
}

.chooiseBox {
    display: inline-flex;
    width: 100%;
    align-items: flex-start;
    margin-top: 15px;

    .title {
        font-size: 15px;
        font-weight: 600;
        color: #333333;
        line-height: 25px;
        display: inline-block;
        width: 90px;
    }

    >div {
        >span {
            font-size: 12px;
            font-weight: 400;
            color: #666666;
            margin-right: 20px;
            border: 1px solid #f2f2f2;
            width: 80px;
            text-align: center;
            padding: 3px 2px;
            display: inline-block;
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;

            &:hover {
                cursor: pointer;
                background-color: #3994FF;
                color: #fff;
                transition: all .5s;
            }

            &:last-child {
                margin-right: 0px;
            }
        }

        .act {
            color: #3994FF;
            border: 1px solid #3994FF;
        }

    }
}</style>