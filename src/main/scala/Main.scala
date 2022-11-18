
object Main extends App {
  // padroes setando a quantidade de linhas e colunas(lc) e numero de threads(thnum)
  final val lc: Int = 1000
  final val thnum: Int  = 10

  //criando as matrizes para o calculo
  var matriz1 = Array.fill(lc,lc)(2);
  var matriz2 = Array.fill(lc,lc)(2);
  var finalMatriz = Array.fill(lc,lc)(0);

  //criando os ponteiros:
  var ponteiro0 = matriz1
  var ponteiro1 = matriz2
  var ponteiro2 = finalMatriz

  /*
  * criando e tratando a operação com o operando 0 para nao ter interferencias na função no loop ou ter que verificar a
  * igualdade a cada iteração
  */


    var x = 0
    val th1 = new Th()
    th1.setName(x.toString)
    th1.start()
    th1.multiplicar(ponteiro0, ponteiro1, ponteiro2, x, finalMatriz.length/thnum)

  /*
  * criando mais threads para calcular o resto da matriz:
  *
  * o calculo é feito passando um ponteiro para cada matriz e calculando as linas das matrizes que devem ser atualizadas
  * por cada thread, o calculo é feito a partir do limite anterior como inferior da atual e calculando o novo limite
  * superior!
  * */


  for(x<-1 to thnum) {
    val th = new Th();
    th.setName(x.toString)
    th.start();
    th.multiplicar(ponteiro0, ponteiro1, ponteiro2, (x-1)*(finalMatriz.length/thnum),x*(finalMatriz.length/thnum))
  }

  /*
  * Printando a matriz
  * */


  for (a <- 0 until  lc){
      for (b <- 0 until  lc){
        printf("%d\t",finalMatriz(a)(b))
      }
      println("")
    }
  }
