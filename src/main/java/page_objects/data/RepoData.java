package page_objects.data;

import org.apache.log4j.Logger;
import selenium_services.PageFactory;

import java.util.List;

public class RepoData {

    private String title;
    private String description;
    private List<String> tags;
    private String time;
    private String language;
    private String stars;

    private final static Logger logger = Logger.getLogger(RepoData.class);

    public RepoData(String title, String description, List<String> tags,
                    String time, String language, String stars) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.time = time;
        this.language = language;
        this.stars = stars;
        logger.info(this.toString());
    }

    public RepoData(){

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getTime() {
        return time;
    }

    public String getLanguage() {
        return language;
    }

    public String getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return "RepoData{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", time='" + time + '\'' +
                ", language='" + language + '\'' +
                ", stars='" + stars + '\'' +
                '}';
    }
}
