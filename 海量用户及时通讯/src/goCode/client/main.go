package main

import (
	"fmt"
	"os"
)

//用户名
var userId int
//密码
var passWord string

func main() {
	//接受用户的选择
	var key int
	//是否还显示菜单
	var loop =true

	for{
		fmt.Println("。。。。。。。。。。登陆聊天室。。。。。。。。。。。")
		fmt.Println("\t\t\t 1 登陆聊天室")
		fmt.Println("\t\t\t 2 注册用户")
		fmt.Println("\t\t\t 3 退出系统")
		fmt.Println("\t\t\t 请选择 1~3")

		_, _ = fmt.Scanf("%d\n", &key)
		switch key {
		case 1:
			fmt.Println("登陆聊天室")
			loop=false
		case 2:
			fmt.Println("注册用户")
			loop=false
		case 3:
			fmt.Println("退出")
			os.Exit(0)
		default:
			fmt.Println("你的输入有误，请重新输入")
			fmt.Println(loop)
		}

		if key==1{
			fmt.Println("请输入用户id")
			_, _ = fmt.Scanf("%d\n", &userId)
			fmt.Println("请输入用户密码")
			_, _ = fmt.Scanf("%s\n", &passWord)
			err := Login(userId, passWord)
			if err!=nil {
				fmt.Println("有问题")
			}else {
				fmt.Println("登陆成功")
			}
		}else {

		}
	}



}
