package RestAssuredFw.requests;


import RestAssuredFw.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class responseExample {
    //Given – prepare our request/precondition
    //
    //When – send the request, when it is prepared in Given
    //
    //Then – verify theresponse


    //use json to read the value of certain attribute

    @Test
    public void jsonPath() {
        Response response= get("https://uinames.com/api/");
        Assert.assertEquals(response.statusCode(),200);

        JsonPath jp =  response.jsonPath();
        String regions =  jp.get("region");
        System.out.println(regions);


        String regionName = RestAssured.get("https://uinames.com/api/").jsonPath().get("region");
        System.out.println(regionName);

    }

    //query param is to sort or specify the resource/how you want to get
    @Test
    public void verifyNumberOfResult(){
        given().queryParam("amount",3).when().get("https://uinames.com/api/").prettyPrint();
    }

    @Test
    public void testRegion(){
        given().queryParam("region", "United States").when().get("https://uinames.com/api/").
                prettyPrint();
    }






    //pathparam-- is used to match a part of the url, with parameter
    // specify what you want to get
    @Test
    public void userTest(){

        Response response= get("https://api.github.com");
        given().pathParam("username", "shayidaAli").when()
                .get("/users/{username}").
                prettyPrint();

//        //   given().pathParam("username","shayidaAli").when().get("/users/{username}").prettyPrint();
    }



    //we can create list with the response
    @Test
    public void responseListOfObjects() {
        Response response = given().
                queryParam("amount", "9").
                when().get("https://uinames.com/api/");

        List<Person> people = response.jsonPath().getList("", Person.class);

        for (Person person : people) {
            System.out.println(person);
        }

    }





}
