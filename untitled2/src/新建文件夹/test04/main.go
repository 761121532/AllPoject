package main

import "fmt"

func addnumber()  {
	defer func() {
		r :=recover()
		if err , ok:=r.(error);ok {
			fmt.Println("反正就是报了个错",err)
		}else {
			panic(r)
		}
	}()

	//var b=0
	//var a=5/b
	//fmt.Println(a)
	panic(123)
}


func main() {
	addnumber()
}

