package me.elrod.GitHub

import dispatch._
import net.liftweb.json._
import net.liftweb.json.Extraction._
import Http._

/** Convenience fields for interacting with GitHub APIv3. */
object GitHub {
  val http = new Http() with NoLogging
  implicit val formats = net.liftweb.json.DefaultFormats

  def apply(
    username: String,
    password: String,
    scopes: List[String] = Nil,
    note: String = "GitHub API Client for Scala") = {

    val authorization: Authorization.Response = basicAuthentication(
      username,
      password,
      scopes,
      note)
    new GitHub(authorization, scopes, note)
  }

  /** Attempt to obtain an OAuth Authorization.
    *
    * @param username the person's GitHub username
    * @param password the person's GitHub password
    * @param scopes API scopes being requested. To request all possible scopes,
    *        pass [[GitHub.Authorization.AllScopes]].
    * @return a [[GitHub.Authentication.Response]], which contains the response
    *         from GitHub in the form of an object.
    */
  def basicAuthentication(
    username: String,
    password: String,
    scopes: List[String] = Nil,
    note: String = "GitHub API Client for Scala"): Authorization.Response = {

    val data = compact(render(decompose(
      Map(
        "scopes" -> scopes,
        "note"   -> note
      )
    )))

    val request = :/("api.github.com", 443) / "authorizations"
    GitHub.http(request.POST.secure.as_!(username, password) << data >- parse)
      .extract[Authorization.Response]
  }

}

class GitHub(
  val authorization: Authorization.Response,
  val scopes: List[String] = Nil,
  val note: String = "GitHub API Client for Scala") {
}
