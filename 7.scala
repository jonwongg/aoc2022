// class Directory(var name: String, var fileSize: Int, var subDirectories: List[Directory]):
// 	def size: Int =
// 		fileSize + subDirectories.map(_.fileSize)
// 	override def toString: String =
// 		s"$name: $fileSize"
// end Directory
import scala.collection.mutable.SortedMap

var map = SortedMap[String, Int]() // var cuz failure
var ownership = SortedMap[String, List[String]]() // var cuz failure etc etc

val source = io.Source.fromFile("7.txt")
val lines = source.getLines


var dir = ""
for (line <- source.getLines) {
	if (line.startsWith("$ cd ")) {
		dir = line.replace("$ cd ", "")
		map(dir) = 0
	}

	if (line.startsWith("dir ")) {
		var child = line.replace("dir ", "")
		if (ownership.contains(dir)) {
			ownership(dir) = ownership(dir) ::: List(child)
		} else {
			ownership(dir) = List(child)
		}
	}

	if (line.matches(".*\\d.*")) {
		val fileSize = line.split(" ")(0)
		map(dir) = map(dir) + fileSize.toInt
	}
}

// make this shit recursive and it should work
val newMap = ownership.toList.reverse.map{ 
	case (b, leafs) => (b, leafs.map(z => map(z)) ::: List(map(b)))
}.toMap

val sums = map.toList.reverse.map {
	case (b, leafs) => {
		if (newMap.contains(b)) {
			map(b) = newMap(b).sum
		}
	}
}

println(map.retain(((k: String, v: Int) => v < 100000)).foldLeft(0)(_+_._2))