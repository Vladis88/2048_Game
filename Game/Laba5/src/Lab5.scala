import java.io.File
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.annotation.tailrec

object Lab5 {
  def main(args: Array[String]): Unit = {
    val filesHere = new File("./INFO/Player").listFiles()
    var i = 1
    var arrayBufferCompere = ArrayBuffer[Int]()
    var arrayBufferFile = ArrayBuffer[String]()
    for (file <- filesHere) {
      var flag = false
      if (file.toString.equals(".\\INFO\\Player\\" + i + "NumberOfProcess.json")) {
        arrayBufferFile += file.toString
        val source = Source.fromFile(file)
        val line = source.mkString
        //println(i+") "+line)
        arrayBufferCompere += line.toInt
        flag = true
      }
      if (flag)
        i += 1
    }
    //Files array
    for (x <- arrayBufferFile)
      println(x)
    //Files array content
    print("\nСодержание файлв:\n")
    for (x <- arrayBufferCompere)
      println(x)

    print("\nСодержание файлов после сортировки:\n")
    for (x <- sort(arrayBufferCompere.toList))
    //for (x <- sort(arrayBufferCompere.toArray))
      println(x)

  }

//  def sort(a: Array[Int]): Array[Int] = {
//    val b: Array[Int] = new Array[Int](a.length)
//    Array.copy(a, 0, b, 0, a.length)
//    // Function to swap elements
//    def exchange(i: Int, j: Int): Unit = {
//      val k = b(i)
//      b(i) = b(j)
//      b(j) = k
//    }
//    @tailrec
//    def helper(b: Array[Int], i: Int): Array[Int] = {
//      if (i == b.length - 1) return b
//      else {
//        val head = b(i)
//        val maximumInTail = b.slice(i, b.length).max
//        //по возрастанию
//        if (head < maximumInTail) {
//          val maximumInTailIndex = b.slice(i, b.length).indexOf(maximumInTail);
//          exchange(i, maximumInTailIndex + i)
//        }
//        //по убыванию
///*        val minimumInTail = b.slice(i, b.length).min
//        if (head > minimumInTail /*head > maximumInTail*/ ) {
//          val minimumInTailIndex = b.slice(i, b.length).indexOf(minimumInTail)
//          exchange(i, minimumInTailIndex + i)
//        }*/
//        helper(b, i + 1)
//      }
//    }
//    helper(b, 0)
//  }

  def sort(list: List[Int]): List[Int] = {
    @tailrec
    def helpSort(l: List[Int], reminder: List[Int]): List[Int] = {
      if (reminder.isEmpty)
        l
      else
        helpSort(reminder.filter(_ == reminder.min) ++ l, reminder.filter(_ > reminder.min)) //по убыванию
      //helpSort(reminder.filter(_ == reminder.max) ++ l, reminder.filter(_ < reminder.max)) //по возрастанию
    }
    helpSort(Nil, list)
  }
}