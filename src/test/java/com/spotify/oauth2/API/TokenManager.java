package com.spotify.oauth2.API;

import com.spotify.oauth2.applicationApi.RestResource;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.API.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken(){
try {
    if ((access_token == null || Instant.now().isAfter(expiry_time))) {
        System.out.println("Renewing the token..");
        Response response = renewToken();
        access_token = response.path("access_token");
        int expiryDurationInSeconds = response.path("expires_in");
        expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
    } else {
        System.out.println("Token is good to use");

    }
    }catch(Exception e){
    throw new RuntimeException("ABORT!!! Failed to get Token");
        }
return access_token;
    }

    private static Response renewToken(){

        HashMap<String,String> formparams=new HashMap<>();
        formparams.put("client_id", ConfigLoader.getInstance().getClientId());
        formparams.put("client_secret",ConfigLoader.getInstance().getClientSecret());
        formparams.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formparams.put("grant_type",ConfigLoader.getInstance().getGrantType());

        Response response= RestResource.PostAccount(formparams);

        if(response.statusCode()!=200){
            throw new RuntimeException("ABORT!!! Renew Token failed");
        }

        return response;


    }
}