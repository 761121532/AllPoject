<template>
   <div class="goods-container">
     <transition @before-enter="beforeEnter" @enter="enter" @after-enter="afterEnter" @after-leave="afterLeave">
      <div class="ball" v-show="ballFlag" ref="ball"></div>
     </transition>
     <!-- 商品轮播区域 -->
    <div class="mui-card">
    <div class="mui-card-content">
      <div class="mui-card-content-inner">
        <swipe :lunbotuList="goodsImgList" :isfull="false"></swipe>
      </div>
    </div>
    </div>

    <!-- 商品购买区域 -->
    <div class="mui-card">
      <div class="mui-card-header">{{ goodsInfo.title }}</div>
      <div class="mui-card-content">
        <div class="mui-card-content-inner">
          <p class="price">
            <span>市场价：</span><del>￥{{ goodsInfo.market_price }}</del>&nbsp;&nbsp;&nbsp;&nbsp;<span>销售价：</span><span class="newprice">￥{{goodsInfo.sell_price}}</span>
          </p>
          <p class="buycount">
           <span>购买数量：</span><NumBox @getCount="getSelectValue" :max="goodsInfo.stock_quantity"  />
          </p>
          <p>
            <mt-button type="primary" size="small">立即购买</mt-button>
            <mt-button type="danger" size="small"  @click="clickFlag && addShopCar()">加入购物车</mt-button>
          </p>
        </div>
      </div>
    </div>

    <!-- 商品详情区域 -->
    <div class="mui-card">
      <div class="mui-card-header">商品参数</div>
      <div class="mui-card-content">
        <div class="mui-card-content-inner">
          <p>商品货号：{{ goodsInfo.goods_no }}</p>
          <p>库存：{{ goodsInfo.stock_quantity }}</p>
          <p>上架时间：{{ goodsInfo.add_time | dateFormat }}</p>
        </div>
        <div class="mui-card-footer">
          <van-button type="primary"  size="large" @click="goodsDesc">图文详情</van-button>
          <van-button type="danger"  size="large" @click="goodsComment">商品评论</van-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import swipe from '../subcomment/Swipe'
import NumBox from '../subcomment/NumBox'

export default {
  data () {
    return {
      id: this.$route.params.goodsid,
      goodsImgList: [],
      goodsInfo: [],
      ballFlag: false,
      clickFlag: true, // 防止多次点击加入购物车产生的BUG
      selectValue: 1
    }
  },
  created () {
    this.getGoodsImg()
    this.getGoodsInfo()
  },
  methods: {
    getGoodsImg () { // 轮播图区域图片的获取
      this.$axios.get('/api/getthumimages/' + this.id).then(res => {
        if (res.data.status === 0) {
          res.data.message.forEach(item => {
            item.img = item.src
          })
          this.goodsImgList = res.data.message
        }
      }).catch(err => {
        console.log(err)
      })
    },
    getGoodsInfo () { // 获取商品详情（标题、价格）
      this.$axios.get('/api/goods/getinfo/' + this.id).then(res => {
        this.goodsInfo = res.data.message[0]
      }).catch(err => {
        console.log(err)
      })
    },
    goodsDesc (id) { // 商品图文详情
      this.$router.push({ name: 'goodsDesc', params: { id } })
    },
    goodsComment (id) { // 商品评论
      this.$router.push({ name: 'goodsComment', params: { id } })
    },
    addShopCar () {
      this.ballFlag = !this.ballFlag
      // {id: 商品的id, count: 要购买的数量, price: 商品的单价, selected: false}
      var goodsinfo = {id: this.id, count: this.selectValue, price: this.goodsInfo.sell_price, selected: true}
      this.$store.commit('addToCar', goodsinfo)
      // console.log(this.selectValue)
    },
    beforeEnter (el) {
      el.style.transform = 'translate(0, 0)'
      console.log('动画开始---')
    },
    enter (el, done) {
      this.clickFlag = false
      let rf = el.offsetWidth
      console.log(rf)
      // 小球优化思路：
      // 1.先得到徽标的横纵坐标，再得到小球的 横纵坐标，然后，让 y值求差， x值也求差，得到的结果，就是横纵坐标要位移的距离
      // 2.徽标和小球横纵坐标的获取 domObject.getBoundingClientRect()

      // 获取小球在页面中的位置
      const ballPosition = this.$refs.ball.getBoundingClientRect()
      // 获取徽标位置
      const badgeposition = document.getElementById('badge').getBoundingClientRect()

      const xDis = badgeposition.left - ballPosition.left
      const yDis = badgeposition.top - ballPosition.top

      this.$nextTick(() => {
        el.style.transform = `translate(${xDis}px, ${yDis}px)`
        el.style.transition = 'all 0.8s cubic-bezier(.4,-0.29,1,.68)'
      })
      el.addEventListener('transitionend', done)
      console.log('动画执行中---')
      // done()
    },
    afterEnter (el) {
      this.ballFlag = !this.ballFlag
      console.log('动画执行完---')
    },
    afterLeave () {
      this.clickFlag = true
    },
    getSelectValue (value) {
      // 当子组件把 选中的数量传递给父组件的时候，把选中的值保存到data上
      this.selectValue = value
      console.log('父组件拿到的值' + this.selectValue)
    }
  },
  components: {
    swipe,
    NumBox
  }
}
</script>

<style lang="scss" scoped>
.goods-container{
  .mui-card{
    .mui-card-content{
      .mui-card-content-inner{
        .newprice{
          color: red;
          font-size: 18px;
          font-weight: bold;
        }
        .buycount{
          display: flex;
          align-items:center;
          >>> .van-stepper{
            display: inline-block;
            .van-stepper__input{
            padding: 0;
            margin-bottom: 0;
            width: 60px;
            height: 26px;
            }
          }
        }
      }
      .mui-card-footer{
        display: block;
        button{
          margin: 10px 0;
          border-radius: 6px;
        }
      }
    }
  }
  .ball{
    width: 18px;
    height:18px;
    background-color: red;
    border-radius: 50%;
    position:absolute;
    z-index: 99;
    left: 148px;
    top: 412px;
  }
}
</style>
