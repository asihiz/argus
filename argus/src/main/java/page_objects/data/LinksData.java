//package page_objects.data;
//
//import org.openqa.selenium.WebElement;
//
//public class LinksData implements Comparable<LinksData>{
//
//    private Integer pageNumber;
//    private WebElement element;
//
//    public LinksData(Integer pageNumber, WebElement element) {
//        this.pageNumber = pageNumber;
//        this.element = element;
//    }
//
//    public Integer getPageNumber() {
//        return pageNumber;
//    }
//
//    public WebElement getElement() {
//        return element;
//    }
//
//    @Override
//    public int compareTo(LinksData l) {
//        if (getPageNumber() == null || l.getPageNumber() == null) {
//            return 0;
//        }
//        return getPageNumber().compareTo(l.getPageNumber());
//    }
//
//}
