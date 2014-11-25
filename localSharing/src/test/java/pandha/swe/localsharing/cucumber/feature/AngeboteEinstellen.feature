Feature: Angebote einstellen
  The use case "Angebote einstellen" enables the user to create
  his own offers which can then be inquired by other users.
  
  As a logged in user
  I want to create offers
  So that other users can find and inquire after them

  Scenario: Create offer for an item to be borrowed
    Given I am logged in
    And I clicked on "Eigene Angbote"
    And I clicked on "Neues Angebot"
    When I enter a title
    And I set the category to "Gegenstand"
    And I set the switch to "Ausleihen"
    And I enter a start date
    And I enter an end date
    And I enter a duration
    And I enter a description
    And I click on "Speichern"
    Then the offer gets saved
    And I get directed to "Eigene Angebote"

  Scenario: Create offer for an item to be exchanged
    Given I am logged in
    And I clicked on "Eigene Angbote"
    And I clicked on "Neues Angebot"
    When I enter a title
    And I set the category to "Gegenstand"
    And I set the switch to "Tauschen"
    And I enter a start date
    And I enter a description
    And I click on "Speichern"
    Then the offer gets saved
    And I get directed to "Eigene Angebote"

  Scenario: Create offer for a service
    Given I am logged in
    And I clicked on "Eigene Angbote"
    And I clicked on "Neues Angebot"
    When I enter a title
    And I set the category to "Hilfeleistung"
    And I set the switch to "Tauschen"
    And I enter a start date
    And I enter an end date
    And I enter a description
    And I click on "Speichern"
    Then the offer gets saved
    And I get directed to "Eigene Angebote"
