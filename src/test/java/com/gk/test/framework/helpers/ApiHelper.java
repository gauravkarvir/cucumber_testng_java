package com.gk.test.framework.helpers;


import com.jayway.restassured.response.Header;

/**
 * Every Api Step definition class should extend this class
 */

public class ApiHelper {
    private static final String APPLICATION_JSON = "application/json";
    private static final String WRAPPED_JSON = "application/vnd.rhq.wrapped+json";
    private static final String APPLICATION_XML = "application/xml";
    private static final String TEXT_CSV = "text/csv";
    private static final String TEXT_HTML = "text/html";
    private static final String REST_TEST_DUMMY = "-rest-test-dummy-";
    private static Header acceptJson = new Header("Accept", APPLICATION_JSON);
    private static Header acceptXml = new Header("Accept", APPLICATION_XML);
    private static Header acceptHtml = new Header("Accept", TEXT_HTML);
    private static Header acceptCsv = new Header("Accept", TEXT_CSV);
    private static Header acceptWrappedJson = new Header("Accept", WRAPPED_JSON);
}