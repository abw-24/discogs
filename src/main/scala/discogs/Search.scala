package discogs

import discogs.client.UserTokenDiscogsClient
import play.api.libs.json.Json

object Search {
  def main(args: Array[String]): Unit = {
    // Pop token from query term arguments, create a client
    val token :: queryList = args.toList
    val client = UserTokenDiscogsClient(token)
    // Build simple query from term list
    val queryBuilder = new StringBuilder()
    val queryString = queryList.addString(queryBuilder , "&").toString
    // Use the client to send query, parse result
    val rawJs = Json.parse(client.search(queryString))
    val resultsField = rawJs \\ "results"
    // Print the first element from the first page of results
    val firstPage = resultsField.head
    println(Json.prettyPrint(firstPage(0)))
  }
}
