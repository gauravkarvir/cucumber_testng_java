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
    static {
        RestAssured.baseURI = UrlBuilder.getBasePathURI().toString();
    }

    protected static RequestSpecification givenConfig() {
        RestAssured.useRelaxedHTTPSValidation();
        return given().
                header("Accept-Language", "en").header("Content-Type", "application/json");
    }

    public static Gson gson() {
        return new GsonBuilder().create();


    }


}