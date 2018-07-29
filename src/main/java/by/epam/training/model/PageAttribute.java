package by.epam.training.model;

import java.io.Serializable;

/**
 * Used to store information about page.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class PageAttribute implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Number of current page.
     */
    private Integer numberOfPage;

    /**
     * Count items in one page.
     */
    private Integer countOnPage;

    /**
     * Locale.
     */
    private String locale;

    public PageAttribute() {
    }

    public PageAttribute(Integer numberOfPage, Integer countOnPage, String locale) {
        this.numberOfPage = numberOfPage;
        this.countOnPage = countOnPage;
        this.locale = locale;
    }

    /**
     * Return number of Page.
     *
     * @return number of Page
     */
    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    /**
     * Set new  role to User
     *
     * @param numberOfPage new number of page
     */
    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    /**
     * Return count items on Page.
     *
     * @return count items on Page
     */
    public Integer getCountOnPage() {
        return countOnPage;
    }

    /**
     * Set new  role to User
     *
     * @param countOnPage new count items
     */
    public void setCountOnPage(Integer countOnPage) {
        this.countOnPage = countOnPage;
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

        PageAttribute that = (PageAttribute) obj;

        if (numberOfPage != null ? !numberOfPage.equals(that.numberOfPage) : that.numberOfPage != null) return false;
        if (countOnPage != null ? !countOnPage.equals(that.countOnPage) : that.countOnPage != null) return false;
        return locale != null ? locale.equals(that.locale) : that.locale == null;
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
        hashCode = prime * hashCode + (numberOfPage != null ? numberOfPage.hashCode() : 0);
        hashCode = prime * hashCode + (countOnPage != null ? countOnPage.hashCode() : 0);
        hashCode = prime * hashCode + (locale != null ? locale.hashCode() : 0);
        return hashCode;
    }
}
