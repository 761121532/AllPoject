package main

import (
	"bufio"
	"fmt"
	"os"
	_ "os"
)

func main() {
	file := "E:\\a\\aa\\aaa.txt"
	fil, err := os.OpenFile(file, os.O_WRONLY|os.O_CREATE, 0666)
	if err!=nil {
		fmt.Println("有毛病")
	}
	defer fil.Close()

	str:=" 在下萝莉控\n"

	writer:=bufio.NewWriter(fil)
	for i:=0;i<5;i++{
		_, _ = writer.WriteString(str)
	}
	//调用WriteString方法的时候 数据在缓存中  必须flush  将其写入文件
	_ = writer.Flush()
}