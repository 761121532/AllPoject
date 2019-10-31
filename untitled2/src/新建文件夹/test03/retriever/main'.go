package main

import (
	"fmt"
	Real "新建文件夹/test03/real"
)

type Retiever interface {
	Get(url string) string
}

func download(r Retiever ) string {
	return r.Get("http://wwww.baidu.com")
}

func main() {
	var r Retiever
	r = Real.Retriever{}
	fmt.Println(download(r))
}
