@Smoke
Feature: HONDA DPI API Smoke Test

  Scenario Outline: VIN API Navigation Filter Test
    Given I perform GET operation with Params and APIKey for Honda "HONDA" "<rowNo>"
    Then I perform GET for Honda and should see the status code as 200
    And read the values of nodes for next request and perform GET Operation for Honda "HONDA" "<rowNo>"
    Then I should see the status code as 200 and get the ID from response and perform GET operation for Honda for Images "HONDA" "<rowNo>"
    Then I should see the status code as 200 and get ImageID from response and perform GET operation for Honda for Big Images "HONDA" "<rowNo>"
    And I should see the status code as 200 and perform GET operation for Honda for Callout Image "HONDA" "<rowNo>"
    Then I should see the status code as 200 for Honda
    Given I perform GET operation with Params from Excel for Honda "HONDA" "<rowNo>"
    Then I should see the status code as 200 and Verify the response for ID for Honda
    Given I perform GET operation for Vehicle with Params from Excel for Honda "HONDA" "<rowNo>"
    Then I should see the status code as 200 and get the Attribute ID from response for Honda
    And read the value of AttributeID node for next request and perform GET operation for Honda "HONDA" "<rowNo>"
    Then I should see the status code as 200 and get the previousHierarchyId from response for Honda
    And read the value of previousHierarchyId node for next request and perform GET operation for Honda "HONDA" "<rowNo>"
    Then I should see the status code as 200 for Honda
    Given I perform GET operation search for all categories for Honda "HONDA" "<rowNo>"
    Then I should see the status code as 200 for Honda
    Examples:
      | rowNo |
      | 1     |
      | 2     |


