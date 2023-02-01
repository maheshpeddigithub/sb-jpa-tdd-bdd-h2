Feature: BDDs for sb-jpa-tdd-bdd-h2

  Scenario: Create Author record
    Given create author request received
    When I place request for create author
    Then author is created

  Scenario Outline: Get Author details
    Given get author details request received
    When I place request for get author details
    Then author details with name "<name>" with response code "<code>" are returned
    Examples:
      | name | code |
      | n1   | 200  |

  Scenario Outline: Update Author details
    Given update author details request received
    When I place request to update author name to "<name>"
    Then author name updated to "<name>" and response code "<code>"
    Examples:
      | name | code |
      | n11  | 202  |

  Scenario Outline: Delete Author
    Given delete author request received
    When I place request for delete author
    Then author is deleted and status code "<code>" returned
    Examples:
      | code |
      | 202  |