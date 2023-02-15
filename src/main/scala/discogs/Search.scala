package discogs

import play.api.libs.json.{JsError, JsSuccess}

import discogs.client.UserTokenDiscogsClient
import discogs.models.SearchResponse


object Search {
  def main(args: Array[String]): Unit = {
    // Pop token from query term arguments, create a client
    val token :: queryList = args.toList
    val client = UserTokenDiscogsClient(token)
    // Build simple query from term list
    val queryBuilder = new StringBuilder()
    val queryString = queryList.addString(queryBuilder , "+").toString
    // Use the client to send query, parse result
    val results = client.search(queryString)

    results match {
      case s: JsSuccess[SearchResponse] =>
        s.value.results.foreach(ele => println(ele.title))
      case e: JsError =>
        println(s"Oops. Something went wrong! Error: \n ${e.toString}")
    }
  }
}
