package by.epam.training.model.news;

import java.io.Serializable;
import java.util.Date;

/**
 * Used to store information about news.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class News extends NewsPreview implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Text of News.
     */
    private String text;

    /**
     * Photo of News.
     */
    private String photoUrl;

    /**
     * ID of User.
     */
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

    /**
     * Return text of News.
     *
     * @return text of News
     */
    public String getText() {
        return text;
    }

    /**
     * Set new text to News
     *
     * @param text new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Return Photo of News.
     *
     * @return photo of News
     */
    public String getPhotoUrl() {
        return photoUrl;
    }

    /**
     * Set new Photo to News
     *
     * @param photoUrl new Photo
     */
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * Return ID of User.
     *
     * @return ID of User
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Set new ID to News
     *
     * @param userId new ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
        if (!super.equals(obj)) return false;

        News news = (News) obj;

        if (text != null ? !text.equals(news.text) : news.text != null) return false;
        if (photoUrl != null ? !photoUrl.equals(news.photoUrl) : news.photoUrl != null) return false;
        return userId != null ? userId.equals(news.userId) : news.userId == null;
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
        int hashCode = super.hashCode();
        hashCode = prime * hashCode + (text != null ? text.hashCode() : 0);
        hashCode = prime * hashCode + (photoUrl != null ? photoUrl.hashCode() : 0);
        hashCode = prime * hashCode + (userId != null ? userId.hashCode() : 0);
        return hashCode;
    }
}
