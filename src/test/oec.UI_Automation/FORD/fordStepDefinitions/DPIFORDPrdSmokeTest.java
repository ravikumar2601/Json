package FORD.fordStepDefinitions;

import FORD.fordAssertion.FORDHomePageAssertion;
import FORD.fordAssertion.FORDLoginPageAssertion;
import FORD.fordObjectRepository.FORDHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.AbstractTest;

import java.io.IOException;

public class DPIFORDPrdSmokeTest extends AbstractTest {

    @Given("Launch the Chrome Browser and Go to the url & verify that the login screen is displayed with all required fields")
    public void LaunchTheChromeBrowserAndGoToTheUrlVerifyThatTheLoginScreenIsDisplayedWithAllRequiredFields() {
        super.setUp();
        fordLoginPage.startAssertions(FORDLoginPageAssertion.class)
                .assertAtLoginPage()
                .assertRequiredFieldsArePresent()
                .endAssertion();
    }

    @When("Enter the Username and Password")
    public void EnterTheUsernameAndPassword() {
        fordLoginPage.inputUserName(configuration.getUserName())
                .inputPassword(configuration.getPassword());
    }

    @When("Click login then confirm that the user landed on Home Page")
    public void ClickLoginThenConfirmThatTheUserLandedOnHomePage() {
        fordLoginPage.loginButton();
        fordHomePage = new FORDHomePage(driver);
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertAtHomePage()
                .assertRequiredFieldArePresent()
                .endAssertion();
    }

    @When("Click on the Make,Year,Model dropdown for the OEM, read the make and select from it {string}, {int}")
    public void ClickOnTheMakeYearModelDropdownForTheOEMReadTheMakeAndSelectFromIt(String sheet, int row) throws IOException {
        fordHomePage.inputMake(sheet, row, 6);
    }

    @Then("User should be able to view the selected make from the dropdown {string}, {int}")
    public void UserShouldBeAbleToViewTheSelectedMakeFromTheDropdown(String sheet, int row) throws IOException {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertSelectedMakeIsDisplayed(sheet, row, 6)
                .endAssertion();
    }

    @When("Read the Year from excel and Select Year from the Year dropdown {string}, {int}")
    public void ReadTheYearFromExcelAndSelectYearFromTheYearDropdown(String sheet, int row) throws IOException {
        fordHomePage.inputYear(sheet, row, 7);
    }

    @Then("User should be able to view the selected Year from the dropdown {string}, {int}")
    public void UserShouldBeAbleToViewTheSelectedYearFromTheDropdown(String sheet, int row) throws IOException {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertSelectedYearIsDisplayed(sheet, row, 7)
                .endAssertion();
    }

    @When("Read the model from excel and Select model from the Model dropdown {string}, {int}")
    public void ReadTheModelFromExcelAndSelectModelFromTheModelDropdown(String sheet, int row) throws IOException {
        fordHomePage.inputModel(sheet, row, 8);
    }

    @Then("User should be able to view the selected Model from the dropdown {string}, {int}")
    public void UserShouldBeAbleToViewTheSelectedModelFromTheDropdown(String sheet, int row) throws IOException {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertSelectedModelIsDisplayed(sheet, row, 8)
                .endAssertion();
    }

    @When("Click on the search icon")
    public void ClickOnTheSearchIcon() {
        fordHomePage.searchBtnClick();
    }

    @Then("User should be able to view the top part categories listed under the Parts search tab {string}, {int}")
    public void UserShouldBeAbleToViewTheTopPartCategoriesListedUnderThePartsSearchTab(String sheet, int row) throws IOException {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertSelectedVehicleCatalogDisplayed(sheet, row, 6)
                .endAssertion();
    }

    @Then("User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories")
    public void UserShouldBeAbleToClickAndSelectOnPartSubPartIllustrationsCalloutPartsCategoriesAndVerifyRightCalloutPartsAreSelectedOnTheRightPaneAndClickOnBackButtonToRepeatTheSameForRemainingPartCategories() {
        fordHomePage.clickPartSubPartCategories();
    }

    @Then("Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view {string} filter option")
    public void VerifyTheUserReturnsBackToTheFirstPageInThePartsSearchTabForTheSelectedYMMAndAbleToViewFilterOption(String string) {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertRequiredFieldArePresent()
                .assertSearchLabelArePresent()
                .endAssertion();
    }

    @When("Enter the keyword in the search box and click on search {string}")
    public void EnterTheKeywordInTheSearchBoxAndClickOnSearch(String string) {
        fordHomePage.inputSearchKeyword(string);
    }

    @Then("User should be able to view the parts returned for the Keyword")
    public void UserShouldBeAbleToViewThePartsReturnedForTheKeyword() {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertSelectedPartsDisplayed()
                .endAssertion();
    }

    @When("Read the VIN from excel and enter the VIN in the field and click search button {string}, {int}")
    public void ReadTheVINFromExcelAndEnterTheVINInTheFieldAndClickSearchButton(String sheet, int row) throws IOException {
        fordHomePage.inputVIN(sheet, row, 1)
                .searchBtnClick();
    }

    @Then("User should be able to view the top part categories listed under the Parts search tab {string}, {int}, {int}")
    public void UserShouldBeAbleToViewTheTopPartCategoriesListedUnderThePartsSearchTab(String sheet, int row, int col) throws IOException {
        fordHomePage.startAssertions(FORDHomePageAssertion.class)
                .assertSelectedVINVehicleCatalogDisplayed(sheet, row, col)
                .endAssertion();
    }

    @When("Click Logout and then confirm that the user landed on Login Page")
    public void ClickLogoutAndThenConfirmThatTheUserLandedOnLoginPage() {
        fordHomePage.logoutBtnClick();
        fordLoginPage.startAssertions(FORDLoginPageAssertion.class)
                .assertAtLoginPage()
                .assertRequiredFieldsArePresent()
                .endAssertion();
        super.close();
    }

}
