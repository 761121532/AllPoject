package main

import (
	"bufio"
	"fmt"
	"io"
	"strings"
)

func fbnc() intGen {
	a , b := 0, 1
	return func() int {
		a,b =b,a+b
		return a
	}
}

type intGen  func() int

func (g intGen) Read(p []byte) (n int, err error) {
	next := g()
	if next>10000{
		return  0,io.EOF
	}
	s := fmt.Sprintf("%d\n",next)
	return strings.NewReader(s).Read(p)
}

//用来打印一个io.Reader 的方法
func printFileContents(reader io.Reader)  {
	scanner := bufio.NewScanner(reader)

	for scanner.Scan(){
		fmt.Println(scanner.Text())
	}
}

func main()  {
	f :=fbnc()
	printFileContents(f)

}
