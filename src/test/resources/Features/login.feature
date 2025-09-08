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

 