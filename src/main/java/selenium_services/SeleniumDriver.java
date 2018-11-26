package selenium_services;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.*;

import java.io.File;
import java.util.concurrent.TimeUnit;


/**
 * Created by asi on 2/19/2017.
 */
public class SeleniumDriver {

    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROME_PATH_PROP = "chrome_path";
    private WebDriver driver;
    private final static Logger logger = Logger.getLogger(SeleniumDriver.class);

    // Thread safe
    private static final SeleniumDriver INSTANCE = new SeleniumDriver();

    private SeleniumDriver() {
        // Avoid reflection calls on Singleton(
        // Private constructor can be called by reflection
        // Solves thread safe issues
        if(INSTANCE != null){
            return;
        }
    }

    public static SeleniumDriver getInstance() {
        return INSTANCE;
    }


    public <T extends WebDriver> T createDriver(Machine machine, DriverType dt)  {

        try {
            switch (dt){
                case CHROME:
                    driver = createChromeDriver(machine);
                    break;
                case FIRE_FOX:
                    driver = createFireFoxDriver();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        configureDriver(driver);
        return (T) driver;
    }

    private <T extends WebDriver> void configureDriver(T driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS);
        print(DriverType.CHROME);
    }


    private RemoteWebDriver createChromeDriver(Machine machine)  {
        System.setProperty(CHROME_DRIVER, new File(machine.driverPath).getAbsolutePath());
        return new ChromeDriver();
    }

    private FirefoxDriver createFireFoxDriver() {
        return new FirefoxDriver();
    }

    public void close() {
        driver.close();
        driver.quit();
    }

    private static void print(DriverType type) {

        logger.info("=========================================================");
        logger.info("============= Created New " + type.name() + " Driver =================");
        logger.info( "=========================================================");
        logger.info("========== Starting New Test In " + type.name() + " Driver ===========");
        logger.info("=========================================================");
        logger.info(type.name() + " driver was created");
    }

    public enum DriverType {

        FIRE_FOX, CHROME;
    }

    public enum Machine {

        WINDOWS("src/test/resources/environment/win/chromedriver.exe"),
        LINUX32("src/test/resources/environment/linux32/chromedriver"),
        LINUX64("src/test/resources/environment/linux64/chromedriver");

        public String driverPath;

        Machine(String driverPath) {
            this.driverPath = driverPath;
        }

    }
}
