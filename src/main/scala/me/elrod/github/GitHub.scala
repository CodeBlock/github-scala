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
    * @return a [[GitHub.Authorization.Response]], which contains the response
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

/** Interact with GitHub's API v3, using this class.
  *
  * This class gets passed an Authorization.Response object, which might be
  * problematic if you try to create an instance of this class directly.
  *
  * Generally, you want to use the GitHub companion object, and pass it
  * a username and password. That will act as a factory, and give you a valid
  * instance of the GitHub class.
  *
  * @constructor creates an instance of GitHub, but assumes authorization was
  *              already successful.
  * @param authorization a valid [[GitHub.Authorization.Response]] (which
  *                      contains a valid token)
  * @param scopes a list of valid GitHub scopes that are being requested
  * @param note a note that GitHub stores, so the user can recall why the
  *             authorization was created.
  */
class GitHub(
  val authorization: Authorization.Response,
  val scopes: List[String] = Nil,
  val note: String = "GitHub API Client for Scala") {
}
