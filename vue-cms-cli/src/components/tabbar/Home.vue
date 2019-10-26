<template>
  <div class="home">
    <swipe :lunbotuList="lunbotuList" :isfull="true"></swipe>
    <ul class="mui-table-view mui-grid-view mui-grid-9">
          <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4"><router-link to="/home/newsList">
                  <img src="../../images/menu1.png" alt="">
                  <div class="mui-media-body">新闻资讯</div></router-link></li>
          <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4"><router-link to="/home/photoList">
                  <img src="../../images/menu2.png" alt="">
                  <div class="mui-media-body">图片分享</div></router-link></li>
          <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4"><router-link to="/home/goodsList">
                  <img src="../../images/menu3.png" alt="">
                  <div class="mui-media-body">商品购买</div></router-link></li>
          <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4"><router-link to="/home/liuyan">
                  <img src="../../images/menu4.png" alt="">
                  <div class="mui-media-body">留言反馈</div></router-link></li>
          <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4"><a href="#">
                  <img src="../../images/menu5.png" alt="">
                  <div class="mui-media-body">视频专区</div></a></li>
          <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-4"><a href="#">
                  <img src="../../images/menu6.png" alt="">
                  <div class="mui-media-body">联系我们</div></a></li>
      </ul>
  </div>
</template>

<script>
import { Toast } from 'mint-ui'
import swipe from '../subcomment/Swipe.vue'

export default {
  data () {
    return {
      lunbotuList: []
    }
  },
  created () {
    this.getLunbotu()
  },
  methods: {
    getLunbotu () {
      this.$axios
        .get('api/getlunbo')
        .then(res => {
          if (res.data.status === 0) {
            res.data.message.forEach(item => {
              item.img = item.homepage_image
              item.id = item.homepage_id
            })
            this.lunbotuList = res.data.message
          } else {
            Toast('首页加载出了点问题')
          }
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  components: {
    swipe
  }
}
</script>

<style scoped>
.mui-grid-view.mui-grid-9{
  background-color: white;
  border: none;
}
.mui-grid-view.mui-grid-9 img{
  height: 10vw;
  width: 10vw;
}
</style>
