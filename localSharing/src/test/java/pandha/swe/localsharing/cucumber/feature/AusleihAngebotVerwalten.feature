Feature: Ausleih-Angebote verwalten
  The use case "Ausleih-Angebote verwalten" enables the user to create,
  update and delete his own borrow offers which then can be inquired by other users.
  
  As a logged in user
  I want to create a borrow offer
  And I want to be able to edit the offers information
  And I want to be able to delete the offer
  So that other users can find and inquire them

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
    Then the offer is saved
    And I am directed to "Eigene Angebote"

  Scenario: Edit offer
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on the offer I want to edit
    And I click on "Bearbeiten"
    And I edit the information about the offer
    And I click on "Speichern"
    Then my edited information on the offer is saved
    And the changed offer is displayed in "Eigene Angebote"

  Scenario: Not saving edited offer
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on a special offer
    And I click on "Bearbeiten"
    And I edit the information
    And I click on "Abbrechen"
    Then my edited information on the offer is not saved
    And my offer is displayed unmodified in "Eigene Angebote"

  Scenario: Offer is unmodified
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on a special offer
    And I click on "Abbrechen"
    Then my offer is displayed unmodified in "Eigene Angebote"

  Scenario: Delete offer
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on a special offer
    And I click on "Löschen"
    Then my offer is deleted
    And my offer is not displayed any longer in "Eigene Angebote"
