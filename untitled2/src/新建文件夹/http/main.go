package main

import (
	"fmt"
	"net/http"
	"net/http/httputil"
)

func main() {
	requst , err :=http.NewRequest(http.MethodGet,"http://www.7pyx.com/result.php",nil)
	str :="阿拉山口京哈可是卡号是客户"

	requst.Header.Add("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1")
	requst.Header.Add("contents",str)
	requst.Header.Add("Cookie","UM_distinctid=16dd4f4fac412d-0bfb44cb4052c5-67e1b3f-1fa400-16dd4f4fac5429; CNZZDATA1278015185=970660637-1571235885-%7C1571235885; Example_auth=375f%2BeUDj%2BIOsRYih41TuioS53W2FmhcEvefd5hYcVBKr%2FwSK4ydc6Ukeg; td_cookie=2671634351")
	requst.Header.Add("xfm_uid","d874896982d320cef3f21dfa865b5886")
	requst.Header.Add("bd_ai","on")
	requst.Header.Add("auto_typeset","on")
	requst.Header.Add("agreement","on")

	client :=http.Client{
		CheckRedirect: func(req *http.Request, via []*http.Request) error {
			fmt.Println("在下萝莉控",req)
			return 	nil
		},
	}
	res, err := client.Do(requst)

	if err!=nil {
		fmt.Println("有毛病",err)
	}
	s, err := httputil.DumpResponse(res, true)
	if err!=nil {
		fmt.Println("不知道为什么报错",err)
	}
	fmt.Printf("%s",s)
}
