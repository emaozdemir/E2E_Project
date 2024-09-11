@BookStore
Feature: Book Store

  @AccountInfo
  Scenario: Get Account Info
    Given set the url for account info
    When send the post request for account info
    Then do assertion for account info