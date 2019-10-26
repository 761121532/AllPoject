<template>
   <div>
     <button @click="getURL">111</button>
     <div class="test"></div>
   </div>
</template>

<script>
export default {
  data () {
    return {}
  },
  methods: {
    // async getU () {
    //   const { data: res } = await this.$axios.get('/api/geturl')
    //   console.log(res)
    // }
    getURL () {
      this.$axios.get('/api/geturl').then(res => {
        console.log(res)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.test{
  width: 200px;;
  height: 200px;
  border: 1px solid lightblue;
  margin: 0 auto;
}
</style>
