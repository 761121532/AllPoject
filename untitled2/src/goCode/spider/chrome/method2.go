package main

import (
	"fmt"
	"github.com/tebeka/selenium"
	"github.com/tebeka/selenium/chrome"
	"log"
)
//获得目标站的所有域名的切片
func 获得目标站的域名(webDriver selenium.WebDriver) []string {
	//声明一个切片
	var 目标站 []string
	//获得登陆过后的body
	body , _:=webDriver.FindElement("tagName","body")
	//获得所有input的值
	element ,_:=body.FindElements("tagName","input")
	//遍历element切片
	for key, value := range element {
		//提取 属于目标站域名的value
		if (key-2)%5==0 {
			//获得value
			path, _ := value.GetAttribute("value")
			//添加到目标站中
			目标站=append(目标站, path)
		}
	}
	return 目标站
}

// StartChrome 启动谷歌浏览器headless模式
func StartChrome() selenium.WebDriver {
	opts := []selenium.ServiceOption{}
	caps := selenium.Capabilities{
		"browserName": "chrome",
	}

	// 禁止加载图片，加快渲染速度
	imagCaps := map[string]interface{}{
		"profile.managed_default_content_settings.images": 2,
	}

	chromeCaps := chrome.Capabilities{
		Prefs: imagCaps,
		Path:  "",
		Args: []string{
			//"--headless", // 设置Chrome无头模式
			"--no-sandbox",
			"--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_2) AppleWebKit/604.4.7 (KHTML, like Gecko) Version/11.0.2 Safari/604.4.7", // 模拟user-agent，防反爬
		},
	}
	caps.AddChrome(chromeCaps)
	// 启动chromedriver，端口号可自定义
	_, err := selenium.NewChromeDriverService("E:\\java\\新建文件夹\\chromedriver.exe", 9515, opts...)
	if err != nil {
		log.Printf("Error starting the ChromeDriver server: %v", err)
	}
	// 调起chrome浏览器
	webDriver, err := selenium.NewRemote(caps, fmt.Sprintf("http://localhost:%d/wd/hub", 9515))
	if err != nil {
		panic(err)
	}

	// 这是目标网站留下的坑，不加这个在linux系统中会显示手机网页，每个网站的策略不一样，需要区别处理。
	//e :=webDriver.AddCookie(&selenium.Cookie{
	//	Name:  "defaultJumpDomain",
	//	Value: "www",
	//})
	//if e!=nil {
	//	fmt.Println("有毛病",e)
	//}

	// 导航到目标网站
	//err = webDriver.Get(str)
	//if err != nil {
	//	panic(fmt.Sprintf("Failed to load page: %s\n", err))
	//}
	return webDriver
}