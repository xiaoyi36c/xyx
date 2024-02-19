<template>
	<view class="wrapper">
		<view class="x-cell u-flex u-row-between" >
			<view class="x-cell-title">
				头像
			</view>
			<view class="x-cell-value u-flex" @click.stop="avaFun">
				<image v-if="personalInfo.head_imager" class="head-img"
					:src="personalInfo.head_imager"></image>
				<text v-else>待上传</text>
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view>
		<view class="x-cell u-flex u-row-between" >
			<view class="x-cell-title">
				昵称
			</view>
			<view class="x-cell-value u-flex" @click.stop="toUrl(`/pages/mine/updateCenter?key=nick_name&val=${personalInfo.nick_name}`)">
				<text>{{ isEmpty(personalInfo.nick_name) }}</text>
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view>
<!-- 		<view class="x-cell u-flex u-row-between">
			<view class="x-cell-title">
				性别
			</view>
			<view class="x-cell-value u-flex" @click="sexSelectShow = true">
				<text>{{ personalInfo.sex==1?'男': personalInfo.sex===0?'女':''}}</text>
				<u-action-sheet :list="sexList" v-model="sexSelectShow" @click="chooiseSex"></u-action-sheet>
				<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt=""></image>
			</view>
		</view> -->
		<view class="u-margin-top-20" >
			<view class="x-cell u-flex u-row-between">
				<view class="x-cell-title">
					手机号码
				</view>
				<view class="x-cell-value u-flex" @click.stop="toUrl(`/pages/mine/updateCenter?key=user_name&val=${personalInfo.user_name}`)">
					<text>{{personalInfo.user_name}}</text>
					<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt="">
					</image>
				</view>
			</view>
<!-- 			<view class="x-cell u-flex u-row-between">
				<view class="x-cell-title">
					实名认证
				</view>
				<view class="x-cell-value u-flex">
					<view class="tag">
						{{personalInfo.isReal ?'已实名':'待实名'}}
					</view>
					<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt="">
					</image>
				</view>
			</view> -->
<!-- 			<view class="x-cell u-flex u-row-between">
				<view class="x-cell-title">
					身份证号
				</view>
				<view class="x-cell-value u-flex" @click.stop="toUrl(`/pages/mine/updateCenter?key=id_card&val=${personalInfo.id_card}`)">
					<text>{{ isEmpty(personalInfo.id_card) }}</text>
					<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt="">
					</image>
				</view>
			</view> -->
			<view class="x-cell u-flex u-row-between" >
				<view class="x-cell-title">
					登录密码
				</view>
				<view class="x-cell-value u-flex" @click.stop="toUrl(`/pages/mine/updateCenter?key=password&val=${personalInfo.password || ''}`)">
					<text class="password-edit">修改密码</text>
					<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt="">
					</image>
				</view>
			</view>
			<view class="x-cell u-flex u-row-between" @click.stop="toUrl(`/pages/mine/updateCenter?key=email&val=${personalInfo.email}`)">
				<view class="x-cell-title">
					邮箱
				</view>
				<view class="x-cell-value u-flex">
					<text>{{ isEmpty(personalInfo.email) }}</text>
					<image class="x-cell-icon u-margin-left-20" src="../../static/mine/to-right-small.png" alt="">
					</image>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				personalInfo: {},
				sexSelectShow: false,
				sexList: [{
						text: '男',
						value: 1
					},
					{
						text: '女',
						value: 0
					}
				],
			}
		},
		onShow() {
			this.initData()
		},
		methods: {
			chooiseSex(index){
				this.updateInfoCell({
					sex:this.sexList[index].value
				})
			},
			toUrl(url){
				this.$common.navigateTo(url)
			},
			updateInfoCell(res){
				this.$api.post(this.Interface.updateMyselfDetail,res).then(res=>{
					this.$common.successToShow('更新成功');
					this.initData()
				})
			},
			avaFun(){
				let url  = this.$api.domain + this.Interface.uploadFile;
				let that = this;
				uni.chooseImage({
					count:1,
					success(file) {
						console.log(file.tempFiles[0].size)
						if(file.tempFiles[0].size > (5 * 1024 * 1024)){
							return uni.showToast({
								title:'图片最大为5m',
								icon:'none'
							})
						}
						uni.uploadFile({
							url: url, // 仅为示例，非真实的接口地址
							filePath: file.tempFilePaths[0],
							name: 'file',
							success: (res) => {
								that.updateInfoCell({
									head_imager:JSON.parse(res.data).data.fileUrl,
									// head_imager:{
									// 	head_imager_name:JSON.parse(res.data).data.fileName,
									// 	head_imager:JSON.parse(res.data).data.fileUrl
									// }
								})
							},
							fail:(err)=>{
								console.log(err);
								that.$common.errorToShow('上传失败，请重试')
							}
						});
					}
				})
			},
			initData() {
				this.$api.get(this.Interface.getMyselfDetail).then(res => {
					if (res.code == 200) {
						this.personalInfo = res.data
					}
				})
			},
			isEmpty(val) {
				return val ?? '待添加'
			}
		}
	}
</script>

<style lang="scss" scoped>
	.wrapper {
		background: #F5F5F5;
		// min-height: 100vh;
		overflow: hidden;
		.tag {
			background: #E4F3FF;
			border-radius: 6rpx;
			color: #3994FF;
			font-size: 24rpx;
			padding: 4rpx 6rpx;
			margin-right: 0rpx;
		}

		.password-edit {
			color: #3994FF;
		}
	}
</style>