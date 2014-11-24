Feature: Edit profile information
  As a logged in user
  I want to edit my personal information
  so that the information is up to date.

  Scenario: Edit information
    Given I am logged in
    When I click on "Mein Profil"
    And I click on "Bearbeiten"
    And I edit the information
    And I click on "Speichern"
    Then my edited information is saved
    And the information is displayed on my profile

  Scenario: Not saving edited information
    Given I am logged in
    When I click on "Mein Profil"
    And I click on "Bearbeiten"
    And I edit the information
    And I click on "Abbrechen"
    Then my edited information is not saved
    And my information is displayed unmodified

  Scenario: Information is unmodified
    Given I am logged in
    When I click on "Mein Profil"
    And I click on "Bearbeiten"
    And I click on "Abbrechen"
    Then my information is displayed unmodified

    
    
    
    
    