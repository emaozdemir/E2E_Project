@BookStore
Feature: Book Store

  @AssignBook
  Scenario: Assign Book To Account
    Given set the url for book assign
    And set the expected data for book assign
    When send the post request for book assign
    Then do assertion for book assign