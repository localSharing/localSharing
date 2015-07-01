Feature: Anfrage beantworten
  The use case "Anfrage beantworten" enables the loggen in User to respond to a received demand on one of his offers.
  
  As a logged in user 
  I want to respond to a demand 
  So that the demanding user can see my response
  
  Scenario: Accept the demand on an offer choosing "Email" as way of contacting
  	Given I am logged in 
  	And I received a demand on one of my offers
  	And I clicked on "Anfragenübersicht"
  	And I clicked on the demand
  	When I click on "Annehmen"
  	And I choose "Email" as way of contacting
  	And I enter a comment
  	And I click on "Senden"
  	Then the accepting is saved
  	And the accepting is displayed to the demanding user
  	And I get directed to "Anfragenübersicht"
  	And the status of the demand is "angenommen"
  	
   Scenario: Accept the demand on an offer choosing "Telefon" as way of contacting
  	Given I am logged in 
  	And I received a demand on one of my offers
  	And I clicked on "Anfragenübersicht"
  	And I clicked on the demand
  	When I click on "Annehmen"
  	And I choose "Telefon" as way of contacting
  	And I enter a comment
  	And I click on "Senden"
  	Then the accepting is saved
  	And the accepting is displayed to the demanding user
  	And I get directed to "Anfragenübersicht"
  	And the status of the demand is "angenommen"
  	
   Scenario: Accept the demand on an offer without a comment
  	Given I am logged in 
  	And I received a demand on one of my offers
  	And I clicked on "Anfragenübersicht"
  	And I clicked on the demand
  	When I click on "Annehmen"
  	And I choose "Email" as way of contacting
  	And I click on "Senden"
  	Then the accepting is saved
  	And the accepting is displayed to the demanding user
  	And I get directed to "Anfragenübersicht"
  	And the status of the demand is "angenommen"
  	
  Scenario: Reject the demand on an offer
  	Given I am logged in
  	And I received a demand n one of my offers
  	And I clicked on "Anfragenübersicht"
  	And I clicked on the demand
  	When I click on "Ablehnen"
  	Then the rejection is saved 
  	And the rejection is displayed to the demanding user
  	And I get directed to "Anfragenübersicht"
  	And the status of the demand is "abgelehnt"
