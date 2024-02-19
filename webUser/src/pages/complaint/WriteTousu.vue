<template>
	<view class="">
		<view class="content">
			<view class="rateCont">
				<view class="line">
					<text>投诉类型</text>
				</view>
				<view class="lineTxt" @click="show = true" >
					<template v-if="queryPamars.complaintTypeName">
						{{queryPamars.complaintTypeName }}
					</template>
					<text v-else class="u-tips-color">请选择</text>
					<u-icon name="arrow-right"></u-icon>
				</view>
			</view>
			<u-action-sheet  @click="TypeClick" :list="typeList" v-model="show"></u-action-sheet>
		</view>
		<view style="text-align: center;">
			<view style="background: #f2f2f2;height: 1upx;margin: 0 30upx;"></view>
		</view>
		<view class="contBox">
			<view class="title">描述凭证
				<text class="u-font-10 u-m-l-0 u-tips-color">（图片上传大小限制5m以内）</text>
			</view>
			<view class="cont">
				<textarea v-model="queryPamars.complaintData" class="textarea" placeholder="请输入内容"></textarea>
				<view class="upload">
					<u-upload 
						:action="$api.domain + Interface.uploadFile"
						@afterRead="afterRead" 
						@delete="deletePic" 
						multiple
						:max-size="5 * 1024 * 1024"
						@on-success="onSuccess"
						:maxCount="3"></u-upload>
				</view>
			</view>
		</view>
		<!-- 提交 -->
		<view class="btn" @click="sub"> 提交投诉 </view>
	</view>

</template>

<script>
	export default {
		data() {
			return {
				show:false,
				queryPamars: {
					complaintTypeName:'',
					complaintType: '',
					complaintData: '',
				},
				typeList:[],
				fileList: []
			};
		},
		mounted() {
			this.$api.get(this.Interface.getComplaintType).then(res=>{
				this.typeList = res.data.length?res.data.map(e=>{e.text = e.lable;return e}) :[];
			})
		},
		methods: {
			TypeClick(index) {
				this.queryPamars.complaintType = this.typeList[index].id
				this.queryPamars.complaintTypeName = this.typeList[index].lable
				console.log(this.typeList[index])
			},
			sub(){
				if(this.queryPamars.complaintType ==='') return uni.showToast({
					icon:'none',
					title:'请先选择投诉类型'
				})
				if(!this.queryPamars.complaintData) return uni.showToast({
					icon:'none',
					title:'请填写投诉内容'
				})
				this.$api.post(this.Interface.addComplaint,{
					...this.queryPamars,
					fileList:this.fileList,
					orgId:this.$route.query.id,
					orgName:this.$route.query.orgName
				}).then(res=>{
					this.$common.successToShow('提交成功');
					uni.navigateBack({
						delta: 1
					})
				})
			},
			//上传成功的钩子 (data, index, lists, name)，data为服务器返回的数据，name为通过props传递的index参数
			onSuccess(data, index, lists, name){
				if(data.code ==200){
					this.fileList.push(data.data)
				}
				this.$common.successToShow('上传成功')
				console.log(this.fileList)
			},
			// 删除图片
			deletePic(event) {
				this[`fileList`].splice(event.index, 1)
			},
			// 新增图片
			async afterRead(event) {
				console.log(event)
				// 当设置 multiple 为 true 时, file 为数组格式，否则为对象格式
				let lists = [].concat(event.file)
				let fileListLen = this[`fileList${event.name}`].length
				lists.map((item) => {
					this[`fileList${event.name}`].push({
						...item,
						status: 'uploading',
						message: '上传中'
					})
				})
				for (let i = 0; i < lists.length; i++) {
					const result = await this.uploadFilePromise(lists[i].url)
					let item = this[`fileList${event.name}`][fileListLen]
					this[`fileList${event.name}`].splice(fileListLen, 1, Object.assign(item, {
						status: 'success',
						message: '',
						url: result
					}))
					fileListLen++
				}
			},
			uploadFilePromise(url) {
				return new Promise((resolve, reject) => {
					let a = uni.uploadFile({
						url: this.Interface.uploadFile, // 仅为示例，非真实的接口地址
						filePath: url,
						name: 'file',
						formData: {
							user: 'test'
						},
						success: (res) => {
							setTimeout(() => {
								resolve(res.data.data)
							}, 1000)
						}
					});
				})
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
		// margin-top: 20upx;
		padding: 25upx 30upx;
		// margin:0 30upx;
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