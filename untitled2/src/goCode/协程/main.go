package main

import (
	"fmt"
	"strconv"
	"time"
)

func main() {

	go hello()//开启一个协程

	for i:=0;i<10 ;i++  {
		fmt.Println("老夫聊发少年狂"+strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}

func hello()  {
	for i:=0;i<10 ;i++  {
		fmt.Println("在下萝莉控"+strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}
