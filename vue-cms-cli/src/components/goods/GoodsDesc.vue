<template>
   <div class="container">
     <h1 class="goods-title">{{ goodsDesc.title }}</h1>
     <hr>
     <p class="goods-content" v-html="goodsDesc.content"></p>
   </div>
</template>

<script>
export default {
  data () {
    return {
      goodsDesc: []
    }
  },
  created () {
    this.getGoodsDesc()
  },
  methods: {
    getGoodsDesc () {
      this.$axios.get('/api/goods/getdesc/' + this.$route.params.goodsid).then(res => {
        this.goodsDesc = res.data.message[0]
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.container{
  padding: 2px;
  .goods-title{
    font-size: 16px;
    text-align: center;
    color: rgb(39, 135, 167);
  }
  .goods-content{
    margin: 2px;
    >>> img{
      width: 100%;
      height: 100%;
    }
  }
}
</style>
