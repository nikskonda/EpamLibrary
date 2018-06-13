package by.epam.java.training.dao;

import by.epam.java.training.model.AuthorizationUser;
import by.epam.java.training.model.RegistrationUser;
import by.epam.java.training.model.User;

public interface UserDAO {

    boolean isExistLoginAndPassword(AuthorizationUser authorizationUser);

    User getUserByLogin(String login);

    User addUser(RegistrationUser registrationUser);

    boolean isFreeLogin(String login);

}
