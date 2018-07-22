package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;

import java.util.List;

public interface UserService {

    boolean isUserExist(SignInForm signInForm) throws DAOException;

    User getUser(Integer userId) throws DAOException;

    ActiveUser addUser(SignUpForm signUpForm) throws DAOException;

    ActiveUser getActiveUser(String login) throws DAOException;

    boolean isFreeLogin(String login) throws DAOException;

    boolean updateUser(ProfileForm profile) throws DAOException;

}
