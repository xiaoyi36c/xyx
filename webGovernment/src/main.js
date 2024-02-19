import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './plugins/bMap.js';
import axios from 'axios';
Vue.config.productionTip = false
Vue.prototype.$store = store
Vue.prototype.$SysName = "管理系统"
Vue.prototype.$axios = axios;
import * as echarts from 'echarts';
Vue.prototype.$echarts = echarts;
window._AMapSecurityConfig = {
  // securityJsCode: '5ed890dab6653a969982321b7b714619',
  securityJsCode: '96717c62a655d402100aec3ab013c72c',
}
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
