<template>
	<view class="wrapper">
		<userHeader />
		<view class="list-container u-margin-top-20">
			<view class="u-flex">
				<image class="list-icon" src="../../static/mine/msg-icon.png"></image>
				<text class="u-font-28 u-margin-left-8">我的投诉</text>
			</view>
			<scroll-view v-if="list.length" class="scroll-list u-margin-top-18" scroll-y="true"
				@scrolltolower="loadMore">
				<view class="list-item" v-for="item in list" :key="item.pkid" @click.stop="toUrl(`/pages/mine/complaintDetail?id=${item.pkid}`)">
					<view class="u-flex">
						<text>{{item.create_time}}</text>
						<view class="u-margin-left-64 status blue">
							{{item.complaintStatusName}}
						</view>
					</view>
					<view class="u-flex">
						<view style="flex-shrink: 0;">
							<view class="item-row u-margin-top-32 u-flex">
								<text>机构名称</text>
								<text class="item-value">{{item.complaint_org_name}}</text>
							</view>
							<view class="item-row u-margin-top-30 u-flex">
								<text>投诉类型</text>
								<text class="item-value">{{item.complaintTypeName}}</text>
							</view>
							<view class="item-row u-margin-top-30 u-flex">
								<text>投诉内容</text>
								<text class="item-value">{{item.complaint_content}}</text>
							</view>
						</view>
						<view class="divider"></view>
						<view class="btn-detail u-margin-left-30" >
							查看详情
						</view>
					</view>
				</view>
			</scroll-view>
			<empty v-else></empty>
		</view>
	</view>
</template>

<script>
	import userHeader from './modules/userHeader.vue'
	export default {
		components: {
			userHeader
		},
		data() {
			return {
				page: {
					pageNum: 1,
					pageSize: 5
				},
				finish: false,
				list: []
			};
		},
		mounted() {
			this.load()
		},
		methods: {
			load() {
				this.$api.get(this.Interface.getComplaintList, this.page).then(res => {
					if (res.code == 200) {
						this.list = this.list.concat(res.rows);
						if (this.list.length >= res.total) {
							this.finish = true;
						}
					}
				})
			},
			loadMore() {
				if (this.finish) {
					return
				}
				this.page.pageNum ++
				this.load()
			},
			toUrl(url) {
				this.$common.navigateTo(url);
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

		.list-container {
			background: #fff;
			padding: 24rpx 30rpx;
			color: #333;
			font-size: 28rpx;
			height: calc(100vh - 296rpx);

			.list-icon {
				width: 40rpx;
				height: 40rpx;
			}

			.scroll-list {
				height: calc(100% - 80rpx);

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
						background: url('../../static/mine/status-blue.png') no-repeat;
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
			}
		}
	}
</style>