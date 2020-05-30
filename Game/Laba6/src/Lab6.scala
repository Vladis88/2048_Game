import java.io.File
import scala.collection.mutable
import scala.io.Source

object Lab6 {
    def main(args: Array[String]) {
        val filesHere = new File("./INFO/Player").listFiles()
        var i = 1
        var list: List[String] = Nil
        for (file <- filesHere) {
            var flag = false
            if (file.toString == ".\\INFO\\Player\\" + i + "Process.json") {
                val source = Source.fromFile(file)
                list = source.getLines.toList
                println(list)
                println("Карта движения (игра №" + i + "):")
                for (x <- SameMovements(list))
                    println(x)
                println()
                flag = true
            }
            if (flag)
                i += 1
        }
    }

    def SameMovements(list: List[String]): mutable.Map[String, Int] = {
        var countUp, countDown, countLeft, countRight: Int = 0
        val mapRep = scala.collection.mutable.
          Map("Up(38)" -> countUp,
              "Down(40)" -> countDown,
              "Left(37)" -> countLeft,
              "Right(39)" -> countRight)
        for (i <- list.indices) {
            if (list(i) == "37") {
                countLeft += 1
                mapRep.put("Left(37)", countLeft)
            }
            if (list(i) == "38") {
                countUp += 1
                mapRep.put("Up(38)", countUp)
            }
            if (list(i) == "39") {
                countRight += 1
                mapRep.put("Right(39)", countRight)
            }
            if (list(i) == "40") {
                countDown += 1
                mapRep.put("Down(40)", countDown)
            }
        }
        return mapRep
    }
}