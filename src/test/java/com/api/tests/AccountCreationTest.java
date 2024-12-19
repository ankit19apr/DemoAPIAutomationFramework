package com.api.tests;

import com.api.base.AuthService;
import com.api.models.requests.SignupRequest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountCreationTest {

    @Test(description = "Verify if Account Creation API is working")
    public void createAccountTest(){
        SignupRequest signupRequest = new SignupRequest.Builder()
                .userName("nidhi123")
                .email("ns25may@gmail.com")
                .password("viraaj123")
                .firstName("Nidhi")
                .lastName("Sharma")
                .mobileNumber("9876543210")
                .build();
        AuthService authService = new AuthService();
        Response response = authService.signUp(signupRequest);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
