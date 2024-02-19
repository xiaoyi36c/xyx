<template>
  <div class="app-container home">
    <el-row :gutter="20" class="homeRow">
      <img :src="logoImg" class="sidebar-logo">
      <el-col :sm="24" :lg="24">
        <h1>信易学管理平台</h1>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8" style="display: inline-flex;width: 100%;justify-content: center;">
        <!--        <el-select-->
        <!--              ref="headerSearchSelect"-->
        <!--              v-model="keyValue"-->
        <!--              :remote-method="querySearch"-->
        <!--              filterable-->
        <!--              default-first-option-->
        <!--              remote-->
        <!--              placeholder="请输入页面关键词，可跳转目标页面...." -->
        <!--              class="header-search-select"-->
        <!--              @change="change"-->
        <!--            >-->
        <!--          <el-option v-for="option in options" :key="option.item.path" :value="option.item" :label="option.item.title.join(' > ')" />-->
        <!--        </el-select>-->
        <!-- <el-button style="margin-left:15px;" type="primary" icon="el-icon-search" @click="querySearch">搜索</el-button> -->
      </el-col>
      <el-col :sm="24" :lg="24" />
    </el-row>
  </div>
</template>

<script>
import logoImg from '@/assets/logo/logo2.png'
import Fuse from 'fuse.js/dist/fuse.min.js'
import path from 'path'
import store from '@/store'
export default {
  name: 'Index',
  data() {
    return {
      logoImg,
      keyValue: '',
      options: [],
      searchPool: [],
      show: false,
      fuse: undefined
    }
  },
  computed: {
    routes() {
      return this.$store.getters.permission_routes
    }
  },
  watch: {
    routes() {
      this.searchPool = this.generateRoutes(this.routes)
    },
    searchPool(list) {
      this.initFuse(list)
    },
    show(value) {
      if (value) {
        document.body.addEventListener('click', this.close)
      } else {
        document.body.removeEventListener('click', this.close)
      }
    }
  },
  mounted() {
    this.searchPool = this.generateRoutes(this.routes)
    console.log(store.getters)
  },
  methods: {
    click() {
      this.show = !this.show
      if (this.show) {
        this.$refs.headerSearchSelect && this.$refs.headerSearchSelect.focus()
      }
    },
    close() {
      this.$refs.headerSearchSelect && this.$refs.headerSearchSelect.blur()
      this.options = []
      this.show = false
    },
    change(val) {
      const path = val.path
      if (this.ishttp(val.path)) {
        // http(s):// 路径新窗口打开
        const pindex = path.indexOf('http')
        window.open(path.substr(pindex, path.length), '_blank')
      } else {
        this.$router.push(val.path)
      }
      this.keyValue = ''
      this.options = []
      this.$nextTick(() => {
        this.show = false
      })
    },
    initFuse(list) {
      this.fuse = new Fuse(list, {
        shouldSort: true,
        threshold: 0.4,
        location: 0,
        distance: 100,
        minMatchCharLength: 1,
        keys: [{
          name: 'title',
          weight: 0.7
        }, {
          name: 'path',
          weight: 0.3
        }]
      })
    },
    // Filter out the routes that can be displayed in the sidebar
    // And generate the internationalized title
    generateRoutes(routes, basePath = '/', prefixTitle = []) {
      let res = []

      for (const router of routes) {
        // skip hidden router
        if (router.hidden) { continue }

        const data = {
          path: !this.ishttp(router.path) ? path.resolve(basePath, router.path) : router.path,
          title: [...prefixTitle]
        }

        if (router.meta && router.meta.title) {
          data.title = [...data.title, router.meta.title]

          if (router.redirect !== 'noRedirect') {
            // only push the routes with title
            // special case: need to exclude parent router without redirect
            res.push(data)
          }
        }

        // recursive child routes
        if (router.children) {
          const tempRoutes = this.generateRoutes(router.children, data.path, data.title)
          if (tempRoutes.length >= 1) {
            res = [...res, ...tempRoutes]
          }
        }
      }
      return res
    },
    querySearch(val) {
      if (val !== '') {
        this.options = this.fuse.search(val)
      } else {
        this.options = []
      }
    },
    ishttp(url) {
      return url.indexOf('http://') !== -1 || url.indexOf('https://') !== -1
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  height: calc(100vh - 84px - 200px);
  align-items: center;
  display: inline-flex;
  width: 100%;
  .homeRow{
    margin: 0;
    text-align: center;
    width:100%;
    .header-search-select{
      width: 55%;
      .el-input{
        input{
          height: 50px;
          line-height: 50px;
        }
      }
    }
  }
}
</style>
