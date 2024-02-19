import Vue from 'vue'
import App from './App'

import * as Api from './config/api.js'
import * as Common from './config/common.js'
import * as Db from './config/db.js'
import store from './store/index.js'
import dayjs from './utils/dayjs1.8.21.min.js'
Vue.config.productionTip = false

Vue.prototype.$dayjs = dayjs;
Vue.prototype.$api = Api;
Vue.prototype.$common = Common;
Vue.prototype.$db = Db;
App.mpType = 'app'
import {Interface} from './config/Interface.js'
Vue.prototype.Interface = Interface;
// 引入全局uView
import uView from 'uview-ui';
Vue.use(uView);

// import './utils/openMap.js';
// (function() {
// 	window.BMAP_PROTOCOL = "https";
// 	window.BMapGL_loadScriptTime = (new Date).getTime();
// 	document.write(
// 		'<script type="text/javascript" src="https://api.map.baidu.com/getscript?type=webgl&v=1.0&ak=hvv31Hzt069G4NGGeDbpobdTOoDQhI3r&services=&t=20230529114224"></script>'
// 		);
// 	document.write(
// 		'<link rel="stylesheet" type="text/css" href="https://api.map.baidu.com/res/webgl/10/bmap.css"/>');
// 	console.log('======',window)
// })();

//自定义组件
import empty from './components/empty.vue'
import regionPicker from './components/regionPicker.vue'
Vue.component('empty',empty)
Vue.component('regionPicker',regionPicker)
const app = new Vue({
    ...App,
	store
})
app.$mount()
