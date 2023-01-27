#created on 26th JAN 2023



@OrangeHRM
Feature: I want to test Background keyword
  
Background: 
Scenario: Launch browser and URL
Given I launch Browsre and URL


 @Test
 Scenario Outline: Scenario Outline
 
   Then I enter "<Username>" and "<Password>"
   
 Examples:
 
     | Username | Password  |
     | India    | Gabn&6!   |
     | Canada   | Bhjklh9*  |
   
