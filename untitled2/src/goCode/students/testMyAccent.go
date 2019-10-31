package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main() {
	file , err :=os.Open("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt")
	if err!=nil {
		fmt.Println(err)
	}
	defer file.Close()

	bf := bufio.NewReader(file)
	for{
		str ,er :=bf.ReadString('\n')
		if er==io.EOF {  //io.eof  表示文件的末位
			break
		}
		fmt.Print(str)
	}
	fmt.Println("文件读取结束")
}