package page_objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import selenium_services.SeleniumBasePage;

public class LoginPage extends SeleniumBasePage {

    private final By searchFieldLocator = By.name("q");
    private final By searchButtonLocator = By.id("jump-to-suggestion-search-global");

    private WebElement searchField;
    private WebElement searchButton;

    private SearchPage homePage;

    public LoginPage(){

    }

    @Override
    public void prepareElements() {
       searchField = super.driver.findElement(searchFieldLocator);
    }

    public void search(String searchString){
        this.searchField.sendKeys(searchString);
        searchButton = super.driver.findElement(searchButtonLocator);
        this.searchButton.click();
    }

}
