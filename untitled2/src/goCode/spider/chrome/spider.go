package main

import (
	"fmt"
)

const Path = "http://www.131555.com/"

func main() {
	//启动谷歌浏览器  并设置无头界面
	webDriver := StartChrome()
	defer webDriver.Close()
	//访问指定路径
	err := webDriver.Get(Path)
	//处理异常
	if err != nil {
		fmt.Println("打不开这个玩意\n", err)
		return
	}
	//选择所有的td
	ele, err := webDriver.FindElements("css selector", "td")
	if err!=nil{
		fmt.Println("有毛病",err)
		return
	}
	//遍历所有的td
	for i,v:= range ele{
		//当i《500时跳过  减少运行时间  提高效率
		if i<500{
			continue
		}
		//获得每一个td的值
		value, _ := v.Text();
		//获取自己需要的那个td
		if value=="118期:内幕六肖"{
			//取到对应的值
			fmt.Println(ele[i+1].Text())
			return
		}
	}
}




