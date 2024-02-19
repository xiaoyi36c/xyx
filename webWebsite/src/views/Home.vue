<template>
  <div class="Box">
    <bannerSearch />
    <div class="content">
      <!-- banner搜索 -->
      <!-- banner图文 -->
      <bannerTab >
        <template slot="content">
          <template v-if="$route.path == '/index'">
            <bannerTabCont/>
          </template>
          <template v-else>
            <router-view></router-view>
          </template>
        </template>
      </bannerTab>
      <template v-if="$route.path == '/index'">
        <router-view></router-view>
      </template>
    </div>
  </div>
</template>
<script>
import Tops from "@/components/heardTop/Tops";
import bannerSearch from "@/components/bannerBox/bannerSearch";
import bannerTabCont from "@/components/bannerBox/bannerTabCont";
import bannerTab from "@/components/bannerBox/bannerTab";
export default {
  components: {
    Tops,bannerSearch,bannerTab,bannerTabCont
  },
  data() {
    return {
    };
  },
  computed:{

  },
  methods: {

  },
};
</script>
<style lang="scss" scoped>
.content{
  width: 1200px;
  margin: 0 auto;
}
</style>