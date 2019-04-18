package RestAssuredFw.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;

public class getData {
    //  basic request
    @Test
    public void testResponseCode1() {
        // hit this endpoint/send request on next line/ making order
        // get("https://uinames.com/api/");

        // response object will store whatever the response we got from the server

        Response response = RestAssured.get("https://uinames.com/api/");
        }


        // getting the Status Code
        @Test
        public void testStatusCode() {

//            Response response = RestAssured.get("https://uinames.com/api/");
//
//            int code = response.getStatusCode();
//            System.out.println("here is my status code  " + code);
//            //verifing with AssertEqual
//            Assert.assertEquals(code, 200);

              int code=  RestAssured.get("https://uinames.com/api/").getStatusCode();
              Assert.assertEquals(code, 200);
        }


        // getting response body
        @Test
        public void testBody() {

            Response response = get("https://uinames.com/api/");
            //first option:
            String data = response.asString();
            System.out.println("data is  " + data);

//            String rt= RestAssured.get("https://uinames.com/api/").asString();
//            System.out.println(rt);

            //second option
            RestAssured.get("https://uinames.com/api/").then().log().body();
        }


        //getting response time
        @Test
        public void testTime() {
//            Response response = get("https://uinames.com/api/");
//            long time = response.getTime();

           Long time=  RestAssured.get("https://uinames.com/api/").getTime();
            System.out.println("response time is " + time);
        }


        // getting everything include status, body, header..using log()
        @Test
         public void testAll() {
            get("https://uinames.com/api/").then().log().all();
        }



         //getting header
         //header -- is the term to describe the additioal information about response/request. (metadata)
         //in the header, there are: dates, server, Content-type..
          @Test
          public void getheader(){
                Response response = get("https://uinames.com/api/");
                String a =response.getHeaders().toString();
                System.out.printf(a);

              //first option
              System.out.println(response.getHeaders());

              //second option
              response.then().log().headers();

        }



        @Test
        public void testResponseCode2() {
        //hit the api, get the status code
        int StatusCode= get("https://uinames.com/api/").getStatusCode();
        String data= get("https://uinames.com/api/").asString();
        long resTime= get("https://uinames.com/api/").getTime();
        System.out.println(StatusCode+"/"+ data + "/"+ resTime);
    }
}
