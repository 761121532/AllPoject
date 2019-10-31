package main

import (
	"database/sql"
	"fmt"
	_ "github.com/go-sql-driver/mysql"
)

func main() {
	var ip string
	var number int
	db, err := sql.Open("mysql", "root:761121532@tcp(127.0.0.1:3306)/test")
	if err !=nil {
		fmt.Println("有毛病")
	}
	res, err := db.Query(`select * from ip`)
	if err!=nil {
		fmt.Println("爆了个错")
	}
	_ = res.Scan(&number,&ip)
	fmt.Printf("%v",res)
}
