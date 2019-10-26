<template>
<!-- 问题我们不知道什么时候能拿到max值 -->
<!-- 我们可以用watch属性监听，父组件传递过来的max值，不管watch会被触发几次，但是，最后一次，肯定是合法的max数值 -->
       <van-stepper v-model="value" :max="max" integer  @change="conuntChanged" />
       <!-- 分析：子组件什么时候把数据传递给父组件 -->
</template>

<script>

export default {
  data () {
    return {
      value: 1
    }
  },
  mounted () {
    // console.log(this.max)
  },
  methods: {
    conuntChanged () {
      // console.log(this.value)
      this.$emit('getCount', parseInt(this.value))
    }
  },
  props: ['max']
}
</script>

<style lang="scss" scoped>

</style>
