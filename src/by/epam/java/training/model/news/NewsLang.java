package by.epam.java.training.model.news;

import java.io.Serializable;

public class NewsLang implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String text;
    private String lang;


    public NewsLang() {
    }

    public NewsLang(Integer id, String title, String text, String lang) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.lang = lang;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
