package by.epam.training.model.book;

import java.io.Serializable;

/**
 * Used to store information about Book preview.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class BookPreview implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Id of Book.
     */
    private Integer id;

    /**
     * Name of Book.
     */
    private String name;

    /**
     * Publish Year of Book.
     */
    private Integer publishYear;

    /**
     * Proce of Book.
     */
    private Double price;

    /**
     * Cover of Book.
     */
    private String coverUrl;


    public BookPreview() {
    }

    public BookPreview(Integer id, String name, Integer publishYear, Double price, String coverUrl) {
        this.id = id;
        this.name = name;
        this.publishYear = publishYear;
        this.price = price;
        this.coverUrl = coverUrl;
    }

    /**
     * Return ID of Book.
     *
     * @return ID of Book
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new ID to Book
     *
     * @param id new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return name of Book.
     *
     * @return name of Book
     */
    public String getName() {
        return name;
    }

    /**
     * Set new name to Book
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return publish year of Book.
     *
     * @return publish year of Book
     */
    public Integer getPublishYear() {
        return publishYear;
    }

    /**
     * Set new publish Year to Book
     *
     * @param publishYear new publish Year
     */
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * Return price of Book.
     *
     * @return price of Book
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set new price to Book
     *
     * @param price new price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Return cover of Book.
     *
     * @return cover of Book
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * Set new cover to Book
     *
     * @param coverUrl new cover
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

        BookPreview that = (BookPreview) obj;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (publishYear != null ? !publishYear.equals(that.publishYear) : that.publishYear != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return coverUrl != null ? coverUrl.equals(that.coverUrl) : that.coverUrl == null;
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
        hashCode = prime * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = prime * hashCode + (name != null ? name.hashCode() : 0);
        hashCode = prime * hashCode + (publishYear != null ? publishYear.hashCode() : 0);
        hashCode = prime * hashCode + (price != null ? price.hashCode() : 0);
        hashCode = prime * hashCode + (coverUrl != null ? coverUrl.hashCode() : 0);
        return hashCode;
    }
}
