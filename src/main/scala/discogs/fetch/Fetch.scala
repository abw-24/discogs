package discogs.fetch

import gigahorse._, support.apachehttp.Gigahorse
import scala.concurrent._, duration._
import ExecutionContext.Implicits._

trait Fetch {
  def fetch(url: String, authHeader: String): String = {
    val rawRequest = Gigahorse.url(url + authHeader).get
    val future = http.processFull(rawRequest, Gigahorse.asString)
    Await.result(future, 10.seconds)
  }
}

class UserTokenFetcher(token: String) extends Fetch {
  private val authHeader = s"-H Authorization: Discogs token=${token}"
  def fetch(url: String): String = super.fetch(url, authHeader)
}

object UserTokenFetcher {
  def apply(token: String): UserTokenFetcher = new UserTokenFetcher(token)
}

class KeySecretFetcher(key: String, secret: String) extends Fetch {
  private val authHeader = s"-H Authorization: Discogs key=${key}, secret=${secret}"
  def fetch(url: String): String = super.fetch(url, authHeader)
}

object KeySecretFetcher {
  def apply(key: String, secret: String): KeySecretFetcher =
    new KeySecretFetcher(key, secret)
}
