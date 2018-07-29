package by.epam.training.model.user.constituents;

import java.io.Serializable;

/**
 * Used to control access of {@link by.epam.training.model.user.User} to certain content
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID of Role.
     */
    private Integer id;

    /**
     * name of Role.
     */
    private String name;

    public Role(){
    }

    public Role(String name) {
        this.id = 0;
        this.name = name;
    }

    public Role(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Return ID of Role.
     *
     * @return ID of Role
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new ID to Role
     *
     * @param id new ID that will instead of current ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return name of Role.
     *
     * @return name of Role
     */
    public String getName() {
        return name;
    }

    /**
     * Set new name to Role
     *
     * @param name new name that will instead of current name
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

        Role role = (Role) obj;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        return name != null ? name.equals(role.name) : role.name == null;
    }

    /**
     * Override {@link Object#hashCode()}<br>
     * All calculations allow for null reference
     *
     * @return integer hashCode of Role object
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

