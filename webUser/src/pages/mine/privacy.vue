<template>
	<view class="wrapper">
		<view class="x-cell u-flex u-row-between" @click="toUrl('/pages/login/AgreeContentSys')">
			<view class="x-cell-title">
				服务协议
			</view>
			<view class="x-cell-value u-flex">
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view>
		<view class="x-cell u-flex u-row-between" @click="toUrl('/pages/login/AgreeContentUser')">
			<view class="x-cell-title">
				用户隐私协议
			</view>
			<view class="x-cell-value u-flex">
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				show: false
			};
		},
		methods: {
			toUrl(url) {
				this.$common.navigateTo(url);
			}
		}
	}
</script>

<style lang="scss" scoped>
	.wrapper {
		background: #F5F5F5;
		min-height: 100vh;
		overflow: hidden;
	}
</style>