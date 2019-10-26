package main

import (
	"fmt"
	"net"
)

func main() {
	listen, err := net.Listen("tcp", "0.0.0.0:8080")//监听8080端口  只支持ipv4
	if err!=nil {
		fmt.Println("有个小毛病 err=",err)
		return
	}
	defer listen.Close()

	for{
		fmt.Println("等人")
		conn,_:=listen.Accept()
		fmt.Printf("%v   %v\n",conn,conn.RemoteAddr().String())
		fmt.Println("搞定")
		go action(conn)
	}
	fmt.Println("已经开始监听",listen)

}

func action(conn net.Conn)  {
	//循环接受客户端发送的数据
	defer conn.Close()

	for{
		buf :=make([]byte ,1024)
		n, err := conn.Read(buf)
		if err!=nil {
			fmt.Println("对方关闭了")
			return
		}
		fmt.Println(string(buf[:n]))
	}

}
