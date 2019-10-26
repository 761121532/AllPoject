package com.example.demo.controller;


import com.example.demo.entity.Goods;
import com.example.demo.entity.aaa;
import com.example.demo.service.GoodsService;
import com.example.demo.service.NewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;



/**
 * @author 在下萝莉控
 */
@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @Autowired
    NewService newService;

    /**
     * 主页内容的接口
     * @return  主页轮播图图片
     */
    @RequestMapping("api/getlunbo")
    public Goods getlunbo(){ System.out.println("有沙雕访问");return goodsService.findAll(); }

    @RequestMapping("api/getnewslist")
    public Goods getnewslist(){ System.out.println("套你猴子");return newService.list(); }

    @RequestMapping("api/getimgcategory")
    public Goods getimgcategory(){ System.out.println("图片被访问了");return goodsService.findAllCategory(); }

    @RequestMapping("api/getimages/*")
    public Goods getimages(HttpServletRequest request){ String a =request.getRequestURI();return goodsService.findAllImages(a); }

    @RequestMapping("api/getgoods")
    public Goods getgoods(HttpServletRequest request)  { String s=request.getParameter("pageindex");
    System.out.println("卧槽泥马");return goodsService.findAllGoodsList(s); }

    @RequestMapping("api/getimageInfo/*")
    public Goods getimageInfo(HttpServletRequest request)  { String s=request.getRequestURI();
    System.out.println("有基佬拉我裤链");return goodsService.findCategoryImage(s); }

    @RequestMapping("api/getcomments/*")
    public Goods getcomments(HttpServletRequest request){
        System.out.println("你忙吧   我吃柠檬");
        String path = request.getRequestURI();
        String agrs = request.getParameter("pageindex");
        return goodsService.findComment(path,agrs);
    }

    @RequestMapping("api/postcomment/*")
    public Boolean postcomment(@RequestBody aaa a , HttpServletRequest request ){
        System.out.println("你不仅长的丑，还想的美");
        String agrs = a.getContent();
        String imageid = request.getRequestURI();
        return goodsService.insertComment(imageid,agrs);
    }

    @RequestMapping("api/getthumimages/*")
    public Goods getthumimages(HttpServletRequest request){
        System.out.println("你在这里等一下，我去买几个橘子");
        String imageid = request.getRequestURI();
        return goodsService.getthumimages(imageid);
    }

    @RequestMapping("api/goods/getinfo/*")
    public Goods findGoddsById(HttpServletRequest request){
        System.out.println("老夫还有杰克逊");
        String Goodsid = request.getRequestURI();
        return goodsService.findGoodsById(Goodsid);
    }

    @RequestMapping("api/goods/getdesc/*")
    public Goods findDesc(HttpServletRequest request){
        System.out.println("老夫还有杰克逊");
        String Goodsid = request.getRequestURI();
        return goodsService.findDesc(Goodsid);
    }

}
