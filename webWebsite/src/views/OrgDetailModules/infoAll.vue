<template>
    <div class="Box">
        <div class="industyBox">
            <div class="industyItem" :class="actKey == i ?'act':''" v-for="(item,i) in options" :key="i" @click="toChange(i)">
                <el-image :src="require('@/assets/orgDetail/'+item.path +(actKey == i?'_r':'')+'.png')"></el-image>
                <span :class="actKey == i ?'act':''">{{item.title}}</span>
            </div>
        </div>
        <slot name="cont"></slot>
    </div>
</template>
<script >
export default{
    data() {
        return {
            actKey:0,
            options:[{title:'简介',path:'dre'},{title:'服务水平',path:'fuwu'},{title:'信用信息',path:'xinyong'},{title:'企业荣誉与社会责任',path:'rongyu'}]
        }
    },
    methods:{
        toChange(i){
            this.actKey = i;
            this.$emit('change',this.actKey)
        }
    }
}

</script>
<style lang="scss" scoped>
.Box{
    background: #fff;
    border-radius: 5px;
    padding: 15px;
    margin-top: 15px;
}
.industyBox{
    display: inline-flex;
    width:100%;
    position: relative;
    border-bottom: 1px solid #f2f2f2;
    .industyItem{
        margin-right:50px;
        border-bottom: 2px solid transparent;
        text-align: center;
        .el-image{
            width: 45px;
            height: 45px;
        }
        span{
            font-size: 14px;
            display: block;
            text-align: center;
            margin-top: 3px;
            padding-bottom: 5px;
        }
        &:last-child{
            margin-right:0;
        }
        &:hover{
            cursor: pointer;
        }
    }
    .act{
        transition: all .5s;
        color:#3994FF;
        font-weight: bold;
        border-bottom: 2px solid #3994FF;
    }
}
</style>