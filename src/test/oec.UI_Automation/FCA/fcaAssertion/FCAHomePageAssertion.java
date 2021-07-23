package FCA.fcaAssertion;

import FCA.fcaObjectRepository.FCAHomePage;
import base.AbstractAssertion;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FCAHomePageAssertion extends AbstractAssertion<FCAHomePage> {

    public FCAHomePageAssertion assertAtHomePage() {
        assertThat((functionalLibrary.isAt(functionalLibrary.getHomepageBaseUrl()))).as("Verify that you are at 'Home' page").isTrue();
        return this;
    }

    public FCAHomePageAssertion assertRequiredFieldArePresent() {
        assertThat(functionalLibrary.isLogoDisplayed()).as("Verify that 'Logo' is present.").isTrue();
        assertThat(functionalLibrary.isMakeYearModelLabelDisplayed()).as("Verify that 'Make Year Model' label is displayed.").isTrue();
        assertThat(functionalLibrary.isVinLabelDisplayed()).as("Verify that 'VIN' label is displayed.").isTrue();
        return this;
    }

    public FCAHomePageAssertion assertSearchLabelArePresent() {
        assertThat(functionalLibrary.isSearchLabelDisplayed()).as("Verify that 'Search By Keyword' label is displayed.").isTrue();
        return this;
    }

    public FCAHomePageAssertion assertSelectedMakeIsDisplayed(String sheet, int row, int col) throws IOException {
        String actualMake = functionalLibrary.getSelectedMake();
        String expectedMake = functionalLibrary.readExcel(sheet, row, col);
        assertThat(actualMake).as("verify that Make displayed in Drop Down is" +
                "\nExpected: " + expectedMake + "\nActual: " + actualMake).isEqualTo(expectedMake);
        return this;
    }

    public FCAHomePageAssertion assertSelectedYearIsDisplayed(String sheet, int row, int col) throws IOException {
        String actualYear = functionalLibrary.getSelectedYear();
        String expectedYear = functionalLibrary.readExcel(sheet, row, col);
        assertThat(actualYear).as("verify that Year displayed in Drop Down is" +
                "\nExpected: " + expectedYear + "\nActual: " + actualYear).isEqualTo(expectedYear);
        return this;
    }

    public FCAHomePageAssertion assertSelectedModelIsDisplayed(String sheet, int row, int col) throws IOException {
        String actualModel = functionalLibrary.getSelectedModel();
        String expectedModel = functionalLibrary.readExcel(sheet, row, col);
        assertThat(actualModel).as("verify that Model displayed in Drop Down is" +
                "\nExpected: " + expectedModel + "\nActual: " + actualModel).isEqualTo(expectedModel);
        return this;
    }

    public FCAHomePageAssertion assertSelectedVehicleCatalogDisplayed(String sheet, int row, int col) throws IOException {
        String actualVehicleCatalogText = functionalLibrary.getVehicleCatalogName();
        String expectedVehicleCatalogText = functionalLibrary.getSearchedVehicleCatalogName(sheet, row, col);
        assertThat(actualVehicleCatalogText).as("verify that Vehicle Catalog displayed is" +
                "\nExpected: " + expectedVehicleCatalogText + "\nActual: " + actualVehicleCatalogText).isEqualTo(expectedVehicleCatalogText);
        assertThat(functionalLibrary.isCategoryNavigationBarDisplayed()).as("Verify that 'Part Categories' is displayed in Category Navigation Bar.").isTrue();
        assertThat(functionalLibrary.getPartCategoriesCount()).
                as("verify List of Part Categories are displayed in Navigation Bar as per Selection of OEM.").isGreaterThan(0);
        return this;
    }

    public FCAHomePageAssertion assertSelectedVINVehicleCatalogDisplayed(String sheet, int row, int col) throws IOException {
        String actualVINVehicleCatalogText = functionalLibrary.getVINVehicleCatalogName();
        String expectedVINVehicleCatalogText = functionalLibrary.getSearchedVINVehicleCatalogName(sheet, row, col);
        assertThat(actualVINVehicleCatalogText).as("verify that VIN Vehicle Catalog displayed is" +
                "\nExpected: " + expectedVINVehicleCatalogText + "\nActual: " + actualVINVehicleCatalogText).isEqualTo(expectedVINVehicleCatalogText);
        assertThat(functionalLibrary.isCategoryNavigationBarDisplayed()).as("Verify that 'Part Categories' is displayed in Category Navigation Bar.").isTrue();
        assertThat(functionalLibrary.getPartCategoriesCount()).
                as("verify List of Part Categories are displayed in Navigation Bar as per Selection of OEM.").isGreaterThan(0);
        return this;
    }

    public FCAHomePageAssertion assertSelectedPartsDisplayed() {
        assertThat(functionalLibrary.getSearchedPartCategoriesCount()).
                as("verify List of Searched Part Categories are displayed as per Selection of OEM.").isGreaterThan(-1);
        return this;
    }

}
