package discogs.models

import play.api.libs.json.{Json, Reads}

sealed trait Discogs {
  def title: String
  def id: Int
}

case class Artist(id: Int, title: String, releaseIds: Seq[String], country: String)
  extends Discogs

object Artist {
  implicit val releaseReads: Reads[Artist] = Json.reads[Artist]
}

case class Label(id: Int, title: String, country: String)
  extends Discogs

object Label {
  implicit val masterReads: Reads[Release] = Json.reads[Release]
}

case class Master(id: Int, masterId: Int, title: String, year: String,
                  artist: String, label: Seq[String], genre: Seq[String],
                  style: Seq[String],country: String)
  extends Discogs

object Master {
  implicit val masterReads: Reads[Master] = Json.reads[Master]
}

case class Release(id: Int, title: String, year: String, artist: String,
                   label: Seq[String], catid: String, genre: Seq[String],
                   style: Seq[String], format: Seq[String], country: String)
  extends Discogs

object Release {
  implicit val releaseReads: Reads[Release] = Json.reads[Release]
}