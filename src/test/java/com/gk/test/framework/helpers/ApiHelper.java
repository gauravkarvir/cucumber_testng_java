package com.gk.test.framework.helpers;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import static com.jayway.restassured.RestAssured.given;

/**
 * Every Api Step definition class should extend this class
 */

public class ApiHelper {
    private static Gson gson;

    static {
        RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
    }

    protected static RequestSpecification givenConfig() {
        RestAssured.useRelaxedHTTPSValidation();
        return given().
                header("Accept-Language", "en").header("Content-Type", "application/json");
    }

    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }


}