package me.elrod.GitHub

object Authorization {
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
