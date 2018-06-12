package by.epam.java.training.servise;

import by.epam.java.training.model.AuthorizationUser;
import by.epam.java.training.model.RegistrationUser;
import by.epam.java.training.model.User;

public interface UserService {
    boolean isExistLoginAndPassword(AuthorizationUser authorizationUser);

    User getUserByLogin(String login);

    User addUser(RegistrationUser registrationUser);
}
