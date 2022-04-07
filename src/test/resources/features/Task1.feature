@enter_number
Feature: tests Enter a number page

  Background:
    Given I am on Enter a number page

  @task1
  Scenario Outline: simple scenario outline
    When I enter "<number>" in Enter a number page
    And I click Submit button in Enter a number page
    Then Error message "<errorMessage>" appears in Enter a number page

    Examples:
      | number  | errorMessage          |
      | 25      | Number is too small   |
      | 150     | Number is too big     |
      | letters | Please enter a number |

  @task1
  Scenario: allowed numbers
    When I enter "55" in Enter a number page
    And I click Submit button in Enter a number page
    Then Alert message "Square root of 55 is 7.42" appears


#  * * * * * * * * * * * * * * * * * * * * * * * * * * *  #
#  below are extra scenarios, out of task, please ignore  #
#  * * * * * * * * * * * * * * * * * * * * * * * * * * *  #


  Scenario Outline: too small numbers
    When I enter "<number>" in Enter a number page
    And I click Submit button in Enter a number page
    Then Error message "Number is too small" appears in Enter a number page

    @passing
    Examples:
      | number |
      | 0      |
      | 1      |
      | 2      |
      | 10     |
      | 12     |
      | 13     |
      | 25     |
      | 31     |
      | 44     |
      | 0.01   |
      | 48.9   |

    @failed
    Examples:
      | number |
      | 42     |
      | 49     |
      | -5     |
      | -122   |

  Scenario Outline: too big numbers
    When I enter "<number>" in Enter a number page
    And I click Submit button in Enter a number page
    Then Error message "Number is too big" appears in Enter a number page

    @passing
    Examples:
      | number               |
      | 101                  |
      | 121                  |
      | 1001                 |
      | 12345                |
      | 123456               |
      | 9900990              |
      | 32511315             |
      | 159977646            |
      | 2406840987           |
      | 65468409849          |
      | 65468409849684987090 |
    @failed
    Examples:
      | number |
      | 666    |


  Scenario Outline: entering non-numbers
    When I enter "<number>" in Enter a number page
    And I click Submit button in Enter a number page
    Then Error message "Please enter a number" appears in Enter a number page

    @passing
    Examples:
      | number   |
      | ---      |
      | abcd     |
      | number   |
      | NaN      |
      | ..33     |
      | ,.,.,.,% |
      | 55k      |
      | d77      |
      | 44 44    |

    @failed
    Examples:
      | number   |
      | Infinity |
      | 1e5      |
      | .5       |
      | 1e-1     |
      | 0xEB     |
      | 0b100101 |
      | 0o55     |

    @weird
    Examples:
      | number |
      | "22"   |

  Scenario Outline: allowed numbers
    When I enter "<number>" in Enter a number page
    And I click Submit button in Enter a number page
    Then Alert message "Square root of <number> is <result>" appears

    @passing
    Examples:
      | number | result |
      | 64     | 8.00   |
      | 81     | 9.00   |
      | 100    | 10.00  |
      | 77     | 8.77   |
      | 50     | 7.07   |
