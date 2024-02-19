<template>
	<view class="content">
		<view class="banner" v-for="(v,i) in typeList" :key="i" @tap="orgType(i)">
			<image :src="v.img" mode="scaleToFill"></image>
			<text>{{v.title}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				typeList: [{
					title: '信用信息',
					img: '../../static/org/icon_xyxx.png'
				}, {
					title: '服务信息',
					img: '../../static/org/icon_fwxx.png'
				}, {
					title: '荣誉&责任',
					img: '../../static/org/icon_zeren.png'
				}, {
					title: '校区',
					img: '../../static/org/icon_xiaoqu.png'
				}]
			};
		},
		methods:{
			orgType(i){
				this.$emit('orgTypeTap',i)
			}
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		display: inline-flex;
		width: 100%;
		margin-top: 20upx;
		padding: 25upx 30upx;
		background: #ffffff;
		justify-content: space-between;
	}

	.banner {
		width: 130upx;
		text-align: center;

		image {
			width: 80upx;
			height: 80upx;
			margin: 0 auto;
		}

		text {
			// white-space: nowrap;
			display: block;
			font-size: 26upx;
			text-align: center;
		}
	}
</style>