package main

import (
	"fmt"
	"github.com/garyburd/redigo/redis"
)
func main() {
	//通过go向redis写入数据  和读取数据
	fmt.Println("在下萝莉控")
	c, err := redis.Dial("tcp", "127.0.0.1:6379")
	if err!=nil {
		fmt.Println("链接失败")
		return
	}
	defer c.Close()
	//直接将interface转成string
	e, err := redis.String(c.Do("Get", "名字"))

	fmt.Println(e)
}

func test()  {

}
