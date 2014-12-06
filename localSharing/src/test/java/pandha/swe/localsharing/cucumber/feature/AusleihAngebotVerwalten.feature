Feature: Ausleih-Angebote verwalten
  The use case "Ausleih-Angebote verwalten" enables the user to create,
  update and delete his own borrow offers which then can be inquired by other users.
  
  As a logged in user
  I want to create a borrow offer
  And I want to be able to edit the offers information
  And I want to be able to delete the offer
  So that other users can find and inquire them

  Scenario: Create borrow offer for an item to be borrowed
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Angebote" on the start page
    And I click on "neuer Ausleihartikel" on my offer page
    And I enter "Inception" as titel
    And I enter "DVD" as category on the new borrow offer page
    And I enter "Ein toller Film. DVD ist in gutem Zustand." as description
    And I choose "01.12.2014" as startdate on the new offer page
    And I choose "01.01.2015" as end date on the new borrow offer page
    And I enter "5" days as duration on the new borrow offer page
    And I click on "Speichern" on the new offer page
    Then I can see my offer page
    And I can see a borrow offer with "Inception" as title on my offer page

  Scenario: Not saving edited borrow offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the borrow offer with "96 Hours" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I change the title to "72 Hours" on the borrow offer edit page
    And I change the descriptio to "BluRay, kaum gebraucht" on the borrow offer edit page
    And I change the startdate to "08.12.2014" on the borrow offer edit page
    And I change the endate to "20.12.2014" on the borrow offer edit page
    And I change the duration to "3" days on the borrow offer edit page
    And I click on "Abbrechen" on the edit offer page
    Then I can see my offer page
    And I can see a borrow offer with "96 Hours" as title on my offer page
    And I can not see a borrow offer with "72 Hours" as title on my offer page

  Scenario: Borrow offer is unmodified
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the borrow offer with "96 Hours" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I click on "Abbrechen" on the edit offer page
    Then I can see my offer page
    And I can see a borrow offer with "96 Hours" as title on my offer page

  Scenario: Edit borrow offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the borrow offer with "96 Hours" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I change the title to "72 Hours" on the borrow offer edit page
    And I change the descriptio to "BluRay, kaum gebraucht" on the borrow offer edit page
    And I change the startdate to "08.12.2014" on the borrow offer edit page
    And I change the endate to "20.12.2014" on the borrow offer edit page
    And I change the duration to "3" days on the borrow offer edit page
    And I click on "Speichern" on the edit offer page
    Then I can see my offer page
    And I can see a borrow offer with "72 Hours" as title on my offer page
    And I can not see a borrow offer with "96 Hours" as title on my offer page

  Scenario: Delete borrow offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the borrow offer with "Die Insel" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I click on "Löschen" on the edit offer page
    Then I can see my offer page
    And I can not see a borrow offer with "Die Insel" as title on my offer page
