import org.scalatest.{FunSpec, BeforeAndAfterAll}
import me.elrod.github.tests._
import me.elrod.github._

class AuthenticationSpec extends FunSpec
  with Authentication
  with BeforeAndAfterAll {

    describe("A user, attempting to authenticate") {
      it("can get a valid token back from the API, given valid credentials") {
        val gh = GitHub(username, password)
        assert(gh.authorization.token.length == 40)
      }

      it("can gets a decent error message on authentication failure") {
        val exception = intercept[dispatch.StatusCode] {
          val gh = GitHub("invaliduser", "testing123ABC")
        }
        assert(exception.code == 401)
      }
    }

    it("can list all active authorizations") {
      pending
    }
}
