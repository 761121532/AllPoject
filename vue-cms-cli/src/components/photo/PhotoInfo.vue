<template>
   <div class="photo-container">
     <!-- 顶部 -->
     <h1 class="photo-title">{{ imgInfo.title }}</h1>
     <p class="photo-xiaotitle">
       <span>发表时间: {{ imgInfo.create_time | dateFormat }}</span>
       <span>点击: {{ imgInfo.click }}次</span>
     </p>
     <hr>
     <!-- 顶部 -->

     <!-- 图片详情区域 -->
      <!-- <ul class="img-container"> // 下面引入了更好的缩略图插件
        <li :src="item.src" v-for="(item, i) in imgList" :key="i">
          <img class="img-style" v-lazy="item.src">
        </li>
      </ul>
      -->
     <div class="img-container">
       <vue-preview :slides="imgList" class="preview" v-lazy="imgList.src"></vue-preview>
     </div>
     <div class="content-container">
       <p class="content" v-html="imgInfo.content"></p>
     </div>
     <!-- 图片详情区域 -->

     <!-- 评论区域 -->
      <cmt-box :id="this.cateid"></cmt-box>
     <!-- 评论区域 -->
   </div>
</template>

<script>
import Comments from '../subcomment/Comment'
import { Toast } from 'mint-ui'

export default {
  data () {
    return {
      cateid: this.$route.params.cateid,
      imgList: [],
      imgInfo: []
    }
  },
  created () {
    this.getImgInfo()
  },
  mounted () {
    this.getImg()
  },
  methods: {
    getImg () { // 获取图片
      this.$axios.get('/api/getthumimages/' + this.cateid).then(res => {
        // 循环每个图片数据，补全图片的宽和高
        console.log(res.data.message)
        if (res.data.status === 0) {
          res.data.message.forEach(item => {
            item.w = 600
            item.h = 400
            item.src = item.src
            item.msrc = item.src
          })
          this.imgList = res.data.message
        } else {
          Toast('图片加载失败')
        }
      }).catch(err => {
        console.log(err)
      })
    },
    getImgInfo () { // 获取图片信息
      this.$axios.get('/api/getimageInfo/' + this.cateid).then(res => {
        this.imgInfo = res.data.message[0]
      }).catch(err => {
        console.log(err)
      })
    },
    handleClose () {
      console.log('close event')
    }
  },
  components: {
    'cmt-box': Comments
  }
}
</script>

<style lang="scss" scoped>
.photo-container{
  padding: 0 3px;
  .photo-title{
    font-size: 16px;
    text-align: center;
    margin: 15px 0 20px 0;
  }
  .photo-xiaotitle{
    display: flex;
    justify-content: space-between;
  }

  .img-container{
    image[lazy=loading] {
      width: 40px;
      height: 300px;
      margin: auto;
    }
}
  .content-container{
    .content{
      font-size: 12px;
      text-indent: 2rem;
    }
  }
}
</style>
