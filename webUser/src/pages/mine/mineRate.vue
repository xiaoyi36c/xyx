<template>
	<view class="wrapper">
		<userHeader />
		<view class="list-container u-margin-top-20">
			<view class="u-flex">
				<image class="list-icon" src="../../static/mine/msg-icon.png"></image>
				<text class="u-font-28 u-margin-left-8">我的评价</text>
			</view>
			<scroll-view v-if="list.length" class="scroll-list u-margin-top-18" scroll-y="true" @scrolltolower="loadMore">
				<view class="list-item" v-for="(item,index) in list" :key="index">
					<view class="u-flex u-row-between">
						<text class="date">{{item.created_time}}</text>
						<view class="u-flex">
							<view class="item-btn" @click.stop="updateFun(item.id,item.org_id)">
								修改
							</view>
							<view class="divider"></view>
							<view class="item-btn" @click.stop="del(item.id)">
								删除
							</view>
						</view>
					</view>
					<view class="u-flex">
						<view>
							<view class="row-name">
								{{item.organization_name}}
							</view>
							<view class="row-rate u-margin-top-10">
								<u-rate activeColor="#FF8C00" disabled :value="item.appraise_num"></u-rate>
								<!-- <image v-for="item of item.appraise_num" :key="item" class="star" src="../../static/mine/star-fill.png" mode=""></image> -->
								<!-- <image v-for="item of 5 - item.appraise_num"  :key="item"  class="star" src="../../static/mine/star.png" mode=""></image> -->
							</view>
							<view class="row-content" v-if="item.showAll">
								{{item.appraise_data}}
							</view>
							<template v-if="item.showAll">
								<view class="row-img" v-if="item.fileList.length">
									<image v-for="(v,i) in item.fileList" class="img-item" :src="v.file_url" mode="aspectFill"></image>
								</view>
							</template>
							<view class="btn-show-all" @click="item.showAll = !item.showAll">
								{{item.showAll ? '收起' : '全文'}}
							</view>
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
	import {
	publicMixin
} from '@/mixins/publicMixin.js';
	export default {
		components: {
			userHeader
		},
		mixins: [publicMixin],
		data() {
			return {
				page: {
					pageNum: 1,
					pageSize: 10
				},
				finish: false,
				list: [],
				showAll: false
			};
		},
		onShow() {
			this.list=[]
			this.initData()
		},
		methods: {
			updateFun(id,org_id){
				uni.navigateTo({
					url:'/pages/complaint/WriteComments?id='+org_id+'&cid='+id
				})
			},
			loadMore() {
				console.log('已触底');
				let that = this;
				if (this.timer) clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					this.pageNation.pageNum += 1;
					this.initData()
				}, 500)
			},
			del(id){
				let that = this;
				uni.showModal({
					content: '是否删除当前评价',
					success(res) {
						if(res.confirm){
							that.$api.get(that.Interface.AppraiseDel, {
								id
							}).then(res=>{
								that.$common.successToShow('删除成功');
								that.status = 'loadmore';
								that.pageNation.pageNum =1;
								that.list=[];
								that.initData()
							})
						}
					}
				})

			},
			initData() {
				if (this.status == 'nomore') return;
				this.$api.get(this.Interface.getMyComplaint, {
					...this.pageNation
				}).then(e => {
					e.rows.map(e=>{
						e.showAll = false
					})
					this.list = this.list.concat(e.rows);
					if (this.list.length >= e.total) this.status = 'nomore';
					else this.status = 'loadmore';
				}).catch(err => {
					console.log(err)
				})
			},
		}
	}
</script>

<style lang="scss">
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
			height: calc(100vh - 300rpx);
			overflow: hidden;
			.list-icon {
				width: 40rpx;
				height: 40rpx;
			}

			.scroll-list {
				height: calc(100% - 80rpx);

				.list-item {
					padding: 30rpx 20rpx;
					background: #f9f9f9;
					border-radius: 16rpx;
					margin-bottom: 24rpx;

					.date {
						color: #676770;
						font-size: 24rpx;
					}

					.item-btn {
						color: #3994FF;
						font-size: 26rpx;
					}

					.divider {
						margin: 0 10rpx;
						width: 2rpx;
						height: 16rpx;
						background: #EDEDED;
						border-radius: 6rpx;
					}

					.row-name {
						font-size: 28rpx;
						color: #333;
						margin-top: 24rpx;
					}

					.row-rate {
						display: flex;

						.star {
							width: 36rpx;
							height: 36rpx;
							margin-right: 10rpx;
						}
					}

					.row-content {
						margin-top: 20rpx;
						font-size: 28rpx;
						color: #343434;
						line-height: 37rpx;
						width: 100%;
					}

					.row-content.hidden {
						overflow: hidden;
						text-overflow: ellipsis;
						display: -webkit-box;
						-webkit-line-clamp: 3;
						line-clamp: 3;
						-webkit-box-orient: vertical;
					}

					.btn-show-all {
						margin-top: 20rpx;
						font-size: 26rpx;
						color: #3994FF;
					}

					.row-img {
						margin-top: 20rpx;
						display: flex;
						.img-item {
							width: 202rpx;
							height: 202rpx;
							border-radius: 8rpx;
							margin-right: 22rpx;
						}
					}
				}
			}
		}
	}
</style>
