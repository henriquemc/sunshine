Feature: View weather data


  Scenario: View week weather data.
    Given that user is on first screen of the application.
    When he click on 'Refresh' menu item.
    Then one list with weather data of week should be shown
