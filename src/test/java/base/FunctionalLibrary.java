package base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utilsBrowser.browserSetting.JavaScriptLogger;
import utilsBrowser.configuration.Configuration;
import utilsBrowser.configuration.ConfigurationProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofSeconds;
import static stepDefinitions.AbstractTest.jslogging;

public class FunctionalLibrary implements SeleniumUtils {
    private static final Log logger = LogFactory.getLog(FunctionalLibrary.class);
    public static final String environment = getEnvironment();

    protected static ConfigurationProvider configurationProvider = new ConfigurationProvider();
    protected Configuration configuration = configurationProvider.getConfiguration(environment);

    private static final int WAIT_UNTIL_TIMEOUT = 30;
    private static final int ANGULAR_WAIT = (System.getProperty("environment").contains("online")) ? 40 : 30;
    private final String BASE_URL = configuration.getUrl();

    public WebDriver driver;
    protected Actions action;

    public JavascriptExecutor jsExecutor;

    public FunctionalLibrary(WebDriver driver) {
        action = new Actions(driver);
        PageFactory.initElements(getElementLocatorFactory(driver), this);
        if (jslogging) {
            JavaScriptLogger.extractJSLogs(driver);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private static String getEnvironment() {
        return System.getProperty("environment");
    }

    private WebDriver getElementLocatorFactory(WebDriver driver) {
        this.driver = driver;
        this.jsExecutor = (JavascriptExecutor) driver;
        return driver;
    }

    @Override
    public void clickRadioBtn(String locator) {
        logger.info("Click radio button with specified option.");
        WebElement e = driver.findElement(getByForLocator(locator));
        waitInSec(5);
        e.click();
    }

    @Override
    public void clickRadioBtn(WebElement e) {
        logger.info("Click radio button with specified option.");
        e.click();
        waitUntilAngularFinishHttpCalls();
    }

    @Override
    public By getByForLocator(String locator) {
        logger.info(": Getting By based on locator provider.");
        return (locator.startsWith("//") ? By.xpath(locator) : By.cssSelector(locator));
    }

    @Override
    public String formatLocatorWithString(String locator, String formattingStr) {
        return String.format(locator, formattingStr);
    }

    @Override
    public WebElement getElementByStringFormattedLocator(String locator, String formattingStr) {
        return driver.findElement(getByForLocator(formatLocatorWithString(locator, formattingStr)));
    }

    @Override
    public boolean clickCheckbox(String locator, boolean state) {
        logger.info("Click checkbox with specified option.");
        WebElement e = driver.findElement(getByForLocator(locator));
        if (!(e.isSelected() == state)) {
            e.click();
            waitUntilAngularFinishHttpCalls();
        }
        return false;
    }

    @Override
    public void waitUntil(ExpectedCondition<?> expectedCondition, int timeoutSeconds) {
        logger.info(": waiting " + timeoutSeconds + " s until : " + expectedCondition);
        new WebDriverWait(driver, timeoutSeconds).until(expectedCondition);
    }

    @Override
    public void waitUntil(ExpectedCondition<?> expectedCondition) {
        logger.info(": waiting " + WAIT_UNTIL_TIMEOUT + " s until expected condition " + expectedCondition + " is met.");
        waitUntil(expectedCondition, WAIT_UNTIL_TIMEOUT);
    }

    @Override
    public void waitUntilWithAngularCalls(ExpectedCondition<?> expectedCondition) {
        logger.info(": waiting until angular calls are finished and expected condition: " + expectedCondition + " is met.");
        waitUntil(expectedCondition);
        waitUntilAngularFinishHttpCalls();
    }

    @Override
    public WebElement fluentWait(WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(ofSeconds(45))
                .pollingEvery(ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> {
            logger.info(": waiting for element " + element
                    + " with locator : \" + locator + \" with timeout 30s polling every 1 s.");
            return element;
        });
    }

    @Override
    public void waitInSec(Integer sec) {
        logger.info(": waiting for " + sec + " with thread sleep for 1 sec");
        long milisec = sec.longValue() * 1000;
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveToElementAndForceClick(WebElement element) {
        logger.info(": move to element action");
        action.moveToElement(element).click().build().perform();
    }

    @Override
    public void doubleClick(WebElement element) {
        logger.info(": double click on element action");
        action.doubleClick(element).perform();
    }

    @Override
    public void scrollIntoView(WebElement element) {
        logger.info(": scroll to element using java script executor");
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Override
    public void scrollToTheBottom() {
        logger.info(": scroll to the bottom of the screen using java script executor");
        jsExecutor.executeScript("window.scrollBy(0,+500)", "");
    }

    @Override
    public void scrollToTheTop() {
        logger.info(": scroll to the top of the screen using java script executor");
        jsExecutor.executeScript("window.scrollBy(0,-500)", "");
    }

    @Override
    public void waitUntilAngularFinishHttpCalls() {
        waitUntilAngularFinishHttpCalls(ANGULAR_WAIT);
    }

    @Override
    public void waitUntilAngularFinishHttpCalls(long timeout) {
        logger.info(": waiting until angular calls finishes with timeout " + timeout);
        final String javaScriptToLoadAngular =
                "var injector = window.angular.element('body').injector();" +
                        "var $http = injector.get('$http');" +
                        "return ($http.pendingRequests.length === 0)";

        ExpectedCondition<Boolean> pendingHttpCallsCondition = driver -> jsExecutor
                .executeScript(javaScriptToLoadAngular).equals(true);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(pendingHttpCallsCondition);
    }

    @Override
    public void waitAndClick(WebElement el) {
        waitAndClickWithoutAngular(el);
        waitUntilAngularFinishHttpCalls();
    }

    @Override
    public void waitAndClickWithoutAngular(WebElement el) {
        logger.info(": waiting until non angular calls are made and element is clickable");
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT_UNTIL_TIMEOUT, 500);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
            wait.until(ExpectedConditions.elementToBeClickable(el)).click();
        } catch (WebDriverException wde) {
            scrollIntoView(el);
            el.click();
        }
    }

    @Override
    public <T extends FunctionalLibrary> T goToPreviousPage(Class<T> clazz) throws RuntimeException {
        logger.info(": go to previous page");
        driver.navigate().back();
        T page = null;
        try {
            page = clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public FunctionalLibrary switchToBrowserTab(int windowNumber) {
        logger.info(": switch to tab number: " + windowNumber);
        waitInSec(3);
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        if (windowHandles.size() > 1) {
            List<String> windowHandlesTemp;
            int i = 0;
            do {
                i++;
                windowHandlesTemp = new ArrayList<>(driver.getWindowHandles());
            } while (windowHandlesTemp.size() < windowNumber && i < 10);

            switchToWindow(windowHandlesTemp.get(windowNumber - 1));
        }
        return this;
    }

    @Override
    public void switchToWindow(String handle) {
        logger.info(": switch to window with handle: " + handle);
        try {
            driver.switchTo().window(handle);
        } catch (NoSuchWindowException e) {
            waitInSec(5);
            logger.info("Attempt to switch to window...");
            driver.switchTo().window(handle);
        }
    }

    @Override
    public void refresh() {
        logger.info("Refresh page");
        driver.navigate().refresh();
    }

    @Override
    public FunctionalLibrary switchToTab(int windowNumber) {
        waitInSec(5);
        List<String> windowHandles;
        int i = 0;
        do {
            i++;
            windowHandles = new ArrayList<>(driver.getWindowHandles());
        } while (windowHandles.size() < windowNumber && i < 10);

        switchToWindow(windowHandles.get(windowNumber - 1));

        return this;
    }


    @Override
    public boolean isAt(String pageUrl) {
        logger.info(": Page is At ");
        String actualUrl = getPageUrl();
        logger.info("Page Class: expected base url: " + pageUrl);
        logger.info("Page Class: actual url: " + actualUrl);
        return actualUrl.contains(pageUrl);
    }

    @Override
    public String getPageUrl() {
        logger.info(": Getting current URL.");
        waitInSec(3);
        return driver.getCurrentUrl();
    }

    @Override
    public String getBaseURL() {
        logger.info(": Getting Base URL");
        return BASE_URL;
    }

    public <G extends FunctionalLibrary, T extends AbstractAssertion<G>> T startAssertions(Class<T> clazz) throws RuntimeException {
        try {
            AbstractAssertion<G> assertion = clazz.newInstance();
            assertion.setPage((G) this);
            return (T) assertion;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error occurred during creating Assertions. ", e);
        }
    }

    @Override
    public <T extends FunctionalLibrary> void acceptAlertIfPresent(Class<T> clazz) throws RuntimeException {
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }
        T functionalLibrary = null;
        try {
            functionalLibrary = clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends FunctionalLibrary> void dismissAlertIfPresent(Class<T> clazz) throws RuntimeException {
        try {
            waitInSec(2);
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
        } catch (Exception e) {
            driver.switchTo().defaultContent();
        }
        T functionalLibrary = null;
        try {
            functionalLibrary = clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WebElement fluentWaits(By locator) {
        WebElement foo;
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(ofSeconds(30))
                .pollingEvery(ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        foo = wait.until(driver -> {
            logger.info(": waiting for element with locator : " + locator + " with timeout 30s polling every 1 s.");
            return driver.findElement(locator);
        });
        return foo;
    }

    @Override
    public WebElement fluentWait(By by) {
        logger.info(": waiting for element with locator is available.");
        return fluentWaits(by);
    }

    @Override
    public List<WebElement> getElementsByLocator(String locator) {
        logger.info(": Getting list of web elements base on locator.");
        waitInSec(2);
        By by = getByForLocator(locator);
        fluentWait(by);
        return driver.findElements(by);
    }

    @Override
    public List<WebElement> getElementsByLocator(String locator, int index) {
        logger.info(": Getting list of web elements base on locator.");
        waitInSec(2);
        By by = getByForLocator(String.format(locator, index));
        fluentWait(by);
        return driver.findElements(by);
    }

    public boolean isElementEnabled(final String locator) {
        By by = getByForLocator(locator);
        return driver.findElement(by).isEnabled();
    }

    @Override
    public WebElement fluentWaitByLocator(String locator) {
        logger.info(": waiting for element with locator " + locator + " is available.");
        return fluentWait(getByForLocator(locator));
    }

    @Override
    public int getSize(List<WebElement> element) {
        logger.info(": getSize method starts.");
        waitInSec(15);
        return element.size();
    }

    @Override
    public FunctionalLibrary click(WebElement element) {
        logger.info(": click method starts");
        fluentWait(element);
        element.click();
        return this;
    }

    @Override
    public FunctionalLibrary clickBasedOnIndex(String element, int index) {
        logger.info(": clickBasedOnIndex method start");
        waitInSec(20);
        return click(driver.findElement(By.xpath(String.format(element, index))));
    }

    @Override
    public int getNumberOfElementsByLocator(String locator) {
        logger.info(": Getting number of web elements with locator method start.");
        By by = getByForLocator(locator);
        return driver.findElements(by).size();
    }

    @Override
    public String readExcel(String sheet, int row, int col) throws IOException {
        logger.info(": Read Data from Excel method start");
        File f = new File(configuration.getExcelPath());
        FileInputStream stream = new FileInputStream(f);
        Workbook w = new XSSFWorkbook(stream);
        Sheet s = w.getSheet(sheet);
        String name = null;
        Row r = s.getRow(row);
        Cell c = r.getCell(col);
        CellType type = c.getCellType();
        if (type == CellType.STRING) {
            name = c.getStringCellValue();

        } else if (type == CellType.NUMERIC) {
            double d = c.getNumericCellValue();
            long l = (long) d;
            name = String.valueOf(l);
        }
        return name;
    }
}
