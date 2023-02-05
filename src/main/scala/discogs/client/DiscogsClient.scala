package discogs.client

class DiscogsClient(private val token: String) {}

object DiscogsClient {
  def apply(token: String): DiscogsClient = new DiscogsClient(token)
}