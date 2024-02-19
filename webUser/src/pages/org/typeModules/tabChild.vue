<template>
	<view class="contentBox">
		<view  class="content1" v-if="tabIndex!=3">
			<text 
				@click="tapTab(item,index)" 
				:class="index===actIndex?'act':''" 
				 v-for="(item,index) in list[tabIndex]"
				:key="index">{{item.title}}</text>
		</view>
		<component :is="PathList[tabIndex]" ref="CusCom"></component>
<!-- 		<pubCont v-if="tabIndex == 0 "/>
		<pubCus v-if="tabIndex == 1 "/>
		<pubContImg v-if="tabIndex == 2 "/>
		<pubContSc v-if="tabIndex == 3 "/> -->
	</view>
</template>

<script>
	import pubCont from './pubCont.vue'
	import pubCus from './pubCus.vue'
	import pubContImg from './pubContImg.vue'
	import pubContSc from './pubContSc.vue'
	export default {
		data() {
			return {
				tabIndex:0, //大分类
				PathList:[pubCont,pubCus,pubContImg,pubContSc],
				actIndex: 0,// 子分类
				list:[
						[
							{ title: '行政管理', type: '1' },
							{ title: '诚实守信', type: '2' },
							{ title: '严重失信名单', type: '3' },
							{ title: '信用承诺', type: '4' },
							{ title: '信用评价', type: '5' },
							{ title: '司法判决', type: '6' },
							{ title: '其他', type: '7' },
						],
						[
							{ title: '师资介绍',type:1 }, 
							{ title: '课程介绍',type:2 },
							{ title: '学员评价',type:3 },
							{ title: '投诉',type:4 },
						],
						[
					        { title: '企业荣誉',type:3 }, 
					        // { title: '公益履职',type:6 }, 
					        { title: '党建情况',type:7 },
					        { title: '信用管理培训',type:8},
					   ],
					],
			};
		},
		methods:{
			//初始化子分类，传递参数触发子栏目的请求
			initData(tabIndex){
				console.log(tabIndex,'tabIndex=====',this.list[tabIndex],tabIndex + 1>this.list.length)
				this.tabIndex = tabIndex;
				let type =tabIndex == 3?'': this.list[tabIndex][0]['type']
				this.$nextTick(()=>{
					this.tapTab({type:type},0)
				})
			},
			tapTab(e,i){
			  this.actIndex = i;
			  this.$refs.CusCom.initData(e.type,i)
			},
			tapChild(e){
				this.current = e;
			}
		}
	}
</script>

<style lang="scss" scoped>
	.contentBox{
		// padding: 15upx 0upx;
		// background: #ffffff;
		}
	::v-deep .u-tab-item{
		color: #666666!important;
		font-size: 25upx!important;
	}
	.content1 {
		padding: 15upx 20upx 0 20upx;
		background: #ffffff;
		white-space: nowrap;
		overflow-x: auto;
		height: fit-content;

		text {
			background: #F5F5F5;
			border-radius: 13px;
			padding: 0upx 20upx;
			border-raduis: 10upx;
			color: #666666;
			font-size: 25upx;
			line-height: 50upx;
			margin-right: 20upx;
			display: inline-block;
		}

		.act {
			background: #3994FF;
			color: #FFFFFF;
			transition: .2s all;
		}
	}
</style>