Feature: Evaluate a user
  As a logged in user
  I want to evaluate the user
  so that other can estimate the quality of the users offers.

  Scenario: Evaluate latest request
    Given I am logged in
    And I have performed a deal
    When I click on "Alle Anfragen"
    And I click on "Bewerten" of the deal
    And I rate the deal with stars
    And I write a comment
    And I click on "Speichern"
    Then my evaluation is saved
    And the evaluation is displayed on the other users profile
    And the evaluation is displayed in the deals information

  Scenario: Not saving the evaluation
    Given I am logged in
    And I have performed a deal
    When I click on "Alle Anfragen"
    And I click on "Bewerten" of the deal
    And I rate the deal with stars
    And I write a comment
    And I click on "Abbrechen"
    Then my evaluation is not saved
    And nothing else happens

  Scenario: Evaluation already exists
    Given I am logged in
    And I have performed a deal
    And I have already evaluated the deal
    When I click on "Alle Anfragen"
    And I click on "Bewerten" of the deal
    Then my given evaluation is displayed
    And I can read it again

    