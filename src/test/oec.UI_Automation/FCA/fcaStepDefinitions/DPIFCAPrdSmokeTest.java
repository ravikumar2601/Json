package FCA.fcaStepDefinitions;

import FCA.fcaAssertion.FCAHomePageAssertion;
import FCA.fcaAssertion.FCALoginPageAssertion;
import FCA.fcaObjectRepository.FCAHomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepDefinitions.AbstractTest;

import java.io.IOException;

public class DPIFCAPrdSmokeTest extends AbstractTest {

    @Given("Launch the Chrome Browser and Go to the url & verify that the login screen is displayed with all required fields")
    public void launch_the_Chrome_Browser_and_Go_to_the_url_verify_that_the_login_screen_is_displayed_with_all_required_fields() {
        super.setUp();
        fcaLoginPage.startAssertions(FCALoginPageAssertion.class)
                .assertAtLoginPage()
                .assertRequiredFieldsArePresent()
                .endAssertion();
    }

    @When("Enter the Username and Password")
    public void enter_the_Username_and_Password() {
        fcaLoginPage.inputUserName(configuration.getUserName())
                .inputPassword(configuration.getPassword());
    }

    @When("Click login then confirm that the user landed on Home Page")
    public void click_login_then_confirm_that_the_user_landed_on_Home_Page() {
        fcaLoginPage.loginButton();
        fcaHomePage = new FCAHomePage(driver);
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertAtHomePage()
                .assertRequiredFieldArePresent()
                .endAssertion();
    }

    @When("Click on the Make,Year,Model dropdown for the OEM, read the make and select from it {string}, {int}")
    public void click_on_the_Make_Year_Model_dropdown_for_the_OEM_read_the_make_and_select_from_it(String sheet, int row) throws IOException {
        fcaHomePage.inputMake(sheet, row, 6);
    }

    @Then("User should be able to view the selected make from the dropdown {string}, {int}")
    public void user_should_be_able_to_view_the_selected_make_from_the_dropdown(String sheet, int row) throws IOException {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertSelectedMakeIsDisplayed(sheet, row, 6)
                .endAssertion();
    }

    @When("Read the Year from excel and Select Year from the Year dropdown {string}, {int}")
    public void read_the_Year_from_excel_and_Select_Year_from_the_Year_dropdown(String sheet, int row) throws IOException {
        fcaHomePage.inputYear(sheet, row, 7);
    }

    @Then("User should be able to view the selected Year from the dropdown {string}, {int}")
    public void user_should_be_able_to_view_the_selected_Year_from_the_dropdown(String sheet, int row) throws IOException {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertSelectedYearIsDisplayed(sheet, row, 7)
                .endAssertion();
    }

    @When("Read the model from excel and Select model from the Model dropdown {string}, {int}")
    public void read_the_model_from_excel_and_Select_model_from_the_Model_dropdown(String sheet, int row) throws IOException {
        fcaHomePage.inputModel(sheet, row, 8);
    }

    @Then("User should be able to view the selected Model from the dropdown {string}, {int}")
    public void user_should_be_able_to_view_the_selected_Model_from_the_dropdown(String sheet, int row) throws IOException {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertSelectedModelIsDisplayed(sheet, row, 8)
                .endAssertion();
    }

    @When("Click on the search icon")
    public void click_on_the_search_icon() {
        fcaHomePage.searchBtnClick();
    }

    @Then("User should be able to view the top part categories listed under the Parts search tab {string}, {int}")
    public void user_should_be_able_to_view_the_top_part_categories_listed_under_the_Parts_search_tab(String sheet, int row) throws IOException {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertSelectedVehicleCatalogDisplayed(sheet, row, 6)
                .endAssertion();
    }

    @Then("User should be able to click and select on Part, Sub-part, Illustrations, Callout Parts categories and verify right callout parts are selected on the right pane and click on back button to repeat the same for remaining part categories")
    public void user_should_be_able_to_click_and_select_on_Part_Sub_part_Illustrations_Callout_Parts_categories_and_verify_right_callout_parts_are_selected_on_the_right_pane_and_click_on_back_button_to_repeat_the_same_for_remaining_part_categories() {
        fcaHomePage.clickPartSubPartCategories();
    }

    @Then("Verify the user returns back to the first page in the Parts search tab for the selected YMM and able to view {string} filter option")
    public void verify_the_user_returns_back_to_the_first_page_in_the_Parts_search_tab_for_the_selected_YMM_and_able_to_view_filter_option(String string) {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertRequiredFieldArePresent()
                .assertSearchLabelArePresent()
                .endAssertion();
    }

    @When("Enter the keyword in the search box and click on search {string}")
    public void enter_the_keyword_in_the_search_box_and_click_on_search(String string) {
        fcaHomePage.inputSearchKeyword(string);
    }

    @Then("User should be able to view the parts returned for the Keyword")
    public void user_should_be_able_to_view_the_parts_returned_for_the_Keyword() {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertSelectedPartsDisplayed()
                .endAssertion();
    }

    @When("Read the VIN from excel and enter the VIN in the field and click search button {string}, {int}")
    public void read_the_VIN_from_excel_and_enter_the_VIN_in_the_field_and_click_search_button(String sheet, int row) throws IOException {
        fcaHomePage.inputVIN(sheet, row, 1)
                .searchBtnClick();
    }

    @Then("User should be able to view the top part categories listed under the Parts search tab {string}, {int}, {int}")
    public void user_should_be_able_to_view_the_top_part_categories_listed_under_the_Parts_search_tab(String sheet, int row, int col) throws IOException {
        fcaHomePage.startAssertions(FCAHomePageAssertion.class)
                .assertSelectedVINVehicleCatalogDisplayed(sheet, row, col)
                .endAssertion();
    }

    @When("Click Logout and then confirm that the user landed on Login Page")
    public void click_Logout_And_Then_Confirm_That_The_User_Landed_On_Login_Page() {
        fcaHomePage.logoutBtnClick();
        fcaLoginPage.startAssertions(FCALoginPageAssertion.class)
                .assertAtLoginPage()
                .assertRequiredFieldsArePresent()
                .endAssertion();
        super.close();
    }

}
