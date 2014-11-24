Feature: Angebote suchen
  The use case "Angebote suchen" enables the user to search for 
  offers in the vicinity of the place he searched for. By 
  selecting one of the found offers he can then send an inquiry 
  to the publisher of the offer.
  
  As a logged in user
  I want to search for offers by other users
  so that I can send them an inquiry for their offer
  so that I can find offers which are interesting to me

  Scenario: Search for offers by location
    Given I am logged in
    And I am on the start page
    When I enter a location
    Then the location gets marked on the map
    And the available offers for this location get shown

  Scenario: Add or edit additional search criteria
    Given I am logged in
    And I am on the start page
    And offers for a specific location are displayed
    When I enter or change additional search criterias
    Then the map and the list get updated with adjusted results