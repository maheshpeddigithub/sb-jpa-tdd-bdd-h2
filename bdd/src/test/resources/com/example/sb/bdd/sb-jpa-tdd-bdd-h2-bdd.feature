Feature: BDDs for sb-jpa-tdd-bdd-h2

  Background: User authorized
    Given I am an authorized user

    Scenario: Authorized user can create update delete Author
      Given list of authors available
      When I add an author
      Then Author is added
      When I updated Author
      Then Author is updated
      When I delete Author
      Then Author is deleted