val source = io.Source.fromFile("4.txt")
val p = source.getLines.toList.map{ z => 
	val zs = z.split(',')
	(zs(0), zs(1)) 																   // List((2-4, 6-8), (1-5, 3-5))
}.map(t => {
	val s = t._1.split('-')
	val s2 = t._2.split('-')
	(List.range(s(0).toInt, s(1).toInt+1), List.range(s2(0).toInt, s2(1).toInt+1)) // List((List(2,3,4), List(6,7,8)),(List(1,2,3,4,5), List(3,4,5)))
}).map { tooples =>
	val common = tooples._1 intersect tooples._2
	if ((common equals tooples._1) || (common equals tooples._2)) 1 else 0
}
println(p sum)

source.close()