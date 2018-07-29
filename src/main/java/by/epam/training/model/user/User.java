package by.epam.training.model.user;

import by.epam.training.model.user.constituents.Role;

import java.io.Serializable;
import java.util.Date;

/**
 * Used to store information about user.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class User extends ActiveUser implements Serializable {
    private static final long serialVersionUID = 208742088604645L;

    /**
     * First name of User.
     */
    private String firstName;

    /**
     * Last name of User.
     */
    private String lastName;

    /**
     * Email of User.
     */
    private String email;

    /**
     * Registration date of User.
     */
    private Date registrationDate;

    public User() {
    }

    public User(ActiveUser activeUser){
        super.setId(activeUser.getId());
        super.setLogin(activeUser.getLogin());
        super.setRole(activeUser.getRole());
    }


    public User(Integer id, String login, String firstName, String lastName, String email, Role role, Date registrationDate) {
        super(id, login, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    /**
     * Return first name of User.
     *
     * @return first name of User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set new first name to User
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return Last name of User.
     *
     * @return Last name of User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set new Last name to User
     *
     * @param lastName new Last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Return email of User.
     *
     * @return email of User
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set new email to User
     *
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return Registration date of User.
     *
     * @return Registration date of User
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Set new Registration date to User
     *
     * @param registrationDate new Registration date
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
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

        User user = (User) obj;

        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return registrationDate != null ? registrationDate.equals(user.registrationDate) : user.registrationDate == null;
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
        hashCode = prime * hashCode + (firstName != null ? firstName.hashCode() : 0);
        hashCode = prime * hashCode + (lastName != null ? lastName.hashCode() : 0);
        hashCode = prime * hashCode + (email != null ? email.hashCode() : 0);
        return hashCode;
    }
}


