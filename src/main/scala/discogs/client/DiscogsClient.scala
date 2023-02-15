package discogs.client

import gigahorse._
import support.apachehttp.Gigahorse
import play.api.libs.json._
import play.api.libs.json.JsonConfiguration.Aux

import discogs.fetch.UserTokenFetcher
import discogs.models._


trait DiscogsClient {

  implicit val discogsReads: Reads[DiscogsResult] = Json.reads[DiscogsResult]
  implicit val urlReads: Reads[PaginationUrl] = Json.reads[PaginationUrl]
  implicit val paginateReads: Reads[Pagination] = Json.reads[Pagination]
  implicit val responseReads: Reads[SearchResponse] = Json.reads[SearchResponse]

  def search(query: String): JsResult[SearchResponse]

  def responseParse(responseString: String): JsResult[SearchResponse] = {
    val rawJs = Json.parse(responseString)
    rawJs.validate[SearchResponse]
  }
}

class UserTokenDiscogsClient(final private[this] val token: String) extends DiscogsClient {
  def search(query: String): JsResult[SearchResponse] = {
    Gigahorse.withHttp(Gigahorse.config) { http =>
      val fetcher = UserTokenFetcher(token, http)
      responseParse(fetcher.fetch(discogs.searchUrl + query))
    }
  }
}

object UserTokenDiscogsClient {
  def apply(token: String): UserTokenDiscogsClient = {
    new UserTokenDiscogsClient(token)
  }
}