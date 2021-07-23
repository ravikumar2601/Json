package FCA.fcaObjectRepository;

import base.FunctionalLibrary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FCAHomePage extends FunctionalLibrary {
    private static final Log logger = LogFactory.getLog(FCAHomePage.class);

    private final String HOMEPAGE_BASE_URL = configuration.getHomePageBaseurl();
    private static final String HOMEPAGE_LOGO_URL = "//a/img[@alt='RepairLink Logo']";
    private static final String MAKE_YEAR_MODEL_LABEL_XPATH = "//div/label[@for='ddMake']";
    private static final String VIN_LABEL_XPATH = "//div/label[@for='txtVin']";
    private static final String SEARCH_KEYWORD_LABEL_XPATH = "//label[@class='partsHeader__element partsHeader__lblSearch']";
    private static final String MAKE_DROPDOWN_ID = "ddMake";
    private static final String YEAR_DROPDOWN_ID = "ddYear";
    private static final String VIN_INPUT_TEXTBOX_ID = "txtVin";
    private static final String MODEL_DROPDOWN_ID = "ddModel";
    private static final String ATTRIBUTE_DROPDOWN_ID = "ddAttr";
    private static final String ENGINE_DROPDOWN_ID = "Engine";
    private static final String ENGINE_DROPDOWN_CLOSE_XPATH = "(//div//a[@class='close'])[2]";
    private static final String SEARCH_ICON_BUTTON_CSS = "#submitPartsSearch";
    private static final String VEHICLE_CATALOG_XPATH = "//div/span/span[@class='catalog__vehicleText']";
    private static final String VIN_VEHICLE_CATALOG_XPATH = "(//div/span/span[@class='catalog__vehicleText'])[2]";
    private static final String LOGOUT_BUTTON_ID = "logoutLink";
    private static final String CATEGORY_NAVIGATION_BAR_XPATH = "//div/h4/span[contains(text(),'Part Categories')]";
    private static final String PART_CATEGORIES_XPATH = "//div/a[@class='lnkHierarchy list-group-item']";
    private static final String LIST_OF_PART_CATEGORIES_SIZE_XPATH = "//div[@id='categoryNavBar']//div//a/span[@style='float: right']";
    private static final String LIST_OF_PART_CATEGORIES_XPATH = "(//div[@id='categoryNavBar']//div//a/span[@style='float: right'])[%d]";
    private static final String LIST_OF_SUB_PART_CATEGORIES_SIZE_XPATH = "//div[@class='list-group-subcat']//a";
    private static final String LIST_OF_SUB_PART_CATEGORIES_XPATH = "(//div[@class='list-group-subcat']//a)[%d]";
    private static final String ACTIVE_PART_CATEGORIES_XPATH = "//div/a[@onclick='collapseCategoryIfActive()']";
    private static final String ACTIVE_THUMBNAIL_SIZE_XPATH = "//div[@class='thumbnail']";
    private static final String ACTIVE_THUMBNAIL_XPATH = "(//div[@class='thumbnail'])[%d]";
    private static final String SELECTED_CALLOUT_PART_XPATH = "(//span[@class='selectCallout'])[1]";
    private static final String PART_IMAGE_ID = "partImage";
    private static final String SELECTED_ANOTHER_CALLOUT_PART_XPATH = "//div[@rel='4']";
    private static final String BACK_BUTTON_ID = "partImageBackBtn";
    private static final String HIGHLIGHTED_CALLOUT_XPATH = "//li[@class='highlight']";
    private static final String SEARCH_BOX_INPUT_XPATH = "//input[@class='form-control partsHeader__element partsHeader__inputSearch']";
    private static final String ALL_PART_CATEGORY_RADIO_BUTTON_CSS = "#partsHeader__searchOption--all";
    private static final String SEARCH_BUTTON_ID = "btnPartKeywordSearch";
    private static final String SEARCHED_PARTS_XPATH = "//tr[@class='tblRow']";

    @FindBy(xpath = HOMEPAGE_LOGO_URL)
    private WebElement homePageLogo;
    @FindBy(xpath = MAKE_YEAR_MODEL_LABEL_XPATH)
    private WebElement makeYearModelLabel;
    @FindBy(xpath = VIN_LABEL_XPATH)
    private WebElement vinLabel;
    @FindBy(xpath = SEARCH_KEYWORD_LABEL_XPATH)
    private WebElement searchLabel;
    @FindBy(id = MAKE_DROPDOWN_ID)
    private WebElement makeDropDown;
    @FindBy(id = YEAR_DROPDOWN_ID)
    private WebElement yearDropDown;
    @FindBy(id = VIN_INPUT_TEXTBOX_ID)
    private WebElement vinInputTextBox;
    @FindBy(id = MODEL_DROPDOWN_ID)
    private WebElement modelDropDown;
    @FindBy(id = ATTRIBUTE_DROPDOWN_ID)
    private WebElement attributeDropDown;
    @FindBy(id = ENGINE_DROPDOWN_ID)
    private WebElement engineDropDown;
    @FindBy(xpath = ENGINE_DROPDOWN_CLOSE_XPATH)
    private WebElement engineDropDownClose;
    @FindBy(css = SEARCH_ICON_BUTTON_CSS)
    private WebElement searchIconBtn;
    @FindBy(xpath = VEHICLE_CATALOG_XPATH)
    private WebElement vehicleCatalog;
    @FindBy(xpath = VIN_VEHICLE_CATALOG_XPATH)
    private WebElement vinVehicleCatalog;
    @FindBy(id = LOGOUT_BUTTON_ID)
    private WebElement logoutBtn;
    @FindBy(xpath = CATEGORY_NAVIGATION_BAR_XPATH)
    private WebElement categoryNavBar;
    @FindBy(xpath = PART_CATEGORIES_XPATH)
    private WebElement partCategories;
    @FindBy(xpath = LIST_OF_PART_CATEGORIES_SIZE_XPATH)
    private List<WebElement> listOFPartCategories;
    @FindBy(xpath = LIST_OF_SUB_PART_CATEGORIES_SIZE_XPATH)
    private List<WebElement> listOFSubPartCategories;
    @FindBy(xpath = ACTIVE_PART_CATEGORIES_XPATH)
    private WebElement activePartCategories;
    @FindBy(xpath = ACTIVE_THUMBNAIL_SIZE_XPATH)
    private List<WebElement> thumbnail;
    @FindBy(xpath = SELECTED_CALLOUT_PART_XPATH)
    private WebElement callOut1;
    @FindBy(id = PART_IMAGE_ID)
    private WebElement partImage;
    @FindBy(xpath = SELECTED_ANOTHER_CALLOUT_PART_XPATH)
    private WebElement callOut2;
    @FindBy(id = BACK_BUTTON_ID)
    private WebElement backBtn;
    @FindBy(xpath = HIGHLIGHTED_CALLOUT_XPATH)
    private List<WebElement> highlightedCallout;
    @FindBy(xpath = SEARCH_BOX_INPUT_XPATH)
    private WebElement searchBox;
    @FindBy(id = SEARCH_BUTTON_ID)
    private WebElement searchBtn;
    @FindBy(xpath = SEARCHED_PARTS_XPATH)
    private WebElement searchedPart;

    public FCAHomePage(WebDriver driver) {
        super(driver);
    }

    public String getHomepageBaseUrl() {
        logger.info(": getHomepageBaseUrl method start");
        return HOMEPAGE_BASE_URL;
    }

    public boolean isLogoDisplayed() {
        logger.info(": isLogoDisplayed method start");
        fluentWait(homePageLogo);
        return homePageLogo.isDisplayed();
    }

    public boolean isMakeYearModelLabelDisplayed() {
        logger.info(": isMakeYearModelLabelDisplayed method start");
        fluentWait(makeYearModelLabel);
        return makeYearModelLabel.isDisplayed();
    }

    public boolean isVinLabelDisplayed() {
        logger.info(": isVinLabelDisplayed method start");
        fluentWait(vinLabel);
        return vinLabel.isDisplayed();
    }

    public boolean isSearchLabelDisplayed() {
        logger.info(": isSearchLabelDisplayed method start");
        fluentWait(searchLabel);
        return searchLabel.isDisplayed();
    }

    public List<String> readDataFromExcel(String sheet, int row, int col) throws IOException {
        logger.info(": readDataFromExcel method start");
        List<String> datas = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            String data = readExcel(sheet, row, col);
            col++;
            datas.add(data);
        }
        return datas;
    }

    public FCAHomePage inputMake(String sheet, int row, int col) throws IOException {
        logger.info(": inputMake method start");
        String make = readExcel(sheet, row, col);
        waitAndClickWithoutAngular(makeDropDown);
        makeDropDown.click();
        Select makeSelection = new Select(makeDropDown);
        makeSelection.selectByVisibleText(make);
        makeYearModelLabel.click();
        return this;
    }

    public String getSelectedMake() {
        logger.info(": getSelectedMake method start");
        waitAndClickWithoutAngular(makeDropDown);
        Select makeSelected = new Select(makeDropDown);
        String make = makeSelected.getFirstSelectedOption().getText();
        return make;
    }

    public FCAHomePage inputYear(String sheet, int row, int col) throws IOException {
        logger.info(": inputYear method start");
        String year = readExcel(sheet, row, col);
        waitAndClickWithoutAngular(yearDropDown);
        yearDropDown.click();
        Select yearSelection = new Select(yearDropDown);
        yearSelection.selectByVisibleText(year);
        makeYearModelLabel.click();
        return this;
    }

    public String getSelectedYear() {
        logger.info(": getSelectedYear method start");
        waitAndClickWithoutAngular(yearDropDown);
        Select yearSelected = new Select(yearDropDown);
        String year = yearSelected.getFirstSelectedOption().getText();
        return year;
    }

    public FCAHomePage inputModel(String sheet, int row, int col) throws IOException {
        logger.info(": inputModel method start");
        String model = readExcel(sheet, row, col);
        waitAndClickWithoutAngular(modelDropDown);
        modelDropDown.click();
        Select modelSelection = new Select(modelDropDown);
        modelSelection.selectByVisibleText(model);
        try {
            if (attributeDropDown != null) {
                waitAndClickWithoutAngular(attributeDropDown);
                Select attributeSelection = new Select(attributeDropDown);
                attributeSelection.selectByIndex(1);
            }
            makeYearModelLabel.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
        waitInSec(5);
        return this;
    }

    public String getSelectedModel() {
        logger.info(": getSelectedModel method start");
        waitAndClickWithoutAngular(modelDropDown);
        Select modelSelected = new Select(modelDropDown);
        String model = modelSelected.getFirstSelectedOption().getText();
        waitInSec(5);
        return model;
    }

    public FCAHomePage inputVIN(String sheet, int row, int col) throws IOException {
        logger.info(": inputVIN method start");
        String vin = readExcel(sheet, row, col);
        waitAndClickWithoutAngular(vinInputTextBox);
        vinInputTextBox.clear();
        vinInputTextBox.sendKeys(vin);
        return this;
    }

    public FCAHomePage searchBtnClick() {
        logger.info(": searchBtnClick method start");
        waitInSec(5);
        fluentWait(searchIconBtn);
        searchIconBtn.click();
        return this;
    }

    public String getVehicleCatalogName() {
        logger.info(": getVehicleCatalogName method start");
        waitAndClickWithoutAngular(vehicleCatalog);
        String vehicleCatalogName = vehicleCatalog.getText();
        return vehicleCatalogName;
    }

    public String getVINVehicleCatalogName() {
        logger.info(": getVINVehicleCatalogName method start");
        waitAndClickWithoutAngular(vinVehicleCatalog);
        String vinVehicleCatalogText = vinVehicleCatalog.getText();
        return vinVehicleCatalogText;
    }

    public String getSearchedVehicleCatalogName(String sheet, int row, int col) throws IOException {
        logger.info(": getSearchedVehicleCatalogName method start");
        List<String> textData = readDataFromExcel(sheet, row, col);
        String searchedVehicleCatalogText = textData.get(1) + " " + textData.get(0) + " " + textData.get(2);
        return searchedVehicleCatalogText;
    }

    public String getSearchedVINVehicleCatalogName(String sheet, int row, int col) throws IOException {
        logger.info(": getSearchedVINVehicleCatalogName method start");
        String textData = readExcel(sheet, row, col);
        String searchedVINVehicleCatalogText = "(VIN: " + textData + ")";
        return searchedVINVehicleCatalogText;
    }

    public boolean isCategoryNavigationBarDisplayed() {
        logger.info(": isCategoryNavigationBarDisplayed method start");
        fluentWait(categoryNavBar);
        return categoryNavBar.isDisplayed();
    }

    public int getPartCategoriesCount() {
        logger.info(": getPartCategoriesCount method start");
        fluentWaitByLocator(PART_CATEGORIES_XPATH);
        return getNumberOfElementsByLocator(PART_CATEGORIES_XPATH);
    }

    public FCAHomePage clickPartSubPartCategories() {
        logger.info(": clickPartSubPartCategories method start");
        //int parts = getSize(listOFPartCategories);
        for (int i = 1; i < 3; i++) {
            clickBasedOnIndex(LIST_OF_PART_CATEGORIES_XPATH, i);
            //int subParts = getSize(listOFSubPartCategories);
            for (int j = 1; j < 2; j++) {
                clickBasedOnIndex(LIST_OF_SUB_PART_CATEGORIES_XPATH, j);
                try {
                    if (engineDropDown != null) {
                        waitInSec(5);
                        engineDropDownClose.click();
                    }
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
                int illustration = getSize(thumbnail);
                if (illustration == 0) {
                    break;
                } else {
                    for (int k = 1; k < 2; k++) {
                        clickBasedOnIndex(ACTIVE_THUMBNAIL_XPATH, k);
                        waitInSec(3);
                        fluentWait(callOut1);
                        callOut1.click();
                        /*callOut2.click();
                        if (getSize(highlightedCallout) > 0) {
                            backBtn.click();
                        }
                        try {
                            if (engineDropDown != null) {
                                waitInSec(5);
                                engineDropDownClose.click();
                            }
                        } catch (NoSuchElementException e) {
                            e.printStackTrace();
                        }*/
                        fluentWait(backBtn);
                        backBtn.click();
                        try {
                            if (engineDropDown != null) {
                                waitInSec(5);
                                engineDropDownClose.click();
                            }
                        } catch (NoSuchElementException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            waitInSec(5);
            waitUntil(ExpectedConditions.elementToBeClickable(activePartCategories));
            activePartCategories.click();
        }
        return this;
    }

    public FCAHomePage inputSearchKeyword(String string) {
        logger.info(": inputSearchKeyword method start");
        searchBox.clear();
        searchBox.sendKeys(string);
        clickRadioBtn(ALL_PART_CATEGORY_RADIO_BUTTON_CSS);
        searchBtn.click();
        return this;
    }

    public int getSearchedPartCategoriesCount() {
        logger.info(": getSearchedPartCategoriesCount method start");
        fluentWaitByLocator(SEARCHED_PARTS_XPATH);
        return getNumberOfElementsByLocator(SEARCHED_PARTS_XPATH);
    }

    public FCALoginPage logoutBtnClick() {
        logger.info(": logoutBtnClick method start");
        waitInSec(5);
        scrollToTheTop();
        fluentWait(logoutBtn);
        logoutBtn.click();
        return new FCALoginPage(getDriver());
    }

}
