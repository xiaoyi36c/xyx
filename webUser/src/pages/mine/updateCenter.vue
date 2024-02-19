<template>
	<view>
		<view class="intBox">
			<u-input :placeholder="`请输入${titleList[$route.query.key]}`"  :type="$route.query.key =='password'?'password':'text'" auto-focus v-model="val" />
		</view>

		<template v-if="$route.query.key =='password'">
			<view class="intBox">
				<u-input type="password" placeholder="请输入新密码" v-model="newval"/> 
			</view>
			<view class="intBox">
				<u-input type="password" placeholder="请再次输入新密码" v-model="aginval"/>
			</view>
			
			
		</template>
		<view class="btnBox">
			<view class="btn" @click="updateInfoCell"> 更新 </view>
			<view class="center" @click="cancle"> 取消 </view>
		</view>
	</view>
</template>

<script>
	const titleList = {
		nick_name:'昵称',
		email:'邮箱',
		user_name:'手机号',
		password:'密码',
		id_card:'身份证号'
	}
	export default {
		data() {
			return {
				titleList,
				val:'',
				newval:'',
				aginval:'',
			};
		},
		mounted() {
			this.val = this.$route.query?.val  && this.$route.query.val !='undefined' ? this.$route.query.val : ''
			document.title =`设置`+ titleList[this.$route.query.key]
		},
		methods:{
			cancle(){
				uni.navigateBack({
					delta:1
				})
			},
			updateInfoCell(){
				let val =this.val;
				if(this.$route.query.key == 'email'){
					if(!this.$u.test.email(this.val)) return this.$common.errorToShow('邮箱输入有误');
				}
				if(this.$route.query.key == 'user_name'){
					if(!this.$u.test.mobile(this.val)) return this.$common.errorToShow('手机号输入有误');
				}
				if(this.$route.query.key == 'id_card'){
					if(!this.$u.test.idCard(this.val)) return this.$common.errorToShow('身份证格式输入有误');
				}
				if(this.$route.query.key == 'password'){
					if(this.val.length<5) return this.$common.errorToShow('至少6位字符以上');
					if(this.newval == this.val) return this.$common.errorToShow('不能和旧密码一样');
					if(this.newval != this.aginval) return this.$common.errorToShow('两次输入密码不一致');
					val = this.newval ;
				}
				let params = {
					[this.$route.query.key]:val
				}
				this.$api.post(this.Interface.updateMyselfDetail,params).then(res=>{
					this.$common.successToShow('更新成功');
					if(this.$route.query.key == 'password'){
						return uni.reLaunch({
							url:'/pages/login/login'
						})
					}
					setTimeout(()=>{
						uni.navigateBack({
							delta:1
						})
					},1000)
				})
			},
		}
	}
</script>

<style lang="scss" scoped>
	.intBox{
		background: #fff;
		padding:15upx 30upx;
		border-bottom: 1upx solid #ccc;
	}
	.btnBox{
		padding-top: 100upx;
		text-align: center;
		margin: 25upx 30upx;
		view{
			margin-top: 15upx;
			font-size:26upx ;
		}
	}
	.btn{
		font-size:30upx!important;
        text-align: center;
		padding: 20upx 0;
		background:#3994FF;
		border-radius: 20px;
		color:#fff;
	}
</style>
