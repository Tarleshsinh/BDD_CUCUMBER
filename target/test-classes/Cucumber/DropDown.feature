#Author: your.email@your.domain.com
#Keywords Summary :

@Dropdown
Feature: Dropdown options 
  

  @Amazon_Auto_Search
  Scenario: Amazon Auto search
    Given when i launch chrome browser
    Then I enter URL
    And enter credential in Amazon serach bar "Recliner for kids"
    Then Recliner for kids gets displayed 
    

  @Googlesearch
  Scenario: Autosuggestion_Google
    Given I launch chrome and launch google url
    When I enter switzerland Package
    Then switzerland package option shows in google

  
