# 24th April 2025
# This validates various alerts
@Alerts
Feature: Validate Various Alerts

  Scenario: TC_01_Validate click Button to see alert
    Given I launch Url
    When I click on Click Button to see alert
    Then I validate You clicked on button alert is present
    And I click ok on pop up
