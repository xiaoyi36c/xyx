<template>
    <div class="box">
        <div class="title">信息公示</div>
        <div class="cont">
            <div>
                <el-image :src="require('@/assets/other/icon_xzgl.png')" fit="cover"></el-image>
                <span>行政管理</span>
            </div>
            <div>
                <el-image :src="require('@/assets/other/icon_xzgl.png')" fit="cover"></el-image>
                <span>严重交通违法</span>
            </div>
            <div>
                <el-image :src="require('@/assets/other/icon_xzgl.png')" fit="cover"></el-image>
                <span>严重违法失信</span>
            </div>
        </div>
    </div>
</template>
<script>
</script>
<style lang="scss" scoped>
.box {
    padding: 15px;
    background: #fff;

    .title {
        font-size: 18px;
        font-weight: 600;
        color: #333333;
        border-bottom: 1px solid #F2F2F2;
    padding-bottom: 10px
    }

    .cont {
        background: #F2F8FF;
        width: calc(100% - 40px);
        margin-top: 15px;
        padding: 20px;
        display: inline-flex;
        justify-content: space-between;

        >div {
            text-align: center;

            .el-image {
                width: 80px;
                height: 60px;
            }

            span {
                display: block;
                font-size: 15px;
                font-weight: 400;
                color: #333333;
            }
        }

        >div:nth-child(2) {
            margin: 0 10px;
        }
    }
}</style>