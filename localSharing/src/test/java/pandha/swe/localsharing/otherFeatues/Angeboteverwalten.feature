Feature: Manage offers
  As a logged in user
  I want to manage my current offers 
  so that the offers are up to date.

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
    And my offer is not diyplayed any longer in "Eigene Angebote"
