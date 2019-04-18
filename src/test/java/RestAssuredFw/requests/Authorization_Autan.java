package RestAssuredFw.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Authorization_Autan {
  //  Authenticate: who are you?
   // Authorization: are you allowed?

    //positive Authentication test
  @Test
  public void basicAuthentication(){
      // auth --> provides different types of authentication
      // based --> authentication using username and password
      RestAssured.given().
              auth().basic("admin", "admin").
              when().get("https://the-internet.herokuapp.com/basic_auth").
              then().assertThat().statusCode(200);
  }

   // negative Authentication test
    @Test
    public void basicAuthentication401Validation(){
        // https://the-internet.herokuapp.com/basic_auth
        // when we try to hit and endpoint without being authorised
        // we can 401 not authorized error
        RestAssured.get("https://the-internet.herokuapp.com/basic_auth").
                then().assertThat().statusCode(401);
    }





//authorization: get the access token, then hit the request with AccessToken in header param,
    @Test
    public void getTokenTest() {
        RestAssured.baseURI = "https://cybertek-reservation-api-qa.herokuapp.com/";
        Response response = given().log().all().
                param("email", "teacherva5@gmail.com").
                param("password", "maxpayne").
                get("/sign");
        response.then().log().all().
                assertThat().statusCode(200);

        // used to parse through json response easily

        String accessToken = response.jsonPath().get("accessToken");

        System.out.println(accessToken);

        // trying to get the my campus
        // we are passing our token as a part of the request header
        RestAssured.given().
                header("Authorization", accessToken).
                get("/api/campuses").then().log().all().
                assertThat().statusCode(200);

    }
}
