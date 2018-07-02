package by.epam.java.training.model.user;

import java.io.Serializable;

public class SignUpForm extends SignInForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String email;
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



    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
