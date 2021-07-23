package FORD.fordObjectRepository;

import base.FunctionalLibrary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FORDLoginPage extends FunctionalLibrary {
    private static final Log logger = LogFactory.getLog(FORDLoginPage.class);

    private static final String USERNAME_ID = "userName";
    private static final String USERNAME_FIELD_XPATH = "//div/label[@for='userName']";
    private static final String PASSWORD_ID = "password";
    private static final String PASSWORD_FIELD_XPATH = "//div/label[@for='password']";
    private static final String LOGIN_BTN_ID = "login";
    private static final String LOGIN_IMAGE_ID = "loginImage";
    private static final String LOGIN_BODY_XPATH = "//div/h2[contains(text(),'Welcome to RepairLink')]";

    @FindBy(id = USERNAME_ID)
    private WebElement userNameInput;
    @FindBy(xpath = USERNAME_FIELD_XPATH)
    private WebElement userNameField;
    @FindBy(id = PASSWORD_ID)
    private WebElement passwordInput;
    @FindBy(xpath = PASSWORD_FIELD_XPATH)
    private WebElement passwordField;
    @FindBy(id = LOGIN_BTN_ID)
    private WebElement loginBtn;
    @FindBy(id = LOGIN_IMAGE_ID)
    private WebElement loginImage;
    @FindBy(xpath = LOGIN_BODY_XPATH)
    private WebElement loginBody;

    public FORDLoginPage(WebDriver driver) {
        super(driver);
    }

    public FORDLoginPage inputUserName(String username) {
        logger.info(": inputUserName method start");
        userNameInput.clear();
        userNameInput.sendKeys(username);
        return this;
    }

    public FORDLoginPage inputPassword(String password) {
        logger.info(": inputPassword method start");
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public FORDLoginPage loginButton() {
        logger.info(": loginButton method start");
        loginBtn.click();
        return this;
    }

    public boolean isUserNameDisplayed() {
        logger.info(": isUserNameDisplayed method start");
        fluentWait(userNameField);
        return userNameField.isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        logger.info(": isPasswordDisplayed method start");
        fluentWait(passwordField);
        return passwordField.isDisplayed();
    }

    public boolean isLoginBtnDisplayed() {
        logger.info(": isLoginBtnDisplayed method start");
        fluentWait(loginBtn);
        return loginBtn.isDisplayed();
    }

    public boolean isLoginImageDisplayed() {
        logger.info(": isLoginImageDisplayed method start");
        fluentWait(loginImage);
        return loginImage.isDisplayed();
    }

    public boolean isLoginBodyDisplayed() {
        logger.info(": isLoginBodyDisplayed method start");
        fluentWait(loginBody);
        return loginBody.isDisplayed();
    }

}
