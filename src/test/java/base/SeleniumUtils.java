package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.IOException;
import java.util.List;

public interface SeleniumUtils {
    String getPageUrl();

    By getByForLocator(String locator);

    void clickRadioBtn(String locator);

    void clickRadioBtn(WebElement e);

    boolean clickCheckbox(String locator, boolean state);

    void doubleClick(WebElement element);

    void waitUntil(ExpectedCondition<?> expectedCondition, int timeoutSeconds);

    void waitUntil(ExpectedCondition<?> expectedCondition);

    void waitUntilWithAngularCalls(ExpectedCondition<?> expectedCondition);

    WebElement fluentWait(final WebElement element);

    void waitInSec(Integer sec);

    void scrollIntoView(WebElement element);

    void scrollToTheBottom();

    void scrollToTheTop();

    void moveToElementAndForceClick(WebElement element);

    void waitUntilAngularFinishHttpCalls();

    void waitUntilAngularFinishHttpCalls(long timeout);

    void waitAndClick(WebElement el);

    void waitAndClickWithoutAngular(WebElement el);


    <T extends FunctionalLibrary> T goToPreviousPage(Class<T> clazz) throws RuntimeException;

    FunctionalLibrary switchToBrowserTab(int windowNumber);

    void switchToWindow(String handle);

    void refresh();

    FunctionalLibrary switchToTab(int windowNumber);

    boolean isAt(String pageUrl);

    <T extends FunctionalLibrary> void acceptAlertIfPresent(Class<T> clazz) throws RuntimeException;

    String getBaseURL();

    <T extends FunctionalLibrary> void dismissAlertIfPresent(Class<T> clazz) throws RuntimeException;

    WebElement fluentWaits(By locator);

    WebElement fluentWait(By by);

    List<WebElement> getElementsByLocator(String locator);

    WebElement fluentWaitByLocator(String locator);

    FunctionalLibrary click(WebElement element);

    FunctionalLibrary clickBasedOnIndex(String element, int index);

    int getNumberOfElementsByLocator(String locator);

    String formatLocatorWithString(String locator, String formattingStr);

    WebElement getElementByStringFormattedLocator(String locator, String formattingStr);

    List<WebElement> getElementsByLocator(String locator, int index);

    int getSize(List<WebElement> element);

    String readExcel(String sheet, int row, int col) throws IOException;

}
