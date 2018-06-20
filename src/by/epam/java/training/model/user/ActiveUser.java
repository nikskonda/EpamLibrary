package by.epam.java.training.model.user;

import java.io.Serializable;
import java.util.Date;

public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 208742088604645L;

    private Integer id;
    private String login;

    public ActiveUser() {
    }


    public ActiveUser(Integer id, String login) {
        this.id = id;
        this.login = login;
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
}
