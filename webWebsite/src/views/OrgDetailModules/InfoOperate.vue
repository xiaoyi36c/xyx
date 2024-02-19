
<template>
    <div class="Box">
        <div class="industyBox">
            <div class="industyItem" :class="actKey===i?'act':''" v-for="(item, i) in options" :key="i" @click="tap(i,item)">
                {{ item.title }}
            </div>
        </div>
        <!-- <template>
            <component :is="path"></component>
        </template> -->
        <template v-if="list.length">
            <div class="info">
                <el-image v-for="(v,i) in list" :key="i" :src="v.file_url" fit="cover"></el-image>
            </div>
            <el-pagination class="pagination" v-if="list.length" v-bind="pagination" @current-change="currentChange">  </el-pagination>
        </template>
        <template v-else>
            <el-empty></el-empty>
        </template>
     </div>
</template>
<script >
import {getSocialPrestige} from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins:[ListMinxins],
    components: {

    },
    data() {
        return {
            list:[],
            actKey: 0,
            options: [
                    { title: '企业荣誉',type:3 },
                    // { title: '公益履职',type:6 },
                    { title: '党建情况',type:7 },
                    { title: '信用管理培训',type:8 },
                ]
        }
    },
    mounted(){
        this.initData()
    },
    methods:{
        tap(i,item){
            this.actKey=i;
            this.pagination.currentPage=1;
            this.initData();
        },
        initData(){
            getSocialPrestige({
                type:this.options[this.actKey]['type'],
                orgId:this.$route.query.id,
                pageNum:this.pagination.currentPage,
                pageSize:this.pagination.pageSize,
            }).then(res=>{
                this.list  = res.rows
                this.pagination.total = res.total
            })
        }
    }
}

</script>
<style lang="scss" scoped>
.Box {
    background: #fff;
    border-radius: 5px;
    padding: 15px 0;
}
.info{
    width: 100%;
    display: inline-flex;
    justify-content: space-between;
    flex-wrap: wrap;
    margin-top: 20px;
    .el-image{
        width:calc(33.33% - 20px);
        margin-bottom: 20px;
        height:220px;
    }
    .el-image:nth-child(2n+2){

    }
}
.industyBox {
    display: inline-flex;
    width: 100%;
    position: relative;

    .industyItem {
        padding: 8px 15px;
        border: 1px solid #f2f2f2;
        font-size: 12px;
        box-sizing: border-box;

        span {
            font-size: 14px;
            font-weight: bold;
            color: #333333;
            display: block;
            text-align: center;
        }

        &:last-child {
            margin-right: 0;
        }

        &:hover {
            cursor: pointer;
        }
    }

    .act {
        transition: all .3s;
        font-weight: 500;
        color: #3994FF;
        // border: 1px solid #3994FF;
    }
}</style>
