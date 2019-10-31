package main

import (
	"fmt"
	"math"
)

type  小花 struct {
	Name string	`json:"name"` //结构体的标签  tag
	Age  int	`json:"age"`
	Color string	`json:"color"`
}

type circle struct {
	radius float64
}

func (c circle)area() float64 {
	return math.Pi*c.radius*c.radius
}

func (a 小花)test()  {
	fmt.Println(a.Name)
}
func main() {
	//var cat 小花
	//cat.Age=18
	//cat.Color="白色"
	//cat.Name="小红"

	//对象转json  返回的是char数组
	//arr,err :=json.Marshal(cat)
	//char数组转字符串
	//if err!=nil {
	//	fmt.Println("转错了")
	//}else {
	//	fmt.Println(string(arr))
	//}

	//var a 小花
	//a.Name="在下萝莉控"
	//a.test()

	var c circle
	c.radius=50
	int:=c.area()
	fmt.Println(int)
}
