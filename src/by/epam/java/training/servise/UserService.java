package by.epam.java.training.servise;

import by.epam.java.training.model.user.*;

import java.util.List;

public interface UserService {

    boolean isExistLoginAndPassword(SignInForm signInForm);

    User getUserByLogin(String login);

    ActiveUser addUser(SignUpForm signUpForm);

    ActiveUser getActiveUser(String login);

    boolean isFreeLogin(String login);

    boolean updateUser(ProfileForm profile);

    List<User> findUsers();
}
