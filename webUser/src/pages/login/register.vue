<template>
	<view class="wrapper">
		<image class="logo" src="../../static/login/logo.png"></image>
		<view class="register-box">
			<view class="register-box-title">
				注册
			</view>
			<u-form :model="form" ref="uForm" :error-type="['toast']" :label-width="170">
<!-- 				<u-form-item label="头像" prop="nick_name" >
					<slot slot="right">
						<text class="u-font-14 u-m-r-20" v-if="!form.head_imager" @click.stop="handleSelectHeadImg">请选择头像</text>
						<img  v-else @click.stop="handleSelectHeadImg" class="header-img" :src="form.head_imager.fileUrl">
						<image class="right-icon" src="../../static/login/to-right.png"></image>
					</slot>
				</u-form-item> -->
				<u-form-item label="姓名" prop="nick_name" required>
					<u-input v-model="form.nick_name" placeholder="请输入姓名" input-align="right" />
				</u-form-item>
<!-- 				<u-form-item label="性别" prop="sex">
					<u-input class="select-input" v-model="sexText" placeholder="请选择性别" input-align="right"
						type="select" @click="sexSelectShow = true" />
					<u-action-sheet :list="sexList" v-model="sexSelectShow" @click="sexSelectCallback"></u-action-sheet>
					<slot slot="right">
						<image class="right-icon" src="../../static/login/to-right.png"></image>
					</slot>
				</u-form-item> -->
<!-- 				<u-form-item required label="手机号码" prop="phone">
					<u-input v-model="form.phone" placeholder="请输入手机号码" input-align="right" />
				</u-form-item> -->
				<u-form-item required label="手机号码" prop="username">
					<u-input v-model="form.username" placeholder="请输入手机号码" input-align="right" />
				</u-form-item>
				<u-form-item required label="密码" prop="password">
					<u-input v-model="form.password" placeholder="请输入密码" type="password" input-align="right" />
				</u-form-item>

				<!-- <u-form-item required label="身份证号码" prop="idCord">
					<u-input v-model="form.idCord" placeholder="请输入身份证号码" input-align="right" />
				</u-form-item> -->
<!-- 				<u-form-item label="邮箱" prop="email">
					<u-input v-model="form.email" placeholder="请输入邮箱" input-align="right" />
				</u-form-item> -->
				<u-form-item required label="短信验证码" prop="code">
					<u-input v-model="form.code" placeholder="请输入验证码" input-align="right" />
					<slot slot="right">
						<u-verification-code :seconds="seconds" ref="uCode" @change="codeChange"></u-verification-code>
						<button class="code-button" @click="getCode">{{tips}}</button>
					</slot>
				</u-form-item>
			</u-form>
		</view>
		<view class="user-agreement">
			<u-checkbox :size="28" v-model="agreementChecked">我已阅读并同意<text class="agreement-link" @click.stop="toUrl('/pages/login/AgreeContentSys')">《服务协议》</text><text
					class="agreement-link" @click.stop="toUrl('/pages/login/AgreeContentUser')">《用户隐私协议》</text></u-checkbox>
		</view>
		<button class="btn-submit" @click="submit">提交注册</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					head_imager: {
						head_imager_name:'default',
						head_imager:'https://op.lorisai.cn//files/upload/a_20230609171227A009.jpg'
					},
					nick_name: null,
					sex: null,
					username: null,
					password: null,
					phone: null,
					idCord: null,
					email: null,
					code: null
				},
				rules: {
					username: [{
							required: true,
							message: '请输入手机号码'
						},
						{
							validator: (rule, value, callback) => {
								return this.$u.test.mobile(value);
							},
							message: '手机号码格式不正确'
						}
					],
					nick_name: [{
						required: true,
						message: '请输入姓名'
					}],
					password: [{
						required: true,
						message: '请输入密码'
					}],
					// phone: [{
					// 		required: true,
					// 		message: '请输入手机号'
					// 	},
					// 	{
					// 		validator: (rule, value, callback) => {
					// 			return this.$u.test.mobile(value);
					// 		},
					// 		message: '手机号码格式不正确'
					// 	}
					// ],
					// idCord: [{
					// 		required: true,
					// 		message: '请输入身份证号'
					// 	},
					// 	{
					// 		validator: (rule, value, callback) => {
					// 			return this.$u.test.idCard(value);
					// 		},
					// 		message: '身份证号码格式不正确'
					// 	}
					// ],
					email: [{
						validator: (rule, value, callback) => {
							if (value === null || value === '') {
								return true
							}
							return this.$u.test.email(value);
						},
						message: '邮箱格式不正确'
					}],
					code: [{
						required: true,
						message: '请输入验证码'
					}],
				},
				headerImgFileName: null,
				sexSelectShow: false,
				sexText: '',
				sexList: [{
						text: '男',
						value: 1
					},
					{
						text: '女',
						value: 0
					}
				],
				// 勾选协议
				agreementChecked: false,
				seconds: 60,
				tips: '请输入验证码'
			}
		},
		methods: {
			toUrl(url) {
				this.$common.navigateTo(url);
			},
			submit() {
				if (!this.agreementChecked) {
					this.$common.errorToShow('请先勾选用户协议')
					return
				}
				this.$refs.uForm.validate(valid => {
					if (valid) {
						this.$api.post(this.Interface.register, this.form).then(res => {
							if (res.code == 200) {
								this.$common.successToShow('注册成功')
								setTimeout(() => {
									uni.reLaunch({
										url: '/pages/login/login'
									})
								}, 500)
							}
						})
					}
				});
			},
			handleSelectHeadImg() {
				// document.querySelector('.u-add-wrap').click()
				let url  = this.$api.domain + this.Interface.uploadFile;
				let that = this;
				uni.chooseImage({
					count:1,
					success(file) {
						console.log(file)
						uni.uploadFile({
							url: url, // 仅为示例，非真实的接口地址
							filePath: file.tempFilePaths[0],
							name: 'file',
							success: (res) => {
								// that.updateInfoCell({
								// 	head_imager:JSON.parse(res.data).data.fileUrl
								// })
								that.form.head_imager =JSON.parse(res.data).data
							},
							fail:(err)=>{
								console.log(err);
								that.$common.errorToShow('上传失败，请重试')
							}
						});
					}
				})
			},
			handleFinishSelect(data) {
				if (data.code == 200) {
					this.form.head_imager = {
						fileUrl: data.data.fileUrl,
						fileName: data.data.fileName
					}
				}
			},
			sexSelectCallback(index) {
				this.sexText = this.sexList[index].text
				this.form.sex = this.sexList[index].value
			},
			codeChange(text) {
				this.tips = text;
			},
			getCode() {
				if (this.form.username === '' || this.form.username === null) {
					this.$u.toast('请输入手机号码');
					return
				}
				if (!this.$u.test.mobile(this.form.username)) {
					this.$u.toast('请输入正确的手机号码');
					return
				}
				if (this.$refs.uCode.canGetCode) {
					this.$api.post(this.Interface.getRegisterCode, {
						phone: this.form.username
					}).then(res => {
						if (res.code == 200) {
							uni.hideLoading();
							this.$u.toast('验证码已发送');
							this.$refs.uCode.start();
						}
					})
				}
			},
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
		background: linear-gradient(180deg, #E6F2FE 0%, #F5F5F5 100%);
		overflow: hidden;

		.logo {
			display: block;
			width: 320rpx;
			height: 106rpx;
			margin: 120rpx auto 0;

		}

		.register-box {
			width: 690rpx;
			background: #FFFFFF;
			border-radius: 44rpx;
			margin: 82rpx auto 0;
			padding: 52rpx 32rpx 19rpx 50rpx;

			.register-box-title {
				color: #384A6B;
				font-size: 40rpx;
				font-weight: 500;
				line-height: 1;
			}

			::v-deep .u-form {
				margin-top: 31rpx;

				.u-form-item {
					font-size: 32rpx;
					padding: 0;
					margin-bottom: 14rpx;

					.head-img-upload {
						display: none;
					}

					.u-form-item--left__content__label {
						color: #384A6B;
					}

					.u-form-item--left__content--required {
						left: -22rpx;
					}

					.u-form-item--right__content__icon {
						margin-left: 18rpx;

						.header-img {
							width: 60rpx;
							height: 60rpx;
							border-radius: 50%;
							margin-right: 20rpx;
						}

						.right-icon {
							width: 13rpx;
							height: 20rpx;
						}

					}

					.select-input {
						.u-input__right-icon {
							display: none;
						}
					}

				}

				.u-border-bottom::after {
					border-bottom-width: 0;
				}
			}

		}

		.user-agreement {
			margin-top: 58rpx;
			text-align: center;

			::v-deep .u-checkbox__label {
				font-size: 24rpx;
				color: #BDC1CB;
				margin-left: 8rpx;
				margin-right: 0;

				.agreement-link {
					color: #3994FF;
				}
			}
		}

		.btn-submit {
			margin: 28rpx auto;
			width: 608rpx;
			height: 90rpx;
			line-height: 90rpx;
			text-align: center;
			color: #fff;
			background: linear-gradient(92deg, #368BF7 0%, #84D0FF 100%);
			border-radius: 64rpx;
		}

		.code-button {
			padding: 14rpx 16rpx;
			background: #EBF4FF;
			color: #368BF7;
			border-radius: 8rpx;
			font-size: 28rpx;
			border: 2rpx solid #EBF4FF;
			line-height: 1;
		}
	}
</style>