package discogs.fetch

import gigahorse._, support.apachehttp.Gigahorse
import scala.concurrent._, duration._

trait Fetch {
  def fetch(url: String, http: HttpClient): String = {
    val rawRequest = Gigahorse.url(url).get
    val future = http.processFull(rawRequest, Gigahorse.asString andThen {_.take(100)})
    Await.result(future, 30.seconds)
  }
}

class UserTokenFetcher(token: String, http: HttpClient) extends Fetch {
  private[this] val discogsAuthStr = s"&token=$token"
  def fetch(url: String): String = super.fetch(url + discogsAuthStr, http)
}

object UserTokenFetcher {
  def apply(token: String, http: HttpClient): UserTokenFetcher =
    new UserTokenFetcher(token, http)
}

class KeySecretFetcher(key: String, secret: String, http: HttpClient) extends Fetch {
  private[this] val discogsAuthStr = s"&key=$key&secret=$secret"
  def fetch(url: String): String = super.fetch(url + discogsAuthStr, http)
}

object KeySecretFetcher {
  def apply(key: String, secret: String, http: HttpClient): KeySecretFetcher =
    new KeySecretFetcher(key, secret, http)
}
