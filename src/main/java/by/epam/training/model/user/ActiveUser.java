package by.epam.training.model.user;

import by.epam.training.model.user.constituents.Role;

import java.io.Serializable;

/**
 * Used to store information about active user.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID of User.
     */
    private Integer id;
    /**
     * login of User.
     */
    private String login;
    /**
     * role of User.
     */
    private Role role;

    public ActiveUser() {
    }


    public ActiveUser(Integer id, String login) {
        this.id = id;
        this.login = login;
    }

    public ActiveUser(Integer id, String login, Role role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    /**
     * Return ID of User.
     *
     * @return ID of User
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set new ID to User
     *
     * @param id new ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Return login of User.
     *
     * @return login of User
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set new login to User
     *
     * @param login new login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Return role of User.
     *
     * @return role of User
     */
    public Role getRole() {
        return role;
    }

    /**
     * Set new  role to User
     *
     * @param role new Role
     */
    public void setRole(Role role) {
        this.role = role;
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

        ActiveUser that = (ActiveUser) obj;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return role != null ? role.equals(that.role) : that.role == null;
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
        hashCode = prime * hashCode + (login != null ? login.hashCode() : 0);
        hashCode = prime * hashCode + (id != null ? id.hashCode() : 0);
        hashCode = prime * hashCode + (role != null ? role.hashCode() : 0);
        return hashCode;
    }
}
