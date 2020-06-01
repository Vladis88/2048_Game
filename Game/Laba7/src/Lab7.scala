import java.io.File
import java.util
import scala.io.Source

object Lab7 {
  def main(args: Array[String]): Unit = {
    val filesHere = new File("./INFO/Player").listFiles()
    var i = 1
    var list: List[String] = Nil
    var arrayListFirstFile: util.ArrayList[String] = new util.ArrayList[String]()
    for (file <- filesHere) {
      var flag = false
      val source = Source.fromFile(file)
      if (file.toString.equals(".\\INFO\\Player\\" + i + "NumberOfProcess.json")) {
        list = source.getLines.toList
        arrayListFirstFile.add(list.toString())
        flag = true
      }
      if (flag)
        i += 1
    }
    println("Список операций и кол-во операций в каждом списке:")
    println(arrayListFirstFile)
    searchBestOperation(arrayListFirstFile)
  }

  def searchBestOperation(arrList: util.ArrayList[String]) {
    println("\nСамая длинная последовательность операции, повторившейся более одного раза:")
    println(matchTest("maxRepeat", arrList))
    println("Единственная самая длинная последовательность операции:")
    println(matchTest("max", arrList))
    println("Единственная самая короткая последовательность операции:")
    println(matchTest("min", arrList))
  }

  def matchTest(x: String, arrList: util.ArrayList[String]): String = x match {
    case "max" => util.Collections.max(arrList)
    case "min" => util.Collections.min(arrList)
    case "maxRepeat" =>
      var str: String = ""
      var max: Int = 0
      for (i <- 0 until arrList.size()) {
        for (j <- i + 1 until arrList.size()) {
          val next: Int = arrList.get(j).substring(4).
            substring(1, arrList.get(j).substring(4).length - 1).toInt
          if (arrList.get(i) == arrList.get(j) && max <= next) {
            str = arrList.get(j).toString
            max = next
          }
        }
      }
      return str
  }
}