@api
Feature: Login API

  Scenario Outline: Login with multiple users
    Given user sets DemoQA base URI
    When user sends POST request to "/Account/v1/Login" with username "<username>" and password "<password>"
    Then login response should be "<result>"

    Examples:
      | username | password | result |
      | user1 | pass@ABC12 | success |
      | user2 | pass@DEF34 | success |
      | user3 | pass@IJK56 | failure |
