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
