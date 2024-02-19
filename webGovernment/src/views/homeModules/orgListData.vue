<template>
    <div class="box">
        <div class="title">机构名单公示</div>
        <div class="tabBox">
            <span @click.stop="creditstatus=1,handleClick()" :class="creditstatus==1?'act':''">信用良好</span>
            <span @click.stop="creditstatus=2,handleClick()" :class="creditstatus==2?'act':''">信用异常</span>
            <span @click.stop="creditstatus=3,handleClick()" :class="creditstatus==3?'act':''">严重失信</span>
        </div>
        <div class="content">
            <el-table 
                :data="dataList" 
                height="258px"
                class="contTable" 
                highlight-current-row 
                ref="rolltable"
                @mouseenter.native="stopScroll" 
                @mouseleave.native="startScroll" 
                style="width: 100%"
            >
                <el-table-column label="排名"  type="index" width="80" align="center">
                </el-table-column>
                <el-table-column prop="organization_name" label="机构名称" width="230">
                </el-table-column>
                <el-table-column prop="cityName" label="所在省市" width="140">
                    <template slot-scope="scope">
                        <span>{{scope.row.omitName}} - {{scope.row.cityName}}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="credit_score" label="信用值">
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<script>
import { getOrgList } from '@/requestPub/api'
export default {
    data() {
        return {
            creditstatus:'',
            dataList: [],
            type:'',
            regionId:'',
            rolltimer:null
        }
    },
    components: {

    },
    computed: {

    },
    mounted() {
    //    this.initData()
    },

    methods: {
        handleClick(){
           console.log('this.type======',this.type,this.regionId)
           this.initData(this.type,this.regionId); 
        },
        initData(type =1,regionId=0){
            this.type=type;
            this.regionId=regionId;
           if(this.rolltimer) clearInterval(this.rolltimer);
           console.log('type======',type,regionId)
            // creditstatus[信用状态:1: 良好，2:一般，3: 轻微失信，4: 严重失信]
            getOrgList({
                type,
                regionId,
                creditStatus:this.creditstatus
            }).then(e=>{
                this.dataList = e.rows
            }).then(()=>{
                this.tableScroll(false)
            })
        },
        startScroll() {
            this.tableScroll(false);
        },

        stopScroll() {
            this.tableScroll(true);
        },

        //滚动方法
        tableScroll(stop) {
            if(this.dataList.length<=7)return  clearInterval(this.rolltimer);
            if (stop) {
                clearInterval(this.rolltimer)
                return
            }
            const table = this.$refs.rolltable
            const divData = table.bodyWrapper
           this.rolltimer = setInterval(() => {
                divData.scrollTop += 1
                if (divData.clientHeight + divData.scrollTop + 1 > divData.scrollHeight) {
                    if (table.tableData.length > 5) {
                        divData.scrollTop = 0
                    }
                }
            }, 45)
        }
    }
}
</script>
<style lang="scss" scoped>
::v-deep .el-table__empty-text{
    color:#fff;
}
::v-deep .el-tabs__header{
    margin: 0;
    .el-tabs__item{
        color:#fff;
    }
    .el-tabs__item.is-active{
        color:#409EFF;
    }
}
::v-deep .el-table::before{
    display: none;
}
::v-deep .el-table tr{
    &:nth-child(1){
        td:nth-child(1){
            color:#FC7019;
        }
    }
    &:nth-child(2){
        td:nth-child(1){
            color:#D54B86;
        }
    }
    &:nth-child(3){
        td:nth-child(1){
            color:#006ADE;
        }
    }
    background-color: transparent;
    color:#fff;
    .el-table__cell{
        border-color:rgba(255, 255, 255, 0.1);
        border: 0;
       div{
        white-space: nowrap;
       }
    }
}
::v-deep .el-table--enable-row-hover .el-table__body tr:hover>td.el-table__cell{
    background:rgba(255, 255, 255,.3);
}
::v-deep .el-table thead{
    color:rgba(154, 168, 212, 1);
    .el-table__cell{
        padding:7px 0;
        background: rgba(8, 90, 111, 0.2);
        border:0;
    }
}
::v-deep .el-table__body>tbody{
        .el-table__cell{
            padding:5px 0;
            font-size: 13px;
        }
        tr {
            background: rgba(14, 86, 241, 0.08);
        }
        tr:nth-child(2n+1){
            background: rgba($color: #054F81, $alpha:0.31);
        }
    }
.box {
    border: 1px solid transparent;
    margin: 5px;
    padding-top: 10px;
    .title {
        font-size: 15px;
        font-weight: 500;
        padding-left: 10px;
        color: #FFFFFF;
        &::before{
            display: inline-block;
            content:'';
            width: 8px;
            height: 8px;
            transform: rotate(45deg);
            margin-right: 10px;
            border: 2px solid #8BD2E6;
        }
    }

    .tabBox {
        border: 1px solid #2F778D;
        width: fit-content;
        margin: 10px 0;
        margin-left: 5px;
        span {
            display: inline-block;
            width:100px;
            margin-right: 10px;
            padding:1px 5px;
            font-size: 12px;
            line-height: 20px;
            color:#2F778D;
            text-align: center;
            cursor: pointer;
        }
        .act{
            transition: all .3s;
            background: linear-gradient(0, #145C77 0%, rgba(9,54,85,0) 100%);
            color:#84F2FF;
            font-size: 14px;
        }
    }

    .content {
        width: calc(100% - 10px);
        padding: 5px;
        
         .contTable {
            background: transparent;
        }
    }

    .item {
        padding: 8px;
    }
}
</style>