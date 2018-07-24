package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

import org.apache.log4j.Logger;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {

    private static final Logger logger = Logger.getLogger(AdministratorServiceImpl.class);

    private final AdministratorDAO administratorDAO = DAOFactory.getAdministratorDAO();

    @Override
    public boolean isAdministrator(String login) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }
        return administratorDAO.isAdministrator(login);

    }

    @Override
    public List<User> getUsersPerPage(PageAttributes pageAttributes) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttributes)){
            return null;
        }
        return administratorDAO.getUsersPerPage(pageAttributes);
    }

    @Override
    public Integer calcPagesCountUsers(Integer countUsersOnPage) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countUsersOnPage)){
            return null;
        }
        return administratorDAO.calcPagesCountUsers(countUsersOnPage);
    }


    @Override
    public List<Role> getRoles() throws DAOException {
        return administratorDAO.getRoles();
    }

    @Override
    public boolean changeRole(Integer userId, String roleName) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId) ||
                !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, roleName)){
            return false;
        }
        return administratorDAO.changeRole(userId, roleName);
    }

    @Override
    public boolean deleteUser(Integer userId) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return false;
        }
        return administratorDAO.deleteUser(userId);
    }

    @Override
    public User getUser(Integer userId) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }
        return administratorDAO.getUser(userId);
    }
}
