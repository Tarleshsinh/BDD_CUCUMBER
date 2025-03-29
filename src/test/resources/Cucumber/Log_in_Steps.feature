# Author: Tarlesh Parmar
# Date: JAN 2025

@Log_In_Steps
Feature: User Login Validation
  I want to validate login functionality using valid and invalid credentials

  @practice1 @testid-12345
  Scenario Outline: Validate login with <username> and <password>
    Given I open the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see the message "<expectedMessage>"

    Examples:
      | username | password     | expectedMessage                     |
      | student  | Password123  | Logged In Successfully              |
      | student  | wrongpass    | Your password is invalid!           |
      | wrong    | Password123  | Your username is invalid!          |
