#Sample test scenario which gives an example of API or Service level testing

@api
Feature: Sample Api tests to demonstrate CRUD operation


  Scenario: Get Details
    When I perform GET request for "/posts/1" endpoint
    Then I get a 200 http status code

  Scenario: Add an Item
    When I add an Item
      | userId | id | title  | body |
      | 1      | 1  | animal | cat  |
    Then the Item is "created"

  Scenario: Update an Item
    When I update an Item
      | userId | id | title  | body |
      | 1      | 1  | animal | dog  |

    Then the Item is "updated"


  Scenario: Delete an Item
    When I delete an Item "1"
    Then the Item is "deleted"

