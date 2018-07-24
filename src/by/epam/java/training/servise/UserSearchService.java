package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;

import java.util.List;


/**
 * The interface defines methods for implementing different
 * activities with user search.
 *
 * @author  Nikita Shkonda
 */
public interface UserSearchService {

    /**
     * Find list of users in which match the search query.
     *
     * @param search - Search word.
     * @param pageAttributes - Information about page.
     *
     * @return list of users for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see User
     * @see PageAttributes
     *
     */
    List<User> findUsersPerPages(String search, PageAttributes pageAttributes) throws DAOException;

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
    Integer calcPagesCountUserSearchResult(String search, Integer countUsersOnPage) throws DAOException;

}
