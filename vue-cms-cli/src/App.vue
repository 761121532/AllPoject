<template>
  <div id="app">
    <!-- header -->
    <mt-header fixed title="Fate商城"></mt-header>
    <!-- router -->
     <transition>
      <router-view></router-view>
    </transition>
    <!-- bottom -->
    <nav class="mui-bar mui-bar-tab">
      <router-link class="mui-tab-item" to="/home">
        <span class="mui-icon mui-icon-home"></span>
        <span class="mui-tab-label">首页</span>
      </router-link>
      <router-link class="mui-tab-item" to="/member">
        <span class="mui-icon mui-icon-contact">
        </span>
        <span class="mui-tab-label">会员</span>
      </router-link>
      <router-link class="mui-tab-item" to="/shopcar">
        <span class="mui-icon mui-icon-extra mui-icon-extra-cart">
          <span class="mui-badge" id="badge">{{ $store.getters.getAllCount }}</span>
        </span>
        <span class="mui-tab-label">购物车</span>
      </router-link>
      <router-link class="mui-tab-item" to="/search">
        <span class="mui-icon mui-icon-search"></span>
        <span class="mui-tab-label">搜索</span>
      </router-link>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style scope>
body,html {
  padding-top: 20px;
  padding-bottom: 25px;
  overflow-x: hidden;
}
body{
  background-color: white
}
.mint-header{
  background-color: red;
}
.v-enter{
  opacity: 0;
  transform: translateX(100%);
}
.v-leave-to{
  opacity: 0;
  transform: translateX(-100%);
    position: absolute;
    top: 40px;
}
.v-enter-active,
.v-leave-active{
  transition: all 0.5s ease;
}
</style>
