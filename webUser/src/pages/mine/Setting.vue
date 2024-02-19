<template>
	<view class="wrapper">
		<view class="x-cell u-flex u-row-between">
			<view class="x-cell-title">
				关于我们
			</view>
			<view class="x-cell-value u-flex">
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view>
		<view class="x-cell u-flex u-row-between">
			<view class="x-cell-title">
				联系我们
			</view>
			<view class="x-cell-value u-flex" @click="call('400-123-123')">
				400-123-123
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view>
		<view class="u-margin-top-20">
			<view class="x-cell u-flex u-row-between" @click="toUrl('/pages/mine/privacy')">
				<view class="x-cell-title">
					隐私
				</view>
				<view class="x-cell-value u-flex">
					<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt="">
					</image>
				</view>
			</view>
		</view>
		<view class="btn-logout u-margin-top-20" @click="show = true">
			退出登录
		</view>
		<u-modal v-model="show" content="确认退出登录吗" show-cancel-button @confirm="logout"></u-modal>
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
			call(tel){
				uni.makePhoneCall({
					phoneNumber:tel
				})
			},
			logout() {
				this.$db.del("token");
				uni.reLaunch({
					url: '/pages/login/login'
				})
			},
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

		.btn-logout {
			width: 100%;
			padding: 26rpx 0;
			text-align: center;
			color: #3994FF;
			background: #fff;
		}
	}
</style>