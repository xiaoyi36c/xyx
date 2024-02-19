<template>
	<view>
        <view class="box">
            <view class="left">
                <view class="title">{{rateInfo[0].appraise_num}}</view>
                <view class="rate">
					<view class=""> 
						{{rateInfo[0].appraise_num >4?'超赞':(rateInfo[0].appraise_num >2 && rateInfo[0].appraise_num <4?'良好':'较差')}}
					</view>
                    <u-rate activeColor="#FF8C00" disabled v-model="rateInfo[0].appraise_num || 0"></u-rate>
                </view>
            </view>
            <view class="right">
                <view class="info">
					{{rateInfo[0].environment_appraise}}<text>环境</text>
                </view>
                <view class="info">
					{{rateInfo[0].teachers_appraise}}<text>师资</text>
                </view>
                <view class="info">
					{{rateInfo[0].server_appraise}}<text>服务</text>
                </view>
                <view class="info">
					{{rateInfo[0].result_appraise}}<text>效果</text>
                </view>
            </view>
        </view>
		<view class="listbox" v-for="(v,i) in list" :key="i">
			<view class="listItem" >
				<image class="itemImg" :src="v.head_imager" mode=""></image>
				<view class="ItemRight">
					<view class="title">
						<text class="info">{{v.created_by_name}}</text>
						<text class="count" v-if="v.phone">{{v.phone.substr(0,4)+'****'+v.phone.substr(8,11)}}</text>
					</view>
					<view class="rate">
						<u-rate activeColor="#FF8C00" disabled :value="v.appraise_num"></u-rate>
						  环境:{{v.environment_appraise}}  
                          师资：{{v.teachers_appraise}}   
                          服务{{v.server_appraise}}   
                          效果：{{v.result_appraise}}  
					</view>
				</view>
			</view>
			<view class="dre">
				{{v.appraise_data}}  
			</view>
			<view class="other" v-if="v.hasOwnProperty('fileList') && v.fileList.length">
			    <image fit="cover"  v-for="(item,i) in v.fileList" :key="i" :src="item.file_url"></image>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props:{
			list:{
				type:Array,
				default:()=>[]
			},
			rateInfo:{
				type:Array,
				default:()=>[]
			}
		},
		data() {
			return {
				
			}
		},
		mounted() {

		},
		methods: {
			
		}
	}
</script>

<style lang="scss" scoped>
	.box{
	    background: #FAFBFC;
	    border-radius: 0px 0px 0px 0px;
	    display: inline-flex;
	    width: 100%;
	    padding:10upx 20upx;
	    margin-top: 20upx;
	    .left{
			display: inline-flex;
			align-items: center;
	        .title{
	            margin-right: 20upx;
	            font-weight: 800;
				font-size: 50upx;
	            color: #3994FF;
	            text-align: left;
	        }
	        .rate{
	            position: relative;
	            text-align: left;
	            >view{
	                font-size: 26upx;
	                font-weight: 600;
	                color: #3994FF;
	            }
	        }
	    }
	    .right{
			padding-left: 30upx;
			display: inline-flex;
			width: 100%;
			justify-content: center;
	        .info{
				font-weight: 500;
				flex:1;
				color: #3994FF;
				font-size:24upx;
				text-align: center;
	            text{
	                color: #676770;
					display: block;
	            }
	        }
	    }
	}
	.listbox{
		margin-bottom: 15upx;
			// border-bottom: 1upx solid #F2F2F2;
			.dre{
				font-size: 24upx;
				margin-left: 120upx;
			}
			            .other{
							margin-left: 120upx;
							margin-top: 10upx;
			                image{
			                    width: 180upx;
			                    height:180upx;
			                    margin-right: 20upx;
			                }
							image:last-child{
								 margin-right: 0upx;
							}
			            }
	}
.listItem{
	display: inline-flex;
	width: 100%;
	padding: 10upx 0 20upx 0;
	.itemImg{
		width: 100upx;
		height: 100upx;
		border-radius: 50%;
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
			line-height: 50upx;
			.count{
				color: #999999;
				font-size: 24upx;
				margin-left: 10upx;
			}
		}
		.rate{
			font-size :22upx;
			line-height: 50upx;
			color:#999999;
		}
		.addr{
			font-size:  24upx;
			color:#999999;
		}
	}
}
</style>
