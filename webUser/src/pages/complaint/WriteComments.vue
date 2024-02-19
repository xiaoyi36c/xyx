<template>
	<view class="">
		<view class="content">
			<view class="startStatus">
				<view class="item" v-for="(item,index) in rateStatus" :key="index">
					<image :src="item.img" mode=""></image>
					<text>{{item.title}}</text>
				</view>
			</view>
			<view class="start">
				<u-rate activeColor="#FF8C00" v-model="queryPamars.appraiseNum" allowHalf></u-rate>
			</view>
			<view class="rateCont">
				<view class="line">
					<text>环境</text>
					<u-rate allowHalf v-model="queryPamars.environmentAppraise" activeColor="#FF8C00"></u-rate>
				</view>
				<view class="lineTxt">
					{{queryPamars.environmentAppraise>4?'超好':(queryPamars.environmentAppraise>3 && queryPamars.environmentAppraise<=4?'不错':(queryPamars.environmentAppraise>1 && queryPamars.environmentAppraise<=3?'一般':'较差'))}}
				</view>
			</view>
			<view class="rateCont">
				<view class="line">
					<text>师资</text>
					<u-rate allowHalf v-model="queryPamars.teachersAppraise" activeColor="#FF8C00"></u-rate>
				</view>
				<view class="lineTxt">
					{{queryPamars.teachersAppraise>4?'超好':(queryPamars.teachersAppraise>3 && queryPamars.teachersAppraise<=4?'不错':(queryPamars.teachersAppraise>1 && queryPamars.teachersAppraise<=3?'一般':'较差'))}}
				</view>
			</view>
			<view class="rateCont">
				<view class="line">
					<text>服务</text>
					<u-rate allowHalf v-model="queryPamars.serverAppraise" activeColor="#FF8C00"></u-rate>
				</view>
				<view class="lineTxt">
					{{queryPamars.serverAppraise>4?'超好':(queryPamars.serverAppraise>3 && queryPamars.serverAppraise<=4?'不错':(queryPamars.serverAppraise>1 && queryPamars.serverAppraise<=3?'一般':'较差'))}}
				</view>
			</view>
			<view class="rateCont">
				<view class="line">
					<text>效果</text>
					<u-rate allowHalf v-model="queryPamars.resultAppraise" activeColor="#FF8C00"></u-rate>
				</view>
				<view class="lineTxt">
					{{queryPamars.resultAppraise>4?'超好':(queryPamars.resultAppraise>3 && queryPamars.resultAppraise<=4?'不错':(queryPamars.resultAppraise>1 && queryPamars.resultAppraise<=3?'一般':'较差'))}}
				</view>
			</view>
		</view>
		<view class="contBox">
			<view class="title">补充描述
				<text class="u-font-10 u-m-l-0 u-tips-color">（图片上传大小限制5m以内）</text>
			</view>
			<view class="cont">
				<textarea class="textarea" v-model="queryPamars.appraiseData" placeholder="请输入内容"></textarea>
				<!-- {{fileList}} -->
				<view class="upload">
					<!-- <image v-for="(v,i) in fileList" :key="i" :src="v.file_url"></image> -->
					<u-upload 
						:action="$api.domain + Interface.uploadFile"
						@on-remove="deletePic" 
						multiple
						:max-size="5 * 1024 * 1024"
						:fileList="fileList"
						@on-success="onSuccess"
						@on-oversize="onOversize"
						:maxCount="3"></u-upload>
				</view>
			</view>
		</view>
		<!-- 提交 -->
		<view class="btn" @click="sub"> 提交评论 </view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				rateStatus: [{
					title: '很糟糕',
					img: '../../static/org/icon_e_1.png'
				},
				{
					title: '较差',
					img: '../../static/org/icon_e_2.png'
				},
				{
					title: '一般',
					img: '../../static/org/icon_e_3.png'
				},
				{
					title: '不错',
					img: '../../static/org/icon_e_4.png'
				},
				{
					title: '超预期',
					img: '../../static/org/icon_e_5.png'
				},
				],
				info:{},//机构信息
				fileList: [],
				queryPamars:{
					appraiseNum:0,//总评
					environmentAppraise:0,//环境
					teachersAppraise:0,//师资
					serverAppraise:0,//服务
					resultAppraise:0,//效果，
					appraiseData:'',//评价内容
					fileList:[]
				}
			};
		},
		mounted() {
			if(this.$route.query?.id){
				this.$api.get(this.Interface.getOrgDetail,{id:this.$route.query.id}).then(e=>{
					 this.info = e.data
				});
			}
			if(this.$route.query?.cid){
				this.$api.get(this.Interface.getEvaluateDetail,{id:this.$route.query?.cid}).then(res=>{
					console.log(res.data,'res=======')
					this.fileList = res.data.fileList.length? res.data.fileList.map(e=>{ return {
						url:e.file_url,
						...e
					}}):[];
					console.log(this.fileList)
					this.queryPamars = res.data;
				})
			}
		},
		methods: {
			onOversize(file, lists, name){
				console.log(file, lists, name)
			},
			sub(){
				this.$api.post(this.Interface.evaluate,{
					...this.queryPamars,
					orgId:this.info.id,
				}).then(res=>{
					this.$common.successToShow('提交成功');
					setTimeout(()=>{						
						uni.navigateBack({
							delta: 1
						})
					},1500)
				})
			},
			//上传成功的钩子 (data, index, lists, name)，data为服务器返回的数据，name为通过props传递的index参数
			onSuccess(data, index, lists, name){
				// debugger
				if(data.code ==200){
					console.log(data.data)
					this.fileList[index] = {
						url:data.data.fileUrl
					}
					this.queryPamars.fileList[index] = data.data
				}
				this.$common.successToShow('上传成功')
			},
			// 删除图片
			deletePic(index, lists, name) {
				this.fileList = lists;
				this.queryPamars.fileList.splice(index,1)
				this.$forceUpdate()
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		padding: 25upx 30upx;
		background: #ffffff;
		.startStatus {
			width: 100%;

			.item {
				width: 20%;
				display: inline-flex;
				align-items: center;
				justify-content: center;
				text-align: center;

				image {
					width: 32upx;
					height: 32upx;
				}

				text {
					font-size: 28upx;
					margin-left: 5upx;
				}
			}
		}

		.start {
			margin-top: 10upx;
			margin-bottom: 6upx;
			display: inline-flex;
			width: 100%;
			justify-content: space-around;

			image {
				width: 40upx;
				height: 40upx;
			}

			::v-deep .u-rate {
				width: 100%;
				justify-content: space-between;

				.u-star-wrap {
					text-align: center;
					width: 20%;
				}
			}
		}

		.rateCont {
			display: inline-flex;
			align-items: center;
			width: 100%;

			.line {
				flex: 1;
				display: inline-flex;
				margin-top: 8upx;
				align-items: center;
				.u-rate{
					margin-top: 5upx;
				}
				text {
					font-size: 28upx;
					color: #333333;
					font-weight: 500;
					margin-right: 8upx;
				}
			}

			.lineTxt {
				// width: 50upx;
				text-align: right;
				font-size: 28upx;
			}
		}
	}

	.contBox {
		margin-top: 20upx;
		padding: 25upx 30upx;
		background: #ffffff;

		.title {
			font-size: 28upx;
			font-weight: 500;
			color: #333333;
		}

		.cont {
			margin-top: 20upx;
			border-radius: 8upx;
			background: #F5F5F5;
			height: 50vh;
			position: relative;
			textarea{
				width: calc(100% - 20upx);
				font-size: 28upx;
				padding: 10upx;
			}
			.upload {
				position: absolute;
				bottom: 0;
				// display: inline-flex;
				// image{
				// 	width:200upx;
				// 	height:200upx;
				// 	margin: 10upx;
				// }
				 ::v-deep .u-upload{
					.u-list-item{
						border:  1upx dashed #ccc;
						.u-delete-icon{
							top:0upx;
							right:-5upx;
							background-color: transparent;
							background: none!important;
							background:url('~@/static/org/icon_del.png') no-repeat 0upx !important;
							background-repeat: no-repeat;
							.uicon-close:before{
								content:''
							}
						}
					}
				}
			}
		}
	}
	.btn{
		margin: 25upx 30upx;
        text-align: center;
		padding: 20upx 0;
		background: #3994FF;
		border-radius: 20px;
		color:#fff;
	}
</style>