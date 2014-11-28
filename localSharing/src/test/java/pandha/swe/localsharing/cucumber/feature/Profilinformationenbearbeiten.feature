Feature: Edit profile information
  As a logged in user
  I want to edit my personal information
  so that the information is up to date.

  Scenario: Not saving edited information
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Mein Profil" on the start page
    And I click on "Bearbeiten" on my profil page
    And I change my firstname to "Hans" on my edit profil page
    And I change my surname to "Gelb" on my edit profil page
    And I change my city to "Heidelberg" on my edit profil page
    And I change my street number to "12" on my edit profil page
    And I change my phone number to "0123451245" on my edit profil page
    And I change my zip code to "78945" on my edit profil page
    And I change my street to "Hansthomaplatz" on my edit profil page
    And I click on "Abbrechen" on my edit profil page
    Then I can see my profile page
    And I can see my firstname is "Peter" on my profil page
    And I can see my surname is "Blau" on my profil page
    And I can see my city is "Karlsruhe" on my profil page
    And I can see my street number is "11" on my profil page
    And I can see my phone number is "0123451232" on my profil page
    And I can see my street is "Erzbergerstraße" on my profil page
    And I can see my zip code is "12345" on my profil page
    And I can see my email is "tester@cucumber.de" on my profil page

  Scenario: Information is unmodified
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Mein Profil" on the start page
    And I click on "Bearbeiten" on my profil page
    And I click on "Abbrechen" on my edit profil page
    Then I can see my profile page
    And I can see my firstname is "Peter" on my profil page
    And I can see my surname is "Blau" on my profil page
    And I can see my city is "Karlsruhe" on my profil page
    And I can see my street number is "11" on my profil page
    And I can see my phone number is "0123451232" on my profil page
    And I can see my street is "Erzbergerstraße" on my profil page
    And I can see my zip code is "12345" on my profil page
    And I can see my email is "tester@cucumber.de" on my profil page

  Scenario: Edit information
    Given I am logged in as "tester@cucumber.de" with password "12345678"
    When I click on "Mein Profil" on the start page
    And I click on "Bearbeiten" on my profil page
    And I change my firstname to "Hans" on my edit profil page
    And I change my surname to "Gelb" on my edit profil page
    And I change my city to "Heidelberg" on my edit profil page
    And I change my street number to "12" on my edit profil page
    And I change my phone number to "0123451245" on my edit profil page
    And I change my zip code to "78945" on my edit profil page
    And I change my street to "Hansthomaplatz" on my edit profil page
    And I click on "Speichern" on my edit profil page
    Then I can see my profile page
    And I can see my edited Information has been saved
    And I can see my firstname is "Hans" on my profil page
    And I can see my surname is "Gelb" on my profil page
    And I can see my city is "Heidelberg" on my profil page
    And I can see my street number is "12" on my profil page
    And I can see my phone number is "0123451245" on my profil page
    And I can see my street is "Hansthomaplatz" on my profil page
    And I can see my zip code is "78945" on my profil page
    And I can see my email is "tester@cucumber.de" on my profil page
