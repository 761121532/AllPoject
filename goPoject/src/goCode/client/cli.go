package main

import (
	"bufio"
	"fmt"
	"net"
	"os"
)

func main() {
	conn, err := net.Dial("tcp", "127.0.0.1:8080")
	if err!=nil {
		fmt.Println("反正就是报了个错",err)
	}else {
		for  {
		buffer:=bufio.NewReader(os.Stdin)//os.Stdin代表标注输入【终端】
		//从终端读取一行用户的输入  并发送到服务器
		line, _ := buffer.ReadString('\n')
		//再将内容发送给服务器
		n, _ := conn.Write([]byte(line))

		fmt.Println(n)
		}
	}
}
