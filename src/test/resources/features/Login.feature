@login @ninja
Feature: Login functionality of app.ninjarmm.com

  Background:
    Given the user is on the NinjaOne login page

  @ignore
  Scenario: Login with valid credentials
    When user logs in to the website
      | email    | valid@ninja.com |
      | password | validPassword   |
    Then verify Multi-Factor Authentication page is displayed

  Scenario: Login attempt with invalid credentials
    When user logs in to the website
      | email    | test@ninja.com |
      | password | test123        |
    Then verify alert message is displayed
      | Human verification failed. Please try again or contact your system administrator for assistance. |

  @link
  Scenario Outline: Verify <link> link on the login page
    When user clicks on <link> link
    Then verify user is navigated to <path> page

    Examples:
      | link                      | path           |
      | "Forgot your password?"   | /resetPassword |
      | "Do not have an account?" | /register      |

