<template>
    <div class="Box">
      <template v-if="info.org_synopsis">
        {{info.org_synopsis}}
      </template>
      <template v-else>
        <el-empty></el-empty>
      </template>
    </div>
</template>
<script >
import {getOrgDetail} from '@/requestPub/api'
export default{
    data() {
        return {
            info:{}
        }
    },
    mounted(){
        getOrgDetail({id:this.$route.query.id}).then(res=>{
            this.info = res.data
        })
    }
}

</script>
<style lang="scss" scoped>
.Box{
    background: #fff;
    border-radius: 5px;
    padding: 15px;
    border: 1px solid #f2f2f2;
    margin-top: 20px;
}
</style>