import Vue from 'vue'
import { location } from '@/utils/location'
export const publicMixin = {
	data() {
		return {
			// 文字提示
			errorTypeTxt: ['message'],
			// 下划线提示
			errorTypeBottom: ['border-bottom'],
			// 不提示
			errorTypeNone: ['none'],
			// 文字和下划线提示
			errorTypeBoth: ['message', 'border-bottom'],
			//导航标题、背景色
			NavbarTitleColor: '#333',
			background: {
				// 'background-image': 'linear-gradient(45deg, #c82606, #e79f0d)',
				'background-color': '#fff'
			},
			PubAMap:null,
			//触底加载用
			timer:null,
			status: 'loadmore',
			pageNation:{
				pageNum:1,
				pageSize:10,
			},
			//页面滚动距离
			scrollTop:0,
		}
	},
	computed: {
		showTop(){
			return this.scrollTop>600 && this.list.length>10
		}
	},
	mounted() {
		// this.getLocation()
	},
	methods: {
		backTop(){
			uni.pageScrollTo({
				scrollTop:0, //距离页面顶部的距离		
				duration: 150
			});
		},
		ObjectToStr(obj){
			let str =''
			if(JSON.stringify(obj)!='{}' && obj){
				Object.keys(obj).map(key=>{
					str+=`${key}=${obj[key]}&`
				})
			}
			return str ? str.substr(0,str.length-1):''
		},
		toKefuFun(){
			
		},
		copyText(val){     
			let that = this;
			if(!val)return;
		    uni.setClipboardData({
		        data: val ,
		        success: function (res) {
					console.log('复制的信息：',val );
					that.$common.successToShow('复制成功');
		        }
		    });        
		},
		toUrl(url) {
			this.$common.navigateTo(url);
		},
		back() { // 首页
			uni.navigateBack({
				delta: 2
			})
		},
		backPrev() { // 上一页
			uni.navigateBack({
				delta: 1
			})
		},

		
	},
}
