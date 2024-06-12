package com.spotify.oauth2.applicationApi;

import com.spotify.oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.API.Route.API;
import static com.spotify.oauth2.API.Route.TOKEN;
import static com.spotify.oauth2.API.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    //static String Access_Token="BQAas9hgMbLS_Qfk58Ietcl1YlfkfnKriBbPwzZjV-wUhN4HJAhuNNFyjsFOBD1DRwLybIgqQfaUMK4ZM6YBQfWdSNQAoIjtv7eOHFNUxtdp3wF3sTdtpPsYWdS1i6js4O5w5a_GdoCdH6g5hY40n7rLVlPJrnjQS0ft2aByhZSHCpVogJKVVfjpiayG8eqzSVPIQP1JDYPRc10LrTjEDhzUwccB2fpRpajKdnTQvtbDaZi1EKBm5o0dq6ZyuKBA-sOFCYFxGyyRs-3p";

    public static Response Post(String path, String token,Object requestPlaylist){

        return given(getRequestSpec())
                .body(requestPlaylist)
                .auth().oauth2(token)
                .when().post(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response PostAccount(HashMap<String,String> formparams) {


        return given(getAccountRequestSpec()).
                formParams(formparams).
                when().post(API + TOKEN).
                then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response Get(String path,String token){

        return given(getRequestSpec())
                .auth().oauth2(token)
                .when().get(path)
                .then().spec(getResponseSpec())
                .extract()
                .response();
    }

    public static Response update(String path, String token,Object requestPlaylist){

        return given(getRequestSpec())
                .auth().oauth2(token)
                .body(requestPlaylist)
                .when().put(path)
                .then().spec(getResponseSpec()).extract()
                .response();

    }


}
