<template>
	<view class="listItem">
		<view class="itemImg">
			<image class="itemImg" :src="item.enterprise_logo" mode="aspectFill" @error="imgerror"></image>
		</view>
		<view class="ItemRight">
			<view class="title">
				<text class="info">{{item.organization_name}}</text>
				<text class="count" v-if="item.schoolRegionCount>1">{{item.schoolRegionCount}}个校区</text>
			</view>
			<!-- <view class="rate" style="padding: 15upx 0;">
				<u-rate :value="item.avgAppeaise" disabled></u-rate>
			</view> -->
<!-- 			<view class="tagBox">
				<text>信用分：{{item.credit_score || '0'}}分</text>
			</view> -->
			<view class="addr">
				<u-icon name="map"></u-icon>
				<text>{{item.area_detail || '暂无'}}</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: ['item'],
		data() {
			return {

			}
		},
		methods: {
			imgerror(e, index) {  
				this.item.enterprise_logo = require('@/static/default.png')
			},

		}
	}
</script>

<style lang="scss" scoped>
	.listItem {
		display: flex;
		width: 100%;
		border-bottom: 2upx solid #F2F2F2;
		align-items: center;
		padding: 32rpx 0;

		.itemImg {
			width: 172rpx;
			height: 172rpx;
			border-radius: 12upx;
		}

		.ItemRight {
			flex: 1;
			margin-left: 20upx;
			width: calc(100% - 200px);
			.title {
				font-weight: 600;
				font-size: 32upx;
				line-height: 1;
				color: #333333;
				display: inline-flex;
				align-items: center;
				width: 100%;

				.info {
					flex: 1;
					white-space: nowrap;
					text-overflow: ellipsis;
					overflow: hidden;
					margin-right: 10upx;
				}

				.count {
					color: #999999;
					font-size: 24upx;

				}
			}

			.rate {
				margin-top: 10rpx;
			}

			.tagBox {
				margin-top: 14rpx;

				text {
					background: #ECF6FF;
					color: #3994FF;
					padding: 3upx 8upx;
					font-size: 26upx;
				}
			}

			.addr {
				margin-top: 16rpx;
				line-height: 1;
				font-size: 26upx;
				color: #999999;
				display: inline-flex;
				align-items: center;
				width:100%;
				text{
					white-space: nowrap;
					text-overflow: ellipsis;
					overflow: hidden;
					margin-left: 10upx;
				}
			}
		}
	}

	.listItem:first-child {
		padding-top: 0;
	}
	.listItem:last-child {
		border-bottom: none;
	}
</style>