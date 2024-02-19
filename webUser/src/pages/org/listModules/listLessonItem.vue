<template>
	<view>
		<view class="listItem">
			<image class="itemImg" :src="item.course_cover_img" mode=""></image>
			<view class="ItemRight">
				<view class="title">
					<text class="info">{{item.course_name}}</text>
					<text class="count">{{item.course_feature_tags}}</text>
				</view>
				<view class="rate">
					{{item.course_category}}
				</view>
				<view class="addr">
					<text>{{item.course_describe}}</text>
				</view>
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
.listItem{
	display: inline-flex;
	width: 100%;
	border-bottom: 1upx solid #F2F2F2;
	padding: 10upx 0 20upx 0;
	.itemImg{
		width: 160upx;
		height: 160upx;
		border-radius:10upx;
	}
	.ItemRight{
		flex:1;
		margin-left: 15upx;
		.title{
			font-weight: 600;
			font-size:28upx;
			color: #333333;
			display: inline-flex;
			align-items: center;
			width: 100%;
			.info{
				// flex:1;
			}
			.count{
				color: #EC4A2F;
				font-size: 20upx;
				 background: #FFFFFF;
				 border-radius:8upx;
				 padding:3upx 5upx;
				 border: 1px solid #EC4A2F;
				 margin-left: 10upx;
			}
		}
		.rate{
			color: #7C8085;
			font-size: 22upx;
		}
		.tagBox{
			text{
				background:#ECF6FF;
				color: #3994FF;
				padding: 3upx 8upx;
				font-size :22upx;
			}
		}
		.addr{
			font-size:  22upx;
			color:#999999;
			                text-overflow: ellipsis;
			            overflow: hidden;
			            display: -webkit-box;
			            -webkit-box-orient: vertical;
			            -webkit-line-clamp: 2;
			            overflow: hidden;
		}
	}
}
</style>
