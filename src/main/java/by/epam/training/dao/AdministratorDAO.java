package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.user.User;
import by.epam.training.model.user.constituents.Role;

import java.util.List;


/**
 * The interface defines methods for implementing administrator activities in database.
 *
 * @author  Nikita Shkonda
 */
public interface AdministratorDAO {

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

    /**
     * List of users for a specific page.
     * The page is indicated in @Param
     *
     * @param pageAttribute - information about page.
     *
     * @return List of users for a specific page
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see PageAttribute
     *
     */
    List<User> getUsersPerPage(PageAttribute pageAttribute) throws DAOException;

    /**
     * Return total number of pages with users.
     *
     *
     * @param countUsersOnOnePage - Count of users displayed on one page
     *
     * @return Total number of pages with users.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws DAOException;

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


}
