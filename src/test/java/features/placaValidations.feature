Feature: Validating Place API's

  Scenario Outline: Verify if place successfully added using AddPlaceAPI

    Given Add place payload with "<name>" "<language>" "<address>"
    When User calls "AddPlaceAPI"  with post http request
    Then The API call with success status code 200
    And "status" call should be "OK"
    And "scope" call should be "APP"
    Examples:
      |name  |language |address           |
      |Ahouse |English  |World Cross Center|




