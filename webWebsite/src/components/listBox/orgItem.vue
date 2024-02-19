<template>
    <div>


    <div class="list">
        <el-image :src="src" fit="cover"></el-image>
        <div class="bannerArt">
            <div class="content">
                这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文这里是正文
            </div>
            <div class="title">
                <span>信用健康分：888 分</span>
                <span>信用评级：A+</span>
                <span>科技金融</span>
            </div>
            <div class="info">
                <i class="el-icon-address"></i>
                <span>成都市成华区99号</span>
            </div>
        </div>
    </div>
    <el-divider v-if="len>ItemIndex"></el-divider>
    </div>
</template>
<script>
export default{
    props:['ItemIndex','len','item'],
    data() {
        return {
            src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg'
        }
    },
}
</script>
<style lang="scss" scoped>
.el-divider {
    background-color: #F2F2F2;
    padding: 0 5px;
    width: calc(100% - 30px);
    margin: 15px auto;
}
.list{
    display: inline-flex;
    width: calc(100% - 20px);
    padding:10px;
    .el-image{
        width: 90px;
        height: 65px;
    }
    .bannerArt {
            padding:0 0 0 10px;
            .title {
                display: inline-flex;
                width: 100%;
                margin-bottom: 0px;
                justify-content: space-between;
                span {
                    background: rgba($color: #FFF2E6, $alpha: .8);
                    font-size: 12px;
                    text-align: center;
                    display: inline-block;
                    padding: 3px 5px;
                    color: #FA8C24;
                    border-radius: 5px;
                }
                span:nth-child(2){
                    color:#43CA47;
                    background: #cce9cd;
                }
                span:nth-child(3){
                    color:#3994FF;
                    background: #EDF5FF;
                }
            }

            .info {
                width: 100%;
                font-size: 12px;
                color: #999999;
                margin-top: 3px;
            }

            .content {
                margin-bottom: 3px;
                text-overflow: ellipsis;
                overflow: hidden;
                font-size: 14px;
                white-space: nowrap;
                width: 270px;
            }

            .detail {
                text-align: right;
                font-size: 14px;
                color: #3994FF;

            }
        }
}

</style>