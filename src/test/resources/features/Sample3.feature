@regression
Feature: Introduction to cucumber part 3
  As a test engineer
  I want to be able to write and execute a scenario outline

  @debugs
  Scenario Outline: a new scenario outline
    Given I am on age page
    When I enter name: "<name>"
    And I enter age: <age>
    And I click submit age
    Then I see message: "<message>"
    @working
    Examples:
      | name  | age | message                        |
      | Ann   | 5   | Hello, Ann, you are a kid      |
      | Marry | 50  | Hello, Marry, you are an adult |
      | Bob   | 61  | Hello, Bob, you are an adult   |
    @not_working
    Examples:
      | name | age | message                   |
      | Tom  | 15  | Hello, Tom, you are a kid |

  # create Scenario Outline for 'Give us your feedback!' page
  # URL: https://kristinek.github.io/site/tasks/provide_feedback
  # Navigate to page
  # Set name and age based on test Examples
  # Click "Send" button and verify that previous input is displayed in correct fields

  Scenario Outline: check with name and age
    Given I am on the feedback page
    When I enter name: "<name>" on feedback page
    And I enter age: "<age>" on feedback page
    And I click submit on feedback page
    Then I see "<name>" in Your name: field
    And I see "<age>" in Your age: field

    Examples:
      | name      | age |
      | John      | 44  |
      | Anna      | 19  |
      | Sebastian | 58  |
      | Elizabeth | 39  |
