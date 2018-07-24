package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;

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
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    boolean isAdministrator(String login) throws DAOException;

    /**
     * List of users for a specific page.
     * The page is indicated in @Param
     *
     * @param pageAttributes - information about page.
     *
     * @return List of users for a specific page
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see PageAttributes
     *
     */
    List<User> getUsersPerPage(PageAttributes pageAttributes) throws DAOException;

    /**
     * Return total number of pages with users.
     *
     *
     * @param countUsersOnPage - Count of users displayed on one page
     *
     * @return Total number of pages with users.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountUsers(Integer countUsersOnPage) throws DAOException;

    /**
     * Return list of roles.
     *
     *
     * @return List of roles.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    List<Role> getRoles() throws DAOException;

    /**
     * Change the user role by id.
     *
     * @param userId - Id of the user.
     * @param roleName - New role of the user.
     *
     * @return <tt>true</tt> if the change was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    boolean changeRole(Integer userId, String roleName) throws DAOException;

    /**
     * Delete the user by id.
     *
     * @param userId - Id of the user.
     *
     * @return <tt>true</tt> if the delete was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    boolean deleteUser(Integer userId) throws DAOException;

    /**
     * Return information about user by id.
     *
     * @param userId - Id of the user.
     *
     * @return User if the query was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see User
     *
     */
    User getUser(Integer userId) throws DAOException;


}
