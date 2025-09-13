Feature: News And Travel Stories

  Scenario: User opens first travel story, closes, then opens another after login
    Given the user is logged in and on the homepage with stories section
    When the user clicks on the first travel story
    Then the travel story should open in a new tab

    When the user clicks on the travel story with caption "Enjoy in the Lap of Nature! 5 Scenic Places to Visit in August in India"
    Then the travel story should open in a new tab

    When the user clicks on the travel story with caption "7 Best International Places to Visit from India in August"
    Then the travel story should open in a new tab
