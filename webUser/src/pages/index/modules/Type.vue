<template>
	<view class="content">
		<view class="item" @click="toUrl">
			<image src="~@/static/index/saoma.png" mode=""></image>
			<text>我要投诉</text>
		</view>
		<view class="item" @click="toSearch('')">
			<image src="~@/static/index/org.png" mode=""></image>
			<text>全部机构</text>
		</view>
<!-- 		<view class="item" @click="toSearch('1')">
			<image src="~@/static/index/K12org.png" mode=""></image>
			<text>K12列表</text>
		</view> -->
		<view class="item" @click="toSearch('2')">
			<image src="~@/static/index/zj.png" mode=""></image>
			<text>职教培训</text>
		</view>
	</view>
</template>

<script>
	export default {
		methods: {
			toUrl(){
				
				this.$common.navigateTo(`/pages/complaint/WriteTousuCenter`)
			},
			toSearch(type) {
				this.$common.navigateTo(`/pages/org/orgList?orgType=${type}`)
			},
		}
	}
</script>

<style scoped lang="scss">
	.content {
		padding: 15upx 20upx;
		background: #ffffff;
		display: inline-flex;
		justify-content: space-around;
		width: 100%;
		margin-top: 20upx;
		box-shadow: 0 -4upx 6upx 10px #fff;
	}

	.item {
		text-align: center;

		image {
			width: 100upx;
			height: 100upx;
		}

		text {
			display: block;
			font-size: 30rpx;
			color: #333333;
		}
	}
</style>