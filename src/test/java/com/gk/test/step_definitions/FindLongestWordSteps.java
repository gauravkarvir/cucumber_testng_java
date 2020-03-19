package com.gk.test.step_definitions;

import com.gk.test.framework.helpers.utils.StringUtilsHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FindLongestWordSteps {
    private static final Logger LOG = LoggerFactory.getLogger(FindLongestWordSteps.class);
    private List<String> wordsFound;
    private String sentence;
    private String validationMessage;

    @Given("I have {string}")
    public void ihaveSentence(String sentenceValue) {
        this.sentence = sentenceValue;
    }

    @When("I search for {string} word from the sentence")
    public void iSearchForWordFromTheSentence(String longOrShort) {
        if (this.sentence.isEmpty()) {
            this.validationMessage = "Please provide valid sentence or word";
        } else {
            this.wordsFound = StringUtilsHelper.findTheLongOrShortWord(sentence, longOrShort);
            this.validationMessage = "Word Found";
        }
    }

    @Then("I am able to find {string} word with its length")
    public void iAmAbleToFindTheWordWithItsLength(String longOrShort) {
        String wordFound = this.wordsFound.get(0);
        System.out.println(("The " + longOrShort + " word is '" + wordFound + "' and its length is " + wordFound.length()));
    }


    @Then("I get message {string}")
    public void iGetMessage(String errorMessage) {
        assertThat(this.validationMessage).contains(errorMessage);
    }
}
