<template>
	<view class="wrapper">
		<view class="complaint-box">
			<view class="complaint-date">
				{{$dayjs(complaintInfo.create_time).format('YYYY-MM-DD')}}
			</view>
			<view class="u-margin-top-30 u-flex u-row-between">
				<text>投诉类型</text>
				<text>{{complaintInfo.complaint_type}}</text>
			</view>
			<view class="u-margin-top-40">
				描述与凭证
			</view>
			<view class="complaint-content">
				<view>{{complaintInfo.complaint_content}}</view>
                <view class="u-margin-top-20 u-flex u-flex-wrap">
                	<image @click="priview(complaintInfo.studentFileList)" class="complaint-image" v-for="(img,index) in complaintInfo.studentFileList"  :key="index" :src="img.file_url" mode="aspectFill"></image>
                </view>
			</view>
		</view>
		<view class="complaint-box u-margin-top-20 u-padding-bottom-10" v-if="complaintInfo.complaint_platform_audit_content">
			<view class="u-flex">
				<view class="complaint-date u-margin-right-20">
					{{$dayjs(complaintInfo.complaint_platform_audit_date).format('YYYY-MM-DD')}}
				</view>
				<view class="tag yellow">
					平台回复
				</view>
			</view>
			<view class="reply-content">
				{{complaintInfo.complaint_platform_audit_content}}
			</view>
			<view class="u-margin-top-20 u-flex u-flex-wrap">
				<image @click="priview(complaintInfo.platformFileList)" class="complaint-image" v-for="(img,index) in complaintInfo.platformFileList" :src="img.file_url" mode="aspectFill"></image>
			</view>
		</view>
		<view class="complaint-box u-margin-top-20 u-padding-bottom-10" v-if="complaintInfo.complaint_org_audit_content">
			<view class="u-flex">
				<view class="complaint-date u-margin-right-20">
					{{$dayjs(complaintInfo.complaint_org_audit_date).format('YYYY-MM-DD')}}
				</view>
				<view class="tag blue">
					机构回复
				</view>
			</view>
			<view class="reply-content">
				{{this.complaintInfo.complaint_org_audit_content}}
			</view>
			<view class="u-margin-top-20 u-flex u-flex-wrap">
				<image @click="priview(complaintInfo.orgFileList)"  class="complaint-image" v-for="(img,index) in complaintInfo.orgFileList" :src="img.file_url" mode="aspectFill"></image>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				complaintInfo: {}
			}
		},
		mounted() {
			this.initData()
			console.log(this.$dayjs,'----')
		},
		methods: {
			priview(v =[]){
				console.log(v)
				v = v.map(e=>e.file_url)
				uni.previewImage({
					urls:v
				})
			},
			initData() {
				this.$api.get(this.Interface.getComplaintDetail, {
					complaintId: this.$route.query.id
				}).then(res => {
					this.complaintInfo = res.data
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.wrapper {
		background: #f5f5f5;
		min-height: 100vh;

		* {
			box-sizing: border-box;
		}

		.complaint-box {
			background: #fff;
			padding: 30rpx;
			color: #333;

			.complaint-date {
				font-size: 24rpx;
				color: #999;
			}
			
			.tag{
				padding: 6rpx 8rpx;
				border-radius: 8rpx;
				font-size: 24rpx;
			}
			
			.tag.yellow{
				background: #FEF7EC;
				color: #FFB138;
			}
			
			.tag.blue{
				background: #ECF6FF;;
				color: #38A9FD;
			}

			.complaint-content {
				margin-top: 18rpx;
				line-height: 39rpx;
				padding: 24rpx 24rpx 9rpx;
				background: #f5f5f5;
			}
			
			.complaint-image{
				width: 180rpx;
				height: 180rpx;
				margin-bottom: 20rpx;
				margin-right: 20rpx;
			}
			
			.reply-content{
				margin-top: 24rpx;
				line-height: 39rpx;
			}
		}
	}
</style>