package main

import "fmt"

func main() {
	var chainl chan int
	chainl = make(chan int,10)
	for i:=0;i<10 ;i++  {
		chainl<-i
	}

	for{
	select {
	case v:=<-chainl :
		fmt.Println(v)
	}
	}



}
