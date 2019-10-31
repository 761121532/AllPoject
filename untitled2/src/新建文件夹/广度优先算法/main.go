package main
//
//import (
//	"fmt"
//	"os"
//)
//
//func readMaze(filename string) [][]int {
//	file, e := os.Open(filename)
//	if e != nil {
//		fmt.Println("在下萝莉控", e)
//	}
//
//	var row, col int
//	_, _ = fmt.Fscanf(file, "%d %d ", &row, &col)
//	fmt.Println(row, col)
//	maze := make([][]int, row)
//	for i := range maze {
//		maze[i] = make([]int, col)
//		for j := range maze[i] {
//			_, _ = fmt.Fscanf(file, "%d", &maze[i][j])
//		}
//	}
//	return maze
//
//}
//
//type point struct {
//	i, j int
//}
//
//func (p point) add(r point) point {
//	return point{p.i + r.i, p.j + r.j}
//}
//
//var dirs = [4]point{
//	{-1, 0}, {0, -1}, {1, 0}, {0, 1},
//}
//
//func walk(maze [][]int, start, end point) {
//	steps := make([][]int, len(maze))
//	for i := range steps {
//		steps[i] = make([]int, len(maze[i]))
//	}
//
//	Q := []point{start}
//
//	for len(Q) > 0 {
//		cur := Q[0]
//		Q = Q[1:]
//		for _, dir := range dirs {
//			next := cur.add(dir)
//
//		}
//	}
//
//}
//
//func main() {
//	maze := readMaze("D:\\untitled2\\src\\新建文件夹\\广度优先算法\\maze.in")
//
//	for _, value := range maze {
//		for _, val := range value {
//			fmt.Printf("%d ", val)
//		}
//		fmt.Println()
//	}
//
//	walk(maze,point{0,0},point{5,6})
//}
