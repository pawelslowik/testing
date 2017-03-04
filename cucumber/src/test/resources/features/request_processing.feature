Feature: Processing request in RequestProcessingService

  RequestProcessingService should process invalid requests, valid requests which result with success
  response and valid requests which result with failure response.

  Scenario: RequestProcessingService receives an invalid request
    Given a request ''
    When the request is processed by RequestProcessingService
    Then the response should be 'invalid response'

  Scenario: RequestProcessingService receives a success request
    Given a request 'some success request'
    When the request is processed by RequestProcessingService
    Then the response should be 'success response'

  Scenario: RequestProcessingService receives a failure request
    Given a request 'failure request'
    When the request is processed by RequestProcessingService
    Then the response should be 'failure response'