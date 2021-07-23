@Smoke
Feature: FCA DPI Smoke Test

  Scenario: YMM Navigation Filter Test
    Given Launch the Chrome Browser and Go to the url & verify that the login screen is displayed with all required fields
    When Enter the Username and Password
    And Click login then confirm that the user landed on Home Page
    And Click on the Make,Year,Model dropdown for the OEM, read the make and select from it "FCA", 1
    Then User should be able to view the selected make from the dropdown "FCA", 1
    When Read the Year from excel and Select Year from the Year dropdown "FCA", 1
    Then User should be able to view the selected Year from the dropdown "FCA", 1
    When Read the model from excel and Select model from the Model dropdown "FCA", 1
    Then User should be able to view the selected Model from the dropdown "FCA", 1
    When Click on the search icon
    Then User should be able to view the top part categories listed under the Parts search tab "FCA", 1
    And User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories
    Then Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view "Search by Keyword / Part #" filter option
    When Enter the keyword in the search box and click on search "filter"
    Then User should be able to view the parts returned for the Keyword
    When Enter the keyword in the search box and click on search "brake"
    Then User should be able to view the parts returned for the Keyword
    When Read the VIN from excel and enter the VIN in the field and click search button "FCA", 1
    Then User should be able to view the top part categories listed under the Parts search tab "FCA", 1, 1
    And User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories
    Then Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view "Search by Keyword / Part #" filter option
    When Enter the keyword in the search box and click on search "filter"
    Then User should be able to view the parts returned for the Keyword
    When Enter the keyword in the search box and click on search "brake"
    Then User should be able to view the parts returned for the Keyword
    When Click Logout and then confirm that the user landed on Login Page

  Scenario: YMM Navigation Filter Test
    Given Launch the Chrome Browser and Go to the url & verify that the login screen is displayed with all required fields
    When Enter the Username and Password
    And Click login then confirm that the user landed on Home Page
    And Click on the Make,Year,Model dropdown for the OEM, read the make and select from it "FCA", 2
    Then User should be able to view the selected make from the dropdown "FCA", 2
    When Read the Year from excel and Select Year from the Year dropdown "FCA", 2
    Then User should be able to view the selected Year from the dropdown "FCA", 2
    When Read the model from excel and Select model from the Model dropdown "FCA", 2
    Then User should be able to view the selected Model from the dropdown "FCA", 2
    When Click on the search icon
    Then User should be able to view the top part categories listed under the Parts search tab "FCA", 2
    And User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories
    Then Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view "Search by Keyword / Part #" filter option
    When Enter the keyword in the search box and click on search "filter"
    Then User should be able to view the parts returned for the Keyword
    When Enter the keyword in the search box and click on search "brake"
    Then User should be able to view the parts returned for the Keyword
    When Read the VIN from excel and enter the VIN in the field and click search button "FCA", 2
    Then User should be able to view the top part categories listed under the Parts search tab "FCA", 2, 1
    And User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories
    Then Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view "Search by Keyword / Part #" filter option
    When Enter the keyword in the search box and click on search "filter"
    Then User should be able to view the parts returned for the Keyword
    When Enter the keyword in the search box and click on search "brake"
    Then User should be able to view the parts returned for the Keyword
    When Click Logout and then confirm that the user landed on Login Page

  Scenario: YMM Navigation Filter Test
    Given Launch the Chrome Browser and Go to the url & verify that the login screen is displayed with all required fields
    When Enter the Username and Password
    And Click login then confirm that the user landed on Home Page
    And Click on the Make,Year,Model dropdown for the OEM, read the make and select from it "FCA", 3
    Then User should be able to view the selected make from the dropdown "FCA", 3
    When Read the Year from excel and Select Year from the Year dropdown "FCA", 3
    Then User should be able to view the selected Year from the dropdown "FCA", 3
    When Read the model from excel and Select model from the Model dropdown "FCA", 3
    Then User should be able to view the selected Model from the dropdown "FCA", 3
    When Click on the search icon
    Then User should be able to view the top part categories listed under the Parts search tab "FCA", 3
    And User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories
    Then Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view "Search by Keyword / Part #" filter option
    When Enter the keyword in the search box and click on search "filter"
    Then User should be able to view the parts returned for the Keyword
    When Enter the keyword in the search box and click on search "brake"
    Then User should be able to view the parts returned for the Keyword
    When Read the VIN from excel and enter the VIN in the field and click search button "FCA", 3
    Then User should be able to view the top part categories listed under the Parts search tab "FCA", 3, 1
    And User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories
    Then Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view "Search by Keyword / Part #" filter option
    #When Enter the keyword in the search box and click on search "filter"
    #Then User should be able to view the parts returned for the Keyword
    #When Enter the keyword in the search box and click on search "brake"
    #Then User should be able to view the parts returned for the Keyword
    When Click Logout and then confirm that the user landed on Login Page

