Feature: BDDs for sb-jpa-tdd-bdd-h2

    Scenario: Create Author record
      Given create author request prepared
      When I place request for create author
      Then author is created