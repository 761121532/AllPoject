<template>
   <div class="goods-list">
    <router-link class="goods-container" v-for="item in goodsList" :key="item.id" tag="div" :to="'/home/goodsinfo/' +item.id ">
          <img class="goods-img" :src="item.img_url" alt="">
          <h3 class="goods-titile">{{ item.title }}</h3>
          <div class="content">
          <p class="info">
            <span class="nowprice">￥{{ item.sell_price}}</span>&nbsp;&nbsp;
            <span class="oldprice">￥{{ item.market_price }}</span>
          </p>
          <p class="kucun">
            <span>热卖中</span>
            <span>库存：{{ item.stock_quantity}}</span>
          </p>
          </div>
      </router-link>
    <mt-button type="danger" size="large" plain @click="getMoreGoods" v-if="flag">加载更多</mt-button>
  </div>
</template>

<script>
import { Toast } from 'mint-ui'
export default {
  data () {
    return {
      pageindex: 1,
      goodsList: [],
      flag: true
    }
  },
  created () {
    this.getGoodsList()
  },
  methods: {
    getGoodsList () { // 获取商品列表
      this.$axios.get('/api/getgoods?' + 'pageindex=' + this.pageindex).then(res => {
        if (res.data.status === 0) {
          console.log(res.data.message)
          this.goodsList = this.goodsList.concat(res.data.message)
          if (res.data.message.length === 0) {
            this.flag = !this.flag
            Toast('没有更多了~')
          }
        } else {
          Toast('商品加载失败')
        }
      }).catch(err => {
        console.log(err)
      })
    },
    getMoreGoods () { // 加载更多商品
      this.pageindex++
      this.getGoodsList()
    }
  }
}
</script>

<style lang="scss" scoped>
.goods-list{
  display: flex;
  flex-wrap: wrap;
  padding: 5px;
  justify-content: space-between;
  .goods-container{
    width: 49%;
    border: 1px solid #ccc;
    box-shadow: 0 0 6px #ccc;
    margin: 4px 0;
    padding: 2px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    min-height: 222px;
    .goods-img{
      width: 100%
    }
    .goods-titile{
      font-size: 14px;
    }
    .content{
        background-color: rgb(235, 235, 235);
      .info{
        margin: 0;
        padding: 2px;
        padding-bottom: 5px;
        .nowprice{
          color: red;
          font-size: 14px;
        }
        .oldprice{
          color: #999;
          font-size: 12px;
          text-decoration: line-through;
        }
      }
      .kucun{
        margin: 0;
        padding: 0 2px;
        font-size: 12px;
        display: flex;
        justify-content: space-between;
      }
    }
  }
}
</style>
