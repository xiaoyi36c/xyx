import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
let  arr = [

]
const generatedRoutes = arr.map(e=>{
    return {
      path: e.path,
      title:e.title,
      meta:e.meta,
      component:  ()=> import(`@/${e.coms}.vue`)
    }
})
console.log(generatedRoutes)
const routes = [
  {
    path: '/',
    name: 'Home',
    meta:{
      title:'信易学官网'
    },
    redirect: '/infoOrgSearch',
  },
  {
    path: '/infoOrgSearch',
    name: 'infoOrgSearch',
    meta:{
      title:'机构查询'
    },
    component: ()=>import('../views/infoOrgSearch.vue'),
  },
  {
    path: '/infoArtSearch',
    name: 'infoArtSearch',
    meta:{
      title:'文章查询'
    },
    component: ()=>import('../views/infoArtSearch.vue'),
  },
  {
    path: '/infoOrgDetail',
    name: 'infoOrgDetail',
    meta:{
      title:'机构详情'
    },
    component: ()=>import('../views/infoOrgDetail.vue'),
  },
  {
    path: '/home',
    name: 'Home',
    meta:{
      title:'信易学官网'
    },
    component: ()=>import('../views/Home.vue'),
    redirect: '/index',
    children:[
      {
        path: '/index',
        name: 'index',
        meta:{
          title:'信易学官网'
        },
        component: ()=>import('../views/HomeModules/index.vue'),
      },
      {
        path: '/infoPubList',
        name: 'infoPubList',
        meta:{
          title:'信易学信息公示'
        },
        component: ()=>import('../views/HomeModules/infoPubList.vue'),
      },
      {
        path: '/infoXinyi',
        name: 'infoXinyi',
        meta:{
          title:'信易学+'
        },
        component: ()=>import('../views/HomeModules/infoXinyi.vue'),
      },
      {
        path: '/infoDetail',
        name: 'infoDetail',
        meta:{
          title:'信易学+'
        },
        component: ()=>import('../views/HomeModules/infoDetail.vue'),
      },
      {
        path: '/InfoCountRules',
        name: 'InfoCountRules',
        meta:{
          title:'评价规则说明'
        },
        component: ()=>import('../views/HomeModules/InfoCountRules.vue'),
      },
      {
        path: '/infoOrgApply',
        name: 'infoOrgApply',
        meta:{
          title:'机构认领'
        },
        component: ()=>import('../views/infoOrgApply.vue'),
      }
    ]
  },
  {
    path:'/404',
    meta:{
      title:'空'
    },
    component:()=>import('@/views/404.vue')
  },
]

const router = new VueRouter({
  // mode: 'history',
  mode:'hash',
  base: process.env.BASE_URL,
  routes:[...routes,...generatedRoutes]})

export default router
