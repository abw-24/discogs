package discogs.client

import gigahorse._
import support.apachehttp.Gigahorse
import play.api.libs.json.JsonConfiguration.Aux
import play.api.libs.json._

import discogs.fetch.UserTokenFetcher
import discogs.models._


trait DiscogsClient {
  def search(query: String): SearchResponse
  def responseParse(responseString: String): SearchResponse = {
    implicit val cfg: Aux[Json.MacroOptions] = JsonConfiguration(
      discriminator = "type",
      typeNaming = JsonNaming(_.toLowerCase)
    )
    val searchResult: JsResult[SearchResponse] =
      Json.parse(responseString).validate[SearchResponse]

    searchResult match {
      case s: JsSuccess[SearchResponse] => s.get
      case e: JsError => SearchResponse(None)
    }

  }
}

class UserTokenDiscogsClient(private val token: String) extends DiscogsClient {
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