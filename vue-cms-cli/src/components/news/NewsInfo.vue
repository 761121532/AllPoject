<template>
  <div id="newsInfo-container">
    <h1 class="title">{{ content.new_title }}</h1>
    <p class="subtitle">
      <span>发表时间:{{ content.new_add_time | dateFormat }}</span>
      <span>点击: {{ content.new_click }}次</span>
    </p>
    <hr />
    <div class="content" v-html="content.content">
    </div>

    <!-- 评论区 -->
    <comment-box :id="this.id"></comment-box>
  </div>
</template>

<script>
import { Toast } from 'mint-ui'
import Comment from '../subcomment/Comment'

export default {
  data () {
    return {
      id: this.$route.params.id, // 获取路由params中的id
      content: []
    }
  },
  created () {
    this.getContent()
  },
  methods: {
    getContent () {
      // 获取新闻正文
      this.$axios
        .get('api/getnew/' + this.id)
        .then(res => {
          if (res.data.status === 0) {
            this.content = res.data.message[0]
            return
          }
          Toast('加载失败')
        })
        .catch(err => {
          console.log(err)
        })
    }
  },
  components: {
    'comment-box': Comment
  }
}
</script>

<style lang="scss" scoped>
#newsInfo-container {
  padding: 0 4px;
  .title {
    font-size: 16px;
    text-align: center;
    margin: 15px 0;
  }
  .subtitle {
    font-size: 12px;
    display: flex;
    justify-content: space-between;
  }
  .content {
   img{
      width: 100%;
    }
  }
}

</style>
