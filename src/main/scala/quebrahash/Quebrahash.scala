package hashing

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.concurrent.{CompletableFuture, ExecutorService, Executors}
import scala.language.postfixOps
import scala.util.hashing.MurmurHash3

object QuebraHashing {

  var charList = Array("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
                     "t", "u", "v", "w", "x", "y", "z")

  var ini : Float = 0
  var fim : Float = 0

  def main(args: Array[String]): Unit = {
    ini = System.nanoTime()
    val entrada : String= "tigas"
    //val meta: String = "c8e60cf43a41d52b44a9542efe6904060a6bde3db1df5057241308af071fd08f"
    // ponte = c8e60cf43a41d52b44a9542efe6904060a6bde3db1df5057241308af071fd08f
    val meta = sha256Hash(entrada)
    var threads : ExecutorService = Executors.newFixedThreadPool(8)
    for(x <- 0 until 26){
      threads.execute(new QuebraSenha(meta, charList(x), threads))
    }
  }

  class QuebraSenha(hash: String, letra: String, threads : ExecutorService) extends Runnable{

  var flag: Int = 0

  override def run(): Unit = {
    var char1: Int = 0
    var char2: Int = 0
    var char3: Int = 0
    var char4: Int = 0

    var i = 0
    var j = 0
    var k = 0
    var l = 0

    while (flag == 0) {
      try {
        concatenar(i, j, k, l);
        l = l+1
        if (l == 26) {
          l = 0;
          k= k+1
        }
        if (k == 26) {
          k = 0;
          j= j+1
        }
        if (j == 26) {
          j = 0;
          i =i+1
        }
        if (i == 26) {
          Thread.currentThread().interrupt();
          return;
        }
      }
    }
  }

  def concatenar(char1: Int, char2: Int, char3: Int, char4: Int): Unit = {
    var palavraFinal = letra + charList(char1)+ charList(char2) + charList(char3) +charList(char4);

    var palavraAsync : CompletableFuture[String] = new CompletableFuture[String]()

    palavraAsync.complete(sha256Hash(palavraFinal))

    var pal = palavraAsync.get()

    if (hash.equals(pal)){
      flag = 1
      printf("A palavra é: %s ", palavraFinal)
      fim = System.nanoTime()

      printf("\nTempo decorrido: %f", fim -ini)
      threads.shutdownNow()
    }
  }
// 7,28
}
}

//gerando o sha256

def sha256Hash(text: String): String = String.format("%064x", new java.math.BigInteger(1,
  java.security.MessageDigest.getInstance("SHA-256").digest(text.getBytes("UTF-8"))))