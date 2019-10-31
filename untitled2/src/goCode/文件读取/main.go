package main

import (
	"fmt"
	"io/ioutil"
)

func main() {
	file := "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt"
	count, err := ioutil.ReadFile(file)
	if err!=nil {
		fmt.Println("读取出错了")
	}
	fmt.Println(string(count))
}
