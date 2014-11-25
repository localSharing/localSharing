Feature: Register
  As a not logged in user
  I want to register
  so that i am a registered user.

  Scenario: Register
    Given I am not logged in
    And I am not yet registered
    And I want to register
    When I enter my personal inforamtion
    And the Email is not used by another user
    And I enter a password twice
    And I click on "Registrieren"
    Then I am a registred user
    And I can login

  Scenario: Register with used email-adress
    Given I am not logged in
    And I am not yet registered
    And I want to register
    When I enter my personal inforamtion
    And the Email is used by another user
    And I enter a password twice
    And I click on "Registrieren"
    Then I am not registred
    And I can try to register again

  Scenario: Register with diffrent passwords
    Given I am not logged in
    And I am not yet registered
    And I want to register
    When I enter my personal inforamtion
    And the Email is not used by another user
    And I enter a password incorrectly
    And I click on "Registrieren"
    Then I am not registred
    And I can try to register again
