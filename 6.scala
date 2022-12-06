val source = io.Source.fromFile("6.txt")
val lines = source.getLines.mkString.toList
// j = 4 and while loop checks != 4 for pt1, current code is pt2
var i = 0 // failure
var j = 14 // is what i am

while (lines.slice(i, j).distinct.size != 14) {
	i+=1
	j+=1
}
println(j)