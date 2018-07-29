package by.epam.training.model.user.form;

import java.io.Serializable;

/**
 * Used to store sign in form.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class SignInForm implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Login of User.
     */
    private String login;

    /**
     * Password of User.
     */
    private String password;

    public SignInForm() {
    }

    public SignInForm(String login, String password) {
        this.login = login;
        this.password = password;
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
     * Return password of User.
     *
     * @return password of User
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set new password to User
     *
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
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

        SignInForm that = (SignInForm) obj;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
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
        hashCode = prime * hashCode + (password != null ? password.hashCode() : 0);
        return hashCode;
    }
}
