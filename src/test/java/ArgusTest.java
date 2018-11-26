
import base.BaseTest;
import hibernate.hibernate.pojo.Repositories;
import hibernate.hibernate.service.DbOperations;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page_objects.SearchPage;
import page_objects.LoginPage;
import page_objects.data.RepoData;
import selenium_services.SeleniumDecorator;
import selenium_services.SeleniumDriver;

import java.util.Collections;
import java.util.List;

import static selenium_services.SeleniumDriver.Machine;
import static selenium_services.SeleniumDriver.DriverType;
import static selenium_services.PageFactory.Page;

public class ArgusTest extends BaseTest {

    private final static String BASE_URL = "https://github.com/";

    private LoginPage loginPage;
    private SearchPage searchPage;
    private DbOperations operations = new DbOperations();

    private final static Logger logger = Logger.getLogger(ArgusTest.class);

    @BeforeClass
    public static void before() {
        BaseTest.configureLog4J();
    }

    @Before
    public void setUp() {
        WebDriver driver = SeleniumDriver.getInstance().createDriver(Machine.WINDOWS, DriverType.CHROME);
        SeleniumDecorator.getInstance().setDriver(driver);
        driver.get(BASE_URL);
    }

    private void search(){
        loginPage = pageFactory.createPage(Page.LOGIN);
        loginPage.search("selenium");
    }

    @Test
    public void automationExercise() throws Exception {

        search();
        searchPage = pageFactory.createPage(Page.SEARCH);
        List<RepoData> repoData =  searchPage.getData();
        int index = 1;
        for(RepoData rd : repoData){
            operations.getRepositoriesOperations().
                    insert(new Repositories(
                            index++,
                            rd.getTitle(),
                            rd.getDescription(),
                            String.join(",", rd.getTags()),
                            rd.getTime(),
                            rd.getLanguage(),
                            rd.getStars()));
        }


    }


    @After
    public void tearDown() throws Exception {
        super.closeDriver();
    }


}


