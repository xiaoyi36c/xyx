<template>
	<view class="wrapper">
		<image class="logo" src="../../static/login/logo.png"></image>
		<view class="main-title">
			<view class="main-title-text">
				让学习更容易
			</view>
		</view>
		<view class="action-box">
			<view class="action-item login" @click="toUrl('/pages/login/codeLogin')">
				登录
			</view>
			<view class="action-item register" @click="toUrl('/pages/login/register')">
				注册
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

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
		width: 100%;
		min-height: 100vh;
		background: url('../../static/login/bg-login.png') no-repeat;
		background-size: 100%;
		overflow: hidden;

		.logo {
			display: block;
			margin: 116rpx auto 0;
			width: 372rpx;
			height: 124rpx;
		}

		.main-title {
			margin-top: 60rpx;
			text-align: center;
			color: #3994FF;
			line-height: 44rpx;
			font-size: 32rpx;
			color: #3994FF;
			font-weight: 500;

			.main-title-text {
				display: inline-block;
				position: relative;
			}

			.main-title-text::before {
				content: '';
				display: block;
				width: 104rpx;
				height: 2rpx;
				background: linear-gradient(118deg, rgba(57, 148, 255, 0) 0%, #3994FF 100%);
				position: absolute;
				left: -12rpx;
				top: 50%;
				transform: translate(-100%, -50%);
			}

			.main-title-text::after {
				content: '';
				display: block;
				width: 104rpx;
				height: 2rpx;
				background: linear-gradient(118deg, #3994FF 0%, rgba(57, 148, 255, 0) 100%);
				position: absolute;
				left: 100%;
				top: 50%;
				transform: translate(12rpx, -50%);
			}
		}

		.action-box {
			margin-top: 300rpx;
			padding: 0 40rpx;

			.action-item {
				border-radius: 40rpx;
				height: 80rpx;
				line-height: 80rpx;
				text-align: center;
				font-size: 28rpx;
			}

			.login {
				border: 2px solid #3994FF;
				background: #3994FF;
				color: #fff;
			}

			.register {
				margin-top: 24rpx;
				background: transparent;
				border: 2rpx solid rgba(57, 148, 255, .4);
				color: #3994FF;
			}
		}
	}
</style>