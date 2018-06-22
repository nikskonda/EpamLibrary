package by.epam.java.training.model.news;

import java.io.Serializable;
import java.util.Date;

public class News extends NewsCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private String text;

    public News() {
    }

    public News(Integer id, String title, String photoUrl, String userFirstName, String userLastName, Date publishDate, String text) {
        super(id, title, photoUrl, userFirstName, userLastName, publishDate);
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
