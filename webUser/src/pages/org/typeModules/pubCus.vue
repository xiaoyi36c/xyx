<template>
	<view class="content">
		<template v-if="list.length">
			<scroll-view class="boxCont"
				@scrolltolower="loadMore"
				scroll-y
				:style="{
					height:`calc(100vh  - 280upx  + 88upx)` 
				}">
				 <template v-if="currIndex ==0">
					 <listTeacherItem :item="item" v-for="(item,i) in list" :key="i"/>
				 </template>
				 <template v-if="currIndex ==1">
					 <listLessonItem :item="item" v-for="(item,i) in list" :key="i"/>
				 </template>
				 <template v-if="currIndex ==2">
					 <listRateItem :rateInfo="rateInfo" :list="list"/>
				 </template>
				 <template v-if="currIndex ==3">
					 <listTousuItem @click.native="TousuDetail(item)" :item="item" v-for="(item,i) in list" :key="i"/>
				 </template>
			</scroll-view>
		</template>
		<template v-else>
			<view :style="{
					height:`calc(100vh  - 280upx  + 88upx)` 
				}">
			<empty ></empty>
			</view>
		</template>
	</view>
</template>

<script>
	import listTeacherItem from '../listModules/listTeacherItem.vue'
	import listLessonItem from '../listModules/listLessonItem.vue'
	import listRateItem from '../listModules/listRateItem.vue'
	import listTousuItem from '../listModules/listTousuItem.vue'
	import {
		publicMixin
	} from '@/mixins/publicMixin.js';
	export default {
		mixins: [publicMixin],	
		components:{
			listTeacherItem,
			listLessonItem,
			listRateItem,
			listTousuItem
		},
		data() {
			return {
				rateInfo:[],
				list:[],
				currIndex:0,//显示分类
			};
		},
		methods:{
			loadMore() {
				console.log('已触底');
				let that = this;
				if (this.timer) clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					this.pageNation.pageNum += 1;
					this.getChildList()
				}, 500)
			},	
			TousuDetail(v){
				uni.navigateTo({
					url:'/pages/org/TousuDetail?id='+v.pkid
				})
			},
			initData(type,i){
				this.status = 'loadmore'
				this.currIndex = i;
				this.pageNation.pageNum = 1;
				this.list=[];
				let urls =['getTeachersPower','getCourse','getOrgEvaluate','getComplaintByOrg'];
				this.url = urls[i];
				if(i==2){
					this.$api.get(this.Interface.getAppraiseById,{
						orgId:this.$route.query.id
					}).then(res=>{
						this.rateInfo = res.data;
					})
				}
				this.getChildList();
			},
			getChildList(){
				if(this.status == 'nomore' ) return;
				this.$api.get(this.Interface[this.url],{
					orgId: this.$route.query.id,
					...this.pageNation
				}).then(res=>{
					if(this.currIndex == 2){
						this.list = this.list.concat(res.data.appraiseList.rows);
						if (this.list.length >= res.data.appraiseList.total) this.status = 'nomore';
						else this.status = 'loadmore';
					}else{
						this.list = this.list.concat(res.rows);
						if (this.list.length >= res.total) this.status = 'nomore';
						else this.status = 'loadmore';
					}

				})
			},
		}
	}
</script>

<style lang="scss" scoped>
.content{
	padding: 15upx 20upx;
	background: #ffffff;
	.line{
		display: inline-flex;
		width:100%;
		font-size:25upx;
		.left{
			padding: 15upx;
			width:300upx;
			border: 1px solid #E6E6E6;
			display: inline-flex;
				align-items: center;
				border-right: 0;
				background: #F6FAFF;
		}
		.right{
			padding: 15upx 10upx;
			flex:1;
			border: 1px solid #E6E6E6;
		}
	}
}
</style>
