<template>
	<view style="overflow: hidden;">
		<userHeader ref="userHeader"/>
		<view class="u-m-t-20" style="background: #fff;">
			<view @click.stop="toUrl('/pages/mine/mineRate')" class="cellMineItem">
				<view class="cellItemLeft">
					<image style="height: 40upx;width: 40upx;" src="../../static/mine/icon-message.png"></image>
					<text class="u-m-l-22 u-font-16" style="color:#262626">我的评价</text>
				</view>
				<image style="height: 20upx;width: 13upx;" src="../../static/mine/to-right-small.png"></image>
			</view>
			<view @click.stop="toUrl('/pages/mine/mineTousu')" class="cellMineItem">
				<view class="cellItemLeft">
					<image style="height: 40upx;width: 40upx;" src="../../static/mine/icon-warning.png"></image>
					<text class="u-m-l-22 u-font-16" style="color:#262626">我的投诉</text>
				</view>
				<image style="height: 20upx;width: 13upx;" src="../../static/mine/to-right-small.png"></image>
			</view>
			<view @click.stop="toUrl('/pages/mine/Setting')" class="cellMineItem">
				<view class="cellItemLeft">
					<image style="height: 40upx;width: 40upx;" src="../../static/mine/icon-setting.png">
					</image>
					<text class="u-m-l-22 u-font-16" style="color:#262626">设置</text>
				</view>
				<image style="height: 20upx;width: 13upx;" src="../../static/mine/to-right-small.png"></image>
			</view>
		</view>
<!-- 		<view class="listItemBox u-m-t-20" style="background: #fff;">
			<view class="title">
				<image src="@/static/mine/msg-icon.png" style="height: 40upx;width: 40upx;"></image>
				<text class="u-m-l-10 u-font-16">联系客服</text>
			</view>
			<view class="u-m-t-20 u-flex u-flex-center">
					<view class="u-flex u-flex-1" style="background: #F2F8FF;align-items: center;padding:10upx 0;border-radius: 12rpx;">
						<image src="@/static/mine/mine_k.png"  style="height: 100upx;width: 100upx;" mode=""></image>
						<view class="u-p-10">
							<view class="u-font-16">在线客服</view>
							<view class="u-font-14" style="color:#999999">及时解决您的问题</view>
						</view>
					</view>
					<view @click="call('400-123-123')" class="u-flex u-flex-1 u-m-l-20" style="background: #FFF8EE;align-items: center;padding:10upx 0;border-radius: 12rpx;">
						<image src="@/static/mine/mine_t.png"   style="height: 100upx;width: 100upx;" mode=""></image>
						<view class="u-p-10">
							<view class="u-font-16">电话客服</view>
							<view class="u-font-14" style="color:#999999">400-123-123</view>
						</view>
					</view>
			</view>
		</view> -->
	</view>
</template>

<script>
	import userHeader from './modules/userHeader.vue'
	export default {
		components:{
			userHeader
		},
		data() {
			return {

			};
		},
		watch:{
			
		},
		onShow(){
			this.$nextTick(()=>{
				this.$refs.userHeader.initData()
			})
		},
		onLoad() {
			
		},
		methods: {
			call(tel){
				uni.makePhoneCall({
					phoneNumber:tel
				})
			},
			toUrl(url) {
				this.$common.navigateTo(url);
			}
		}
	};
</script>

<style lang="scss" scoped>
	.listItemBox{
			padding: 15upx 20upx;
			background: #fff;
			.title{
				display: inline-flex;
				align-items: center;
				image{
					height:45upx;
					width: 45upx;
				}
				text{
					font-size: 30rpx;
					font-weight: 400;
					color: #333333;
				}
			}
	}
	// /deep/ .u-cell-item-box{
	// 	background-color: transparent;
	// }
	// /deep/ .u-border-bottom:after{
	// 	border: 0;
	// }
	.cellMineItem {
		align-items: center;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		padding: 30rpx;
		border-bottom: 2rpx solid #f5f5f5;

		.cellItemLeft {
			align-items: center;
			display: inline-flex;

			image {
				height: 50upx;
				width: 50upx;
			}

		}
	}
</style>
