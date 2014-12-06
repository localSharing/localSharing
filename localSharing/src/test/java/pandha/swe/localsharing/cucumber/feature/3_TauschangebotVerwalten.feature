Feature: Tausch-Angebote verwalten
  The use case "Tausch-Angebote verwalten" enables the user to create,
  update and delete his own exchange offers which then can be inquired by other users.
  
  As a logged in user
  I want to create a exchange offer
  And I want to be able to edit the offers information
  And I want to be able to delete the offer
  So that other users can find and inquire them

  Scenario: Create a exchange offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on "neuer Tauschartikel" on my offer page
    And I enter "Java Buch" as titel
    And I enter "Buch" as category on the new exchange offer page
    And I enter "Ich würde gern dieses Buch gegen eine Musik CD tauschen. Bei Interresse bitte melden!!!" as description
    And I choose "08.12.2014" as startdate on the new offer page
    And I click on "Speichern" on the new offer page
    Then I can see my offer page
    And I can see a exchange offer with "Java Buch" as title on my offer page

  Scenario: Not saving edited exchange offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the exchange offer with "Rom: Marco-Polo-Reiseführer" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I change the title to "Berlin: Marco-Polo-Reiseatlas" on the exchange offer edit page
    And I change the description to "MarcoPolo-Reiseführer für Berlin (2. Auflage, 2015). Tausche gerne gegen andere Reiseführer ;)" on the exchange offer edit page
    And I change the startdate to "09.12.2014" on the exchange offer edit page
    And I click on "Abbrechen" on the edit offer page
    Then I can see my offer page
    And I can see a exchange offer with "Rom: Marco-Polo-Reiseführer" as title on my offer page
    And I can not see a exchange offer with "Berlin: Marco-Polo-Reiseatlas" as title on my offer page

  Scenario: Exchange offer is unmodified
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the exchange offer with "Rom: Marco-Polo-Reiseführer" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I click on "Abbrechen" on the edit offer page
    Then I can see my offer page
    And I can see a exchange offer with "Rom: Marco-Polo-Reiseführer" as title on my offer page

  Scenario: Edit exchange offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the exchange offer with "Rom: Marco-Polo-Reiseführer" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I change the title to "Berlin: Marco-Polo-Reiseatlas" on the exchange offer edit page
    And I change the description to "MarcoPolo-Reiseführer für Berlin (2. Auflage, 2015). Tausche gerne gegen andere Reiseführer ;)" on the exchange offer edit page
    And I change the startdate to "09.12.2014" on the exchange offer edit page
    And I click on "Speichern" on the edit offer page
    Then I can see my offer page
    And I can see a exchange offer with "Berlin: Marco-Polo-Reiseatlas" as title on my offer page
    And I can not see a exchange offer with "Rom: Marco-Polo-Reiseführer" as title on my offer page

  Scenario: Delete exchange offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the exchange offer with "Java Buch" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I click on "Löschen" on the edit offer page
    Then I can see my offer page
    And I can not see a exchange offer with "Java Buch" as title on my offer page
