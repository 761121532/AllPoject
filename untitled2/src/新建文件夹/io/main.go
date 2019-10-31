package main

import (
	"fmt"
	"os"
)

func main() {
	fileName := "C:\\Users\\Administrator\\Desktop\\新建文本文档.txt"
	file, err := os.Open(fileName)
	if err !=nil {
		fmt.Println("包了个错",err)
		return
	}
	defer file.Close()
	bs := make([]byte,10,10)
	n, err := file.Read(bs)
	fmt.Println(n)
	fmt.Println(bs)
}
