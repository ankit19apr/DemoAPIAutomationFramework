package com.api.base;

import com.api.models.requests.LoginRequest;
import com.api.models.requests.SignupRequest;
import io.restassured.response.Response;

import java.util.HashMap;

public class AuthService extends BaseService{

    private static final String BASE_PATH = "/api/auth/";

    public Response login(LoginRequest body){
        return postRequest(body, BASE_PATH + "login");
    }

    public Response signUp(SignupRequest body){
        return postRequest(body, BASE_PATH + "signup");

    }



    public Response forgotPassword(String emailAddress){
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("email",emailAddress);

        return postRequest(body,BASE_PATH + "forgot-password");
    }

}
