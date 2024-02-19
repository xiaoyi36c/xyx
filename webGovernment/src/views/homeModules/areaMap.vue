<template>
    <div class="box">
        <div class="title">机构分布</div>
        <div :style="{ height: '328px', width: '100%' }" id="Amap"></div>
    </div>
</template>
<script>
const cityJson = require('/public/city.json')
import AMapLoader from '@amap/amap-jsapi-loader'
import { getAllOrgMap} from "@/requestPub/api";
export default {
    components: {
        // echarts
    },
    data() {
        return {
            Bmap:null,
            baseOptions: {},
            AMap:null,
            marker:null,
            AddrList:[]
            // AddrList:[{lat: 28.229237,lng:112.94025,orgName:'机构4'},{orgName:'机构5',lng:112.14025,lat:28.229237}]
        }
    },
    computed: {

    },
    mounted() {
    //    this.AmapLoad()
    },
    methods: {
       async AmapLoad(cityName='成都',areaId,type){

        //   await AMapLoader.load({
        //         key: 'd5b2320157e3e4a58a964ceaa1793d33',
        //         version: '2.0',
        //         "plugins": ['AMap.PlaceSearch', 'AMap.Geolocation', 'AMap.Geocoder'],
        //         AMapUI: {
        //             version: "1.1", // AMapUI 版本
        //         },
        //     }).then((map) => {
        //         this.$nextTick(() => {
        //             getAllOrgMap({
        //             type:type,
        //             areaId:areaId
        //         }).then((e)=>{
        //             this.AddrList= e.data;
        //         }).then(()=>{
        //             this.initData(cityName)
        //         })
        //         })
        //     })
        this.$nextTick(() => {
                    getAllOrgMap({
                    type:type,
                    areaId:areaId
                }).then((e)=>{
                    this.AddrList= e.data;
                }).then(()=>{
                    this.initData(cityName)
                })
            })
        },
        initData(cityName) {
            this.Bmap = new BMapGL.Map("Amap");          // 创建地图实例 
				var point = new BMapGL.Point(cityJson[cityName][0],cityJson[cityName][1]);  // 创建点坐标 
				this.Bmap.centerAndZoom(point, 11);                 // 初始化地图，设置中心点坐标和地图级别
				this.Bmap.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
            // this.marker, this.AMap =  new AMap.Map("Amap", {
            // //设置地图容器id
            //     viewMode: "3D", //是否为3D地图模式
            //     zoom: 10, //初始化地图级别
            //     resizeEnable: true,
            //     center: cityJson[cityName], //初始化地图中心点位置
            // });
            //添加机构点位
            let i = 0;
            this.addMarker().then(()=>{
                if(this.AddrList.length){
                    setInterval(()=>{
                        this.markerClick(this.AddrList[i])
                        i++;
                    },3000)
                }
            })
            // this.AMap.on('click', this.showInfoClick);
        },
      async  addMarker(){
            // let makerList=[];
            // this.AddrList.map((item,i)=>{    
            // let markerContent = `<div class="custom-content-marker status_${item.business_status}">
            //     <div class="custom-content-title">
            //         <span>${item.organization_name}</span>
            //         <span></span>
            //     </div>
            //     <span class="custom-content-tag"><span>
            //     </div>`
            //     // 遍历生成多个标记点
            //     let marker = new AMap.Marker({
            //         map: this.AMap,
            //         zIndex: 9999999,
            //         content: markerContent, // 将 html 传给 content
            //         offset: new AMap.Pixel(-60, -60),
            //         position:[item.longitude,item.latitude],
                    
            //     });
            //     marker.on('click', this.markerClick);
            //     makerList.push(marker)
            // });
            // this.AMap.add(makerList)
            let that = this;
			 await	that.AddrList.map(item=>{				
					var point = new BMapGL.Point(item.longitude, item.latitude);
					var marker = new BMapGL.Marker(point);   // 创建标注   
					marker.addEventListener("click", function(e){   
					   that.markerClick(item)
					});
					that.Bmap.addOverlay(marker);  
				})
        },
        showInfoClick(e){
            console.log(e,'====')
            // 自动适应显示想显示的范围区域
            // this.marker.setMap(this.AMap);
            // this.AMap.setFitView();
        },
        markerClick(item){
                var opts = {
                    width: 100,     // 信息窗口宽度
                    height: 'auto',    // 信息窗口高度
                    title: item.organization_name,  // 信息窗口标题
                }
                let cont = `<div>
                    信用分：${item.credit_score}<span style="background:green;color:#fff;padding:2px 5px;">${item.business_status} </span>   
                    </div>`
                var infoWindow = new BMapGL.InfoWindow(cont, opts);  // 创建信息窗口对象
                this.Bmap.openInfoWindow(infoWindow, {lat:item.latitude,lng:item.longitude});        // 打开信息窗口
        },
        initCharts(name, JsonName) {

        },
    }
}
</script>
<style lang="scss">
        .status_1{
            background: green;
        }
        .status_2{
            background: olive;
        }
        .status_3{
            background: orange;
        }
        .status_4{
            background: orangered;
        }
.custom-content-marker{
    background: #fff;
    padding: 5px 0;
    width: 120px;
    position: relative;
    border-radius: 8px;
    box-shadow: 0 0 5px 5px #ccc;
    .custom-content-title{
        font-size:13px;
        transform: scale(.8);
        span:last-child{
           font-size: 10px;
           color:#fff;
           padding: 2px 6px;
        }

    }
    .custom-content-content{
        transform: scale(.8);
        font-size:13px;
    }
    .custom-content-tag{
        position: absolute;
        width: 0px;
        height: 0px;
        border: 10px solid transparent;
        margin-left: 46px;
        border-top-color: #fff;
        margin-top: 5px;
        z-index: 12;
    }
}
</style>
<style lang="scss" scoped>

.box {
    border: 1px solid transparent;
    // padding-top: 5px;
    margin: 5px;
    padding-top: 10px;
    .title {
        font-size: 15px;
        font-weight: 500;
        padding-left: 10px;
        margin-bottom: 10px;
        color: #FFFFFF;
        &::before{
            display: inline-block;
            content:'';
            width: 8px;
            height: 8px;
            transform: rotate(45deg);
            margin-right: 10px;
            border: 2px solid #8BD2E6;
        }
    }
}
</style>