<<<<<<< HEAD
Feature: Search Round Trip Flights

  @SearchFlight
  Scenario Outline: Search round-trip flights and see results
    Given the user is on the homepage
    When the user selects "Round Trip" trip type
    And the user enters origin as "<from>"
    And the user enters destination as "<to>"
    And the user sets travellers as "<adults>" adults, "<children>" children, "<infants>" infants and class as "<travel_class>"
    And the user clicks Search
    Then search results should be displayed

    Examples:
      | from           | to         |  adults | children | infants | travel_class |
      | Chennai  | Mumbai |  1      | 1      | 0       | Economy      |
=======
Feature: flight searching successfully

  Scenario Outline: Successful flight search
    Given the user is on the login page
    And the user selects the from as "<from>" and to as "<to>"
    And the user selects the departure as "<departure>" and return as "<return>"
    And the user clicks the travellars icon
    And the user selects the special fare 
    Then the user clicks the search button

    Examples:
      | from    | to       | departure  | return     |
      | Chennai | New Delhi| 2025-09-10 | 2025-09-20 |
>>>>>>> 825a72480ba566cd572f1affd3f4e98dc04ca366
