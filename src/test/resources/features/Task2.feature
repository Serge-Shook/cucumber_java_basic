Feature: People with jobs page test

  Background:
    Given I am on People with jobs page


  Scenario Outline: adding new person
    When I click "Add person" button
    And I enter following info in corresponding fields
      | Name          | <name>      |
      | Surname       | <surname>   |
      | Job           | <job>       |
      | Date of birth | <birthDate> |
    And I choose languages: "<languages>"
    And I choose gender: "<gender>"
    And I select "<status>" from employee status
    And I click "Add" button
    Then I can see "<name> <surname>" in people list
    And persons info is as in list
      | Full name         | <name> <surname> |
      | Job               | <job>            |
      | Date of birth     | <birthDate>      |
      | Knows language(s) | <langOut>        |
      # Knows language(s) field ^ has unexpected behavior:
      # absence of English causes "undefined" to appear in languages list, dangling comma, etc.
      | Gender            | <gender>         |
      | Employee status   | <status>         |

    Examples:
      | name   | surname  | job           | birthDate  | gender | status     | languages                | langOut                   |
      | Jason  | Cucumber | Tester        | 07/07/1999 | Male   | Contractor | English                  | English,                  |
      | Anna   | Iglesias | Developer     | 01/13/1983 | Female | Employee   | English, Spanish         | English, spanish,         |
      | George | Baron    | Director      | 01/13/1973 | Male   | Contractor | English, Spanish, French | English, French, spanish, |
      | Sergey | Shuk     | Junior tester | 10/08/1973 | Male   | Intern     | English                  | English,                  |
      | Sophie | Poulen   | Designer      | 05/09/2002 | Female | Intern     | French                   | undefinedFrench,          |


  Scenario: editing person record
    When I click on pencil icon at right of "Jill Watson"
    And I clear "Job" field
    And I enter "Head of Support" in "Job" field
    And I select "Contractor" from employee status
    And I click "Edit" button
    Then persons info is as in list
      | Full name         | Jill Watson      |
      | Job               | Head of Support  |
      | Date of birth     | 06/06/1966       |
      | Knows language(s) | undefinedSpanish |
      # Knows language(s) field has unexpected behavior:
      # absence of English causes "undefined" to appear in languages list, dangling comma, etc.
      | Gender            | female           |
      | Employee status   | contractor       |

  @wip
  Scenario: removing person record
    When I click on cross icon at right of "Mike Kid"
    Then I can not see "Mike Kid" person record
    But I can see "Jill Watson" in people list


  Scenario: reset list after adding
    When I add a person with data:
      | Name            | Jason      |
      | Surname         | Cucumber   |
      | Job             | Tester     |
      | Date of birth   | 07/07/1999 |
      | Languages       | English    |
      | Gender          | Male       |
      | Employee status | Contractor |
    Then I can see "Jason Cucumber" in people list
    When I click "Reset List" button
    Then I can see person list:
      | Mike Kid    |
      | Jill Watson |
      | Jane Doe    |


  Scenario: reset after editing name
    When I click on pencil icon at right of "Jill Watson"
    And I clear "Surname" field
    And I enter "Holmes" in "Surname" field
    And I click "Edit" button
    Then I can see person list:
      | Mike Kid    |
      | Jill Holmes |
      | Jane Doe    |
    When I click "Reset List" button
    Then I can see person list:
      | Mike Kid    |
      | Jill Watson |
      | Jane Doe    |


  Scenario: reset list after deleting persons
    When I click on cross icon at right of "Mike Kid"
    Then I can see person list:
      | Jill Watson |
      | Jane Doe    |
    And I click on cross icon at right of "Jane Doe"
    Then I can see person list:
      | Jill Watson |
    When I click "Reset List" button
    Then I can see person list:
      | Mike Kid    |
      | Jill Watson |
      | Jane Doe    |


  Scenario: clear button reset defaults in add person form
    When I click "Add person" button
    And I enter following info in corresponding fields
      | Name          | Jason      |
      | Surname       | Cucumber   |
      | Job           | Tester     |
      | Date of birth | 07/07/1999 |
    And I choose languages: "English"
    And I choose gender: "Male"
    And I select "Contractor" from employee status
    And I click "Clear all fields" button
    Then I can see "Name" field is empty
    And I can see "Surname" field is empty
    And I can see "Job" field is empty
    And I can see Date of birth field is empty
    And Only English language is checked
    And Gender radios are unchecked
    And Employee status is Employee
#    And Wait for 3 seconds
