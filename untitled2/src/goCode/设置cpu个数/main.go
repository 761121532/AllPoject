package main

import (
	"fmt"
	"runtime"
)

func main() {
	//获取cpu数量的方法
	cpunumber:= runtime.NumCPU()
	fmt.Println(cpunumber)
	//设置cpu数量
	runtime.GOMAXPROCS(1)
}
