<template>
	<view class="wrapper">
		<image class="logo" src="../../static/login/logo.png"></image>
		<view class="main-title">
			<view class="main-title-text">
				让学习更容易
			</view>
		</view>
		<view class="login-box">
			<u-form :model="form" ref="uForm" :error-type="['toast']">
				<u-form-item prop="username">
					<u-input v-model="form.username" placeholder="请输入手机号" />
				</u-form-item>
				<u-form-item prop="password">
					<u-input v-model="form.password" type="password" placeholder="请输入密码" />
				</u-form-item>
			</u-form>
			<view class="switch" @click="toUrl('/pages/login/codeLogin')">
				<image class="icon-switch" src="../../static/login/icon-switch.png"></image>
				<text>验证码登录</text>
			</view>
		</view>
		<button @click="submit" class="btn-login">立即登录</button>
		<view class="user-agreement">
			<u-checkbox :size="28" v-model="agreementChecked">我已阅读并同意
			<text class="agreement-link" @click.stop="toUrl('/pages/login/AgreeContentUser')">《用户服务及隐私协议》</text></u-checkbox>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					loginType: 'password',
					username: '',
					password:'' ,
				},
				rules: {
					username: [{
							required: true,
							message: '请输入手机号'
						},
						{
							validator: (rule, value, callback) => {
								return this.$u.test.mobile(value);
							},
							message: '手机号码格式不正确'
						}
					],
					password: [{
						required: true,
						message: '请输入密码'
					}]
				},
				// 勾选协议
				agreementChecked: false
			};
		},
		methods: {
			submit() {
				if (!this.agreementChecked) {
					this.$common.errorToShow('请先勾选用户协议')
					return
				}
				this.$refs.uForm.validate(valid => {
					if (valid) {
						this.$api.post(this.Interface.login, {
							...this.form,
							username: this.form.username + '&4'
						}).then(res => {
							if (res.code == 200) {
								this.$common.successToShow('登录成功')
								this.$db.set('token', res.token)
								setTimeout(() => {
									uni.reLaunch({
										url: '/pages/index/index'
									})
								}, 500)
							}
						})
					} else {
						console.log('验证失败');
					}
				});
			},
			toUrl(url) {
				this.$common.navigateTo(url);
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
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

		.login-box {
			margin-top: 98rpx;
			padding: 0 40rpx;

			::v-deep .u-form {
				margin-top: 31rpx;

				.u-form-item {
					font-size: 30rpx;
					padding: 13rpx 0;
				}

				.u-border-bottom::after {
					// border-color: #f2f2f2;
				}
			}
			.switch{
				display: inline-flex;
				align-items: center;
				margin-top: 32rpx;
				color: #3994FF;
				font-size: 24rpx;
				line-height: 1;
				.icon-switch{
					width: 20rpx;
					height: 20rpx;
					margin-right: 12rpx;
				}
			}
		}

		.btn-login {
			width: 670rpx;
			height: 80rpx;
			line-height: 80rpx;
			text-align: center;
			background: #3994FF;
			color: #fff;
			font-size: 28rpx;
			border-radius: 40rpx;
			margin: 160rpx auto 0;
		}
		
		.user-agreement {
			padding-top: 100upx;
			text-align: center;

			::v-deep .u-checkbox__label {
				font-size: 24rpx;
				color: #BDC1CB;
				margin-left: 10rpx;
				margin-right: 0;

				.agreement-link {
					color: #3994FF;
				}
			}
		}
	}
</style>