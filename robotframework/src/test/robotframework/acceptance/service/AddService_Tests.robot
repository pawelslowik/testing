*** Settings ***
Library    pl.com.psl.testing.robotframework.KeywordsLibrary

*** Test Cases ***
Service should return sum of all input values
    Given Value    3
    And Given Value    2
    And Given Value    13
    When The Values Are Processed By Add Service
    Then The Result Should Be    18