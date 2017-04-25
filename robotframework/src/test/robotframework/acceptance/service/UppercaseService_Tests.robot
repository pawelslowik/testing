*** Settings ***
Library    pl.com.psl.testing.robotframework.KeywordsLibrary
Test Template    UppercaseServiceTestTemplate

*** Keyword ***
UppercaseServiceTestTemplate  [Arguments]  ${request}  ${response}
    Given Request    ${request}
    When The Request Is Processed By Uppercase Service
    Then The Response Should Be    ${response}

| *Test Case*       | *request* | *response*
| Lowercase request | tom       | TOM
| Uppercase request | BOB       | BOB
| Mixed request     | pAt       | PAT
| Empty Request     | ${EMPTY}  | ${EMPTY}