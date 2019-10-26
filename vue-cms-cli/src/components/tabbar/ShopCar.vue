<template>
  <div class="shopcar-container">
    <div v-for="item in goodsList" :key="item.id">
    <van-card
      :num="$store.getters.getGoodsCount[item.id]"
      :price="item.sell_price"
      :title="item.title"
      :thumb="item.thumb_path"
    >
      <div slot="footer">
        <van-button size="mini" @click="delGoods">删除</van-button>
      </div>
      <numbox></numbox>
    </van-card>
    </div>
   <van-submit-bar
      :price="3050"
      button-text="提交订单"
      @submit="onSubmit"
    />
  </div>
</template>

<script>
import numbox from '../subcomment/NumBox'
export default {
  data () {
    return {
      goodsList: []
    }
  },
  created () {
    this.getShopCarInfo()
  },
  methods: {
    getShopCarInfo () {
      var idArr = [] // 用来保存vuex中car.id
      this.$store.state.car.forEach(item => idArr.push(item.id))
      // 如果购物车没有商品直接return出去
      if (idArr.length <= 0) {
        return
      }
      // console.log(idArr)
      this.$axios.get('/api/goods/getshopcarlist/' + idArr.join(',')).then(res => {
        this.goodsList = res.data.message
        // this.goodsList = JSON.parse(JSON.stringify(this.goodsList))
        console.log(this.goodsList)
      }).catch(err => {
        console.log(err)
      })
    },
    delGoods () { // 删除商品
      console.log(this.$store.getters.getGoodsCount)
    },
    onSubmit () { // 提交订单

    }
  },
  components: {
    numbox
  }
}
</script>

<style  scoped>
.van-card{
  border-bottom: 1px solid #ccc;
  position: relative;
}

/* 结算区域 */
.van-submit-bar{
position: absolute;
bottom: 8.1%;
box-shadow: 0 5px 3px #ccc;
}
/* 结算区域 */

.van-button--default{
  color: red;
  border-color: red;
}
.van-card__footer{
  display: inline-block;
  position: absolute;
  right: 5%;
  bottom: 7%;
}
</style>
