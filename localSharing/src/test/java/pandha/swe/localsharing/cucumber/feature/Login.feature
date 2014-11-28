Feature: Login
  As a not logged in user
  I want to login
  so that i am a logged in user.

  Scenario: Login
    Given I am not logged in yet
    And I am on the login Page
    When I enter "tester@cucumber.de" as email
    And I enter "12345678" as Password
    And I click on "Login"
    Then I am a logged in user
    And I can see the mainpage

  Scenario: Login with wrong credentials
    Given I am not logged in yet
    And I am on the login Page
    When I enter "tester@cucumber.de" as email
    And I enter "abcdefgh" as Password
    And I click on "Login"
    Then I am not logged in
    And I can try to login again

  Scenario: Login without credentials
    Given I am not logged in yet
    And I am on the login Page
    When I do not enter any credentials
    And I click on "Login"
    Then I am not logged in
    And I can try to login again

  Scenario: Register
    Given I am not logged in yet
    When I click on "Registrieren"
    Then I can see the register page
