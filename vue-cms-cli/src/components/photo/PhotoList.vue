<template>
   <div class="photoListContainer">
    <mt-navbar v-model="selected">
      <mt-tab-item :id="item.new_id" v-for="item in category" :key="item.new_id">
        <a @click="getImgList(item.new_id)">{{item.new_title }}</a>
      </mt-tab-item>
    </mt-navbar>

    <!-- 图片列表区域 -->
    <ul class="photo-list">
     <router-link class="photo-li" v-for="item in list" :key="item.id" :to="'/home/photoInfo/' + item.id" tag="li">
      <img v-lazy="item.img_url">
      <div class="info">
        <h1 class="info-title">{{ item.title }}</h1>
        <div class="info-zhaoyao">{{ item.zhaiyao }}</div>
      </div>
     </router-link>
    </ul>
   </div>
</template>

<script>
export default {
  data () {
    return {
      selected: 0,
      category: [],
      list: []
    }
  },
  created () {
    this.getImgCategory()
    this.getImgList(0)
  },
  methods: {
    getImgCategory () { // 获取图片分类
      this.$axios.get('/api/getimgcategory').then(res => {
        this.category = res.data.message
        this.category.unshift({new_title: '全部图片', new_id: 0})
        console.log(this.category)
      }).catch(err => {
        console.log(err)
      })
    },
    getImgList (cateid) { // 获取图片列表
      this.$axios.get('/api/getimages/' + cateid).then(res => {
        this.list = res.data.message
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.photoListContainer{
  .mint-navbar{
    font-size: 20px;
  }
  .mint-tab-item{
    .mint-tab-item-label{
      a{
    color: rgb(131, 129, 129);
      }
    }
  }
  .is-selected{
    .mint-tab-item-label{
      a{
      color: #26a2ff;
      }
    }
    margin-bottom: 0;
  }
  .photo-list{
    list-style: none;
    padding: 10px;
    margin: 0;
    padding-bottom: 10px;
    li{
        text-align: center;
        background-color: #ccc;
        margin-bottom: 10px;
        box-shadow: 0 0 9px #999;
        position: relative;
        img{
          width: 100%;
          vertical-align: middle;
        }
        image[lazy=loading] {
          width: 40px;
          height: 300px;
          margin: auto;
        }
        .info{
          position: absolute;
          color: white;
          text-align: left;
          bottom: 0;
          background-color: rgba(0, 0, 0, 0.4);
          max-height: 84px;
          .info-title{
            font-size: 14px;
          }
          .info-zhaoyao{
            font-size: 13px;
          }
        }
    }
  }
}
</style>
