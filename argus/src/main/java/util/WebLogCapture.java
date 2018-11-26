package util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import page_objects.LoginPage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by asih on 10/09/2017.
 */
public class WebLogCapture {

    private static final List<Integer> illegalReturnCodes = Arrays.asList(400, 401, 404, 403, 500, 501);
    private static StringBuilder sb = new StringBuilder();
    private final static Logger logger = Logger.getLogger(JsonUtils.class);

    private WebLogCapture(){

    }

    private static LogEntries getEntries(WebDriver driver){
        return driver.manage().logs().get("browser");
    }

    public static void analyzeLog(WebDriver driver) {

        String message = null;
        boolean isFailure = false;

        LogEntries logEntries = getEntries(driver);

        for (LogEntry entry : logEntries) {
            message = entry.getMessage();
            sb.append(message);
            logger.info(sb.toString());
        }
        for(Integer retCode : illegalReturnCodes){
            if(sb.toString().contains(retCode.toString())) {
                isFailure = true;
            }
        }
        if (isFailure) {
            String.format("%s", "Console msg has a failure");
        } else {
            String.format("%s", "Console msg has no failures");
        }
    }

    public static void concat(WebDriver driver) {

        LogEntries logEntries = getEntries(driver);
        for (LogEntry entry : logEntries) {
            sb.append(entry.getMessage());
        }
    }

}
