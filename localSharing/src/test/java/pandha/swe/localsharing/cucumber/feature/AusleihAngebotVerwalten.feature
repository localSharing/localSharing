Feature: Ausleih-Angebote verwalten
  The use case "Ausleih-Angebote verwalten" enables the user to create,
  update and delete his own borrow offers which then can be inquired by other users.
  
  As a logged in user
  I want to create a borrow offer
  And I want to be able to edit the offers information
  And I want to be able to delete the offer
  So that other users can find and inquire them

  Scenario: Create borrow offer for an item to be borrowed
    Given I am logged in
    And I clicked on "Eigene Angbote" on the startpage
    And I clicked on "Neues Angebot" on my offer page
    When I enter "Tarzan" as titel
    And I set the category to "Gegenstand"
    And I set the switch to "Ausleihen"
    And I choose "01.12.2014" as startdate
    And I choose "01.01.2014" as end date
    And I enter "5" days as duration
    And I enter "Ein toller Film. DVD ist in gutem Zustand." as description
    And I click on "Speichern" on the new offer page
    Then I am on my offer page
    And I can see my new borrow offer with "Tarzan" as title an

  Scenario: Edit borrow offer
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on the offer I want to edit
    And I click on "Bearbeiten"
    And I edit the information about the offer
    And I click on "Speichern"
    Then my edited information on the offer is saved
    And the changed offer is displayed in "Eigene Angebote"

  Scenario: Not saving edited borrow offer
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on a special offer
    And I click on "Bearbeiten"
    And I edit the information
    And I click on "Abbrechen"
    Then my edited information on the offer is not saved
    And my offer is displayed unmodified in "Eigene Angebote"

  Scenario: Borrow offer is unmodified
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on a special offer
    And I click on "Abbrechen"
    Then my offer is displayed unmodified in "Eigene Angebote"

  Scenario: Delete borrow offer
    Given I am logged in
    When I click on "Eigene Angebote"
    And I click on a special offer
    And I click on "Löschen"
    Then my offer is deleted
    And my offer is not displayed any longer in "Eigene Angebote"
