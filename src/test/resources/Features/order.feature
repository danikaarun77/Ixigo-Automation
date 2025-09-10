Feature: Mobile Login

  @PositiveLogin
  Scenario Outline: Successful login with valid mobile number
    Given the user is on the login page
    When the user enters mobileno as "<Mobile_no>"
    And enters the correct OTP
    Then the user should be navigated to the booking page

    Examples:
      | Mobile_no   |
      | 9003855489 |

  @NegativeLogin
  Scenario Outline: Unsuccessful login with invalid mobile number
    Given the user is on the login page
    When the user enters invalid mobileno as "<Mobile_No>"
    And the user clicks the login button
    Then the system should display "<error_message>"

    Examples:
      | Mobile_No | error_message               |
      | 12345     | Please enter a valid number |
      
      
     

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
      
      
      
 
Scenario Outline: Application of filters for selecting the flight
Given the user is on the selection page
And the user wants to apply the first filter recommended_filter as "<Recommended_filter>"
And the the second filter is selected Airlines as "<Airlines>"
And the user clicks third filter departure as "<Departure>"
And the user clicks fourth filter arrival as "<Arrival>"
Then select the first available flight
 

Examples:
|Recommended_filter|Airlines|Departure|Arrival|
|Non-stop|Air-India|Before 6 AM|12 PM - 6 PM|