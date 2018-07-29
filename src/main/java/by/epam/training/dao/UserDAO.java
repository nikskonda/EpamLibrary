package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.user.form.ProfileForm;
import by.epam.training.model.user.form.SignInForm;
import by.epam.training.model.user.form.SignUpForm;
import by.epam.training.model.user.ActiveUser;
import by.epam.training.model.user.User;

/**
 * The interface defines methods for implementing different
 * activities with user in database.
 *
 * @author  Nikita Shkonda
 */
public interface UserDAO {

    /**
     * Return true if the user is exist.
     *
     * @param signInForm - Information of the user.
     *
     * @return <tt>true</tt> if the user is exist.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see SignInForm
     *
     */
    boolean isUserExist(SignInForm signInForm) throws DAOException;

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
     * This method add new user in system and return active user.
     *
     * @param signUpForm - Information of the user.
     *
     * @return active user.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see SignUpForm
     * @see ActiveUser
     *
     */
    ActiveUser addUser(SignUpForm signUpForm) throws DAOException;

    /**
     * Return active user with specified login.
     *
     * @param login - Login of the user.
     *
     * @return active user.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see ActiveUser
     *
     */
    ActiveUser getActiveUser(String login) throws DAOException;

    /**
     * Return true if the login is free.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login is free.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    boolean isFreeLogin(String login) throws DAOException;

    /**
     * This method update information about user in system.
     *
     * @param profileForm - Information of the user.
     *
     * @return <tt>true</tt> if updated was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see ProfileForm
     *
     */
    boolean updateUser(ProfileForm profileForm) throws DAOException;

}
