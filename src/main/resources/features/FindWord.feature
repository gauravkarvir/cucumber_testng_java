Feature: Find longest and shortest word from sentence

  #Assumptions
  #Sentence is not blank
  #Result contains only 1  Value returned ,if there are many words with same length and same value then only first value is returned

  @single @findWord
  Scenario Outline: Positive Test to find longest and shortest word from the sentence
    Given I have "<sentence>"
    When I search for "LONG" word from the sentence
    Then I am able to find "LONG" word with its length

    When I search for "SHORT" word from the sentence
    Then I am able to find "SHORT" word with its length

    Examples:
      | sentence                      |
      | The cow jumped over the moon. |
      | Dinosaur jumped over the sun. |


