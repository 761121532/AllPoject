package main

import "fmt"

type student struct {
	Name string
	Gender string
	Age int
	Id int
	score int
}

func (s *student)say() string {
	infostr :=fmt.Sprintf("name={%v} gender={%v} age={%v} id={%v} score={%v}",s.Name,s.Gender,s.Age,s.Id,s.score)
	return infostr
}

func main() {
 studen :=student{
	 Name:   "在下萝莉控",
	 Gender: "man",
	 Age:    18,
	 Id:     7,
	 score:  90,
 }
 fmt.Println(studen.say())

}
