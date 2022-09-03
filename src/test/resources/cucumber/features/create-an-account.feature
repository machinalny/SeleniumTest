Feature: Hotel Users

  Scenario: new account can be created

    Given an open browser with https://hotel-testlab.coderslab.pl/en/
    When new user register
    Then an account is created
    And close browser