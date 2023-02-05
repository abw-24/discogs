package discogs

import discogs.client.UserTokenDiscogsClient

object Search {
  def main(args: Array[String]): Unit = {
    val token :: queryList = args.toList
    val client = UserTokenDiscogsClient(token)
    val queryBuilder = new StringBuilder()
    val queryString = queryList.addString(queryBuilder , " ").toString
    println(client.search(queryString))
  }
}
