<template>
	<view class="content">
		<InfoModal :info="info"/>
		<infoType :info="info"  @orgTypeTap="orgTypeTap"/>
		<infoDre :info="info" />
		<view style="height: 170upx;"></view>
		<infoBottom :info="info" />
	</view>
</template>

<script>
	import InfoModal from "./modules/InfoModal.vue"
	import infoType from "./modules/infoType.vue"
	import infoDre from "./modules/infoDre.vue"
	import infoBottom from "./modules/infoBottom.vue"
	export default {
		components:{
			InfoModal,infoType,infoDre,infoBottom
		},
		data() {
			return {
				info:{}
			};
		},
		computed:{
			 currentPostion(){
				 return this.$store.state.regionData
			 }
		},
		mounted() {
			this.$api.get(this.Interface.getOrgDetail,{
				id:this.$route.query.id,
				long:this.currentPostion[0],
				lat:this.currentPostion[1],
			}).then(e=>{
				 this.info = e.data
			})
		},
		methods:{
			orgTypeTap(i){
				uni.navigateTo({
					url:`/pages/org/orgType?typeIndex=${i}&id=${this.$route.query.id}`
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
.content{

}
</style>
