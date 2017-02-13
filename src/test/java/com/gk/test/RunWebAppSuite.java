package com.gk.test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "target/test-classes", tags = {"@mobile"}, monochrome = true, plugin = {
        "pretty", "html:target/cucumber-report/webapp",
        "json:target/cucumber-report/webapp/cucumber.json",
        "rerun:target/cucumber-report/webapp/rerun.txt"},
        glue = "com.gk.test.step_definitions.webapp")
public class RunWebAppSuite extends AbstractTestNGCucumberTests {

}
