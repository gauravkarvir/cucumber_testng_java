package com.gk.test.step_definitions.api;

import com.gk.test.framework.helpers.Props;
import com.gk.test.models.database.UserRegModel;
import com.gk.test.sql.UserRegDB;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */
public class DatabaseSteps {
    private UserRegDB userRegDB;
    private List results;
    private List<UserRegModel> userRegModels;

    public DatabaseSteps(UserRegDB userRegDB) {
        this.userRegDB = userRegDB;
    }

    @When("^I run query \"([^\"]*)\" to get list of users in record set$")
    public void I_run_query_to_get_list_of_users_in_record_set(String sqlQuery) throws Throwable {
        results = userRegDB.executeQuery(Props.getMessage(sqlQuery));
    }

    @When("^I run query \"([^\"]*)\" to get list of users in bean$")
    public void I_run_query_to_get_list_of_users_in_bean(String sqlQuery) throws Throwable {
        userRegModels = userRegDB.getUserRegResults(Props.getMessage(sqlQuery));
    }

    @Then("^the list of users is not empty$")
    public void the_list_of_users_is_not_empty() throws Throwable {
        assertThat(results.size()).isGreaterThan(0);
    }

    @Then("^the list of users contains \"([^\"]*)\" as a user$")
    public void the_list_of_users_contains_as_a_user(String userName) throws Throwable {
        List<UserRegModel> userRegModels = this.userRegModels;
        Optional<Boolean> user = userRegModels.stream().map(userRegMode -> userRegMode.getLogonId().contains(userName)).findFirst();
        assertThat(user.isPresent()).isTrue();
    }
}