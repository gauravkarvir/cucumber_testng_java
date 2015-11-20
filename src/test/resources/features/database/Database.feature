@database
Feature: Sample Database tests

  Scenario: Sample example to query Database and validate results
    When I run the query to get list of users "select * from users" from mysql database
    Then the list of users is "NOT_EMPTY"
