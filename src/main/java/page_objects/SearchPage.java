package page_objects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import page_objects.data.RepoData;
import selenium_services.SeleniumBasePage;
import selenium_services.SeleniumDecorator;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends SeleniumBasePage {

    private static final int NUM_OF_PAGES = 1;
    private int pageNumber = 0;
    private int repoNumber = 0;
    private int tagNumber = 0;

    private final By nextLocator = By.className("next_page");
    private final By repoLocator = By.className("repo-list-item");
    private final By tagsContainerLocator = By.className("topics-row-container");
    private final By titleLocator = By.className("v-align-middle");
    private final By descriptionLocator = By.className("d-inline-block");
    private final By tagsLocator = By.className("topic-tag-link");
    private final By starsLocator = By.className("muted-link");
    private final By languageLocator = By.className("flex-autor");

    private final String timeAttName = "datetime";
    private List<WebElement> repos;

    private List<RepoData> repoData = new ArrayList<>();

    private WebElement container;
    private WebElement next;

    private final static Logger logger = Logger.getLogger(SearchPage.class);

    public SearchPage(){

    }

    @Override
    public void prepareElements() {
        next = super.driver.findElement(nextLocator);
    }

    public List<RepoData> getData() {
        for(int i = 0 ; i < NUM_OF_PAGES ; i++){
            repos = super.driver.findElements(repoLocator);
            repoNumber = 0;
            for(WebElement repo : repos){
                repoData.add(new RepoData(
                        getTitle(repo),
                        getDescription(repo),
                        getTags(repo),
                        getTime(repo),
                        getLanguage(repo),
                        getStar(repo)));
                repoNumber++;
            }
            next();
        }
        return repoData;
    }

    private void next(){
        prepareElements();
        next.click();
        pageNumber++;
        logger.info("move to next page");
    }

    private WebElement getCurrentRepo(){
        return super.driver.findElements(repoLocator).get(repoNumber);
    }

    private WebElement getCurrentTag(){
        return getCurrentRepo().findElement(tagsContainerLocator).findElements(tagsLocator).get(tagNumber);
    }

    // link handling (A bit more complex) avoid stale element
    // avoid stale element

    private String doLinkFlow(WebElement repo, By itemLocator){
        WebElement item = repo.findElement(itemLocator);
        SeleniumDecorator.getInstance().measurePageLoad(item);
        item = getCurrentRepo().findElement(itemLocator);
        SeleniumDecorator.getInstance().assertConsole(item);
        return getCurrentRepo().findElement(itemLocator).getText();
    }

    private String doInnerLinkFlow(){
        SeleniumDecorator.getInstance().measurePageLoad(getCurrentTag());
        SeleniumDecorator.getInstance().assertConsole(getCurrentTag());
        return getCurrentTag().getText();
    }

    private String getTitle(WebElement repo){
        return doLinkFlow(repo, titleLocator);
    }

    private String getDescription(WebElement repo){
        WebElement description = null;
        try {
            description = getCurrentRepo().findElement(descriptionLocator);
        } catch (Exception e){
            return "Description not exists";
        }
        return description.getText();
    }

    private List<String> getTags(WebElement repo){
        List<String> tagsText = new ArrayList<>();
        WebElement tagContainer = null;
        try {
            tagNumber = 0;
            tagContainer = getCurrentRepo().findElement(tagsContainerLocator);
        } catch (Exception e){
            logger.warn("No Tags Found");
            return tagsText;
        }
        List<WebElement> tags = tagContainer.findElements(tagsLocator);
        for(WebElement tag : tags){
            tagsText.add(doInnerLinkFlow());
            tagNumber++;
        }
        return tagsText;
    }

    private String getLanguage(WebElement repo){
        WebElement language = getCurrentRepo().findElement(languageLocator);
        return language.getText();
    }

    private String getTime(WebElement repo){
        WebElement time = SeleniumDecorator.getInstance().findElementByAttr(getCurrentRepo(), timeAttName);
        return time.getText();
    }

    private String getStar(WebElement repo){
        return doLinkFlow(getCurrentRepo(), starsLocator);
    }


}
