@database
Feature: Sample Database tests for DB2 Database

  Scenario: Simple example to query Database and validate results with Record Set
    When I run query "query.getUserReg" to get list of users in record set
    Then the list of users is not empty

  Scenario: Example to query Database and validate results using Java Bean
    When I run query "query.getUserReg" to get list of users in bean
    Then the list of users contains "wcsadmin" as a user
