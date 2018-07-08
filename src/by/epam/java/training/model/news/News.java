package by.epam.java.training.model.news;

import java.io.Serializable;
import java.util.Date;

public class News extends NewsCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;
    private String photoUrl;

    public News() {
    }

    public News(Integer id, String title, String smallPhotoUrl, String userFirstName, String userLastName, Date publishDate, String text, String photoUrl) {
        super(id, title, smallPhotoUrl, userFirstName, userLastName, publishDate);
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
}
