package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;

import java.util.List;

public interface UserDAO {

    boolean isExistUser(SignInForm signInForm) throws ConnectionPoolException, DAOException;

    User getUser(Integer userId) throws DAOException;

    ActiveUser addUser(SignUpForm signUpForm) throws ConnectionPoolException, DAOException;

    ActiveUser getActiveUser(String login) throws ConnectionPoolException, DAOException;

    boolean isFreeLogin(String login) throws ConnectionPoolException, DAOException;

    boolean updateUser(ProfileForm profile) throws ConnectionPoolException, DAOException;

}
