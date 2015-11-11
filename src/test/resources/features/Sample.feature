Feature: Step Definition different types of  Data injection usage

  Scenario: Add data to Step Definition via Map
    Given i add data to Step Definition via List
      | title   |
      | process |
      | aspects |
    When I sort the list using comparator
    Then the list is sorted
      | aspects |
      | process |
      | title   |


    Given i add data to Step Definition via Map
      | title   | qa         |
      | process | automation |

    Given i add data to Step Definition via DataTable and DataTable list of Strings
      | title   | qa         |
      | process | automation |

    Given i add multi column data to Step Definition via List of Model
      | title      | process    | location |
      | tester     | manual     | Global   |
      | qa manager | management | Paris    |
      | automator  | automation | UK       |

    When I sort the multi column data using comparator
    Then the multi column data is sorted by title

    Given i add multi column data to Step Definition via Transposed List of Model
      | title    | qa         | qa manager |
      | process  | automation | management |
      | location | UK         | Global     |


