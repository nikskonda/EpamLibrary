package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserSearchDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.UserSearchService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing the {@link UserSearchService} interface for different activities with user's search.
 *
 * @author  Nikita Shkonda
 */
public class UserSearchServiceImpl implements UserSearchService {

    private static final Logger logger = Logger.getLogger(UserSearchServiceImpl.class);

    private final UserSearchDAO userSearchDAO = DAOFactory.getUserSearchDAO();

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
    @Override
    public List<User> findUsersPerPage(String search, PageAttribute pageAttribute) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)){
            return null;
        }

        try{
            return userSearchDAO.findUsersPerPage(search, pageAttribute);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

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
    @Override
    public Integer calcPagesCountUserSearchResults(String search, Integer countUsersOnPage) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countUsersOnPage)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)){
            return null;
        }

        try{
            return userSearchDAO.calcPagesCountUserSearchResults(search, countUsersOnPage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

}
