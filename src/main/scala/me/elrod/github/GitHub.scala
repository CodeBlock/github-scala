package me.elrod.github

import dispatch._
import net.liftweb.json._
import net.liftweb.json.Extraction._
import Http._

/** Convenience fields for interacting with GitHub APIv3. */
object GitHub {
  val AllScopes: List[String] = List(
    "user",
    "public_repo",
    "repo",
    "delete_repo",
    "gist"
  )

  val http = new Http() with NoLogging
  implicit val formats = net.liftweb.json.DefaultFormats

  def apply(
    username: String,
    password: String,
    scopes: List[String] = Nil,
    note: String = "GitHub API Client for Scala") = {

    val authorization: GitHub.AuthenticationResponse = GitHub.acquireToken(
      username,
      password,
      scopes,
      note)
    new GitHub(authorization, scopes, note)
  }


  case class Application(
    name: String,
    url: String
  )

  case class AuthenticationResponse(
    id: Int,
    url: String,
    scopes: List[String],
    token: String,
    app: Application,
    note: String,
    note_url: String,
    updated_at: String,
    created_at: String
  )

  /** Attempt to authenticate with GitHub via OAuth.
    *
    * @return a [[GitHub.AuthenticationResponse]], which contains the response
    *         from GitHub in the form of an object.
    */
  def acquireToken(
    username: String,
    password: String,
    scopes: List[String] = Nil,
    note: String = "GitHub API Client for Scala"): AuthenticationResponse = {

    val data = compact(render(decompose(
      Map(
        "scopes" -> scopes,
        "note"   -> note
      )
    )))

    val request = :/("api.github.com", 443) / "authorizations"
    GitHub.http(request.POST.secure.as_!(username, password) << data >- parse)
      .extract[AuthenticationResponse]
  }

}

/** Authenticate to GitHub and provide access to API Version 3.
  *
  * @constructor create a new client to the GitHub API.
  * @param username a valid GitHub username
  * @param password a valid password for the given GitHub username
  * @param scopes the API scopes to be requested. To request all possible
  *               scopes use GitHub.AllScopes
  * @param note a note to add to the authorization, to later remind the user
  *             of why it was created
  */
class GitHub(
  val authorization: GitHub.AuthenticationResponse,
  val scopes: List[String] = Nil,
  val note: String = "GitHub API Client for Scala") {
}
