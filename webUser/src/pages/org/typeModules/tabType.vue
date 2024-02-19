<template>
	<view class="content">
		<u-tabs
		 :scrollable="false"
				:list="tabList" 
				:current="current"  
				@change="tapTab"
				active-color="#3994FF"
				></u-tabs>
	</view>
</template>

<script>
    export default {
        data() {
            return {
				current:0,
                tabList: [{
                    name: '信用信息',
                }, {
                    name: '服务信息',
                }, {
                    name: '荣誉&责任'
                }, {
                    name: '校区'
                }]
            }
        },
		mounted() {
			this.current = this.$route.query?.typeIndex || 0
			this.tapTab(this.current)
		},
		methods:{
			tapTab(e){
				this.current = e;
				this.$emit('tapTab',e)
			}
		}
    }
</script>

<style lang="scss" scoped>
	.content{
		width:100%;
		position: fixed;
		background: #fff;
		z-index: 1;
	}
	::v-deep .u-tab-item{
		width:25%!important;
		text-align: center;
		font-size: 35upx;
		padding: 0!important;
	}
</style>