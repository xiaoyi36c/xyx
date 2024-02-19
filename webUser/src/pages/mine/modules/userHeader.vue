<template>
	<view class="mingBox">
		<view class="u-margin-top-76 u-margin-left-30 u-flex">
			<image v-if="personalInfo.head_imager" class="header-img" :src="personalInfo.head_imager" mode="aspectFill">
			</image>
			<image v-else class="header-img" src="../../../static/mine/cusBg.png" mode="aspectFill"></image>
			<view class="u-margin-left-16" v-if="JSON.stringify(personalInfo) !='{}'">
				<view class="u-m-b-8 u-font-18">
					{{nickName}}
				</view>
				<view class="u-font-13 edit" @click="toUrl('/pages/mine/personal')">
					<u-icon name="edit-pen" class="u-margin-right-10"></u-icon>编辑个人资料
				</view>
			</view>
			<view class="u-margin-left-16" v-else @click="toLogin">
				去登录
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		name: "userHeader",
		data() {
			return {
				personalInfo: {}
			};
		},
		computed: {
			nickName() {
				return this.personalInfo.nick_name ?? this.personalInfo.user_name
			}
		},
		mounted() {
			this.initData()
		},
		methods: {
			toLogin(){
				uni.reLaunch({
					url:'/pages/login/login'
				})
			},
			initData() {
				this.$api.get(this.Interface.getMyselfDetail).then(res => {
					if (res.code == 200) {
						this.personalInfo = res.data
					}
				})
			},
			toUrl(url) {
				this.$common.navigateTo(url);
			}
		}
	}
</script>

<style lang="scss" scoped>
	.mingBox {
		width: 750rpx;
		height: 276rpx;
		background-image: url(../../../static/mine/mine_bg.png);
		background-size: 100% 100%;
		background-repeat: no-repeat;
		color: #fff;
		overflow: hidden;
		
		*{
			box-sizing: border-box;
		}

		.header-img {
			height: 100upx;
			width: 100upx;
			border: 5upx solid #b1d6fb;
			border-radius: 50%;
		}


		.edit {
			color: #C9E3FD;
		}
	}
</style>