package by.epam.training.model.book.constituents;

import java.io.Serializable;

/**
 * Used to store a publishing house.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class PublishingHouse implements Serializable {
   private static final long serialVersionUID = 1L;

    /**
     * ID of Publishing House.
     */
   private Integer id;
    /**
     * Name of Publishing House.
     */
   private String name;

    public PublishingHouse() {
    }

    public PublishingHouse(String name) {
        this.name = name;
    }

    public PublishingHouse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Return ID of Publishing House.
     *
     * @return ID of Publishing House
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new ID to Publishing House
     *
     * @param id new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return Name of Publishing House.
     *
     * @return name of Publishing House
     */
    public String getName() {
        return name;
    }

    /**
     * Set new name to Publishing House
     *
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
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

        PublishingHouse that = (PublishingHouse) obj;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;
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
        hashCode = prime * hashCode + (name != null ? name.hashCode() : 0);
        return hashCode;
    }
}
