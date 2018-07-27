package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.exception.ServiceException;

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
     * @param pageAttribute - Information about page.
     *
     * @return list of users for specified page.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see User
     * @see PageAttribute
     *
     */
    List<User> findUsersPerPage(String search, PageAttribute pageAttribute) throws ServiceException;

    /**
     * Return total count of pages with users in which match the search query.
     *
     * @param search - Search word.
     * @param countUsersOnPage - Count users on one page.
     *
     * @return Total count of pages with users in which match the search query.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
    Integer calcPagesCountUserSearchResults(String search, Integer countUsersOnPage) throws ServiceException;

}
