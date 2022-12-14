
Feature: As a data consumer, I want UI and DB book categories are match.

  @Regression @wip1 @db
  Scenario: verify book categories with DB
  Given I login as a librarian
  When I navigate to "Books" page
  And I take all book categories in UI
  And I execute query to get book categories
  Then verify book categories must match book_categories table from db
