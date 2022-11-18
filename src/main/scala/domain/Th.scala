package domain

class Th extends Thread{
  override def run(): Unit = {
    printf("thread %s\n", Thread.currentThread().getName)

  }
  /*Recebe o ponteiro de duas matrizes e a resultante, calculando a multiplicação limitando as linhas contidas em
  * inferior e superior
  * */


  def multiplicar(matriz0: Array[Array[Int]],
                  matriz1: Array[Array[Int]],
                  finalMatriz: Array[Array[Int]],
                  inferior: Int,
                  superior: Int ): Unit = {


    var i = inferior;
    var j = inferior;
    var k = inferior;
    var sum = 0;


    while (i < superior) {
      j = 0;
      while (j < finalMatriz.length) {
        sum = 0;
        k = 0;
        while (k < finalMatriz.length) {
          sum = sum + (matriz0(i)(k) * matriz1(k)(j));
          k = k + 1;
        }
        finalMatriz(i)(j) = sum;
        j = j + 1;
      }
      i = i + 1;
    }
  //printf("trabalho da thread: %s esta feito\n", Thread.currentThread().getName)
  }

  def adicionar(vetor: Array[Int]): Unit = {
    printf("thread %s\n", Thread.currentThread().getName)

    for(x <- 0 to 100000){
      vetor(1) += 1;
    }
  }

  def subtrair(vetor: Array[Int]): Unit = {
    printf("thread %s", Thread.currentThread().getName)

    for(x <- 0 to 10){
      vetor(1) -= 1;
    }
  }
}
