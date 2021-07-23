@Smoke
Feature: GM DPI API Smoke Test

  Scenario Outline: VIN API Navigation Filter Test
    Given I perform GET operation with Params and APIKey "GM" "<rowNo>"
    Then I perform GET and should see the status code as 200
    And read the values of nodes for next request and perform GET Operation "GM" "<rowNo>"
    Then I should see the status code as 200
    And read the value of nodes for next request and perform GET operation "GM" "<rowNo>"
    Then I should see the status code as 200 and get the ID from response and perform GET operation for Images "GM" "<rowNo>"
    Then I should see the status code as 200 and get ImageID from response and perform GET operation for Big Images "GM" "<rowNo>"
    And I should see the status code as 200 and perform GET operation for Callout Image "GM" "<rowNo>"
    Then I should see the status code as 200
    Given I perform GET operation with Params from Excel "GM" "<rowNo>"
    Then I should see the status code as 200 and Verify the response for ID
    Given I perform GET operation for Vehicle with Params from Excel "GM" "<rowNo>"
    Then I should see the status code as 200 and get the Attribute ID from response
    And read the value of AttributeID node for next request and perform GET operation "GM" "<rowNo>"
    Then I should see the status code as 200 and get the previousHierarchyId from response
    And read the value of previousHierarchyId node for next request and perform GET operation "GM" "<rowNo>"
    Then I should see the status code as 200
    Given I perform GET operation search for all categories "GM" "<rowNo>"
    Then I should see the status code as 200
    Examples:
      | rowNo |
      | 1     |
      | 2     |


