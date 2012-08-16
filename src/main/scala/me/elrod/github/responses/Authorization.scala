package me.elrod.github

object Authorization {
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
