<template>
   <div>
    <ul class="mui-table-view">
      <li class="mui-table-view-cell mui-media" v-for="item in newsList" :key="item.new_id">
        <router-link :to="'/home/newsInfo/' + item.new_id">
          <img class="mui-media-object mui-pull-left" :src="item.new_img_url">
          <div class="mui-media-body">
            <h3>{{ item.new_title }}</h3>
            <p class='mui-ellipsis'>
              <!-- <span>发表时间:{{ item.add_time | dateFormat('YYYY-MM-DD') }}</span> -->
              <span>发表时间:{{ item.new_add_time | dateFormat }}</span>
              <span>点击:{{ item.new_click }}次</span>
            </p>
          </div>
        </router-link>
      </li>
    </ul>
   </div>
</template>

<script>
import { Toast } from 'mint-ui'

export default {
  data () {
    return {
      newsList: [],
      contentList: []
    }
  },
  created () {
    this.getList()
  },
  // 日期还是定义全局过滤器 main.js
  // filters: {
  //   dateFormat: function (dateStr) {
  //     var dt = new Date(dateStr)
  //     var y = dt.getFullYear()
  //     var m = (dt.getMonth() + 1).toString().padStart(2, '0')
  //     var d = (dt.getDate()).toString().padStart(2, '0')
  //     var h = (dt.getHours()).toString().padStart(2, '0')
  //     var min = (dt.getMinutes()).toString().padStart(2, '0')
  //     var s = (dt.getSeconds()).toString().padStart(2, '0')
  //     // 用toString().padStart(2, '0')代替以下代码
  //     // h = h < 10 ? '0' + h : h;
  //     // min = min < 10 ? '0' + min : min;
  //     // s = s < 10 ? '0' + s : s;
  //     // return y+'-'+m+'-'+d+' '+ h +':' + min +':'+ s;
  //     return `${y}-${m}-${d} ${h}:${min}:${s}`
  //   }
  // },
  methods: {
    getList () { // 获取图文
      this.$axios.get('api/getnewslist').then(res => {
        if (res.data.status === 0) {
          this.newsList = res.data.message
          return
        }
        Toast('新闻资讯加载失败')
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.mui-table-view{
  li {
    h3 {
      font-size: 14px;
    }
    .mui-ellipsis {
      font-size: 12px;
      color: rgb(54, 113, 250);
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>
