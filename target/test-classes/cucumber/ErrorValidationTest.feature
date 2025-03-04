
@tag
Feature: Error Validation 

  @ErrorValidation
  Scenario Outline: Negative test for login
    Given I landed on ecommerce page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." wrong credentials message is displayed

    Examples: 
      | username         | password    |
      | akki@example.com | Akki@098768 |
