package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.User;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with user search in database.
 *
 * @author  Nikita Shkonda
 */
public interface UserSearchDAO {

    /**
     * Find list of users in which match the search query.
     *
     * @param search - Search word.
     * @param pageAttribute - Information about page.
     *
     * @return list of users for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see User
     * @see PageAttribute
     *
     */
    List<User> findUsersPerPage(String search, PageAttribute pageAttribute) throws DAOException;

    /**
     * Return total count of pages with users in which match the search query.
     *
     * @param search - Search word.
     * @param countUsersOnPage - Count users on one page.
     *
     * @return Total count of pages with users in which match the search query.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountUserSearchResults(String search, Integer countUsersOnPage) throws DAOException;

}
