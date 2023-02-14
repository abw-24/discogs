package discogs.fetch

import discogs.models._
import play.api.libs.json.JsonConfiguration.Aux
import play.api.libs.json._

case class SearchResponse(results: Option[Seq[Discogs]])

object SearchResponse {
  implicit val cfg: Aux[Json.MacroOptions] = JsonConfiguration(
    discriminator = "type",
    typeNaming = JsonNaming(_.toLowerCase)
  )
  implicit val artistReads: Reads[Artist] = Json.reads[Artist]
  implicit val releaseReads: Reads[Release] = Json.reads[Release]
  implicit val masterReads: Reads[Master] = Json.reads[Master]
  implicit val labelReads: Reads[Label] = Json.reads[Label]
  implicit val discogsReads: Reads[Discogs] = Json.reads[Discogs]
  implicit val searchReads: Reads[SearchResponse] = Json.reads[SearchResponse]
}
