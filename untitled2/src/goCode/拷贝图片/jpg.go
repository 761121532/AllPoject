package main
import (
	"bufio"
	"fmt"
	"io"
	"os"
)
func main() {
	_, _ = copyfile("E:\\a\\bb\\a.jpg","E:\\a\\aa\\aa.jpg")
}
//自己的函数  接受两个文件路径
func copyfile(fist string, last string) (witten int64, err error) {
	srcFile, e := os.Open(last)
	if e!=nil{
		fmt.Printf("读取文件出错 %v",e)
	}
	defer srcFile.Close()
	reder :=bufio.NewReader(srcFile)
	//获取文件句柄
	pathFile, er := os.OpenFile(fist, os.O_APPEND|os.O_CREATE, 0666)
	if er!=nil{
		fmt.Printf("读取文件出错 %v",er)
	}
	defer pathFile.Close()
	write:=bufio.NewWriter(pathFile)
	return io.Copy(write,reder)
}
