Feature: Validating Place API's

  Scenario: Verify if place successfully added using AddPlaceAPI

    Given Add place payload
    When User calls "AddPlaceAPI"  with post http request
    Then The API call with success status code 200
    And "status" call should be "OK"
    And "scope" call should be "APP"