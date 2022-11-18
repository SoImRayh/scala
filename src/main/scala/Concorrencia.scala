import domain.{Th, WorkerSomador}

object Concorrencia extends App {


  var variavel = new Array[Int](10);
  var ponteiro = variavel

  for(x <- variavel.indices){
    printf("\t %d", variavel(x))
  }

}
