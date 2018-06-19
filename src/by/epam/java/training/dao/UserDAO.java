package by.epam.java.training.dao;

import by.epam.java.training.model.ActiveUser;
import by.epam.java.training.model.AuthorizationForm;
import by.epam.java.training.model.RegistrationForm;
import by.epam.java.training.model.User;

public interface UserDAO {

    boolean isExistLoginAndPassword(AuthorizationForm authorizationForm);

    User getUserByLogin(String login);

    ActiveUser addUser(RegistrationForm registrationForm);

    ActiveUser getActiveUser(String login);

    boolean isFreeLogin(String login);



}
