Feature: Processing request in Service
  Scenario: Service receives an invalid request
    Given a request ''
    When the request is processed by Service
    Then the response should be 'invalid response'

  Scenario: Service receives a valid request
    Given a request 'some valid request'
    When the request is processed by Service
    Then the response should be 'success response'

  Scenario: Service receives a failure request
    Given a request 'failure request'
    When the request is processed by Service
    Then the response should be 'failure response'