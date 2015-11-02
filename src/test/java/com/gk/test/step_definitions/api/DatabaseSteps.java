package com.gk.test.step_definitions.api;

import com.gk.test.framework.helpers.DatabaseHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class DatabaseSteps extends DatabaseHelper {

    private List results;

    @When("^I run the query to get list of users \"(.*?)\" from mysql database$")
    public void i_run_the_query_to_get_list_of_users_from_mysql_database(String sqlQuery) throws Throwable {
        results = DatabaseHelper.executeQuery(sqlQuery);

    }

    @Then("^the list of users is \"(.*?)\"$")
    public void the_list_of_users_is(String checkResult) throws Throwable {
        assertThat(results.size()).isGreaterThan(0);
    }


}