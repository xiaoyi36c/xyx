<template>
	<view class="content">
		<view class="left" 
		:style="{
			justifyContent:(info.jk_h5_url && info.is_push_jk==1)?'space-between':'space-around'
		}">
			<!-- <view class="title" @click="toPath('/pages/complaint/WriteTousu')">
					<image src="~@/static/org/icon_tousu.png" mode="scaleToFill"></image>
					<text>投诉</text>
			</view>
			<view class="title" @click="toPath('/pages/complaint/WriteComments')">
					<image src="~@/static/org/icon_pingjia.png" mode="scaleToFill"></image>
					<text>写评价</text>
			</view> -->
<!-- 			<view class="title" @click="toFun('客服')">
					<image src="~@/static/org/icon_kefu.png" mode="scaleToFill"></image>
					<text>客服</text>
			</view> -->
		</view>
		<view class="btn" v-if="info.jk_h5_url && info.is_push_jk==1">
			<view  @click="toFun('去咨询')">去咨询</view>
		</view>
	</view>
</template>

<script>
	export default {
		props:['info'],
		data() {
			return {
				 
			};
		},
		methods:{
			toPath(v){
				uni.navigateTo({
					url:v+'?id='+this.info.id+'&orgName='+this.info.organization_name
				})
			},
			toFun(v){
				if(!this.info.jk_h5_url || this.info.is_push_jk!=1) return uni.showToast({
					title:'尚未配置',
					icon:'none'
				})
				window.open(this.info.jk_h5_url)
			}
		}
	}
</script>

<style lang="scss" scoped>
.content{
	position: fixed;
	bottom:0;
	width: 100%;
	// height: 100upx;
	display: inline-flex;
	align-items: center;
	justify-content: space-between;
	padding:25upx 30upx;
	background: #ffffff;
	.left{
		display: inline-flex;
		justify-content: space-between;
		flex:1;
		.title{
			color: #333333;
			text-align: center;
			image{
				width:50upx;
				height:50upx;
			}
			text{
				display:block;
				font-size:24upx;
			}
		}
	}

	.btn{
		width: 300upx;
		height: 80upx;
		background: #3994FF;
		border-radius: 20px;
		color: #fff;
		text-align: center;
		line-height: 80upx;
		margin-left: 120upx;
	}
}


</style>
