package by.epam.java.training.model.news;

import java.io.Serializable;
import java.util.Date;

public class News extends NewsPreview implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private String photoUrl;
    private Integer userId;

    public News() {
    }

    public News(NewsPreview newsPreview){
        super.setId(newsPreview.getId());
        super.setTitle(newsPreview.getTitle());
        super.setThumbsUrl(newsPreview.getThumbsUrl());
        super.setPublishDate(newsPreview.getPublishDate());
        super.setUserFirstName(newsPreview.getUserFirstName());
        super.setUserLastName(newsPreview.getUserLastName());
    }

    public News(Integer id, String title, String thumbsUrl, String userFirstName, String userLastName, Date publishDate, String text, String photoUrl) {
        super(id, title, thumbsUrl, userFirstName, userLastName, publishDate);
        this.text = text;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
