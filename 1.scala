object Hello {
    def main(args: Array[String]) = {
        val elves = Seq(0)
        var calories = new scala.collection.mutable.ListBuffer[Int]() += 0

        val source = io.Source.fromFile("1.txt")
        val contents = source.getLines.foreach { b => {
            if (b == "") calories = calories += 0
            else calories = calories.init += (calories.last + b.toInt)
        }}
        
        println("max elf calories = " + calories.toList.max)

        val (values, indices) = calories.toList
          .zipWithIndex       
          .sortBy(t => -t._1) 
          .take(3)            
          .unzip

        println("top 3 elves on the shelves = " + values.sum)

        source.close()
    }

}