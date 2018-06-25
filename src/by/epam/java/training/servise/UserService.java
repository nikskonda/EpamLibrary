package by.epam.java.training.servise;

import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.SignInForm;
import by.epam.java.training.model.user.SignUpForm;
import by.epam.java.training.model.user.User;

public interface UserService {

    boolean isExistLoginAndPassword(SignInForm signInForm);

    User getUserByLogin(String login);

    ActiveUser addUser(SignUpForm signUpForm);

    ActiveUser getActiveUser(String login);

    boolean isFreeLogin(String login);

}
