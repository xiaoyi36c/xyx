<template>
	<view class="empty">
		<image src="@/static/index/empty.png" mode=""></image>
		<view class="dre">{{dre}}</view>
	</view>
</template>

<script>
	export default{
		name:'empty',
		props:{
			dre:{
				type:String,
				default:'暂无数据'
			}
		},
		mounted() {
	
		}
	}
</script>

<style lang="scss" scoped>
	.empty{
		text-align: center;
		width:100%;
		height:100%;
		// background: #f5f6f8 !important;
		padding-top: 10vh;
		image{
			width:50vw;
			height:35vw;
		}
		.dre{
			color:#ccc;
			font-size:28upx;
			padding-bottom: 5vh;
		}
	}
</style>