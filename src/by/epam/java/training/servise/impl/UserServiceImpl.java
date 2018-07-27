package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO = DAOFactory.getUserDAO();

    @Override
    public boolean isUserExist(SignInForm signInForm) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.SIGN_IN_FORM_VALIDATOR, signInForm)){
            return false;
        }
        try{
            return userDAO.isUserExist(signInForm);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }

    @Override
    public User getUser(Integer userId) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }

        try{
            return userDAO.getUser(userId);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public ActiveUser addUser(SignUpForm signUpForm) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.SIGN_UP_FORM_VALIDATOR, signUpForm)){
            return null;
        }

        try{
            return userDAO.addUser(signUpForm);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean isFreeLogin(String login) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }

        try{
            return userDAO.isFreeLogin(login);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public ActiveUser getActiveUser(String login) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return null;
        }

        try{
            return userDAO.getActiveUser(login);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean updateUser(ProfileForm profileForm) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, profileForm.getLogin())){
            return false;
        }

        try{
            return userDAO.updateUser(profileForm);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }


}
