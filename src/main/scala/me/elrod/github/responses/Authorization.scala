package me.elrod.GitHub

/** Helpers for GitHub authorization.
  *
  * This object contains things global to the authorization process.
  */
object Authorization {

  /** All valid scopes for the API. */
  val AllScopes: List[String] = List(
    "user",
    "public_repo",
    "repo",
    "delete_repo",
    "gist"
  )

  case class Application(
    name: String,
    url: String
  )

  /** A response from the API. */
  case class Response(
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
}
