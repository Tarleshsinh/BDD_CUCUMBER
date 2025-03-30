@Tab_Switch
Feature: Open new tabs and validate elements on the W3Schools website

  Scenario: Open new tabs and validate elements
    Given I open the W3Schools main site
    When I simulate middle-click on the "Try it Yourself" links
    Then I switch to each new tab and validate elements
    And I close all the tabs except the main one
