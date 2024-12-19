package com.api.base;

import com.api.filters.LoggingFilter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {
    //wrapper for RestAssured, because this is where abstraction starts
    // to come in picture


    //This class is responsible for handling base URI
    //Creating the Request
    // Handling the Response
    private static final String BASE_URL = "http://64.227.160.186:8080";
    private RequestSpecification requestSpecification;

    static {
        RestAssured.filters(new LoggingFilter());

    }

    //constructor
    public BaseService() {
      requestSpecification = RestAssured.given().baseUri(BASE_URL);
    }
    protected void setAuthToken(String token){
         requestSpecification.header("Authorization", "Bearer " + token);


    }
    protected Response getRequest(String endPoint){
        return requestSpecification.get(endPoint);


    }
    protected Response postRequest(Object body, String endPoint){
       return requestSpecification.contentType(ContentType.JSON).body(body).post(endPoint);


    }
    protected Response putRequest(Object body, String endPoint){
        return requestSpecification.contentType(ContentType.JSON).body(body).put(endPoint);


    }
}
