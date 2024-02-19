import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '@/store'
Vue.use(VueRouter)
let arr = [
  {
    meta: {
      title: '区域地图监控'
    },
    path: '/areaAnlis',
    coms: 'views/areaAnlis/areaAnlis'
  },
]
const generatedRoutes = arr.map(e => {
  return {
    path: e.path,
    title: e.title,
    meta: e.meta,
    component: () => import(`@/${e.coms}.vue`)
  }
})
console.log(generatedRoutes)
const routes = [
  {
    path: '/',
    name: 'Home',
    meta: {
      title: '信易学政府端'
    },
    component: () => import('../views/Home.vue')
  },
  {
    path: '*',
    meta: {
      title: '未找到'
    },
    component: () => import('../views/404.vue')
  },

  {
    path: '/login',
    meta: {
      title: '登录'
    },
    component: () => import('../views/Login.vue')
  },
]
const router = new VueRouter({
  // mode: 'history',
  mode: 'hash',
  base: process.env.BASE_URL,
  routes: [...routes, ...generatedRoutes]
})
router.beforeEach((to, from, next) => {
  let  isLogin = store.state.isLogin
  if (to.meta.title) {
    document.title = to.meta.title
  }
  console.log(store.state.isLogin, 'isLogin====', to.path)
  if (!isLogin && to.path != '/login') {
    next({
      path: '/login'
    })
  } else {
    next()
  }
});
export default router
