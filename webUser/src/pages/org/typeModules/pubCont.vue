<template>
	<view class="content">
		<view class="tabBox" v-if="childList.length">
			<u-tabs :current="current"   :list="childList" @change="tapChild"></u-tabs>
		</view>
		<view class="place" v-else></view>
		<template v-if="info.length">
			<scroll-view class="boxCont" 
			@scrolltolower="loadMore"
			scroll-y
			:style="{
				height:`calc(100vh  - 260upx  + ${childList.length?0:64}upx)` 
			}">
				<view class="ListBox" v-for="(v, i) in info" :key="i">		
					<view class="line" v-for="(item, idx) in v" :key="idx">
						<view class="left">
							{{ idx }}
						</view>
						<view class="right">
							<template v-if="item &&  item.length>200">
								<template v-if="v.open">
									{{ item}}
								</template>
								<template v-else>
									{{ item.substring(0,200)+'...' }}
								</template>
							
							</template>
							<template v-else>
								{{ item}}
							</template>
						</view>
					</view>
				</view>
				<!-- <u-loadmore :status="status" /> -->
			</scroll-view>
		</template>
		<template v-else>
			<view :style="{
				height:`calc(100vh  - 260upx  + ${childList.length?0:64}upx)` 
			}">
				<empty></empty>
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
				currType:1,
				info:[],
				childList:[],
				current:0,
				url:'',
			};
		},
		mounted() {
			
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
				console.log(type,i)
				let urls =['getAdministrativePenalty','getHonestyTrustworthiness','getLoseCredit','getCreditCommitment','','','getFeeOther'];
				this.url = urls[i]
				this.current=0;
				this.childList=[];
				this.info=[];
				this.status = 'loadmore';
				//对信用评价及司法判决直接转为空 ：type： 5,6
				if(['5','6'].includes(type)){
					this.status == 'nomore' 
					this.childList=[];
					this.info=[];
					return	false;
				}
				this.$api.get(this.Interface.getCreditChildrenList,{
					type:type,
					orgId: this.$route.query.id,
				}).then(res=>{
					this.childList= res.data.length ? res.data.map(e=>{e.name = e.value +`(${e?.countNum || 0})`; return e}) :[];
				}).then(()=>{
					this.getChildList(1)
				})
			},
			getChildList(type){
				//对信用评价及司法判决直接转为空 ：type： 5,6
				if(['5','6'].includes(type)){
					this.status == 'nomore' 
					this.info=[];
					return	false;
				}
				this.currType = type;
				console.log(this.Interface[this.url])
				if(this.status == 'nomore' ) return;
				this.$api.get(this.Interface[this.url],{
					type: type,
					orgId: this.$route.query.id,
					...this.pageNation
				}).then(res=>{
					this.info = this.info.concat(res.rows);
					if (this.info.length >= res.total) this.status = 'nomore';
					else this.status = 'loadmore';
				})
			},
			tapChild(i){
				this.status = 'loadmore';
				this.current = i;
				this.pageNation.pageNum = 1;
				this.info=[];
				this.getChildList(this.childList[i]['key'])
			}
		}
	}
</script>

<style lang="scss" scoped>
.content{
	padding: 0upx 20upx;
	background: #ffffff;
	.ListBox{
		white-space: pre-wrap;
		margin-bottom: 15px;
	}
	.line{
		display: inline-flex;
		width:100%;
		font-size:25upx;
		.left{
			padding: 15upx;
			width:300upx;
			border: 1px solid #E6E6E6;
			display: inline-flex;
				align-items: center;
				border-right: 0;
				background: #F6FAFF;
		}
		.right{
			padding: 15upx 10upx;
			flex:1;
			border: 1px solid #E6E6E6;
			.open{
				color:#fff;
				background: #3994FF;
				padding: 3upx 8upx;
			}
		}
	}
}
</style>
