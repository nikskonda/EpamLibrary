package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;

import java.util.List;

public interface UserDAO {

    boolean isExistLoginAndPassword(SignInForm signInForm) throws ConnectionPoolException;

    User getUser(Integer userId);

    ActiveUser addUser(SignUpForm signUpForm) throws ConnectionPoolException;

    ActiveUser getActiveUser(String login) throws ConnectionPoolException;

    boolean isFreeLogin(String login) throws ConnectionPoolException;

    boolean updateUser(ProfileForm profile) throws ConnectionPoolException;

}
