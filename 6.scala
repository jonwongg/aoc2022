val source = io.Source.fromFile("6.txt")
val lines = source.getLines.mkString.toList

def getConsecUnique(lines: List[Char], chunk: Int) = {
	lines.sliding(chunk,1).map(_.distinct).zipWithIndex.find(_._1.size == chunk).map(_._2).get + chunk
}

println(getConsecUnique(lines, 4)) // pt1
println(getConsecUnique(lines, 14))// pt2