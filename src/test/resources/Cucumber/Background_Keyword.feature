
@background_Keyword
Feature: Login functionality using Background keyword

  # This is the background section where the steps will be common for all scenarios
  Background: 
    Given I am on the Sauce Demo login page

  # Scenario 1: Valid login with correct credentials
  Scenario: Valid login with correct credentials
    Given I enter valid username and password
    When I click on the login button
    Then I should see the homepage elements
    And I should see my username displayed

  # Scenario 2: Invalid login with incorrect credentials
  Scenario: Invalid login with incorrect credentials
    Given I enter invalid username and password
    When I click on the login button
    Then I should see an error message "Invalid credentials"

  # Scenario 3: Empty login fields
  Scenario: Empty login fields
    Given I enter empty username and password
    When I click on the login button
    Then I should see an error message "Username and password cannot be empty"
