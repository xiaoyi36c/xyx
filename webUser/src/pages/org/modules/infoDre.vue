<template>
	<view class="content">
		<view class="title">
			<u-icon name="order" size="33" color="#3994FF"></u-icon>
			<text>机构简介</text>
		</view>
		<view class="cont" v-if="info.org_synopsis">
				<rich-text :nodes="info.org_synopsis"></rich-text>
		</view>
		
		<view v-else :style="{
				height:`calc(100vh  - 830upx )` ,
				overflow:'hidden'
			}">
		<empty  dre="暂无简介"></empty>
		</view>
	</view>
</template>

<script>
	export default {
		props:['info'],
		data() {
			return {

			};
		}
	}
</script>

<style lang="scss" scoped>
.content{
	width:100%;
	margin-top: 20upx;
	padding:25upx 30upx;
	background: #ffffff;
	justify-content: space-between;
	.title{
		font-weight: 500;
		color: #333333;
		text{
			font-size: 28upx;
			margin-left: 10upx;
		}
	}
	.cont{
		margin-top: 15upx;
		overflow: hidden;
	}
}


</style>
