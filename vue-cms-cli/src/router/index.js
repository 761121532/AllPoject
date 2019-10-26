import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/tabbar/Home'
import NewsList from '@/components/news/NewsList'
import NewsInfo from '@/components/news/NewsInfo'
import PhotoList from '@/components/photo/PhotoList'
import PhotoInfo from '@/components/photo/PhotoInfo'
import GoodsList from '@/components/goods/GoodsList'
import GoodsInfo from '@/components/goods/GoodsInfo'
import Liuyan from '@/components/liuyan/Liuyan'
import GoodsDesc from '@/components/goods/GoodsDesc'
import GoodsComment from '@/components/goods/GoodsComment'

Vue.use(Router)

export default new Router({
  routes: [
    {path: '/', redirect: '/home'},
    {path: '/home', component: Home},
    {path: '/member', component: _ => import('@/components/tabbar/Member')},
    {path: '/shopcar', component: () => import('@/components/tabbar/ShopCar')},
    {path: '/search', component: () => import('@/components/tabbar/Search')},
    {path: '/home/newsList', component: NewsList},
    {path: '/home/newsInfo/:id', component: NewsInfo},
    {path: '/home/photoList', component: PhotoList},
    {path: '/home/photoInfo/:cateid', component: PhotoInfo},
    {path: '/home/goodsList', component: GoodsList},
    {path: '/home/goodsinfo/:goodsid', component: GoodsInfo},
    {path: '/home/liuyan', component: Liuyan},
    {path: '/home/goodsDesc/:goodsid', component: GoodsDesc, name: 'goodsDesc'},
    {path: '/home/goodsComment/:goodsid', component: GoodsComment, name: 'goodsComment'}
  ],
  linkActiveClass: 'mui-active'
})
