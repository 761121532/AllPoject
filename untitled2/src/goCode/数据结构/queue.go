package main

import (
	"errors"
	"fmt"
)

func main() {
	//先创建一个队列
	queue :=&queue{
		max_size: 5,
		array:    [5]int{},
		front:    -1,
		rear:     -1,
	}
	fmt.Println(queue)
}

//使用一个结构体来管理队列
type queue struct {
	max_size int
	array [5]int   //用来模拟队列
	front int	   //表示指向的队列队首
	rear int       //表示指向队列尾部
}

// 添加数据到队列
func (queue *queue)addqueue(val int)(err error)  {
	//先判断队列已满
	if queue.rear==queue.max_size-1{
		return errors.New("队列用完了")
	}
	queue.rear++  //往后移
	queue.array[queue.rear]=val

	return
}
//显示队列
func (queue *queue)show()  {
	fmt.Println("队列当前的情况是")
	//this。front不包含队首元素的
	for i :=queue.front+1; i<queue.rear ;i++  {
		fmt.Println("array[%d]=%d\t",i,queue.array[i])
	}
}
