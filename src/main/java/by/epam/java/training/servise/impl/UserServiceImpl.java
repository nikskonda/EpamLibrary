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

/**
 * Implementing the {@link UserService} interface for various user actions.
 *
 * @author  Nikita Shkonda
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO = DAOFactory.getUserDAO();


    /**
     * Return true if the user is exist.
     *
     * @param signInForm - Information of the user.
     *
     * @return <tt>true</tt> if the user is exist.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see SignInForm
     *
     */
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

    /**
     * Return information about user by id.
     *
     * @param userId - Id of the user.
     *
     * @return User if the query was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see User
     *
     */
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

    /**
     * This method add new user in system and return active user.
     *
     * @param signUpForm - Information of the user.
     *
     * @return active user.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see SignUpForm
     * @see ActiveUser
     *
     */
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

    /**
     * Return true if the login is free.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login is free.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
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

    /**
     * Return active user with specified login.
     *
     * @param login - Login of the user.
     *
     * @return active user.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see ActiveUser
     *
     */
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

    /**
     * This method update information about user in system.
     *
     * @param profileForm - Information of the user.
     *
     * @return <tt>true</tt> if updated was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see ProfileForm
     *
     */
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
