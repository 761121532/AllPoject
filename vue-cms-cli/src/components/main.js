
// 导入MUICSS
import './lib/mui/css/mui.min.css'
import './lib/mui/css/icons-extra.css'
import './lib/mui/fonts/mui-icons-extra.ttf'
import 'mint-ui/lib/style.css'
import './components/liuyan/test'

import Vue from 'vue'
import App from './App'
import router from './router'
// 引入axios
import axios from 'axios'

// 引入moment做时间格式规范
import moment from 'moment'

// 引入Vue提供的缩略图插件
import VuePreview from 'vue-preview'

// 引入vuex状态管理
import Vuex from 'vuex'
// vantUI
import { Stepper, Button, Card, SubmitBar } from 'vant'
// 按需导入mint-ui
// import { Header, Swipe, SwipeItem, Button, Field, Navbar, TabItem, Lazyload } from 'mint-ui'
// Vue.component(Header.name, Header)
// Vue.component(Swipe.name, Swipe)
// Vue.component(SwipeItem.name, SwipeItem)
// Vue.component(Button.name, Button)
// Vue.component(Field.name, Field)
// Vue.component(Navbar.name, Navbar)
// Vue.component(TabItem.name, TabItem)
// Vue.use(Lazyload)
import MintUI from 'mint-ui'
Vue.prototype.$axios = axios
Vue.use(Stepper)
Vue.use(Button)
Vue.use(MintUI)
Vue.use(Card)
Vue.use(Vuex)
Vue.use(SubmitBar)
Vue.use(VuePreview)
Vue.use(VuePreview, {
  mainClass: 'pswp--minimal--dark',
  barsSize: {top: 0, bottom: 0},
  captionEl: false,
  fullscreenEl: false,
  shareEl: false,
  bgOpacity: 0.85,
  tapToClose: true,
  tapToToggleControls: false
})

Vue.config.productionTip = false
// 全局axios默认访问路径
axios.defaults.baseURL = 'http://www.liulongbin.top:3005/'
// 全局设置axios的请求头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'

// 定义全局过滤器
Vue.filter('dateFormat', (dataStr, pattern = 'YYYY-MM-DD HH:mm:ss') => {
  return moment(dataStr).format(pattern)
})

// 读取本地储存
var car = JSON.parse(localStorage.getItem('car') || '[]')

// 定义Vuex全局状态管理
var store = new Vuex.Store({
  state: {
    car: car // {id: 商品的id, count: 要购买的数量, price: 商品的单价, selected: false}
  },
  mutations: {
    addToCar (state, goodsinfo) { // 点击加入购物车，把信息保存到store中的car上
      console.log(state.car)
      // 1.如果购物车之前以及有这个对应的商品了，那么只需要更新数量
      // 2.如果没有，则直接把商品数据，push到car中即可
      var flag = false // 用于查看购物车里有没有对应的商品
      state.car.some(item => {
        if (item.id === goodsinfo.id) {
          item.count += parseInt(goodsinfo.count)
          flag = true
          console.log(goodsinfo.count)
          return true
        }
      })
      // some()循环完毕，得到的flag最终还是false，则把商品数据直接push到购物车中
      if (!flag) {
        state.car.push(goodsinfo)
      }
      console.log(goodsinfo)
      // 将全局数据car存放当本地存储中
      localStorage.setItem('car', JSON.stringify(state.car))
    }
  },
  getters: {
    getAllCount (state) {
      var c = 0
      state.car.forEach(item => {
        c += item.count
      })
      // for (let i; i < state.car.length; i++) {
      //   c += state.car[i]
      //   console.log(state.car.count)
      // }
      return c
    },
    getGoodsCount (state) { // 获取购物车每件商品的数量
      var cou = {}
      state.car.forEach(item => {
        cou[item.id] = item.count
      })
      return cou
    },
    getAllPrice (state) { // 获取购物车总价

    }
  }
})

// eslint-disable-next-line no-new
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  store
})
