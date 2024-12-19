package com.api.tests;

import static io.restassured.RestAssured.*;

import com.api.base.AuthService;
import com.api.models.requests.LoginRequest;
import com.api.models.response.LoginResponse;
import com.beust.ah.A;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.api.listeners.TestListener.class})
public class LoginApiTest {

    @Test (description = "Verify if login API is working..")
    public void loginTest(){
// Way1
       // RestAssured.baseURI = "http://64.227.160.186:8080";
        //baseURI = "http://64.227.160.186:8080";
//        RequestSpecification requestSpecification = RestAssured.given();
//        RequestSpecification requestHeader = requestSpecification.header("Content-Type","application/json");
//        RequestSpecification requestBody = requestHeader.body("{\"username\": \"uday1234\", \"password\": \"uday1234\"}");
//        Response response = requestBody.post("/api/auth/login");
//        System.out.println(response.asPrettyString());
        //Response response = RestAssured.given()

//Way2
//        Response response =  given()
//                            .baseUri("http://64.227.160.186:8080")
//                            .header("Content-Type","application/json")
//                            .body("{\"username\": \"uday1234\", \"password\": \"uday1234\"}")
//                            .post("/api/auth/login");

//Way3
        LoginRequest loginRequest = new LoginRequest("uday1234","uday1234");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);

        //Deserialize
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());
        System.out.println(loginResponse.getToken());
        System.out.println(loginResponse.getEmail());
        System.out.println(loginResponse.getId());
        System.out.println(loginResponse.getType());
        System.out.println(loginResponse.getUsername());
        System.out.println(loginResponse.getRoles());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
