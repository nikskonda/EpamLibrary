package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

import org.apache.log4j.Logger;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {

    private static final Logger logger = Logger.getLogger(AdministratorServiceImpl.class);

    private final AdministratorDAO administratorDAO = DAOFactory.getAdministratorDAO();

    @Override
    public boolean isAdministrator(String login) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }
        try {
            return administratorDAO.isAdministrator(login);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<User> getUsersPerPage(PageAttribute pageAttribute) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute)){
            return null;
        }
        try{
            return administratorDAO.getUsersPerPage(pageAttribute);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }

    @Override
    public Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countUsersOnOnePage)){
            return null;
        }
        try{
            return administratorDAO.calcPagesCountUsers(countUsersOnOnePage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }


    @Override
    public List<Role> getRoles() throws ServiceException {
        try{
            return administratorDAO.getRoles();
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean changeRole(Integer userId, String roleName) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId) ||
                !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, roleName)){
            return false;
        }
        try {
            return administratorDAO.changeRole(userId, roleName);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean deleteUser(Integer userId) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return false;
        }
        try {
            return administratorDAO.deleteUser(userId);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }

    }

    @Override
    public User getUser(Integer userId) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }
        try {
            return administratorDAO.getUser(userId);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}
