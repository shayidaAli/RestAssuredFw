package RestAssuredFw.requests;

import RestAssuredFw.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class verify {
    // Hamcrest library provides matchers (assertion method)
    // Rest Assured uses hamcrest assertions (matchers)
    // works with both junit and testng
    @Test
    public void test() {
        //example:
        assertThat(1, equalTo(1));
        String str1 = "Kunkka";
        String str2 = "Kunkka";
        // verify if first argument is equal to the second
        assertThat(str1, is("Kunkka"));
        assertThat(str1, is(str2));

        //t: verify content type
        Response response = RestAssured.get("https://uinames.com/api/");
        response.getHeaders();
        String contentType = response.header("Content-Type");
        assertThat(contentType,is("application/json; charset=utf-8"));


         //t3: verify list size:
        given().queryParam("amount", "9").
       when().get("https://uinames.com/api/");
       List<Person> people = response.jsonPath().getList("", Person.class);
       assertThat(people, Matchers.<Person>hasSize(9));

    }


    //using jsonpath to read certain attribute
    @Test
    public void jsonPath() {
        Response response= get("https://uinames.com/api/");
        Assert.assertEquals(response.statusCode(),200);
        JsonPath jp =response.jsonPath();
        String regions = jp.get("region");
        System.out.println(regions);
    }


    //using pojo, with de- serilization
    @Test
    public void test2() throws IOException {
        Response response = given().get("https://uinames.com/api/");
//        String name = response.path("name");
//        System.out.println("name = " + name);
        // USING REST ASSURED

        // values from the json response is automatically mapped to correspoonding fields
        // in the person object
        Person p1 = response.as(Person.class);
        System.out.println("p1.getName() = " + p1.getName());
        System.out.println("p1.getSurname() = " + p1.getSurname());
        System.out.println("p1.getGender() = " + p1.getGender());
        System.out.println("p1.getRegion() = " + p1.getRegion());


        //now, we are doing deserilization
        // USING GSON read json w java object
        Gson gson = new Gson();
        Person p2 = gson.fromJson(response.asString(), Person.class);
        System.out.println("p2 = " + p2);



        // USING JACKSON DATABIND
        ObjectMapper objectMapper = new ObjectMapper();
        Person p3 = objectMapper.readValue(response.asString(), Person.class);
        System.out.println("p3 = " + p3);

    }
//verify header
    @Test
    public void headerTest(){
        given().
                when().get().
                then().log().headers().
                header("Content-Type",
                        "application/json; charset=utf-8");

        // getting the value of the header given in first parameter
        // and verifies against the value in the second parameter
    }





}
