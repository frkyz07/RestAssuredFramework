Feature: Validating Place API's

  Scenario Outline: Verify if place successfully added using AddPlaceAPI

    Given Add place payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "Post" http request
    Then The API call with success status code 200
    And "status" call should be "OK"
    And "scope" call should be "APP"
    Examples:
      |name  |language |address           |
      |House A |English  |World Cross Center|
      |House B |Spanish  |Corum             |




