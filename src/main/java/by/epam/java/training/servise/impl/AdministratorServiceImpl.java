package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing the {@link AdministratorService} interface for various administrator actions.
 *
 * @author  Nikita Shkonda
 */
public class AdministratorServiceImpl implements AdministratorService {

    private static final Logger logger = Logger.getLogger(AdministratorServiceImpl.class);

    private final AdministratorDAO administratorDAO = DAOFactory.getAdministratorDAO();

    /**
     * Return true if the login belongs to the administrator.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login belongs to the administrator.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service
     *
     */
    @Override
    public boolean isAdministrator(String login) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }
        try {
            return administratorDAO.isAdministrator(login);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * List of users for a specific page.
     * The page is indicated in @Param
     *
     * @param pageAttribute - information about page.
     *
     * @return List of users for a specific page
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service
     *
     * @see PageAttribute
     *
     */
    @Override
    public List<User> getUsersPerPage(PageAttribute pageAttribute) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute)){
            return null;
        }
        try{
            return administratorDAO.getUsersPerPage(pageAttribute);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }

    /**
     * Return total number of pages with users.
     *
     *
     * @param countUsersOnOnePage - Count of users displayed on one page
     *
     * @return Total number of pages with users.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service
     *
     */
    @Override
    public Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countUsersOnOnePage)){
            return null;
        }
        try{
            return administratorDAO.calcPagesCountUsers(countUsersOnOnePage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Return list of roles.
     *
     *
     * @return List of roles.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service
     *
     */
    @Override
    public List<Role> getRoles() throws ServiceException {
        try{
            return administratorDAO.getRoles();
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Change the user role by id.
     *
     * @param userId - Id of the user.
     * @param roleName - New role of the user.
     *
     * @return <tt>true</tt> if the change was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service
     *
     */
    @Override
    public boolean changeRole(Integer userId, String roleName) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId) ||
                !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, roleName)){
            return false;
        }
        try {
            return administratorDAO.changeRole(userId, roleName);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    /**
     * Delete the user by id.
     *
     * @param userId - Id of the user.
     *
     * @return <tt>true</tt> if the delete was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service
     *
     */
    @Override
    public boolean deleteUser(Integer userId) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return false;
        }
        try {
            return administratorDAO.deleteUser(userId);
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
     * in the service
     *
     * @see User
     *
     */
    @Override
    public User getUser(Integer userId) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }
        try {
            return administratorDAO.getUser(userId);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}
