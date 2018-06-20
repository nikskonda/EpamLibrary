package by.epam.java.training.model.user;

import java.io.Serializable;

public class AuthorizationForm implements Serializable {
    private static final long serialVersionUID = 3846457549196098786L;

    private String login;
    private String password;

    public AuthorizationForm() {
    }

    public AuthorizationForm(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
