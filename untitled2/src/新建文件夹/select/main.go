package main

import (
	"fmt"
	"math/rand"
	"time"
)

func gen() chan int {
	out := make(chan int)
	go func() {
		i := 0
		for {
			time.Sleep(time.Duration(rand.Intn(1500)) * time.Microsecond)
			out <- i
			i++
		}
	}()
	return out
}

func main() {
	c1, c2 := gen(), gen()
	for {
		select {
		case n := <-c1:
			fmt.Println("来自 c1 ", n)
		case n := <-c2:
			fmt.Println("来自 c2", n)
		}
	}
}
