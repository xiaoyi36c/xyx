<template>
    <div>
    <div class="list">
        <el-image :src="item.enterprise_logo" fit="cover"></el-image>
        <div class="bannerArt">
            <div class="content">
                {{item.organization_name}}
            </div>
            <!-- <div class="title">
                <span>信用健康分：{{item.credit_score || 0}} 分</span>
                <span>信用评级：{{item.credit_rank}}</span>
                <span v-if="item.tag">{{item.tag}}</span>
            </div> -->
            <div class="info">
                <i class="el-icon-location"></i>
                <span>{{item.area_detail || '暂无'}}</span>
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
                // justify-content: space-between;
                span {
                    background: rgba($color: #FFF2E6, $alpha: .8);
                    font-size: 12px;
                    text-align: center;
                    display: inline-block;
                    padding: 3px 5px;
                    color: #FA8C24;
                    border-radius: 2px;
                    margin-right: 20px;
                }
                span:nth-child(2){
                    color:#43CA47;
                    background: rgba(59,212,64,0.1);
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