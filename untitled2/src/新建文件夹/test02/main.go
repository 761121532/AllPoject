package main

import (
	"fmt"
	Queue "新建文件夹/treeNode"
)

func main() {
	q :=Queue.Queue{1}
	q.Push(2)
	q.Push(3)
	fmt.Println(q.IsEnpity())
	fmt.Println(q.Pop())
}
