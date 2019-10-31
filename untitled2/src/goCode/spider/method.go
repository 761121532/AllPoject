package main

import (
	"bytes"
	"fmt"
	"github.com/PuerkitoBio/goquery"
	"github.com/axgle/mahonia"
	"net/http"
	"strings"
)

func splitOfString(string string) ( 目标站,品牌,备案,更新 []string) {
	//用goquery 来将页面解析出来
	dom,_:=goquery.NewDocumentFromReader(strings.NewReader(string))
	//获得页面包含所有a标签的结构体的指针
	selection :=dom.Find("a")
	//遍历包含所有a标签的结构体的nodes结构体的值
	for j,i:=range selection.Nodes {
		//取出品牌的值
		if (j-1)%14==0{
			//遍历nodes的属性Attr切片的值
			for _, v := range i.Attr {
				//提取所有href的值
				if v.Key=="href" {
					更新 = append(更新,v.Val)
				}
			}
		}
		if (j-4)%14==0{
			//遍历nodes的属性Attr切片的值
			for _, v := range i.Attr {
				//提取所有href的值
				if v.Key=="href" {
					品牌 = append(品牌,v.Val)
				}
			}
		}
		if (j-7)%14==0{
			//遍历nodes的属性Attr切片的值
			for _, v := range i.Attr {
				//提取所有href的值
				if v.Key=="href" {
					备案 = append(备案,v.Val)
				}
			}
		}
		if (j-11)%14==0{
			//遍历nodes的属性Attr切片的值
			for _, v := range i.Attr {
				//提取所有href的值
				if v.Key=="href" {
					目标站 = append(目标站,v.Val)
				}
			}
		}
	}
	return
}

func Get目标站(string string) string {
	if res , err := http.Get(string);err!=nil {
		return "目标站有问题"
	}else {
		fmt.Println(changeBody(res))
	}
	return ""
}


/**
用来将字符串编码  转成相应编码
*/
func ConvertToString(src string, srcCode string, tagCode string) string {
	srcCoder := mahonia.NewDecoder(srcCode)
	srcResult := srcCoder.ConvertString(src)
	tagCoder := mahonia.NewDecoder(tagCode)
	_, cdata, _ := tagCoder.Translate([]byte(srcResult), true)
	result := string(cdata)
	return result
}

//用来将http的response转换为一个字符串的
func changeBody(res *http.Response) string {
	var  resBody =res.Body
	buf := new(bytes.Buffer)
	_, _ = buf.ReadFrom(resBody)
	return buf.String()
}
