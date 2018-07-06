package by.epam.java.training.servise;

import by.epam.java.training.model.user.*;

import java.util.List;

public interface UserService {

    boolean isExistLoginAndPassword(SignInForm signInForm);

    boolean isAdministrator(SignInForm signInForm);

    User getUser(Integer userId);

    ActiveUser addUser(SignUpForm signUpForm);

    ActiveUser getActiveUser(String login);

    boolean isFreeLogin(String login);

    boolean updateUser(ProfileForm profile);

    List<User> getUsers();

    List<Role> getRoles();

    boolean changeRole(Integer userId, String roleName);

    boolean deleteUser(Integer userId);


}
