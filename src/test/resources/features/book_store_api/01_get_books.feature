@BookStore
Feature: Book Store

  @GetAllBooks
  Scenario: Get All Books
    Given set the url for all books
    When send the get request for all books
    Then do assertion for all books
    And there must be 8 books
    And first book must be "Git Pocket Guide"