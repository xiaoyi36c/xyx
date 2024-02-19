<script>
import { publicMixin } from '@/mixins/publicMixin.js';
import AMapLoader from '@amap/amap-jsapi-loader'
export default {
	mixins: [publicMixin],
	globalData: {
		allCity:[],
		city:{
			label:'',
			value:''
		},
		isScroll:false,//禁止下拉刷新
	},
	onLaunch: function() {
		if (navigator.geolocation) {
		
		console.log("浏览器支持!");
		
		}
		
		else {
		
		console.log("浏览器不支持!");
		
		}

		// AMapLoader.load({
		// 	key: '3db10cfc9a69cbd2d1aae6e2abf80e77',
		// 	version: '2.0',
		// 	plugins: ['AMmap.PlaceSearch', 'AMap.Geolocation', 'AMap.Geocoder'],
		// }).then((AMap) => {
		// 	console.log('map=', AMap)
		// 	this.$nextTick(() => {
		// 		//获取当前定位，设置中心点，需要开启https访问
		// 	   AMap.plugin('AMap.Geolocation', function() {
		// 			var geolocation = new AMap.Geolocation({
		// 				enableHighAccuracy: true,//是否使用高精度定位，默认:true
		// 				timeout: 10000,          //超过10秒后停止定位，默认：5s
		// 				buttonPosition:'RB',    //定位按钮的停靠位置
		// 				buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
		// 				zoomToAccuracy: true,   //定位成功后是否自动调整地图视野到定位点
		// 			});
		// 			geolocation.getCurrentPosition(function(status,result){
		// 				if(status=='complete'){
		// 					console.log(result)
		// 					//[104.053023,30.588744]
		// 					that.$store.commit('saveRegionData',result.positon)
		// 				}else{
		// 					console.log(result)
							
		// 				}
		// 			});
		// 		});
		// 	})
		// }).catch(err => {
		// 	console.log('err==', err)
		// })
	},
	methods: {
	},
	onShow: function() {
		// let that= this;
		// setTimeout(()=>{
		// 	console.log('show=====',window);
		// 	(new BMap.Geolocation()).getCurrentPosition(function(r){
		// 		console.log(r, '====')
		// 	    if(r?.point){
		// 			that.$store.commit('saveRegionData',[r.point.lng,r.point.lat])
		// 	    }     
		// 	});
		// },1500)
		// console.log('show=====',window['onBMapCallback'])
		// window.onBMapCallback()

	},
	onHide: function() {
		console.log('App Hide');
	}
};

</script>

<style lang="scss">
@import 'uview-ui/index.scss';
@import './common/css/cell.scss';
page {
	background: #f5f6f8 !important;
}
//返回顶部
.backTop{
	position: fixed;
	right: 30upx;
	bottom:100upx;
	z-index: 99;
	width: 80upx;
	height: 80upx;
	background: #f5f5f5;
	box-shadow:0upx 0upx 10upx 5upx #ccc;
	border-radius: 50%;
	text-align: center;
	font-size: 24upx;
	text{
		line-height: 40upx;
		display: block;
	}
}
// 去掉地图logo
.amap-logo,.amap-copyright,.anchorBL{
    display: none!important;
}
.uicon-arrow-right::before{
	color:#999999;
}
</style>
<style lang="scss">
	.u-more{
		padding-top: 10upx!important;
	}
	.place{
		height:35upx;
	}
	.custom-content-marker{
	    background: #fff;
	    padding: 5px 0;
	    width:120upx;
	    position: relative;
	    border-radius: 8px;
	    box-shadow: 0 0 10upx 5upx #ccc;
	    .custom-content-title{
	        font-size:13px;
			text-align: center;
			position: relative;
	        span{
	           font-size: 20upx;
	           color:#333;
	           padding: 2px 6px;
	        }
	
	    }
	    .custom-content-tag{
	        position: absolute;
	        width: 0px;
	        height: 0px;
	        border: 10upx solid transparent;
	        margin-left: 50upx;
	        border-top-color: red;
	        margin-top: 10upx;
	        z-index: 12;
			
	    }
	}
	.custom-content-Curr{
		background: #fff;
		padding: 5px 0;
		width:120upx;
		position: relative;
		border-radius: 8px;
		font-size: 25upx;
		text-align: center;
		box-shadow: 0 0 10upx 5upx #ccc;
		.custom-content-curr{
			position: absolute;
			width: 0px;
			height: 0px;
			border: 20upx solid transparent;
			border-top-color: red;
			margin-left: -20upx;
			margin-top: 10upx;
			z-index: 12;
		}
	}
</style>