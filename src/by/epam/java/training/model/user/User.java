package by.epam.java.training.model.user;

import by.epam.java.training.model.user.constituents.Role;

import java.io.Serializable;
import java.util.Date;

public class User extends ActiveUser implements Serializable {
    private static final long serialVersionUID = 208742088604645L;

    private String firstName;
    private String lastName;
    private String email;
    private Date registrationDate;

    public User() {
    }

    public User(ActiveUser activeUser){
        super.setId(activeUser.getId());
        super.setLogin(activeUser.getLogin());
        super.setRole(activeUser.getRole());
    }


    public User(Integer id, String login, String firstName, String lastName, String email, Role role, Date registrationDate) {
        super(id, login, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = registrationDate;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }


}


