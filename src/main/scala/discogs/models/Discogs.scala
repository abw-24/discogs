package discogs.models

sealed trait Discogs {
  def title: String
  def id: Int
}

case class Artist(id: Int, title: String, releaseIds: Seq[String], country: String)
  extends Discogs

case class Label(id: Int, title: String, country: String)
  extends Discogs

case class Master(id: Int, masterId: Int, title: String, year: String,
                  artist: String, label: Seq[String], genre: Seq[String],
                  style: Seq[String],country: String)
  extends Discogs

case class Release(id: Int, title: String, year: String, artist: String,
                   label: Seq[String], catid: String, genre: Seq[String],
                   style: Seq[String], format: Seq[String], country: String)
  extends Discogs