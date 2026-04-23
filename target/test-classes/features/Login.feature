Feature: Login functionality

  Scenario Outline: Login with multiple users
    Given user is on login page
    When user enters username "<username>" and password "<password>"
    And clicks on login button
    Then login should be "<result>"

    Examples:
      | username | password    | result  |
      | user1    | pass@ABC12  | success |
      | user2    | pass@DEF34  | success |
      | user3    | pass@IJK56  | failure |