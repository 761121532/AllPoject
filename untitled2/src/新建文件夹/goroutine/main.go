package main

import (
	"fmt"
	"time"
)

func main() {
	for i:=0;i<10;i++ {
		go func(i int) {
			fmt.Println("卧槽泥猴子",i)
		}(i)
		time.Sleep(time.Microsecond)
	}
}
