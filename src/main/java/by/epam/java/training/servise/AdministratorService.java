package by.epam.java.training.servise;

import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.exception.ServiceException;

import java.util.List;


/**
 * The interface defines methods for implementing administrator activities.
 *
 * @author  Nikita Shkonda
 */
public interface AdministratorService {

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
    boolean isAdministrator(String login) throws ServiceException;

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
    List<User> getUsersPerPage(PageAttribute pageAttribute) throws ServiceException;

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
    Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws ServiceException;

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
    List<Role> getRoles() throws ServiceException;

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
    boolean changeRole(Integer userId, String roleName) throws ServiceException;

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
    boolean deleteUser(Integer userId) throws ServiceException;

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
    User getUser(Integer userId) throws ServiceException;


}
