package somador
object Somador {
  def main(args: Array[String]): Unit = {
    var eu = new Obj("rayh", 0)
    var run = new Run( eu )

    var thread = new Thread(run, "th1");
    var thread1 = new Thread(run, "th2");

    thread.start()
    thread1.start()

    Thread.sleep(2000)
    printf("VALOR FINAL DA VARIÁVEL: %d",eu.age)

  }
}
//setando o objeto para trabalhar
class Obj(var name: String, var age : Int) {
  override def toString: String = super.toString
 }
//construindo o "trabalho da thread" extendendo a uma classe runnable
class Run(var obj : Obj) extends Runnable{
  override def run(): Unit = {
    // para executar algo unico em escala como no java usamos synchronized para impedir que outras threads possam
    // executar alguma ação em cima de um sharedObject
    synchronized {
      for (x <- 1 to 25000) {
        obj.age += 1
      }
    }
    printf("THREAD: %s TCHAU, BRIGADO!\n",Thread.currentThread().getName)
  }
}

