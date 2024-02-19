<template>
	<view class="content">
		<view class="backTop" @click="backTop" v-if="showTop">
			<u-icon name="arrow-up"></u-icon>
			<text>Top</text>
		</view>
		<view class="topbox">
			<view class="top">
				<view class="title">
					<region-picker @chooseCity="refresh"></region-picker>
				</view>
				<view class="searchBox">
					<u-icon name="search" style="margin-right: 10upx;"></u-icon>
					<input @blur="refresh" v-model="search"  placeholder="输入机构名/统一社会信用代码" prefixIcon="search"></input>
				</view>
			</view>
			<view class="">
				<u-tabs active-color="#3994FF" keyName="industryName" :scrollable="false" :list="tabList" :current="current" @change="tapTab"></u-tabs>
			</view>
			<view  class="contentChild" v-if="Childlist.length">
				<template >
					<text @click="tapChild(item,index)" :class="index===actIndex?'act':''" v-for="(item,index) in Childlist"
						:key="index">{{item.industryName}}</text>
				</template>
			</view>
		</view>
		<view class="" :style="{
			height:`calc(278upx - ${Childlist.length ? 0:100}upx)`
		}">
			
		</view>
		<view class="listItemBox"  v-if="list.length">
			<template>
				<listItem 
					@click.native="toDre(item)" 
					v-for="(item,index) in list"
					:key="index"
					:item="item"
				/>
				<u-loadmore :status="status" />
			</template>
		</view>
		<empty v-else></empty>
	</view>
</template>

<script>
	import {
		publicMixin
	} from '@/mixins/publicMixin.js';
	import  listItem from './listModules/listItem.vue'
	export default {
		components:{
			listItem
		},
		mixins: [publicMixin],
		data() {
			return {
				current: 0,
				actIndex: '-',
				tabList: [],
				Childlist: [],
				list:[],
				orgType:'',
				industry:'',
				topIndustry:'',
				search:'',
			};
		},
		mounted(){
			console.log(getApp().globalData.city)
			this.orgType = this.$route.query.orgType;
			this.search = this.$route.query.search;
			this.initInstry().then(()=>{
				this.initData();
			})
		},
		onPageScroll(e){
			console.log(e)
			this.scrollTop=e.scrollTop;
		},
		onPullDownRefresh() {
			this.refresh()
		},
		onReachBottom(){
			console.log('已触底');
			let that = this;
			if(this.timer) clearTimeout(this.timer)
			this.timer = setTimeout(()=>{
				this.pageNation.pageNum +=1;
				this.initData()
			},500)
		},
		methods: {
			refresh(){
				this.pageNation.pageNum =1;
				this.timer=null;
				this.status='loadmore';
				this.list=[];
				this.initData();
				uni.stopPullDownRefresh();
			},
			async initInstry(){		
				await this.$api.get(this.Interface.getIssueindustryTree).then(e=>{
					this.tabList = e.data.map(res=>{res.name=res.industryName;return res});
					if(this.tabList.length){
						this.Childlist = this.tabList[this.current]?.children || []
						this.topIndustry = this.tabList[this.current]?.id || ''
					}
				})
			},
			initData(){
				if(this.status == 'nomore' ) return;
				this.$api.get(this.Interface.orgList,{
					city:getApp().globalData.city.value,
					level:getApp().globalData.city.value?2:'',
					search:this.search,
					orgType:this.orgType,
					industry:this.industry,
					topIndustry:this.topIndustry,
					...this.pageNation
				}).then(e=>{
					console.log(e,'e====')
					this.list=this.list.concat(e.rows);
					if(this.list.length >= e.total) this.status = 'nomore';
					else this.status = 'loadmore';
				}).catch(err=>{
					console.log(err)
				})
			},
			tapChild(v,index){
				this.actIndex = index;
				this.industry = v.id;
				this.refresh();
			},
			tapTab(i) {
				console.log(i)
				this.current = i;
				this.industry='';
				this.actIndex='-';//初始化子行业
				this.topIndustry=this.tabList[i]?.id || '';
				this.Childlist = this.tabList[i]?.children || [];
				this.status = 'loadmore';
				this.list=[];
				this.initData();
			},
			toDre(v){
				uni.navigateTo({
					url:`/pages/org/orgDetail?id=${v.id}`
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		// height: 100vh;
		.topbox{
			position: fixed;
			top:0;
			width:100%;
			background: #fff;
			z-index: 11;
		}
	}

	.top {
		padding: 15upx 20upx;
		width: 100%;
		display: inline-flex;
		align-items: center;
		background: #ffffff;

		.title {
			font-size: 25upx;
			white-space: nowrap;
			min-width: 100upx;
			text-overflow: ellipsis;
			overflow: hidden;

			text {
				margin-right: 5upx;
			}
			.arrow-down {
				width: 36rpx;
				height: 38rpx;
			}
		}

		.searchBox {
			flex: 1;
			display: inline-flex;
			background: #F5F5F5;
			padding: 10upx 15upx;
			border-radius: 30upx;

			::v-deep .uni-input-wrapper {
				font-size: 28upx;
			}
			::v-deep uni-input{
				width: calc(100% - 80upx);
			}
		}
	}

	.contentChild {
		background: #ffffff;
		// margin-top: 20upx;
		padding: 15upx 20upx;
		white-space: nowrap;
		overflow-x: auto;
		height: fit-content;
		width: 100%;
		overflow-x: auto;
		text {
			background: #F5F5F5;
			border-radius: 13px;
			padding: 0upx 20upx;
			border-raduis: 10upx;
			color: #666666;
			font-size: 25upx;
			// height: 50upx;
			line-height: 50upx;
			margin-right: 20upx;
			display: inline-block;
		}

		.act {
			background: #3994FF;
			color: #FFFFFF;
			transition: .2s all;
		}
	}
	.listItemBox{
		background: #ffffff;
		padding: 15upx 20upx;
		// margin-top: 280upx;
		// heigh;
	}
</style>