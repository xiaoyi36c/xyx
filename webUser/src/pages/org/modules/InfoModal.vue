<template>
	<view class="content">
		<view class="title" style="align-items: center;">
			<text>{{info.organization_name}}</text>
			<!-- <text class="score" v-if="info.credit_score >650">信用评级：{{info.credit_rank}}  信用分：{{info.credit_score}} </text> -->
		</view>
		<!-- <view class="title rate" style="align-items: center;" v-if="info.credit_score >650">
			<text class="score">信用评级：{{info.credit_rank}} </text>
			<text class="score" > 信用分：{{info.credit_score}} </text>
		</view> -->
<!-- 		<view class="title">
			<text>信用等级：{{info.credit_rank}}  </text>
			<text class="score" v-if="info.credit_score >650">信用等级：{{info.credit_rank}}  信用分：{{info.credit_score}}分 </text>
		</view> -->
		<!-- <view class="rate">
			<u-rate disabled v-model="info.appraiseNum"></u-rate>
			<text class="count">{{info.appraiseCount || 0}}条评价</text>
		</view> -->
		<view class="banner" >
			<image 
				v-for="(v,i) in info.fileList"  
				:key="i" 
				:src="v.front_door_photo"
				 mode="aspectFit"
				 @error="imgerror(i)"
			></image>
		</view>
		<view class="rate addr">
			<text><u-icon name="map"></u-icon>{{info.area_detail || '暂无'}}</text>
			<!-- <text class="distance u-font-11 u-tips-color" v-if="info.area_detail">距我{{info.distance || '-'}}km</text> -->
		</view>
	</view>
</template>

<script>
	export default {
		props:{
			info:{
				type:Object,
				defaule:()=>{}
			}
		},
		data() {
			return {
				
			};
		},
		methods:{
			imgerror(index) {
				this.info.fileList[index]['front_door_photo'] = require('@/static/default.png')
				// this.item.enterprise_logo = require('@/static/default.png')
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content{
		padding:25upx 30upx;
		background: #ffffff;
	}
.title{
	font-size: 30upx;
	font-weight: 500;
	color: #333333;
	display: inline-flex;
	width:100%;
	justify-content: space-between;
	.score{
		font-size: 25upx;
		font-weight: 400;
		color: #999999;
	}
}
.rate{
	margin-top: 10upx;
	display: inline-flex;
	width:100%;
	justify-content: space-between;
	.count{	
		font-size: 25upx;
		font-weight: 400;
		color: #999999;
	}
}
.banner{
	margin-top: 10upx;
	width:100%;
	white-space: nowrap;
	overflow-y: hidden;
	overflow-x: auto;
	image{
		width: 240upx;
		height:150upx;
		margin-right: 10upx;
	}
}
.addr{
	margin-top: 0;
	padding:8upx 0;
	align-items: center;
	.distance{
		
	white-space: nowrap;
	}
}
</style>
