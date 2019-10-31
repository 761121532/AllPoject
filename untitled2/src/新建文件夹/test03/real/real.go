package Real

import (
	"net/http"
	"net/http/httputil"
	"time"
)

type Retriever struct {
	UserAgent string
	TimeOut time.Duration
}

func (r Retriever)Get(url string) string {
	response, err := http.Get(url)
	if err!=nil {
		panic(err)
	}
	resoult,err := httputil.DumpResponse(response, true)
	_ = response.Body.Close()
	if err!=nil {
		panic(err)
	}

	return string(resoult)
}