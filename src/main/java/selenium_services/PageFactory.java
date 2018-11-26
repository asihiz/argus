package selenium_services;

import org.apache.log4j.Logger;
import page_objects.SearchPage;
import page_objects.LoginPage;

public class PageFactory {

    private static final String PREPARE_ELEMENTS_METHOD_NAME = "prepareElements";

    private final static Logger logger = Logger.getLogger(PageFactory.class);

    public <T extends SeleniumBasePage> T createPage(Page page) {

        T instance = null;
        try {
            instance = (T) page.getClazz().newInstance();
            page.getClazz().getMethod(PREPARE_ELEMENTS_METHOD_NAME).invoke(instance);
        } catch (Throwable t) {
            logger.error(t.getMessage(), t);
        }
        return instance;
    }

    public enum Page {

        LOGIN (LoginPage.class),
        SEARCH (SearchPage.class);

        private Class<? extends SeleniumBasePage> clazz;

        Page(Class<? extends SeleniumBasePage> clazz){
            this.setClazz(clazz);
        }

        public Class<? extends SeleniumBasePage> getClazz() {
            return clazz;
        }

        public void setClazz(Class<? extends SeleniumBasePage> clazz) {
            this.clazz = clazz;
        }
    }

}