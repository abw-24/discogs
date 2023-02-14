package discogs.client

import gigahorse._
import support.apachehttp.Gigahorse
import play.api.libs.json._

import discogs.fetch.{SearchResponse, UserTokenFetcher}


trait DiscogsClient {
  def search(query: String): SearchResponse
  def responseParse(responseString: String): SearchResponse = {

    val searchResult: JsResult[SearchResponse] =
      Json.parse(responseString).validate[SearchResponse]

    searchResult match {
      case s: JsSuccess[SearchResponse] => s.get
      case e: JsError => SearchResponse(None)
    }
  }
}

class UserTokenDiscogsClient(final private[this] val token: String) extends DiscogsClient {
  def search(query: String): SearchResponse = {
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val fetcher = UserTokenFetcher(token, http)
      responseParse(fetcher.fetch(discogs.searchUrl + query))
    }
  }
}

object UserTokenDiscogsClient {
  def apply(token: String): UserTokenDiscogsClient = new UserTokenDiscogsClient(token)
}