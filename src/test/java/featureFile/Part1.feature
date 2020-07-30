Feature: Login and Edit Feature

  Scenario: Successfully login in to basqar
    Given I navigate to AutomationPractice
    When I try to login using username and password
    Then I am logged in


  Scenario: Succesfully edit account information
    Given I navigate to AutomationPractice
    When I try to login using username and password
    Then I am logged in
    Given Click on the My personal information
    When I try to Change the first name "Ali",currentPassword, and the new password
    Then Verify the name "Ali Kocakurt" on the top right is updated as your first name

  Scenario: Succesfully Change the first name as the previous name in the first time
    Given I navigate to AutomationPractice
    When I try to login using username and password
    Then I am logged in
    Given Click on the My personal information
    When I try to Change the first name "Umare",currentPassword, and the new password
    Then Verify the name "Umare Kocakurt" on the top right is updated as your first name