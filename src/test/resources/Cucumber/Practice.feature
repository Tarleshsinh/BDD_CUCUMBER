#Author: Tarlesh Parmar
#JAN 2025
@Practice
Feature: Title of your feature
  I want to use this template for my feature file

  @practice1 @testid-12345
  Scenario: Title of your scenario
     Given I launch url 
    When I eneter <username> 
    Then I enter <password> 
    And I click on login button 
    Then I validate "successmessage" 

    Examples: 
      | Username name | Password |
      | name1         | success  |
      | name2         | Fail     |



   