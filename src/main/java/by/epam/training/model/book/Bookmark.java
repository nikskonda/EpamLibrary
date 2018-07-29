package by.epam.training.model.book;

import java.io.Serializable;

/**
 * Used to store information about bookmark.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class Bookmark implements Serializable {
    private static final long serialVersionUID = 4065622531901058852L;

    /**
     * Id of User
     */
    private Integer userId;
    /**
     * Id of Book
     */
    private Integer bookId;
    /**
     * Number of page
     */
    private Integer pageNumber;
    /**
     * Locale.
     */
    private String locale;


    public Bookmark() {
    }

    public Bookmark(Integer userId, Integer bookId, Integer pageNumber, String locale) {
        this.userId = userId;
        this.bookId = bookId;
        this.pageNumber = pageNumber;
        this.locale = locale;
    }

    /**
     * Return id of User.
     *
     * @return id of User
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Set new  id to User
     *
     * @param userId new id of User
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Return id of Book.
     *
     * @return id of Book
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * Set new  id to Book
     *
     * @param bookId new id of book
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * Return number of Page.
     *
     * @return number of Page
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * Set new  role to User
     *
     * @param pageNumber new number of page
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Return locale of Page.
     *
     * @return locale of Page
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Set new  role to User
     *
     * @param locale new locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Override {@link Object#equals(Object)}<br>
     * Check all fields of objects<br>
     * All checks allow for null reference
     *
     * @param obj object to compare
     * @return Boolean equality of input and current objects
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Bookmark bookmark = (Bookmark) obj;

        if (userId != null ? !userId.equals(bookmark.userId) : bookmark.userId != null) return false;
        if (bookId != null ? !bookId.equals(bookmark.bookId) : bookmark.bookId != null) return false;
        if (pageNumber != null ? !pageNumber.equals(bookmark.pageNumber) : bookmark.pageNumber != null) return false;
        return locale != null ? locale.equals(bookmark.locale) : bookmark.locale == null;
    }

    /**
     * Override {@link Object#hashCode()}<br>
     * All calculations allow for null reference
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        hashCode = prime * hashCode + (userId != null ? userId.hashCode() : 0);
        hashCode = prime * hashCode + (bookId != null ? bookId.hashCode() : 0);
        hashCode = prime * hashCode + (pageNumber != null ? pageNumber.hashCode() : 0);
        hashCode = prime * hashCode + (locale != null ? locale.hashCode() : 0);
        return hashCode;

    }
}
