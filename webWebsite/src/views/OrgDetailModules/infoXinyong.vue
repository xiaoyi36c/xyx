<template>
    <div class="Box">
        <div class="industyBox">
            <div class="industyBoxItem">
                <div class="industyItem" :class="actKey === i ? 'act' : ''" v-for="(item, i) in options" :key="i"
                    @click="tap(i, item)">
                    {{ item.title }}
                </div>
            </div>
            <!-- <div class="industyItem actOther" @click="downLoad()">
                <i class="el-icon-download"></i>下载信用信息报告
            </div> -->
        </div>
        <template v-show="list.length">
            <pubItem ref="pubItem" :options="childOptions" :list="list" @change="childTap"/>
            <el-pagination class="pagination" v-if="list.length" v-bind="pagination" @current-change="currentChange">  </el-pagination>
        </template>
        <template v-if="list.length==0">
            <el-empty></el-empty>
        </template>
    </div>
</template>
<script >
import {
    pubItem
} from '@/components/CusXinyong'
import {getAdministrativePenalty,
getHonestyTrustworthiness,
getLoseCredit,
getCreditCommitment,getChildType,getFeeOther} from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins:[ListMinxins],
    props:['info'],
    components: {
        pubItem
    },
    data() {
        return {
            childOptions: [],
            list: [],
            actKey: 0,
            options: [
                { title: '行政管理', type: '1' },
                { title: '诚实守信', type: '2' },
                { title: '严重失信名单', type: '3' },
                { title: '信用承诺', type: '4' },
                { title: '信用评价', type: '5' },
                { title: '司法判决', type: '6' },
                { title: '其他', type: '7' },
            ],
            childKey:'',//子类
        }
    },
    mounted(){
        //初始化大分类选中
        this.tap(0, this.options[0]);
    },
    methods: {
        //子分类点击事件
        childTap(i){
            this.childKey = i;
            this.initData()
        },
        //大分类点击事件
        tap(i, item) {
            this.actKey = i;
            this.pagination.currentPage=1;
            this.$refs['pubItem'].actKey=0;
            this.childKey='';
            getChildType({
                type:item.type,
                orgId: this.$route.query.id,
            }).then(res=>{
                this.childOptions = res.data;
            }).then(()=>{
                this.childTap(this.childOptions.length?this.childOptions[0]['key'] : '');
            })
        },
        downLoad() {
        
            window.open(this.info.credit_information_report)
        },
        initData(){
            //行政管理
            if(this.actKey == 0){
                getAdministrativePenalty({
                    type:this.childKey,
                    orgId:this.$route.query.id,                
                    pageNum:this.pagination.currentPage,
                    pageSize:this.pagination.pageSize,
                }).then(res=>{
                    this.list = res.rows
                    this.pagination.total = res.total
                })
            }
            //诚实守信
            else if(this.actKey == 1){
                getHonestyTrustworthiness({
                    type:this.childKey,
                    orgId:this.$route.query.id,                
                    pageNum:this.pagination.currentPage,
                    pageSize:this.pagination.pageSize,
                }).then(res=>{
                    this.list = res.rows
                    this.pagination.total = res.total
                })
            }
            //严重失信名单
             else if(this.actKey == 2){
                getLoseCredit({
                    type:this.childKey,
                    orgId:this.$route.query.id,                
                    pageNum:this.pagination.currentPage,
                    pageSize:this.pagination.pageSize,
                }).then(res=>{
                    this.list = res.rows
                    this.pagination.total = res.total
                })
            }
            //信用承诺
             else if(this.actKey == 3){
                getCreditCommitment({
                    type:this.childKey,
                    orgId:this.$route.query.id,                
                    pageNum:this.pagination.currentPage,
                    pageSize:this.pagination.pageSize,
                }).then(res=>{
                    this.list = res.rows
                    this.pagination.total = res.total
                })
            }
            //信用评价
             else if(this.actKey == 4){
                
                this.list=[];
                this.pagination.total =0
            }
            //司法判决
             else if(this.actKey == 5){
                this.list=[];
                this.pagination.total =0
            }
            //其他
             else if(this.actKey == 6){                
                getFeeOther({
                    type:this.childKey,
                    orgId:this.$route.query.id,                
                    pageNum:this.pagination.currentPage,
                    pageSize:this.pagination.pageSize,
                }).then(res=>{
                    this.list = res.rows
                    this.pagination.total = res.total
                })
            }

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

.industyBox {
    // display: inline-flex;
    width: 100%;
    position: relative;

    .industyBoxItem {
        display: inline-flex;
        position: relative;
    }

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

    .actOther {
        padding: 3px 15px;
        font-weight: 500;
        color: #fff;
        background: #3994FF;
        border: 1px solid #3994FF;
        float: right;
        border-radius: 5px;
    }
}</style>