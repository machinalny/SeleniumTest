Feature: Hotel Users

  Scenario: new account can be created

    Given an open browser with https://hotel-testlab.coderslab.pl/en/
    When new user register
    Then an account is created
    And close browser


  Scenario Outline: new account can be created for given users

    Given an open browser with https://hotel-testlab.coderslab.pl/en/
    When a user with <name> and <lastname> is registered
    Then an account is created
    And close browser

    Examples:
    | name | lastname   |
    | jan  | januszewski|
    | tom  | tomaszewski|
