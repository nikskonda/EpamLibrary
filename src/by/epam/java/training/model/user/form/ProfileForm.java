package by.epam.java.training.model.user.form;

import java.io.Serializable;

public class ProfileForm extends SignUpForm implements Serializable {

    private static final long serialVersionUID = 1L;

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


    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
