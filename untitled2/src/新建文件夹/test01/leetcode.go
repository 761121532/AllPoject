package main

import (
	"fmt"
	"unicode/utf8"
)

func main() {
	s :="helol在下萝莉控！"
	fmt.Println(utf8.RuneCount([]byte(s)))
	byt:=[]byte(s)

	for len(byt)>0 {
		in , size :=utf8.DecodeRune(byt)
		byt=byt[size:]
		fmt.Printf("%c",in)
	}
	fmt.Println()
}
