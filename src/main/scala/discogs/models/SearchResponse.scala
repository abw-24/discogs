package discogs.models

import play.api.libs.json.{Json, Reads}

case class SearchResponse(results: Option[Seq[Discogs]]) {
  override def toString: String = results match {
    case Some(s) => s.toString()
    case None => "Error!"
  }
}

object SearchResponse {
  implicit val searchReader: Reads[SearchResponse] = Json.reads[SearchResponse]
}
