Feature: One-Way Ride Booking Functionality

  Scenario: Book a one-way ride and verify booking summary
    Given I launch the ride booking application
    When I enter "Rathinam college" as the pickup location
    And I type "GA" as the drop location prefix
    And I select the 2nd location from the dropdown suggestions
    And I capture the pickup and drop location text
    And I select "Book Later" and choose a future date
    And I click on the "Search Cabs" button
    Then I should see a list of available cabs with their prices
    When I click "Book Now" for the specified car
    Then the pickup and drop locations in the summary should match the captured values
    And I should see the correct tax and final price calculated
    When I fill in the contact details
    And I verify the pickup and drop locations again in the final step
    And I click on "Confirm Booking"
    Then the booking should be confirmed successfully