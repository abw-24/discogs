package discogs.client

import gigahorse._, support.apachehttp.Gigahorse
import discogs.fetch.UserTokenFetcher

trait DiscogsClient {
  def search(query: String): String
}

class UserTokenDiscogsClient(private val token: String) extends DiscogsClient {
  def search(query: String): String = {
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val fetcher = UserTokenFetcher(token, http)
      fetcher.fetch(discogs.searchUrl + query)
    }
  }
}

object UserTokenDiscogsClient {
  def apply(token: String): UserTokenDiscogsClient = new UserTokenDiscogsClient(token)
}