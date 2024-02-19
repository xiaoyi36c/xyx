<template>
	<view>
		<view @click="openAddress">
			<text>{{value ||  cityLable}}</text>
			<u-icon  style="margin:0 5upx;" name="arrow-down-fill"></u-icon>
		</view>
		<city style="left:0;" ref="scroll" @change='chooseSuccess'></city>
	</view>
</template>

<script>
	import city from '@/components/city.vue'
	export default {
		name: "regionPicker",
		components:{
			city
		},
		computed:{
			// cityLable(){
			// 	return getApp().globalData.city.label || '全国'
			// }
		},
		data() {
			return {
				value:'',
				cityLable:''
			};
		},
		mounted() {
			console.log(233)
			this.cityLable  =getApp().globalData.city.label || '全部'
		},
		methods: {
			//打开地址选择器
			openAddress() {
			    this.$refs.scroll.open()
				getApp().globalData.isScroll = true;
			},
			//地址选择成功
			 chooseSuccess(e) {
				getApp().globalData.isScroll = false;
				getApp().globalData.allCity = e.value
				getApp().globalData.city=e.value[e.value.length -1];
				this.value = getApp().globalData.city.label
				this.$emit('chooseCity')
			    console.log('所选择的地址信息:', e,getApp().globalData.city)
			},
		},
		created() {

		},
	}
</script>

<style>
::v-deep .scroll-popup{
	left:0;
}
</style>
