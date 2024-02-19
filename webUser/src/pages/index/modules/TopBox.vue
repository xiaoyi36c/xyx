<template>
	<view class="box">
		<view class="logo">
			<image src="@/static/index/logo_xyx.png" mode=""></image>
			<text>--让学习更容易</text>
		</view>
		<view class="top">
			<view class="title">
				<region-picker ref="cityBox"></region-picker>
			</view>
			<view class="searchBox">
				<u-icon name="search" style="margin-right: 10upx;"></u-icon>
				<input v-model="search" placeholder="输入机构名/统一社会信用代码" prefixIcon="search"></input>
			</view>
			<view class="searchBtn" @click="toSearch">
				搜索
			</view>
		</view>
		<view class="other">
			<view class="topLine"> </view>
			<view class="cont">
				<text class="week">{{NowDate}}{{NowTime}}</text>
				<view class="title" :style="{
					lineHeight:$store.state.complainBoo?'':'70upx'
				}">{{PmAm}} ,今天也要开心鸭 </view>
				<view class="pro"
					@click="toUrl('/pages/mine/mineTousu')"
					v-if="$store.state.complainBoo"
				> 
					查看投诉进度
					<u-icon name="arrow-right" color="#C9C9C9"></u-icon>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	const dateFun = new Date()
	const weekList =['天','一','二','三','四','五','六']
	export default {
       computed:{
		   PmAm(){
			   return dateFun.getHours() > 12 && dateFun.getHours() <= 18 ?'下午好':(dateFun.getHours()>18 && dateFun.getHours()<23?'晚上好':'早上好')
		   },
		   NowTime(){
		       return ` 星期${weekList[new Date().getDay()] }`
		   },
		    NowDate(){
		               return `${dateFun.getFullYear()}年${dateFun.getMonth() + 1}月${dateFun.getDate()}日`
		           },
	   },
		data() {
			return {
				region: null,
				search: ''
			}
		},
		mounted(){

		},
		methods: {
			updateCity(){
				this.$refs.cityBox.cityLable = getApp().globalData.city.label || '全部'
			},
			toSearch() {
				this.$emit('onSearch',this.search)
				// this.$common.navigateTo('/pages/org/orgList?search='+this.search)
			},
			toUrl(url) {
				uni.navigateTo({
					url: url
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.box {
		padding: 15upx 20upx;
		height: 360upx;
		background: url('~@/static/index/logo_bg.png');
	}

	.other {
		margin-top: 20upx;

		.topLine {
			height: 20rpx;
			background: #0367BF;
			border-radius: 10rpx 10rpx 10rpx 10rpx;
			opacity: 0.13;
		}

		.cont {
			margin-top: -10upx;
			padding: 20upx;
			height: 122rpx;
			background: #FFFFFF;
			border-radius: 0rpx 0rpx 16rpx 16rpx;
			opacity: 1;
			position: relative;
			.week{
				position: absolute;
				right: 18upx;
				top:0;
				bottom:0;
				z-index: 1;
				font-size: 30upx;
				font-weight: bold;
				height: 122upx;
				line-height: 122upx;
			}
			.title {
				margin-top: 5upx;
				font-size: 32rpx;
				font-weight: 500;
				color: #4D4D4D;
			}

			.pro {
				margin-top: 5upx;
				font-size: 26rpx;
				font-weight: 400;
				color: #3994FF;

			}
		}
	}

	.logo {
		display: inline-flex;
		width: 100%;
		align-items: center;
		margin-top: 10upx;

		image {
			width: 175upx;
			height: 60upx;
		}

		text {
			font-weight: 500;
			margin-left: 20upx;
			color: #FFFFFF;
			font-size: 30upx;
			background: #ffffff;
			-webkit-background-clip: text;
			-webkit-text-fill-color: transparent;
		}
	}

	.top {
		margin-top: 10upx;
		padding: 10upx 20upx;
		width: 100%;
		display: inline-flex;
		align-items: center;
		background: #ffffff;
		border-radius: 50upx;

		.title {
			font-size: 28upx;
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
			padding: 10upx 15upx;
			border-radius: 30upx;

			::v-deep .uni-input-wrapper {
				font-size: 24upx;
			}

			::v-deep uni-input {
				width: 100%;
			}
		}

		.searchBtn {
			padding: 10upx 30upx;
			background: #3994FF;
			color: #fff;
			font-size: 28upx;
			border-radius: 30upx;
		}
	}
</style>