val source = io.Source.fromFile("5-crates.txt")
val readonlyCratesMap = source.getLines.map{a =>
	a.grouped(4).toList.map(_.trim)
}.toList.dropRight(1).reverse.transpose.map(_.filter(_.nonEmpty)).zip(Stream.from(1)).map(_.swap).toMap
source.close()
val cratesMap = collection.mutable.SortedMap(readonlyCratesMap.toSeq: _*) 

val sourceInst = io.Source.fromFile("5-instructions.txt")
val instructionsList = sourceInst.getLines.toList
sourceInst.close()

def processInstruction(insturutrturtction: String) = {
	val chunks = insturutrturtction.split(" ")
	(chunks(1).toInt, chunks(3).toInt, chunks(5).toInt)
}

instructionsList.map(processInstruction).map { case (move, from, to) => {
	val popped = cratesMap(from).takeRight(move).reverse
	cratesMap(from) = cratesMap(from).dropRight(move)
	cratesMap(to) = cratesMap(to) ++ popped.reverse // pt2 = adding .reverse to this
}}

println(cratesMap.map(_._2.last).mkString.replaceAll("[\\[\\]]", ""))