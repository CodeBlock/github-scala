package me.elrod.GitHub.tests

trait Authentication {
      val username = scala.util.Properties.envOrNone("GITHUB_USERNAME") match {
      case Some(u) => u
      case None => throw new IllegalArgumentException(
        "You must set GITHUB_USERNAME to a valid username to run the tests.")
    }

    val password = scala.util.Properties.envOrNone("GITHUB_PASSWORD") match {
      case Some(p) => p
      case None => throw new IllegalArgumentException(
        "You must set GITHUB_PASSWORD to a valid password to run the tests.")
    }
}
