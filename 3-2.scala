val source = io.Source.fromFile("3.txt")
println(source.getLines.toList.grouped(3).toList.flatMap(_.map(_.toList).reduceLeft(_.intersect(_)).distinct).map { a => if (a.isUpper) a.toInt - 38 else a.toInt - 96} sum)
source.close()