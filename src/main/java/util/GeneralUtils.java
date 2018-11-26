
/**
 * General Utils
 * Performs util actions for test
 *
 * @author ASIH
 *
 */


package util;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.io.PrintWriter;
import java.io.StringWriter;

public class GeneralUtils {

	private static final Logger logger = Logger.getLogger(GeneralUtils.class);

	private static boolean isTestFailed = false;

	private GeneralUtils(){
       // avoid non static behaviour
	}

	public static String stacktraceToString(Throwable t){
		StringWriter errors = new StringWriter();
		t.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}

	public static <T extends Throwable> void handleError(String error , T t)  throws AssertionError{
		logger.error(error);
		logger.error(GeneralUtils.stacktraceToString(t));
		Assert.assertFalse(error + GeneralUtils.stacktraceToString(t), true);
	}

	public static <T extends Throwable> void handleError(String error)  throws AssertionError{
		logger.error(error);
		Assert.assertFalse(error, true);
	}

}
