package main

import (
	"fmt"
	"strconv"
)

func main() {
	var a string="aaaaaa"
	b,err :=strconv.Atoi(a);
	if err!= nil {
		fmt.Println(err)
	}else {
		fmt.Println(b)
	}

	c :=123
	//int 整形 转字符串
	a = strconv.Itoa(c)
	fmt.Println(a)
	//string 转 buyes
	var bytes=[]byte("在下萝莉控")
	fmt.Printf("啊是大 + %v \n",bytes)
	//byte 转字符串
	str :=string([]byte{97,98,99})
	fmt.Printf(str)
}
