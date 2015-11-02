#Sample test scenario which gives an example of API or Service level testing

@api
Feature: Sample Api & Database tests

  Scenario: Sample example to query Restful service and get colour details from API
    When I perform GET request for "/en/api/products/colors" endpoint
    Then I get a 200 http status code
    And the colour collections contains colour name

#  Scenario: Sample example to query Database and validate results
#    When I run the query to get list of users "select * from users" from mysql database
#    Then the list of users is "NOT_EMPTY"
