Feature: Register
  As a not logged in user
  I want to register
  so that i am a registered user.

  Scenario: Register
    Given I am not logged in
    And I am on the Register Page
    When I enter "Bart" as my first name
    And I enter "Simpson" as my surname
    And I enter "Evergreen Terrace" as street
    And I enter "742" as street number
    And I enter "12345" as zip code
    And I enter "Springfield" as city
    And I enter "123456789" as phone number
    And I enter "bart@simpson.de" as email
    And I enter the password "12345678" twice
    And I click on "Registrieren" button on the Register page
    Then I can see the login page

  Scenario: Register with used email-adress
    Given I am not logged in
    And I am on the Register Page
    When I enter "Bart" as my first name
    And I enter "Simpson" as my surname
    And I enter "Evergreen Terrace" as street
    And I enter "742" as street number
    And I enter "12345" as zip code
    And I enter "Springfield" as city
    And I enter "123456789" as phone number
    And I enter "tester@cucumber.de" as email
    And I enter the password "12345678" twice
    And I click on "Registrieren" button on the Register page
    Then I see a error message for already used emails
    And I can try to register again

  Scenario: Register with diffrent passwords
    Given I am not logged in
    And I am on the Register Page
    When I enter "Bart" as my first name
    And I enter "Simpson" as my surname
    And I enter "Evergreen Terrace" as street
    And I enter "742" as street number
    And I enter "12345" as zip code
    And I enter "Springfield" as city
    And I enter "123456789" as phone number
    And I enter "bart@simpson.de" as email
    And I enter the password "12345678" not twice
    And I click on "Registrieren" button on the Register page
    Then I see a error message for wrong password
    And I can try to register again
