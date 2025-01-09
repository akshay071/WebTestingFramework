Feature: Launch Google

  Scenario: Open Google Homepage
    Given I navigate to Google
    Then I switch "GoogleCalloutFrame" frame on "Home" screen
    Then I click "StaySignoutButton" on "Home" screen
    Then I switch back to default content
    Then I enter text "Chat Gpt" in "GoogleSearchField" on "Home" screen
#    Then I click "firstOption" on "Home" screen
#    Then I click "GoogleSearchButton" on "Home" screen

