
@tag
Feature: Purchase the order from ecommerce website 

# First we have to land on the application thats a prerequsite step 
# In cucumber we can actually separate prerequsite test from your actual scenario
# There is a keyword called Background, this executes before each and every scenario
# Just like before method in testng same thing we can achieve in cucumber using Background
Background:
Given I landed on ecommerce page 
  
  
 @Regression @SpecificScenario
  Scenario Outline: Positive test of submitting order
    Given Logged in with username <username> and password <password>
    When Add the product <product> to cart 
    And Checkout <product> and submit the order
    Then Verify "THANKYOU FOR THE ORDER." message displayed on confirmationPage

    Examples: 
      | username         | password |   product  |
      | akki@example.com | Akki@098 | ZARA COAT 3|
      
