import org.scalatest.{FunSpec, BeforeAndAfterAll}
import me.elrod.github.tests._
import me.elrod.github._

class AuthenticationSpec extends FunSpec
  with Authentication
  with BeforeAndAfterAll {

    describe("A user, attempting to authenticate") {
      it("can get a valid token back from the API, given valid credentials") {
        val gh = new GitHub(username, password)
        val authentication = gh.acquireToken()
        assert(authentication.token.length == 40)
      }

      it("can gets a decent error message on authentication failure") {
        val gh = new GitHub("invaliduser", "testing123ABC")
        val exception = intercept[dispatch.StatusCode] {
          val authentication = gh.acquireToken()
        }
        assert(exception.code == 401)
      }
    }
}
