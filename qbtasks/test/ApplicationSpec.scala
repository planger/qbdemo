import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.Json
import play.api.libs.json.JsString

/**
 * Tests the application's RESTful API.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "RESTful API" should {

    "reply with BAD_REQUEST on posting user with invalid email" in new WithApplication {
      val request = FakeRequest(POST, "/users").withJsonBody(userWithInvalidEmail)
      val response = route(request).get
      status(response) must equalTo(BAD_REQUEST)
    }
    
    "reply with BAD_REQUEST on posting user with missing email" in new WithApplication {
      val request = FakeRequest(POST, "/users").withJsonBody(userWithMissingEmail)
      val response = route(request).get
      status(response) must equalTo(BAD_REQUEST)
    }
    
    "reply with BAD_REQUEST on posting user with invalid status" in new WithApplication {
      val request = FakeRequest(POST, "/users").withJsonBody(userWithInvalidStatus)
      val response = route(request).get
      status(response) must equalTo(BAD_REQUEST)
    }

    "reply with OK on posting valid user" in new WithApplication {
      val request = FakeRequest(POST, "/users").withJsonBody(validUser)
      val response = route(request).get
      status(response) must equalTo(OK)
    }
    
    "reply with stored user on posting valid user" in new WithApplication {
      val request = FakeRequest(POST, "/users").withJsonBody(validUser)
      val response = route(request).get

      val content = contentAsJson(response)
      content \ "name" must equalTo(JsString("Bart Simpson"))
      content \ "status" must equalTo(JsString("ACTIVE"))
      content \ "email" must equalTo(JsString("bart@simpson.com"))
    }

    "reply with added user on getting all users after adding one" in new WithApplication {
      val postRequest = FakeRequest(POST, "/users").withJsonBody(validUser)
      val postResponse = route(postRequest).get
      status(postResponse) must equalTo(OK)
      
      val postResponseContent = contentAsJson(postResponse)
      val id = postResponseContent \ "id"
      
      val getRequest = FakeRequest(GET, "/users").withJsonBody(validUser)
      val getResponse = route(getRequest).get
      status(getResponse) must equalTo(OK)
      
      val getResponseContent = contentAsJson(getResponse)
      val lastIdInDb = (getResponseContent \\ "id").last
      lastIdInDb must equalTo(id)
    }

  }

  private val validUser = Json.parse("""
      {
		  "name": "Bart Simpson",
		  "status": "ACTIVE",
		  "email": "bart@simpson.com"
      }
  """)

  private val userWithInvalidEmail = Json.parse("""
      {
		  "name": "Bart Simpson",
		  "status": "ACTIVE",
		  "email": "wrong"
      }
  """)
  
  private val userWithMissingEmail = Json.parse("""
      {
		  "name": "Bart Simpson",
		  "status": "ACTIVE"
      }
  """)
  
  private val userWithInvalidStatus = Json.parse("""
      {
		  "name": "Bart Simpson",
		  "status": "WRONG",
		  "email": "bart@simpson.com"
      }
  """)

}
