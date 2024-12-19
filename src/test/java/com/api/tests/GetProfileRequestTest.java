package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.requests.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class GetProfileRequestTest {

    @Test(description ="Verify if Get profile API is working")
    public void getProfileInfoTest(){

        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("vd102519","viraaj123"));
        LoginResponse loginResponse = response.as(LoginResponse.class); //Deserialization
        System.out.println(loginResponse.getToken());


        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response = userProfileManagementService.getProfile(loginResponse.getToken());
        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        System.out.println(userProfileResponse.getUsername());
    }
}
