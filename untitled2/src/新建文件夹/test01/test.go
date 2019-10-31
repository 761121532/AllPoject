package main

import (
	"fmt"
	"io/ioutil"
	"math"
	"math/cmplx"
)

func aaa() {
	const fileName  = "C:\\Users\\Administrator\\Desktop\\abc.txt"
	consts, err := ioutil.ReadFile(fileName)
	if err!=nil {
		fmt.Println(err)
	}
	fmt.Printf("%s\n" , consts)
}

func euler()  {
	fmt.Println(cmplx.Pow(math.E , 1i * math.Pi) +1)
}



