package main

import "fmt"

func main() {
	//var a [5]int
	//	for i:=0;i<len(a);i++{
	//		fmt.Printf("请输入第%d的值", i+1)
	//		fmt.Scanln(&a[i])
	//	}
	//
	//for key, value := range a {
	//	fmt.Printf("地%v的值是 %v" ,key+1,value)
	//}
	//var a [5]int
	////为了每次生成的随机数不一样  我们需要给一个seed值 现在给的是 当前秒数
	//rand.Seed(time.Now().UnixNano())
	//for i:=0;i<len(a);i++{
	//	a[i] = rand.Intn(100)
	//}
	//fmt.Println(a)

	//var 啊 []int = make([]int , 5 , 10 )
	//啊[0] = 10
	//啊[1] = 20
	//啊[2] = 30
	//fmt.Println(len(啊))
	//fmt.Println(cap(啊))


	var a =make(map[string]string)
	a["hh"]="kk"
	a["zz"] = "gg"
	delete(a, "hh")
	fmt.Println(a)

}
