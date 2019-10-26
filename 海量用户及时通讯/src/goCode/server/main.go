package main

import (
	"fmt"
	"net"
)

func main() {
	//提示信息
	fmt.Println("服务器再8889端口监听")
	lisent,err := net.Listen("tcp", "127.0.0.1:8889")
	defer lisent.Close()
	if err!=nil {
		fmt.Println("服务器启动失败",err)
		return
	}
	//监听成功就  开始等待客户端来链接服务器
	for{
		fmt.Println("等待客户端来相连接")
		conn, err := lisent.Accept()
		if err!=nil{
			fmt.Println("链接失败，直接报错")
			return
		}
		//链接成功  则启动一个协程 ，保持与客户端的数据通信
		go process(conn)
	}
}

func process(conn net.Conn)  {
	//需要延时关闭
	defer conn.Close()
	//读取客户端的数据
	for{
		buf:=make([]byte,1024*4)
		fmt.Println("等待客户端发送的数据")
		n, err := conn.Read(buf[:4])
		if n!=4||err!=nil {
			fmt.Println("读错了",err)
			return
		}
		fmt.Println("读到的长度为",buf[0:4])
	}
}
