package main

import (
	"bytes"
	"fmt"
	"github.com/PuerkitoBio/goquery"
	"io"
	"log"
	"net/http"
	"strings"
)

const path  = "https://wap.smg5.com/"

func main() {
	res, err := http.Get("http://www.131555.com/")
	if err != nil {
		// 错误处理
		log.Fatal(err)
	}
	defer res.Body.Close()
	var  resBody =res.Body
	buf := new(bytes.Buffer)
	_, _ = buf.ReadFrom(resBody)

	document , _ := goquery.NewDocumentFromReader(io.Reader(buf))
	str  :=document.Text()
	s :=strings.Replace(str," ","",-1)
	s =strings.Replace(s,"\n","",-1)
	selecti := document.Find("118期:内幕六肖")
	fmt.Println(selecti.Text())

}
