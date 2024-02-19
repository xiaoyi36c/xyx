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
						<!-- <text v-if="v.distance">距我{{v.distance || '-'}}km</text> -->
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
		<u-modal v-model="showInfo" :title="`${showCont.campus_name || '校区'}详情'`" :content='showCont.campus_address'></u-modal>
	</view>
</template>

<script>
	export default {
		components: {

		},
		data() {
			return {
				showInfo:false,//是否显示模态框
				showCont:{},//是否显示模态框
				countIndex: 0,
				list: [],
				areaCount: [],
				AMap: null,
				marker: null,
				regionId: '',
				//
				Bmap:null,
				
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
				this.Bmap = new BMapGL.Map("Amap");          // 创建地图实例 
				var point = new BMapGL.Point(this.currentPostion[0],this.currentPostion[1]);  // 创建点坐标 
				this.Bmap.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别
				this.Bmap.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
				that.initMap().then(()=>{
					that.addMarker()
				});
			},
			async initMap(position) {
				let that =this;
				(new BMapGL.Geolocation()).getCurrentPosition(function(r){
					console.log(r, '====')
				    if(r?.point){
						var marker = new BMapGL.Marker(r.point);
						that.Bmap.addOverlay(marker);
						that.Bmap.panTo(r.point);
						marker.addEventListener("click", function(e){
						   that.showInfoClick(e,"")
						});
						that.$store.commit('saveRegionData',[r.point.lng,r.point.lat])
				    }     
				});
			},
			// 将标注添加到地图中
			addMarker(e) {
				let that = this;
				that.list.map(item=>{				
					var point = new BMapGL.Point(item.longitude, item.latitude);
					var marker = new BMapGL.Marker(point);   // 创建标注   
					marker.addEventListener("click", function(e){   
					   that.showInfoClick(e,item)
					});
					that.Bmap.addOverlay(marker);  
				})
			},
			showInfoClick(e,cont) {
				if(cont=="") return uni.showToast({
					title:'当前位置',
					icon:'none'
				})
				this.showInfo = true
				this.showCont = cont
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