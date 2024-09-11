@BookStore
Feature: Book Store

  @CreateAccount
  Scenario: Create Account
    Given set the url for account creation
    And set the expected data for account creation
    When send the post request for account creation
    Then do assertion for account creation