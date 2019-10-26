package main

import (
	"encoding/binary"
	"encoding/json"
	"fmt"
	"goCode/common"
	"net"
)
func Login(UserId int , PassWord string)(err error)  {
	////开始定义协议
	//fmt.Println(UserId,passWord)
	//return nil
	//链接到服务器端
	conn, e := net.Dial("tcp", "127.0.0.1:8889")
	if e!=nil {
		fmt.Println("链接失败",e)
		return
	}
	//延时关闭
	defer conn.Close()
	//准备通过conn 发送消息到服务器
	var mes common.Massage
	mes.Type=common.LoginMesType
	//创建一个loginmessage结构体
	var loginmes=common.LoginMes{
		UserId,
		PassWord,
		"",
	}
	//转成byte切片
	data, e := json.Marshal(loginmes)
	if e!=nil {
		fmt.Println("转为为json失败",e)
		return
	}
	//转成字符串
	mes.Data=string(data)

	data, e = json.Marshal(mes)
	if e!=nil {
		fmt.Println("转为为json失败",e)
		return
	}
	//conn。writ（）这个方法只能发送一个byte切片  不能直接发送 int 和string
	var pkglen uint32
	pkglen=uint32(len(data))
	var bytes [4]byte
	var a=bytes[0:4]
	binary.BigEndian.PutUint32(a,pkglen)
	n, err := conn.Write(a)
	if n!=4||err!=nil {
		fmt.Println("conn.Write(a)失败了",err)
	}
	fmt.Println("发送的字节长度为=",len(a))
	return err
}

