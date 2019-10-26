package common

//确定一些消息类型
const  (
	LoginMesType = "LoginMes"
	LoginResMesType ="LoginResMes"
)


type Massage struct {
	Type string  `json:"type"` //消息类型
	Data string  `json:"data"` //消息数据
}

//定义两个消息，后面需要再增加
type LoginMes struct {
	UserId int 		`json:"user_id"`
	PassWord string	`json:"pass_word"`
	UserName string	`json:"user_name"`
}

type LoginResMes struct {
	Code int 	`json:"code"` //500表示该用户未注册    200表示登陆成功
	Error string	`json:"error"`	//返回的错误消息
}
