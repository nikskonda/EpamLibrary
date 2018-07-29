package by.epam.training.model.user.form;

import java.io.Serializable;

/**
 * Used to store profile form.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class ProfileForm extends SignUpForm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * New Password of User.
     */
    private String newPassword;

    public ProfileForm() {
    }

    public ProfileForm(String login, String firstName, String lastName, String email) {
        super.setLogin(login);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setEmail(email);
    }

    public ProfileForm(Integer id, String login, String password, String newPassword, String confirmPassword, String firstName, String lastName, String email) {
        super(login, password, confirmPassword, firstName, lastName, email);
        this.newPassword = newPassword;
    }

    /**
     * Return new password of User.
     *
     * @return new password of User
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Set new new password to User
     *
     * @param newPassword new new password
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
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

        ProfileForm that = (ProfileForm) obj;

        return newPassword != null ? newPassword.equals(that.newPassword) : that.newPassword == null;
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
        hashCode = prime * hashCode + (newPassword != null ? newPassword.hashCode() : 0);
        return hashCode;
    }
}
