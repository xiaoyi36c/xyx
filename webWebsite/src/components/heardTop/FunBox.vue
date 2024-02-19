<template>
    <div class="FunBox">
        <div class="item">
          <router-link to="/">
            <i class="el-icon-s-home" style="font-size:30px;"></i>
            <span>返回首页</span>
          </router-link>
        </div>
    </div>
</template>
<style lang="scss" scoped>
.FunBox{
   
    position: fixed;
    right: 8px;
    height: auto;
    background: #fff;
    bottom: 25vh;
    box-shadow: -2px 3px 8px 2px #ccc;
    border-radius: 7px;
    .item{
        font-size: 15px;
        text-align: center;
        padding: 10px 5px;
        a{
            color:#333;
            text-decoration: none;
        }
        span{
            font-size: 11px;
            display: block;
        }
        &:hover{
            transform: scale(1.02);
            transition: all 0.5s;
        }
    }
}
</style>