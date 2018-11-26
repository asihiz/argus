package selenium_services;


import org.openqa.selenium.WebDriver;

public abstract class SeleniumBasePage {

    protected WebDriver driver = SeleniumDecorator.getInstance().getDriver();

    public abstract void prepareElements();

}
