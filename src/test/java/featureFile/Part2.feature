Feature: Add to cart and checkout
  Scenario: Successfully adding to cart and checkout
    Given I navigate to AutomationPractice
    When I try to login using username and password
    Then I am logged in
    Given navigate to dresses and add to cart
    When  select shipping and payment