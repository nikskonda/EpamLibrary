package by.epam.java.training.model.user;

import by.epam.java.training.model.user.constituents.Role;

import java.io.Serializable;
import java.util.Date;

public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 208742088604645L;

    private Integer id;
    private String login;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
