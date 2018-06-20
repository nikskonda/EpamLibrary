package by.epam.java.training.servise;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.AuthorizationForm;
import by.epam.java.training.model.user.RegistrationForm;
import by.epam.java.training.model.user.User;

public interface UserService {

    boolean isExistLoginAndPassword(AuthorizationForm authorizationForm);

    User getUserByLogin(String login);

    ActiveUser addUser(RegistrationForm registrationForm);

    ActiveUser getActiveUser(String login);

    boolean isFreeLogin(String login);
}
