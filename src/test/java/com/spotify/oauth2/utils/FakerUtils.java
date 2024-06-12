package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker=new Faker();
        return faker.regexify("/^[a-z][a-z '-.,]{0,31}$|^$/i");
    }


}
