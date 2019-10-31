package main

import (
	"fmt"
	"math/rand"
	"sort"
)

type hero struct {
	Name string
	Age  int
}
//声明一个hero的结构体切片类型
type herosilce []hero

//实现一个接口Interface

func (hs herosilce) Len() int  {
	return len(hs)
}
//这个方法就是觉得你使用升序还是降序排列
func (hs herosilce) Less(i,j int) bool  {
	return hs[i].Age < hs[j].Age
}

func (hs herosilce) Swap(i,j int)  {
	temp :=hs[i]
	hs[i] =hs[j]
	hs[j] = temp
}

//对结构体切片排序
func main() {
	//先定义一个数组，或者切片
	var intslice=[]int{0,-15,56,2,-98}
	sort.Ints(intslice)
	fmt.Println(intslice)
	//对一个结构体切片进行排序

	var heros herosilce
	for  i :=0;i<10 ; i++ {
		hero :=hero{
			Name : fmt.Sprintf("英雄~%d",rand.Intn(100)),
			Age : rand.Intn(100),
		}
		//将hero append到heros切片
		heros=append(heros,hero)
	}
	//看看排序前的顺序
	for _ , v :=range heros {
		fmt.Println(v)
	}
	sort.Sort(heros)
	fmt.Println(".........ok.........")
	for _, value := range heros {
		fmt.Println(value)
	}
}