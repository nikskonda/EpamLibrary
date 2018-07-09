package by.epam.java.training.model.book;

import java.io.Serializable;

public class Bookmark implements Serializable {
    private static final long serialVersionUID = 4065622531901058852L;

    private Integer userId;
    private Integer bookId;
    private Integer pageNumber;
    private String locale;


    public Bookmark() {
    }

    public Bookmark(Integer userId, Integer bookId, Integer pageNumber, String locale) {
        this.userId = userId;
        this.bookId = bookId;
        this.pageNumber = pageNumber;
        this.locale = locale;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
