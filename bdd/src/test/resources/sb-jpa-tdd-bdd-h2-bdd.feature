Feature: BDDs for sb-jpa-tdd-bdd-h2

  Scenario: Create Author record
    Given create author request received
    When I place request for create author
    Then author is created

  Scenario: Get Author details
    Given get author details request received
    When I place request for get author details
    Then author details are retrieved

  Scenario: Update Author details
    Given update author details request received
    When I place request for update author details
    Then author details are updated

  Scenario: Delete Author
    Given delete author request received
    When I place request for delete author
    Then author is deleted