<template>
    <div>


    <div class="list">
        <template v-if="item?.url">
            <el-image :src="src" fit="cover"></el-image>
        </template>
       <template v-else>
            <div class="imgeTxt">
                {{item.title}}
            </div>
       </template>
        <div class="bannerArt">
            <div class="content">
               {{item.cont}}
            </div>
        </div>
    </div>
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
    width: 100%;
    border: 1px solid #F2F2F2;
    .el-image,.imgeTxt{
        width: 100%;
        text-align: center;
        line-height: 280px;
        height:280px;
        background-color: #e7afaf;
        background-image: -webkit-linear-gradient(left, #41b3cd, #3994ff, #2989ed);
        /* -webkit-background-clip: text; */
        /* -webkit-text-fill-color: transparent; */
        font-size: 56px;
        font-weight: 700;
        color: #fff
    }
    .bannerArt {
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
                padding:0 10px;
                line-height: 22px;
                display: -webkit-box;
                -webkit-box-orient: vertical;
                -webkit-line-clamp:4;
                margin: 10px 0;
            }

            .detail {
                text-align: right;
                font-size: 14px;
                color: #3994FF;

            }
        }
}

</style>