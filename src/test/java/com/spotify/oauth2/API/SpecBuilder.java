package com.spotify.oauth2.API;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static com.spotify.oauth2.API.Route.BASE_PATH;

public class SpecBuilder {




    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder()
               .setBaseUri(System.getProperty("BASE_URI"))
                //.setBaseUri("https://api.spotify.com")
                .setBasePath(BASE_PATH)
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL).build();



    }
    public static RequestSpecification getAccountRequestSpec(){

        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
               // .setBaseUri("https://accounts.spotify.com")
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.URLENC)
                .log(LogDetail.ALL).build();



    }

    public static ResponseSpecification getResponseSpec(){

        return new ResponseSpecBuilder()
                .log(LogDetail.ALL).build();

    }




}
