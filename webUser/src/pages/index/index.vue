<template>
	<view class="">
		<TopBox ref="topBox" @onSearch="searchFun"/>
		<view :class="isFixed?'hiddenView':''">
			<Type />
			<view class="place"></view>
		</view>

		<view class="listItemBox">
			<view class="title u-margin-bottom-24">
				<image src="@/static/index/top_org.png" mode=""></image>
				<text>优选机构</text>
			</view>
			<scroll-view 
			 @scroll="onScroll"
			 v-if="list.length" 
			 class="scroll-list" 
			 scroll-y="true"
			 @scrolltolower="loadMore"
			 :style="{
				 height:`calc(100vh - 360upx - 168upx - 34upx - 24upx ${isFixed?'':'- 230upx'})`
			 }"
			>
				<listItem @click.native="toDre(item)" v-for="(item,index) in list" :key="index" :item="item" />
			 <view class="place"></view>
			</scroll-view>
			<empty v-else></empty>
		</view>
	</view>
</template>

<script>
	import {
		publicMixin
	} from '@/mixins/publicMixin.js';
	import TopBox from './modules/TopBox'
	import Type from './modules/Type'
	import listItem from './modules/listItem.vue'
	export default {
		components: {
			TopBox,
			Type,
			listItem
		},
		mixins: [publicMixin],
		data() {
			return {
				list: [],
				timer:null,
				isFixed:false,//是否开始滚动
			}
		},
		computed: {

		},
		onShow() {
			console.log('033');
			if(this.$db.token()){
				this.$api.get(this.Interface.getComplaintPross).then(res=>{
					this.$store.commit('saveComplainBoo',res.data)
				})
			}
			this.list=[];
			this.pageNation.pageNum = 1;
			this.isFixed=false;
			this.initData();
			this.$nextTick(()=>{
				this.$refs.topBox.updateCity();
			})
		},
		onPullDownRefresh() {
			if(getApp().globalData.isScroll) return uni.stopPullDownRefresh();
			this.pageNation.pageNum = 1;
			this.timer = null;
			this.status = 'loadmore';
			this.list = [];
			this.initData()
			uni.stopPullDownRefresh()
		},
		methods: {
			onScroll(e){
				if(e.detail.scrollTop > 50){
					this.isFixed  =true
				}else{
					this.isFixed  =false
				}
			},
			toDre(v) {
				uni.navigateTo({
					url: `/pages/org/orgDetail?id=${v.id}`
				})
			},
			searchFun(key){
				this.pageNation.pageNum = 1;
				this.timer = null;
				this.status = 'loadmore';
				this.list = [];
				this.initData(key)
			},
			initData(search='') {
				if (this.status == 'nomore') return;
				this.$api.get(this.Interface.orgList, {
					creditRank:'-',
					city:getApp().globalData.city.value,
					level:getApp().globalData.city.value?2:'',
					search: search,
					...this.pageNation
				}).then(e => {
					this.list = this.list.concat(e.rows);
					if (this.list.length >= e.total) this.status = 'nomore';
					else this.status = 'loadmore';
				}).catch(err => {
					console.log(err)
				})
			},
			loadMore() {
				console.log('已触底');
				let that = this;
				if (this.timer) clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					this.pageNation.pageNum += 1;
					this.initData()
				}, 500)
			}
		}
	}
</script>

<style lang="scss" scoped>
	.hiddenView{
		  height: 0;
		  opacity: 0;
		  transition: all .3s;
		  visibility: hidden;
	}
	.scroll-list{
		// height: calc(100vh - 360upx - 168upx - 34upx - 24upx - 230upx);
	}
	.listItemBox {
		padding: 24upx 30upx;
		background: #fff;

		.title {
			display: inline-flex;
			align-items: center;

			image {
				height: 50upx;
				width: 50upx;
				margin-right: 10upx;
			}

			text {
				font-size: 30rpx;
				font-weight: 500;
				color: #333333;
			}
		}
	}
</style>