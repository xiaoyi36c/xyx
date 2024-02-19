import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import axios from 'axios';
import dayjs  from 'dayjs'
import Element from 'element-ui'

Vue.use(Element)

Vue.config.productionTip = false
Vue.prototype.$dayjs = dayjs
Vue.prototype.$store = store
Vue.prototype.$axios = axios;


//登录控制  状态管理若存在已登录信息，则首页  否之，登录页
router.beforeEach((to ,from ,next)=>{
  if (to.meta.title) {
    // document.title = to.meta.title
    document.title = '信易学'
  }
  if(store.state.LoginInfo.isLogin==0 && to.path !='/login'){
    next({
      path:'/login'
    })
  }else{
    next()
  }
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
