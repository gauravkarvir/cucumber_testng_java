#Sample test scenario which gives an example of API or Service level testing

@api
Feature: Sample Api tests to demonstrate CRUD operation


  Scenario: Sample  to query Restful service and get colour details from API
    When I perform GET request for "/en/api/products/colors" endpoint
    Then I get a 200 http status code
    And the colour collections contains colour name

# Dummy examples will not run as there is not real Api to test
  @dummy
  Scenario: Create an Item
    When I create an Item
      | itemType | itemName |
      | tablet   | ipad     |
      | mobile   | iphone   |

    Then the Item is "created"

  @dummy
  Scenario: Update an Item
    When I update an Item
      | itemType | itemName |
      | tablet   | tab pro  |
      | mobile   | note 5   |
    Then the Item is "updated"

  @dummy
  Scenario: Delete an Item
    When I delete an Item "tablet"
    Then the Item is "deleted"

