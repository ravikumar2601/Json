package apiStepDefinitions.HONDA;

import apiAssertion.DPIAPIPrdSmokeAssertion;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import stepDefinitions.AbstractAPITest;

import java.util.List;
import java.util.Map;

public class DPIHONDAAPIPrdSmokeTest extends AbstractAPITest {

    public static String make = null;
    public static String model = null;
    public static String year = null;
    public static String oemId = null;
    public static String vin = null;
    public static String id = " ";
    public static String hierarchyId = null;
    public static String imageId = null;
    public static String previousHierarchyId = null;

    @Given("I perform GET operation with Params and APIKey for Honda {string} {string}")
    public void iPerformGETOperationWithParamsAndAPIKeyForHondaRowNo(String sheet, String row) {
        response = apiPage.vinGetOperation(sheet, row);
    }

    @Then("I perform GET for Honda and should see the status code as {int}")
    public void iPerformGETForHondaAndShouldSeeTheStatusCodeAs(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
    }

    @And("read the values of nodes for next request and perform GET Operation for Honda {string} {string}")
    public void readTheValuesOfNodesForNextRequestAndPerformGETOperationForHonda(String sheet, String row) {
        Map<String, String> value = response.jsonPath().getMap("YearMakeModel[0]");
        make = value.get("Make");
        model = value.get("Model");
        year = String.valueOf(value.get("Year"));
        oemId = String.valueOf(value.get("OemId"));
        vin = response.jsonPath().get("Vin[0]");
        response = apiPage.hierarchyGetOperation(sheet, row, vin, make, model, year, oemId);
    }

    @Then("I should see the status code as {int} for Honda")
    public void iShouldSeeTheStatusCodeAsForHonda(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
    }

    @Then("I should see the status code as {int} and get the ID from response and perform GET operation for Honda for Images {string} {string}")
    public void iShouldSeeTheStatusCodeAsAndGetTheIDFromResponseAndPerformGETOperationForHondaForImages(int code, String sheet, String row) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        hierarchyId = response.jsonPath().getString("Id[0]");
        String imageURI = "/" + hierarchyId + "/images";
        response = apiPage.imageGetOperation(sheet, row, vin, make, model, year, oemId, imageURI);
    }

    @Then("I should see the status code as {int} and get ImageID from response and perform GET operation for Honda for Big Images {string} {string}")
    public void iShouldSeeTheStatusCodeAsAndGetImageIDFromResponseAndPerformGETOperationForHondaForBigImages(int code, String sheet, String row) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        imageId = response.jsonPath().getString("Id[0]");
        String bigImageURI = "/" + imageId;
        response = apiPage.bigImageGetOperation(sheet, row, vin, make, model, year, oemId, bigImageURI, hierarchyId);
    }

    @And("I should see the status code as {int} and perform GET operation for Honda for Callout Image {string} {string}")
    public void iShouldSeeTheStatusCodeAsAndPerformGETOperationForHondaForCalloutImage(int code, String sheet, String row) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        String calloutImageURI = "/" + imageId + "/calloutparts";
        response = apiPage.bigImageGetOperation(sheet, row, vin, make, model, year, oemId, calloutImageURI, hierarchyId);
    }

    @Given("I perform GET operation with Params from Excel for Honda {string} {string}")
    public void iPerformGETOperationWithParamsFromExcelForHonda(String sheet, String row) {
        response = apiPage.attributeGetOperation(sheet, row);
    }

    @Then("I should see the status code as {int} and Verify the response for ID for Honda")
    public void iShouldSeeTheStatusCodeAsAndVerifyTheResponseForIDForHonda(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        id = apiPage.getId(response);
    }

    @Given("I perform GET operation for Vehicle with Params from Excel for Honda {string} {string}")
    public void iPerformGETOperationForVehicleWithParamsFromExcelForHonda(String sheet, String row) {
        response = apiPage.vehicleGetOperation(sheet, row);
    }

    @Then("I should see the status code as {int} and get the Attribute ID from response for Honda")
    public void iShouldSeeTheStatusCodeAsAndGetTheAttributeIDFromResponseForHonda(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        List<String> attributeId = response.jsonPath().get("AttributeIds[0]");
        if (id.length() == 1 && id.contains(" ")) {
            id = attributeId.get(0);
        } else {
            id = id + "," + attributeId.get(0);
        }
    }

    @And("read the value of AttributeID node for next request and perform GET operation for Honda {string} {string}")
    public void readTheValueOfAttributeIDNodeForNextRequestAndPerformGETOperationForHonda(String sheet, String row) {
        response = apiPage.hierarchiesYMMGetOperation(sheet, row, id);
    }

    @Then("I should see the status code as {int} and get the previousHierarchyId from response for Honda")
    public void iShouldSeeTheStatusCodeAsAndGetThePreviousHierarchyIdFromResponseForHonda(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        previousHierarchyId = response.jsonPath().getString("Id[0]");
    }

    @And("read the value of previousHierarchyId node for next request and perform GET operation for Honda {string} {string}")
    public void readTheValueOfPreviousHierarchyIdNodeForNextRequestAndPerformGETOperationForHonda(String sheet, String row) {
        response = apiPage.subHierarchiesYMMGetOperation(sheet, row, previousHierarchyId, id);
    }

    @Given("I perform GET operation search for all categories for Honda {string} {string}")
    public void iPerformGETOperationSearchForAllCategoriesForHonda(String sheet, String row) {
        response = apiPage.searchForAllCategoriesGetOperation(sheet, row);
    }
}
