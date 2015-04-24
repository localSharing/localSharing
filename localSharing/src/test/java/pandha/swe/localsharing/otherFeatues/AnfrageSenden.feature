Feature: Anfrage an User senden
	The use case "Anfrage an User senden" enables the logged in User to demand an offer
	
	As a logged in user
	I want to send a demand 
	So that the offering user can see my demand
	
	Scenario: Demand a service offer from another user
		Given I am logged in
		And I clicked on an offer
		When I click on "Anfragen" 
		And I enter a date
		And I enter a comment
		And I click on "Senden"
		Then the demand is checked 
		And the demand is saved
		And the demand is displayed to the offering user
		And I get directed to the offer 
		And the demand is displayed in the "Anfragenübersicht"
	
	Scenario: Demand a borrow offer from another user
		Given I am logged in
		And I clicked on an offer
		When I click on "Anfragen" 
		And I enter a date
		And I enter a comment
		And I click on "Senden"
		Then the demand is checked 
		And the demand is saved
		And the demand is displayed to the offering user
		And I get directed to the offer 
		And the demand is displayed in the "Anfragenübersicht"
		
	Scenario: Demand an exchange offer from another user
		Given I am logged in
		And I clicked on an exchange offer
		When I click on "Anfragen" 
		And I enter a startdate
		And I enter an enddate
		And I enter a comment
		And I click on "Senden"
		Then the demand is checked 
		And the demand is saved
		And the demand is displayed to the offering user
		And I get directed to the offer 
		And the demand is displayed in the "Anfragenübersicht"
		
	Scenario: Demand an offer I already demanded 
		Given I am logged in
		And I clicked on an offer
		And I already demanded the offer
		When I click on "Anfragen" 
		Then the information about the last demand is displayed
	