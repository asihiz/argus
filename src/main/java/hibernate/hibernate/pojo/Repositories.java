package hibernate.hibernate.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "repositories")
public class Repositories {

    private Integer id;
    private String title;
    private String description;
    private String tags;
    private String time;
    private String language;
    private String star;

    public Repositories(Integer id, String title, String description, String tags,
                          String time, String language, String star) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.time = time;
        this.language = language;
        this.star = star;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "title", length = 50)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "tags", length = 50)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name = "time", length = 50)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Column(name = "language", length = 50)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "star", length = 50)
    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "Repositories{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags='" + tags + '\'' +
                ", time='" + time + '\'' +
                ", language='" + language + '\'' +
                ", star='" + star + '\'' +
                '}';
    }
}
