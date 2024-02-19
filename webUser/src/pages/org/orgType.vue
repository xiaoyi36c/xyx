<template>
	<view class="">
		<view class="top">	
			<tabType @tapTab="tapTab"/>
			<view class="" style="height: 100upx;"></view>
			<tabChild ref="tabChild" :tabIndex="CurrPath"/>
		</view>
	</view>
</template>

<script>
	import tabType from './typeModules/tabType.vue'
	import tabChild from './typeModules/tabChild.vue'
	import pubCont from './typeModules/pubCont.vue'
	import pubCus from './typeModules/pubCus.vue'
	import pubContImg from './typeModules/pubContImg.vue'
	import pubContSc from './typeModules/pubContSc.vue'
    export default {
		components:{
			tabType,tabChild,
		},
        data() {
            return {
				CurrPath:0,
			}
        },
		mounted() {
	
		},
		methods:{
			tapTab(i){
				console.log('tapTab====',i)
				this.CurrPath = i;
				this.$refs.tabChild.initData(i);
			}
		}
    }
</script>
<style lang="scss" scoped>
	.top{
		// position: fixed;
		top:0;
		width:100%;
	}
</style>