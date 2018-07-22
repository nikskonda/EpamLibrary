package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO = DAOFactory.getUserDAO();

    @Override
    public boolean isExistUser(SignInForm signInForm) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.SIGN_IN_FORM_VALIDATOR, signInForm)){
            return false;
        }
        return userDAO.isExistUser(signInForm);

    }

    @Override
    public User getUser(Integer userId) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }
        return userDAO.getUser(userId);
    }

    @Override
    public ActiveUser addUser(SignUpForm signUpForm) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.SIGN_UP_FORM_VALIDATOR, signUpForm)){
            return null;
        }
        return userDAO.addUser(signUpForm);
    }

    @Override
    public boolean isFreeLogin(String login) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }
        return userDAO.isFreeLogin(login);
    }

    @Override
    public ActiveUser getActiveUser(String login) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return null;
        }
        return userDAO.getActiveUser(login);
    }

    @Override
    public boolean updateUser(ProfileForm profile) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, profile.getLogin())){
            return false;
        }
        return userDAO.updateUser(profile);
    }


}
