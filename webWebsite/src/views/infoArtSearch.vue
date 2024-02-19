<template>
    <div class="Box">
        <!-- banner搜索 -->
        <bannerSearch  @search="searchFun" :searchOrg="true" />
        <div class="content">
            <!-- banner切换 -->
            <bannerTab >
                <template slot="content">
                    <template v-if="list.length">
                        <div style="height:20px"></div>
                        <listItem 
                            v-for="(v,i) in list" 
                            :item="v" 
                            :len="list.length" 
                            :ItemIndex="i+1"  
                            :key="i" 
                            @click.native="$router.push('/infoDetail?id='+v.id)"
                        />
                        <el-pagination 
                            class="pagination"
                            v-if="list.length" 
                            v-bind="pagination"
                            @current-change="currentChange"
                        >:
                        </el-pagination>
                    </template>
                    <template v-else>
                        <el-empty></el-empty>
                    </template>
                </template>
            </bannerTab>
        </div>
    </div>
</template>
<script>
import listItem  from "@/components/listBox/listItem.vue";
import bannerTab from "@/components/bannerBox/bannerTab";
import bannerSearch from "@/components/bannerBox/bannerSearch";
import {getArticleList } from '@/requestPub/api'
import { ListMinxins } from '@/mixins/ListMinxins'
export default {
    mixins:[ListMinxins],
    components: {
        listItem,bannerTab, bannerSearch, 
    },
    data() {
        return {
            keyWords:'',
            list: []
        };
    },
    computed: {

    },
    mounted(){
        if(this.$route.query?.keyWords){
            this.keyWords = this.$route.query?.keyWords
        }
        this.initData();
    },
    methods: {
        searchFun(val){
            this.keyWords = val
            this.initData();
        },
        initData(e){
            getArticleList({
                pageNum:this.pagination.currentPage,
                pageSize:this.pagination.pageSize,
                search:this.keyWords
            }).then(res=>{
                this.list = res.rows;
                this.pagination.total = res.total
            })
        },
        toDetail(item){
            console.log(1)
            this.$router.push('/infoOrgDetail')
        }
    }
};
</script>
<style lang="scss" scoped>
$h:32px;
.content{
  width: 1200px;
  margin: 0 auto;
}
.content {
    background: #fff;
    .cont {
        margin-top: 20px;
    }
}
</style>