package com.api.tests;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.requests.LoginRequest;
import com.api.models.requests.ProfileRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateProfileTest {

    @Test(description = "Verify if update profile API is working..")
    public void updateProfileInfo() {

        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("vd102519", "viraaj123"));
        LoginResponse loginResponse = response.as(LoginResponse.class);
        System.out.println(response.asPrettyString());

        System.out.println("--------------------------------------------------");

        UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
        response = userProfileManagementService.getProfile(loginResponse.getToken());
        System.out.println(response.asPrettyString());

        UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
        Assert.assertEquals(userProfileResponse.getUsername(), "vd102519");

        System.out.println("--------------------------------------------------");

        ProfileRequest profileRequest = new ProfileRequest.Builder()
                .firstName("ajay")
                .lastName("sharma")
                .email("abc1234@gmail.com")
                .mobileNumber("7313196202")
                .build();

        response = userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
        System.out.println(response.asPrettyString());



    }
}
