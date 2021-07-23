package apiStepDefinitions;

import apiAssertion.DPIAPIPrdSmokeAssertion;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import stepDefinitions.AbstractAPITest;

import java.util.List;
import java.util.Map;

public class DPIAPIPrdSmokeTest extends AbstractAPITest {

    public static String make = null;
    public static String model = null;
    public static String year = null;
    public static String oemId = null;
    public static String vin = null;
    public static String id = " ";
    public static String hierarchyId = null;
    public static String subHierarchyId = null;
    public static String imageId = null;
    public static String previousHierarchyId = null;

    @Given("I perform GET operation with Params and APIKey {string} {string}")
    public void iPerformGETOperationWithParamsAndAPIKeyRowNo(String sheet, String row) {
        response = apiPage.vinGetOperation(sheet, row);
    }

    @Then("I perform GET and should see the status code as {int}")
    public void iPerformGETAndShouldSeeTheStatusCodeAs(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
    }

    @And("read the values of nodes for next request and perform GET Operation {string} {string}")
    public void readTheValuesOfNodesForNextRequestAndPerformGETOperation(String sheet, String row) {
        Map<String, String> value = response.jsonPath().getMap("YearMakeModel[0]");
        make = value.get("Make");
        model = value.get("Model");
        year = String.valueOf(value.get("Year"));
        oemId = String.valueOf(value.get("OemId"));
        vin = response.jsonPath().get("Vin[0]");
        response = apiPage.hierarchyGetOperation(sheet, row, vin, make, model, year, oemId);
    }

    @And("read the value of nodes for next request and perform GET operation {string} {string}")
    public void readTheValueOfNodeForNextRequestAndPerformGETOperation(String sheet, String row) {
        hierarchyId = response.jsonPath().getString("Id[0]");
        response = apiPage.subHierarchyGetOperation(sheet, row, vin, make, model, year, oemId, hierarchyId);
    }

    @Then("I should see the status code as {int}")
    public void iShouldSeeTheStatusCodeAs(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
    }

    @Then("I should see the status code as {int} and get the ID from response and perform GET operation for Images {string} {string}")
    public void iShouldSeeTheStatusCodeAsAndGetTheIDFromResponseAndPerformGETOperationForImages(int code, String sheet, String row) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        subHierarchyId = response.jsonPath().getString("Id[0]");
        String imageURI = "/" + subHierarchyId + "/images";
        response = apiPage.imageGetOperation(sheet, row, vin, make, model, year, oemId, imageURI);
    }

    @Then("I should see the status code as {int} and get ImageID from response and perform GET operation for Big Images {string} {string}")
    public void iShouldSeeTheStatusCodeAsAndGetImageIDFromResponseAndPerformGETOperationForBigImages(int code, String sheet, String row) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        imageId = response.jsonPath().getString("Id[0]");
        String bigImageURI = "/" + imageId;
        response = apiPage.bigImageGetOperation(sheet, row, vin, make, model, year, oemId, bigImageURI, subHierarchyId);
    }

    @And("I should see the status code as {int} and perform GET operation for Callout Image {string} {string}")
    public void iShouldSeeTheStatusCodeAsAndPerformGETOperationForCalloutImage(int code, String sheet, String row) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        String calloutImageURI = "/" + imageId + "/calloutparts";
        response = apiPage.bigImageGetOperation(sheet, row, vin, make, model, year, oemId, calloutImageURI, subHierarchyId);
    }

    @Given("I perform GET operation with Params from Excel {string} {string}")
    public void iPerformGETOperationWithParamsFromExcel(String sheet, String row) {
        response = apiPage.attributeGetOperation(sheet, row);
    }

    @Then("I should see the status code as {int} and Verify the response for ID")
    public void iShouldSeeTheStatusCodeAsAndVerifyTheResponseForID(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        id = apiPage.getId(response);
    }

    @Given("I perform GET operation for Vehicle with Params from Excel {string} {string}")
    public void iPerformGETOperationForVehicleWithParamsFromExcel(String sheet, String row) {
        response = apiPage.vehicleGetOperation(sheet, row);
    }

    @Then("I should see the status code as {int} and get the Attribute ID from response")
    public void iShouldSeeTheStatusCodeAsAndGetTheAttributeIDFromResponse(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        List<String> attributeId = response.jsonPath().get("AttributeIds[0]");
        if (id.length() == 1 && id.contains(" ")) {
            id = attributeId.get(0);
        } else {
            id = id + "," + attributeId.get(0);
        }
    }

    @And("read the value of AttributeID node for next request and perform GET operation {string} {string}")
    public void readTheValueOfAttributeIDNodeForNextRequestAndPerformGETOperation(String sheet, String row) {
        response = apiPage.hierarchiesYMMGetOperation(sheet, row, id);
    }

    @Then("I should see the status code as {int} and get the previousHierarchyId from response")
    public void iShouldSeeTheStatusCodeAsAndGetThePreviousHierarchyIdFromResponse(int code) {
        DPIAPIPrdSmokeAssertion.assertStatusCode(response.getStatusCode(), code);
        previousHierarchyId = response.jsonPath().getString("Id[0]");
    }

    @And("read the value of previousHierarchyId node for next request and perform GET operation {string} {string}")
    public void readTheValueOfPreviousHierarchyIdNodeForNextRequestAndPerformGETOperation(String sheet, String row) {
        response = apiPage.subHierarchiesYMMGetOperation(sheet, row, previousHierarchyId, id);
    }

    @Given("I perform GET operation search for all categories {string} {string}")
    public void iPerformGETOperationSearchForAllCategories(String sheet, String row) {
        response = apiPage.searchForAllCategoriesGetOperation(sheet, row);
    }
}
