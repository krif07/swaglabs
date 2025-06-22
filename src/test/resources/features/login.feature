Feature: Login in Swag Labs

  Scenario: Successful Login
    Given the user access the login page
    When he enters the username "standard_user" and password "secret_sauce"
    Then he should see the title "Products"