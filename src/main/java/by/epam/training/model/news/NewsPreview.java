package by.epam.training.model.news;

import java.io.Serializable;
import java.util.Date;

/**
 * Used to store information about news preview.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class NewsPreview implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID of News.
     */
    private Integer id;
    /**
     * Title of News.
     */
    private String title;
    /**
     * Thumbs of News.
     */
    private String thumbsUrl;
    /**
     * User first name of News.
     */
    private String userFirstName;
    /**
     * User last name of News.
     */
    private String userLastName;
    /**
     * Publish date of News.
     */
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

    /**
     * Return ID of News.
     *
     * @return ID of News
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new ID to News
     *
     * @param id new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return title of News.
     *
     * @return title of News
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set new title to News
     *
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return thumbs of News.
     *
     * @return thumbs of News
     */
    public String getThumbsUrl() {
        return thumbsUrl;
    }

    /**
     * Set new thumbs to News
     *
     * @param thumbsUrl new thumbs
     */
    public void setThumbsUrl(String thumbsUrl) {
        this.thumbsUrl = thumbsUrl;
    }

    /**
     * Return user first name of News.
     *
     * @return user first name of News
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Set new user first name to News
     *
     * @param userFirstName new user first name
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * Return user last name of News.
     *
     * @return user last name News
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Set new user last name to News
     *
     * @param userLastName new userLastName
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Return publish date of News.
     *
     * @return publish date of News
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * Set new publish date to News
     *
     * @param publishDate new publish date
     */
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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

        NewsPreview that = (NewsPreview) obj;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (thumbsUrl != null ? !thumbsUrl.equals(that.thumbsUrl) : that.thumbsUrl != null) return false;
        if (userFirstName != null ? !userFirstName.equals(that.userFirstName) : that.userFirstName != null)
            return false;
        if (userLastName != null ? !userLastName.equals(that.userLastName) : that.userLastName != null) return false;
        return publishDate != null ? publishDate.equals(that.publishDate) : that.publishDate == null;
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
        hashCode = prime * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = prime * hashCode + (title != null ? title.hashCode() : 0);
        hashCode = prime * hashCode + (thumbsUrl != null ? thumbsUrl.hashCode() : 0);
        hashCode = prime * hashCode + (userFirstName != null ? userFirstName.hashCode() : 0);
        hashCode = prime * hashCode + (userLastName != null ? userLastName.hashCode() : 0);
        hashCode = prime * hashCode + (publishDate != null ? publishDate.hashCode() : 0);
        return hashCode;
    }
}
