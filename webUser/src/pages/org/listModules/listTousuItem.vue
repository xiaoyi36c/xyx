<template>
				<view class="list-item">
					<view class="u-flex">
						<text>{{item.create_time}}</text>
						<view v-if="item.complaint_status" class="u-margin-left-64 status blue">
							{{item.complaint_status}}
						</view>
					</view>
					<view class="u-flex">
						<view style="flex-shrink: 0;">
							<view class="item-row u-margin-top-32 u-flex">
								<text>投诉人</text>
								<text class="item-value">
								{{item.complaint_user_name.substr(0,4)+'****'+item.complaint_user_name.substr(8,11)}}
								</text>
							</view>
							<view class="item-row u-margin-top-30 u-flex">
								<text>投诉类型</text>
								<text class="item-value">{{item.complaint_type}}</text>
							</view>
							<view class="item-row u-margin-top-30 u-flex">
								<text>投诉内容</text>
								<text class="item-value">{{item.complaint_content}}</text>
							</view>
						</view>
						<view class="divider"></view>
						<view class="btn-detail u-margin-left-30">
							查看详情
						</view>
					</view>
				</view>
</template>

<script>
	export default {
		props:['item'],
		data() {
			return {
				
			}
		},
		methods: {
			
		}
	}
</script>

<style lang="scss" scoped>
	.list-item {
		padding: 30rpx 30rpx 30rpx 24rpx;
		background: #EDF5FF;
		border-radius: 8rpx;
		margin-bottom: 24rpx;
	
		.status {
			min-width: 92rpx;
			height: 32rpx;
			line-height: 32rpx;
			text-align: center;
			font-size: 20rpx;
			padding: 0 12rpx;
		}
	
		.status.blue {
			background: url('@/static/mine/status-blue.png') no-repeat;
			background-size: 100%;
			color: #fff;
		}
	
	
		.item-value {
			color: #999;
			margin-left: 56rpx;
			width: 306rpx;
			text-overflow: ellipsis;
			overflow: hidden;
			white-space: nowrap;
		}
	
		.divider {
			width: 2rpx;
			height: 120rpx;
			background: #F0F0F0;
			flex-shrink: 0;
		}
	
		.btn-detail {
			padding: 8rpx 18rpx;
			color: #3994FF;
			font-size: 24rpx;
			border: 2rpx solid #3994FF;
			flex-shrink: 0;
			border-radius: 26rpx;
		}
	}
</style>
