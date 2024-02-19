import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
// 创建一个新的 store 实例
const store = new Vuex.Store({
  state: {
    regionData: [104.053023,30.588744],
    complainBoo: false,
  },
  mutations: {
    saveRegionData (state,data) {
		console.log('====data',data)
      state.regionData = data
    },
    saveComplainBoo (state,data) {
      state.complainBoo = data
    }
  }
})
export default store