<template>
	<view class="content">
		<scroll-view class="boxCont"
		v-if="list.length"
		@scrolltolower="loadMore"
		scroll-y
		:style="{
			height:`calc(100vh  - 280upx )` 
		}">
			<view class='item' v-for="(v,i) in list" :key="i">
				<image :src="v.file_url" mode="aspectFill"></image>
			</view>
			<!-- <u-loadmore :status="status" /> -->
		</scroll-view>
		<template v-else>
			<view :style="{
					height:`calc(100vh  - 280upx )` 
				}">
			<empty ></empty>
			</view>
		</template>
	</view>
</template>

<script>
import {
	publicMixin
} from '@/mixins/publicMixin.js';
	export default {
		mixins: [publicMixin],
		components:{
			
		},
		data() {
			return {
				list:[],
				type:'',
				currType:''
			};
		},
		methods:{
			loadMore() {
				console.log('已触底');
				let that = this;
				if (this.timer) clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					this.pageNation.pageNum += 1;
					this.getChildList(this.currType)
				}, 500)
			},
			initData(type,i){
				console.log(type)
				this.status = 'loadmore';
				this.list=[];
				this.getChildList(type)
			},
			getChildList(type){
				this.currType = type;
				if(this.status == 'nomore' ) return;
				this.$api.get(this.Interface.getSocialPrestige,{
					type:type || this.type,
					orgId: this.$route.query.id,
					...this.pageNation
				}).then(res=>{
					this.list = this.list.concat(res.rows);
					if (this.list.length >= res.total) this.status = 'nomore';
					else this.status = 'loadmore';
				})
			},	
		}
	}
</script>

<style lang="scss" scoped>
.content{
	padding: 15upx 20upx;
	background: #ffffff;
	.item{
		border-radius: 4px;
		uni-image{
			width:100%;
		}
	} 
}
</style>
