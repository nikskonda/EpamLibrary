package by.epam.java.training.model.news;

import java.io.Serializable;
import java.util.Date;

public class News extends NewsCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private String photoUrl;
    private Integer userId;

    public News() {
    }

    public News(NewsCover newsCover){
        super.setId(newsCover.getId());
        super.setTitle(newsCover.getTitle());
        super.setThumbsUrl(newsCover.getThumbsUrl());
        super.setPublishDate(newsCover.getPublishDate());
        super.setUserFirstName(newsCover.getUserFirstName());
        super.setUserLastName(newsCover.getUserLastName());
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
