Feature: Selecting max value in MaxValueSelectionService

  MaxValueSelectionService should select the max of two provided values

  Scenario Outline: Max value selection
    Given value1 <value1>
    And value2 <value2>
    When the max value is selected
    Then the result should be <maxValue>

    Examples:
      | value1 | value2 | maxValue |
      | '1'    | '2'    | '2'      |
      | '0'    | '-1'   | '0'      |
      | '3'    | '3'    | '3'      |