package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserSearchDAO;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.servise.UserSearchService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class UserSearchServiceImpl implements UserSearchService {

    private static final Logger logger = Logger.getLogger(UserSearchServiceImpl.class);

    private final UserSearchDAO userSearchDAO = DAOFactory.getUserSearchDAO();


    @Override
    public List<User> findUsersPerPages(String search, PageAttributes pageAttributes) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttributes)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)){
            return null;
        }
        return userSearchDAO.findUsersPerPage(search, pageAttributes);
    }

    @Override
    public Integer calcPagesCountUserSearchResult(String search, Integer countUsersOnPage) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countUsersOnPage)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)){
            return null;
        }
        return userSearchDAO.calcPagesCountUserSearchResults(search, countUsersOnPage);
    }

}
