<template>
    <div class="Box">
        <bannerSearch :searchOrg="true"  @search="searchFun"/>
        <div class="content">
            <!-- banner搜索 -->
            <!-- 城市选择 -->
            <cityChooise @change="cityChange" />
            <!-- 站位 -->
            <div style="height:20px"></div>
            <!-- 行业 -->
            <industyChooise ref="industyChooise" @change="industyChooiseChange">
                <!-- 内容 -->
                <template slot="cont">
                    <template v-if="list.length">
                        <listCarditem v-for="(v, i ) in list" :key="i" :len="list.length" :ItemIndex="i + 1" :item="v"  @click.native="toDetail(v)"/>
                        <el-pagination 
                            class="pagination"
                            v-if="list.length" 
                            v-bind="pagination"
                            @current-change="currentChange"
                        >
                        </el-pagination>
                    </template>
                    <template v-else>
                        <el-empty></el-empty>
                    </template>
                </template>
            </industyChooise>

        </div>
    </div>
</template>
<script>
import bannerSearch from "@/components/bannerBox/bannerSearch";
import cityChooise from './OrgModules/cityChooise'
import industyChooise from './OrgModules/industyChooise'
import listCarditem from './OrgModules/listCarditem'
import { ListMinxins } from '@/mixins/ListMinxins'
import { getOrgJoinList } from "@/requestPub/api";
export default {
    mixins:[ListMinxins],
    components: {
        bannerSearch, cityChooise,
        industyChooise, listCarditem
    },
    data() {
        return {
            keyWords:'',
            list: []
        };
    },
    computed: {

    },
    watch:{
        "$route":{
            handler(){
                console.log('-----',this.$route.query)
                if(this.$route.query?.keyWords){
                    this.keyWords = this.$route.query?.keyWords;
                }
                this.$refs['industyChooise'].query = this.$route.query;
                this.$refs['industyChooise'].initIntry()
                this.initData();
            },
            deep:true,
            // immediate:true
        }
    },
    mounted(){
        if(this.$route.query?.keyWords){
            this.keyWords = this.$route.query?.keyWords
        }
        this.initData();
    },
    methods: {
        industyChooiseChange(e){
            console.log('e=====',e)
            let ret = ''
            Object.keys(e).map((v)=>{
                ret += v+'='+e[v] +'&'
            })
            console.log(this.$route.path)
            const newUrl = '/#'+this.$route.path + `?${ret}`
            window.history.replaceState('', '', newUrl);
            this.$refs['industyChooise'].query = e;
            // this.$refs['industyChooise'].initIntry()
            this.initData(e);
        },
        cityChange(e){
            // this.$refs['industyChooise'].query = e;
            this.$refs['industyChooise'].initCity(e)
            // this.industyChooiseChange(e)
        },
        searchFun(val){
            this.keyWords = val
            this.initData();
        },
        initData(query){
            // omit：省，city：市，industry：行业，search：机构名字或者统一信用码
            getOrgJoinList({
                pageNum:this.pagination.currentPage,
                pageSize:this.pagination.pageSize,
                search:this.keyWords,
                omit:query?.cityVal ?query.cityVal.split(',')[0]:'',
                city:query?.cityVal ?query.cityVal.split(',')[1]:'',
                area:query?.areaId,
                industry:query?.typeIndustyId || query?.actKeyId,
            }).then(res=>{
                this.list = res.rows;
                this.pagination.total = res.total
            })
        },
        toDetail(item){
            console.log(1)
            this.$router.push('/infoOrgDetail?id='+item.id)
        }
    }
};
</script>
<style lang="scss" scoped>

.content{
  width: 1200px;
  margin: 0 auto;
}

.content {
    .cont {
        margin-top: 20px;
    }
}
</style>