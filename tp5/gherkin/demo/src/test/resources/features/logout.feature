@LogoutSuccess
Feature: User Logout

  Background:
    Given the user is on the login page
    When the user enters a username as "tomsmith"
    And the user enters a password as "SuperSecretPassword!"
    And clicks on the login button
    Then the user should see a successful login message

  @ValidLogout
  Scenario: Successful logout
    When the user clicks on the logout button
    Then the user should see a logout success message
