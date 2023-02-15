package discogs.models

case class DiscogsResult(`type`: String, title: String, id: Int)
case class PaginationUrl(last: String, next: String)
case class Pagination(page: Int, pages: Int, items: Int, urls: PaginationUrl)
case class SearchResponse(pagination: Pagination, results: Seq[DiscogsResult])
