<template>
   <div class="cmt-box">
     <h1 class="cmt-header">发表评论</h1>
     <hr>
      <textarea class="cmt-txt" placeholder="说些什么吧..." maxlength="120" value="默认提示文字" v-model="usersComments"></textarea>
     <mt-button type="primary" size="large" @click="postComment">发表评论</mt-button>
     <!-- 评论数据 -->
     <div class="cmt-list" v-for="(item, i) in comments" :key="i">
       <div class="cmt-item">
         <div class="cmt-title">
           第{{ i+1 }}楼：&nbsp;&nbsp;{{ item.user_name }}&nbsp;&nbsp; 发布日期：{{ item.add_time | dateFormat }}
         </div>
          <div class="cmt-body">
             {{ ( item.content == 'undefined' || item.content == '') ? item.content ='此用户很懒，什么都没说' : item.content }}
           </div>
       </div>
     </div>
     <!-- 评论数据 -->
     <mt-button type="danger" size="large" plain @click="getMore" v-if="flag">加载更多</mt-button>
     <p class="txt-bottom" v-else>没有更多了哦！</p>
   </div>
</template>

<script>
import { Toast } from 'mint-ui'
export default {
  data () {
    return {
      pagesindex: 1,
      comments: [],
      flag: true,
      usersComments: ''
    }
  },
  created () {
    this.getComments()
  },
  methods: {
    getComments () { // 获取评论数据
      this.$axios.get('api/getcomments/' + this.id + '?pageindex=' + this.pagesindex).then(res => {
        if (res.data.status === 0) {
          this.comments = this.comments.concat(res.data.message)
          if (res.data.message.length === 0) {
            this.flag = !this.flag
            Toast('没有更多了')
          }
        } else {
          Toast('评论加载失败')
        }
      }).catch(err => {
        console.log(err)
      })
    },
    getMore () { // 点击加载更多数据
      this.pagesindex++
      this.getComments()
      // this.$axios.get('http://www.liulongbin.top:3005/api/getcomments/' + this.id + '?pageindex=' + this.pagesindex).then(res => {
      //   if (res.data.status === 0) {
      //     this.comments = this.comments.concat(res.data.message)
      //     console.log(this.comments)
      //   }
      // }).catch(err => {
      //   console.log(err)
      // })
    },
    postComment () { // 用户发表评论
      if (this.usersComments.trim().length === 0) { // 先判断用户发送内容是否为空
        Toast('评论内容不能为空！')
      } else {
        this.$axios.post(`/api/postcomment/${this.id}`, {content: this.usersComments}).then(res => {
          Toast('评论成功~')
          let cmt = {user_name: '匿名用户', add_time: Date.now(), content: this.usersComments}
          this.comments.unshift(cmt)
          this.usersComments = ''
        }).catch(err => {
          console.log(err)
        })
      }
    }
  },
  props: ['id'] // 父组件传过来的id
}
</script>

<style lang="scss" scoped>
.cmt-box{
  .cmt-header{
    font-size: 18px;
  }
  .cmt-txt{
    margin: 0 0;
    height: 85px;
  }
  // 评论区域样式
  .cmt-list{
    .cmt-item{
      .cmt-title{
        line-height: 35px;
        font-size: 15px;
        background-color: rgb(222, 255, 222);
        color: lightslategray;
      }
      .cmt-body{
        line-height: 35px;
        font-size: 15px;
        background-color: rgb(243, 243, 243);
        text-indent: 2em;
      }
    }
  }
  .mint-button--primary{
    margin-bottom: 5px;
  }
  .mint-button--danger{
    margin-top: 5px;
  }
  .txt-bottom{
    text-align: center;
    color: #ccc;
    margin-bottom: 2px;
    font-size: 16px;
  }
}
</style>
