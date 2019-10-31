package main

import (
	"fmt"
)

func test() {
	//创建两个管道
	intchinl :=make(chan int,50)
	exit     :=make(chan bool,1)

	go writdata(intchinl)
	go readdata(intchinl,exit)

	for{
		_, ok :=<-exit
		if !ok {
			break
		}
	}

}

func writdata(intchinl chan int)  {
	for i:=0;i<=50;i++  {
		intchinl<-i
		fmt.Printf("在写东西%v\n",i)
		//time.Sleep(time.Second)
	}
	close(intchinl)
}

func readdata(intchinl chan int,exit chan bool)  {
	for{
		v,x :=<-intchinl
		if !x {
			break
		}
		fmt.Printf("读到数据了%v\n",v)
		//time.Sleep(time.Second)
	}
	//读取完数据后，即任务完成
	exit<-true
	close(exit)
}