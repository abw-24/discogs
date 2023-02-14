package discogs

import discogs.client.UserTokenDiscogsClient
import discogs.fetch.SearchResponse

object Search {
  def main(args: Array[String]): Unit = {
    // Pop token from query term arguments, create a client
    val token :: queryList = args.toList
    val client = UserTokenDiscogsClient(token)
    // Build simple query from term list
    val queryBuilder = new StringBuilder()
    val queryString = queryList.addString(queryBuilder , "&").toString
    // Use the client to send query, parse result
    val res: SearchResponse = client.search(queryString)
    println(res.toString)
  }
}
