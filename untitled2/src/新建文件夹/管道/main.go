package main

import "fmt"

func main() {
	chanDemo()
	
}
func werk( c chan int) {
	for {
		n:= <- c
		fmt.Println(n)
	}
}

func chanDemo()  {
	c :=make(chan int)
	go werk(c)
	c <- 1
	c <- 2
}
