<template>
   <div>
     <goodscomment :id="id"></goodscomment>
   </div>
</template>

<script>
import goodscomment from '../subcomment/Comment'
export default {
  data () {
    return {
      id: this.$route.params.goodsid
    }
  },
  components: {
    goodscomment
  }
}
</script>

<style lang="scss" scoped>

</style>
