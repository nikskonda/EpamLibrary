package by.epam.training.model.book.constituents;

import java.io.Serializable;

/**
 * Used to store a genre.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class Genre implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * ID of Genre.
     */
    private Integer id;

    /**
     * Name of Genre.
     */
    private String name;

    public Genre() {
    }

    public Genre(Integer id) {
        this.id = id;
    }

    public Genre(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Return ID of Genre.
     *
     * @return ID of Genre
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new ID to Genre
     *
     * @param id new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return Name of Genre.
     *
     * @return name of Genre
     */
    public String getName() {
        return name;
    }

    /**
     * Set new name to Genre
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

        Genre genre = (Genre) obj;

        if (id != null ? !id.equals(genre.id) : genre.id != null) return false;
        return name != null ? name.equals(genre.name) : genre.name == null;
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
