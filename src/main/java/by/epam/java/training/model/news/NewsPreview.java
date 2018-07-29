package by.epam.java.training.model.news;

import java.io.Serializable;
import java.util.Date;

public class NewsPreview implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String thumbsUrl;
    private String userFirstName;
    private String userLastName;
    private Date publishDate;

    public NewsPreview() {
    }

    public NewsPreview(Integer id, String title, String thumbsUrl, String userFirstName, String userLastName, Date publishDate) {
        this.id = id;
        this.title = title;
        this.thumbsUrl = thumbsUrl;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbsUrl() {
        return thumbsUrl;
    }

    public void setThumbsUrl(String thumbsUrl) {
        this.thumbsUrl = thumbsUrl;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
