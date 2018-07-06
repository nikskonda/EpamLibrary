package by.epam.java.training.model.news;

import java.io.Serializable;
import java.util.Date;

public class NewsConstr implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String photoUrl;
    private String text;
    private Integer userId;

    public NewsConstr() {
    }

    public NewsConstr(String title, String photoUrl, String text, Integer userId) {
        this.title = title;
        this.photoUrl = photoUrl;
        this.text = text;
        this.userId = userId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
