package by.epam.training.model.user.form;

import by.epam.training.model.user.constituents.Role;

import java.io.Serializable;

/**
 * Used to store sign up form.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class SignUpForm extends SignInForm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Confirm Password of User.
     */
    private String confirmPassword;

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
     * Role of User.
     */
    private Role role;

    public SignUpForm() {
        this.role = new Role("User");
    }

    public SignUpForm(String login, String password, String confirmPassword, String firstName, String lastName, String email) {
        super(login, password);
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;


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
     * Return role of User.
     *
     * @return role of User
     */
    public Role getRole() {
        return role;
    }

    /**
     * Return confirm password of User.
     *
     * @return confirm password of User
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Set new confirm password to User
     *
     * @param confirmPassword new confirm password
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

        SignUpForm that = (SignUpForm) obj;

        if (confirmPassword != null ? !confirmPassword.equals(that.confirmPassword) : that.confirmPassword != null)
            return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
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
        int hashCode = super.hashCode();
        hashCode = prime * hashCode + (confirmPassword != null ? confirmPassword.hashCode() : 0);
        hashCode = prime * hashCode + (firstName != null ? firstName.hashCode() : 0);
        hashCode = prime * hashCode + (lastName != null ? lastName.hashCode() : 0);
        hashCode = prime * hashCode + (email != null ? email.hashCode() : 0);
        hashCode = prime * hashCode + (role != null ? role.hashCode() : 0);
        return hashCode;
    }
}
