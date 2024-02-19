<template>
	<view class="content">
		<view class="AmapBox">
			<view class="Amap" id="Amap">

			</view>
			<view class="countBox">
				<text @click="lookArea(v,i)" :class="countIndex ==i ?'act':''" v-for='(v,i) in areaCount'
					:key="i">{{v.areaName}}({{v.schoolRegionNum || 0}})</text>
			</view>
		</view>
		<view class="list" v-if="list.length">
			<view class="listItem" v-for="(v,i) in list " :key="i">
				<image v-show="v.logo_url" :src="v.logo_url" mode="scaleToFill"></image>
				<view class="middel" :style="{
					width: `calc(100% - ${v.logo_url?80:0}upx - 10upx - 120upx)`
				}">
					<view class="title">
						<text>{{v.campus_name}}</text>
						<!-- <text>距我{{v.distance || '-'}}km</text> -->
					</view>
					<view class="info">
						{{v.campus_address}}
					</view>
				</view>
				<view class="right">
			<!-- 		<view class="addr" @click="toAddr">
						<u-icon color="#3994FF" name="map-fill"></u-icon><text>导航</text>
					</view> -->
					<view class="tel" @click="tel(v.phone)"><u-icon color="orange"
							name="phone-fill"></u-icon><text>电话</text></view>
				</view>
			</view>
		</view>
		<empty v-else></empty>
	</view>
</template>

<script>
	import AMapLoader from '@amap/amap-jsapi-loader'
	import { GPS } from '@/utils/location.js'
	import {
		openMap
	} from '@/utils/openMap.js'
	export default {
		components: {

		},
		data() {
			return {
				countIndex: 0,
				list: [],
				areaCount: [],
				AMap: null,
				marker: null,
				regionId: ''
			};
		},
		computed:{
			 currentPostion(){
				 return this.$store.state.regionData
			 }
		},
		mounted() {
			console.log(this.$store.state.regionData)
		},
		methods: {
			lookArea(v, i) {
				this.countIndex = i;
				this.regionId = v?.area;
				this.initData()
			},
			initData() {
				// alert(JSON.stringify(getApp().globalData.currPosition))
				this.$api.get(this.Interface.getOrgSchoolRegion, {
					orgId: this.$route.query.id,
					regionId: this.regionId,
					type: 8,
					long:this.currentPostion[0],
					lat:this.currentPostion[1],
				}).then(res => {
					this.list = res.data?.schoolRegionList || [];
					this.areaCount = res.data?.areaCount || []
				}).then(() => {
					this.AmapLoad()
				})
			},
			toAddr(lat = '', lng = '', position = '') {
				// openMap(104.075809, 30.651239,'天府幸福')
				uni.showToast({
					title: '暂未开发'
				})
			},
			tel(tel) {
				uni.makePhoneCall({
					phoneNumber: tel
				})
			},

			async AmapLoad() {
				let that= this
				console.log(window._AMapSecurityConfig)
				await AMapLoader.load({
					key: '3db10cfc9a69cbd2d1aae6e2abf80e77',
					version: '2.0',
					plugins: ['AMmap.PlaceSearch', 'AMap.Geolocation', 'AMap.Geocoder'],
					AMapUI: {
						version: "1.1", // AMapUI 版本
					},
				}).then((AMap) => {
					console.log('map=', AMap)
					this.$nextTick(() => {
						AMap.plugin('AMap.Geolocation', function() {
							var geolocation = new AMap.Geolocation({
								enableHighAccuracy: true, //是否使用高精度定位，默认:true
								timeout: 10000, //超过10秒后停止定位，默认：5s
								buttonPosition: 'RB', //定位按钮的停靠位置
								        showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
								        showCircle: true,        //定位成功后用圆圈表示定位精度范围，默认：true
								buttonOffset: new AMap.Pixel(10, 20), //定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
								zoomToAccuracy: true, //定位成功后是否自动调整地图视野到定位点
							});

							geolocation.getCurrentPosition(function(status, result) {
								if (status == 'complete') {
									console.log(result)
									// alert(JSON.stringify(result))
									 // var gpsPoint = GPS.gcj_decrypt(result.position.getLat(), result.position.getLng());
									// alert(JSON.stringify(gpsPoint)+'-----------------------')
									that.$store.commit('saveRegionData', result.position)
								} else {
									console.log(result)
								}
								that.initMap();
							});
						});
					})
				}).catch(err => {
					console.log('err==', err)
				})

			},
			initMap(position) {
				this.marker, this.AMap = new AMap.Map('Amap', {
					//设置地图容器id
					viewMode: "3D", //是否为3D地图模式
					zoom: 10, //初始化地图级别
					resizeEnable: true,
					center:this.currentPostion, //初始化地图中心点位置
				});
				//
				this.addMarker()
				// this.addCurrMarker().then((res)=>{					
				// 	//添加机构点位
				// 	this.addMarker(res)
				// })
				// this.AMap.on('click', this.showInfoClick);
			},
			// async addCurrMarker(){
			// 	let that =this;
			// 	console.log(that.currentPostion);
			// 		let markerContent = `<div class="custom-content-Curr">   
			// 				 <div>当前位置</div>
			// 				<span class="custom-content-curr"></span>
			// 			</div>`
			// 		// 遍历生成多个标记点
			// 		let marker = new AMap.Marker({
			// 			map: that.AMap,
			// 			zIndex: 9999999,
			// 			content: markerContent, // 将 html 传给 content
			// 			offset: new AMap.Pixel(0, 0),
			// 			position:that.currentPostion 
			// 		});
			// 		return marker
			// },
			addMarker(e) {
				// let makerList = [].concat(e);
				let makerList = [];
				this.list.map((item, i) => {
					let markerContent = `<div class="custom-content-marker status_${item.business_status}">
			                <div class="custom-content-title">
			                    <span>${item.campus_name}</span>
			                </div>
							<span class="custom-content-tag"></span>
			                </div>`
					// 遍历生成多个标记点
					let marker = new AMap.Marker({
						map: this.AMap,
						zIndex: 9999999,
						content: markerContent, // 将 html 传给 content
						offset: new AMap.Pixel(0, 0),
						position: [item.longitude, item.latitude]
					});
					makerList.push(marker);
					marker.on('click', function(e) {
						console.log('点击了 Marker');
						console.log(e, '====')
					});
				});
				this.AMap.add(makerList)
			},
			showInfoClick(e) {
				console.log(e, '====')
				// 自动适应显示想显示的范围区域
				// this.marker.setMap(this.AMap);
				// this.AMap.setFitView();
			}

		}
	}
</script>

<style lang="scss" scoped>
	.content {

		.AmapBox {
			background: #ffffff;

			.countBox {
				margin: 20upx 0;
				padding: 0upx 20upx;
				padding-bottom: 30upx;
				padding-top: 10upx;

				text {
					font-size: 28upx;
					padding: 8upx 20upx;
					color: #666666;
					background: #F5F5F5;
					border-radius: 20upx;
					margin-right: 15upx;
				}

				.act {
					color: #3994FF;
					background: #F2F8FF;
				}
			}
		}

		.Amap {
			width: 100%;
			height: 30vh;
		}

		.list {
			padding: 15upx 20upx;
			background: #ffffff;
			white-space: pre-wrap;

			.listItem:last-child {
				border-bottom: 1upx solid transparent;
				margin-bottom: 0upx;
				padding-bottom: 0upx;
			}

			.listItem {
				width: 100%;
				display: inline-flex;
				align-items: center;
				border-bottom: 1upx solid #f2f2f2;
				padding-bottom: 10upx;
				margin-bottom: 20upx;
				height: 100upx;

				uni-image {
					width: 80upx;
					height: 80upx;
				}

				.middel {
					// flex: 1;
					margin-left: 10upx;
					
					.title {
						font-size: 30upx;
						color: #3994FF;
						line-height: 50upx;
						width:100%;
						white-space: nowrap;
						overflow: hidden;
						text-overflow: ellipsis;
						>text:nth-child(2) {
							font-size: 24upx;
							margin-left: 10upx;
						}
					}

					.info {
						font-size: 26upx;
						color: #999999;
						width:100%;
						white-space: nowrap;
						overflow: hidden;
						text-overflow: ellipsis;
					}
				}

				.right {
					width:120upx;
					display: inline-flex;
					// text-align: right;
					justify-content: flex-end;

					>view {
						color: #999999;
						text-align: center;

						text {
							display: block;
							font-size: 26upx;
						}
					}
				}
			}

		}
	}
</style>