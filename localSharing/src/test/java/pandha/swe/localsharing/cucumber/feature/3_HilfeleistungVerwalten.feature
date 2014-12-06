Feature: Hilfeleistungs-Angebote verwalten
  The use case "Hilfeleistungs-Angebote verwalten" enables the user to create,
  update and delete his own service offers which then can be inquired by other users.
  
  As a logged in user
  I want to create a service offer
  And I want to be able to edit the offers information
  And I want to be able to delete the offer
  So that other users can find and inquire them

  Scenario: Create offer for a service
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on "neue Hilfeleistung" on my offer page
    And I enter "Mathe Nachhilfe" as titel
    And I enter "Ich Studiere Mathmatik im 4. Semester und helfe gern." as description
    And I choose "10.12.2014" as startdate on the new offer page
    And I choose "15.01.2015" as end date on the new service offer page
    And I click on "Speichern" on the new offer page
    Then I can see my offer page
    And I can see a service offer with "Mathe Nachhilfe" as title on my offer page

  Scenario: Not saving edited service offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the service offer with "Streichen" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I change the title to "Malen" on the service offer edit page
    And I change the description to "Ich bin ein echter Künstler!" on the service offer edit page
    And I change the startdate to "08.12.2014" on the service offer edit page
    And I change the endate to "20.12.2014" on the service offer edit page
    And I click on "Abbrechen" on the edit offer page
    Then I can see my offer page
    And I can see a service offer with "Streichen" as title on my offer page
    And I can not see a service offer with "Malen" as title on my offer page

  Scenario: Service offer is unmodified
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the service offer with "Streichen" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I click on "Abbrechen" on the edit offer page
    Then I can see my offer page
    And I can see a service offer with "Streichen" as title on my offer page

  Scenario: Edit service offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the service offer with "Streichen" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I change the title to "Malen" on the service offer edit page
    And I change the description to "Ich bin ein echter Künstler!" on the service offer edit page
    And I change the startdate to "08.12.2014" on the service offer edit page
    And I change the endate to "20.12.2014" on the service offer edit page
    And I click on "Speichern" on the edit offer page
    Then I can see my offer page
    And I can see a service offer with "Malen" as title on my offer page
    And I can not see a service offer with "Streichen" as title on my offer page

  Scenario: Delete service offer
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Eigene Angebote" on the start page
    And I click on the service offer with "Malen" as title on my offer page
    And I click on "Bearbeiten" on the offer page
    And I click on "Löschen" on the edit offer page
    Then I can see my offer page
    And I can not see a service offer with "Malen" as title on my offer page
