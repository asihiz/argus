package selenium_services;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.WebLogCapture;

import java.util.logging.Logger;

public class SeleniumDecorator {

    private static final SeleniumDecorator INSTANCE = new SeleniumDecorator();
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SeleniumDecorator.class);

    private WebDriver driver;

    private SeleniumDecorator() {
        // Avoid reflection calls on Singleton(
        // Private constructor can be called by reflection
        // Solves thread safe issues
        if(INSTANCE != null){
            return;
        }
    }

    public static SeleniumDecorator getInstance(){
        return INSTANCE;
    }

    public  <T extends WebDriver> void setDriver(T driver) {
        this.driver = driver;
    }

    public <T extends WebDriver> T getDriver() {
        return (T) driver;
    }

    public WebElement findElementByAttr(WebElement element, String attName) {
        return element.findElement(By.xpath("//element[@attribute=' " + attName + "']"));
    }

    public boolean isElementContainsLink() {
        return true;
    }

    public boolean wait(long timeout, By locator) {
        new WebDriverWait(driver, timeout)
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    d.findElement(locator).getText();
                    return true;
                });
        return false;
    }

    public void assertConsole(WebElement link) {
        link.click();
        WebLogCapture.analyzeLog(SeleniumDecorator.getInstance().getDriver());
        driver.navigate().back();
    }

    public void measurePageLoad(WebElement link) {
        link.click();
        Long loadtime = (Long)((JavascriptExecutor)driver).executeScript(
                "return performance.timing.loadEventEnd - performance.timing.navigationStart;");
        logger.info("Load time is " + loadtime);
        driver.navigate().back();
    }

}
