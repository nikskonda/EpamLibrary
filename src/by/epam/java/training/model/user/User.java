package by.epam.java.training.model.user;

import by.epam.java.training.model.user.constituents.Role;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 208742088604645L;

    private Integer id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Date registrationDate;

    public User() {
    }

    public User(ActiveUser activeUser){
        this.id = activeUser.getId();
        this.login = activeUser.getLogin();
        this.role = activeUser.getRole();
    }


    public User(Integer id, String login, String firstName, String lastName, String email, Role role, Date registrationDate) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.registrationDate = registrationDate;
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

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


}


