package discogs

import discogs.client.UserTokenDiscogsClient

object Search {
  def main(args: Array[String]): Unit = {
    val client = UserTokenDiscogsClient(args(0))
    println(client.search("Madlib"))
  }
}
