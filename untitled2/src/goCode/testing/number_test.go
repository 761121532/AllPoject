package main

import "testing"

func test(t *testing.T)  {
	a:=addNumber(10)
	if a<10 {
		t.Fatalf("期望一个值为%v  实际值为%v",20,a)
	}
	t.Logf("执行正确")
}
