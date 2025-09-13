Feature: Search OneWay Trip Flights

  @SearchFlight
  Scenario Outline: Search One Way Trip flights and see results
    Given the user is on the homepage
    When the user selects "One Way" trip type
    And the user enters origin as "MAA"
    And the user enters destination as "BOM"
    And the user sets travellers as "<adults>" adults, "<children>" children, "<infants>" infants and class as "<travel_class>"
    And the user clicks Search
    Then search results should be displayed

    Examples:
      | from | to   | adults | children | infants | travel_class |
      | DEL  | BLR  | 2      | 1        | 0       | Economy      |
     