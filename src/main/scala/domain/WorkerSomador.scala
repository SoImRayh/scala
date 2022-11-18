package domain

class WorkerSomador extends Thread{
   def run(vetor:Array[Int]): Unit = {
    vetor(0) = 21
  }
}
