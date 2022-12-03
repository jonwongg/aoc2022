val source = io.Source.fromFile("3.txt")
var total = 0
for (line <- source.getLines) {
    val arr = line.toList
    val splitted = arr.splitAt(arr.length/2)
    val common = splitted._1 intersect splitted._2

    def prios = common.distinct.map { a =>
      if (a.isUpper) a.toInt - 38 else a.toInt - 96
    }

    total += prios.sum
}

println(total)

source.close()