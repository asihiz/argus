
package base;


import org.apache.log4j.BasicConfigurator;
import selenium_services.PageFactory;
import selenium_services.SeleniumDriver;

public class BaseTest {

    protected final static String BASE_URL = "https://opensource-demo.orangehrmlive.com";
    protected PageFactory pageFactory = new PageFactory();

    protected static void configureLog4J () {
        BasicConfigurator.configure();
    }

    protected void closeDriver(){
        SeleniumDriver.getInstance().close();
    }

}
