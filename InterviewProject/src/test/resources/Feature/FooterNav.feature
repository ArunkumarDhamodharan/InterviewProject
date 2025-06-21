Feature: Footer Navigation Link - Privacy Policy

  Scenario: Validate Privacy Policy footer link navigation
    Given I am on the homepage
    When I scroll to the footer section of the page
    Then I should see the "Privacy Policy" link in the footer
    When I click on the "Privacy Policy" footer link
    Then I should be redirected to the Privacy Policy page
    And the Privacy Policy page should load successfully without any error