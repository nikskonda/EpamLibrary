package by.epam.java.training.model.user;

import java.io.Serializable;

public class SignUpForm implements Serializable {
    private static final long serialVersionUID = 1239618902290L;

    private String login;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;

    public SignUpForm() {
        this.role = new Role("User");
    }

    public SignUpForm(String login, String password, String confirmPassword, String firstName, String lastName, String email, Address address, Role role) {
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;


    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Role getRole() {
        return role;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean comparePasswords(){
        return this.password.equals(this.confirmPassword);
    }
}
