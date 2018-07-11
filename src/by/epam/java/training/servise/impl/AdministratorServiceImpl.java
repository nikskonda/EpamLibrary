package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.servise.AdministratorService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;

import org.apache.log4j.Logger;

import java.util.List;

public class AdministratorServiceImpl implements AdministratorService {
    private static final Logger logger = Logger.getLogger(AdministratorServiceImpl.class);

    private final AdministratorDAO administratorDAO = DAOFactory.getAdministratorDAO();

    @Override
    public boolean isAdministrator(SignInForm signInForm) throws ConnectionPoolException {
        if (!ValidatorManager.isValid(ValidatorType.SIGN_IN_FORM_VALIDATOR, signInForm)){
            return false;
        }
        return administratorDAO.isAdministrator(signInForm);

    }

    @Override
    public List<User> getUsers() {
        return administratorDAO.getUsers();
    }

    @Override
    public List<Role> getRoles() {
        return administratorDAO.getRoles();
    }

    @Override
    public boolean changeRole(Integer userId, String roleName) {
        if (!ValidatorManager.isValid(ValidatorType.ID_VALIDATOR, userId) ||
                !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, roleName)){
            return false;
        }
        return administratorDAO.changeRole(userId, roleName);
    }

    @Override
    public boolean deleteUser(Integer userId) {
        if (!ValidatorManager.isValid(ValidatorType.ID_VALIDATOR, userId)){
            return false;
        }
        return administratorDAO.deleteUser(userId);
    }

    @Override
    public User getUser(Integer userId) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.ID_VALIDATOR, userId)){
            return null;
        }
        return administratorDAO.getUser(userId);
    }
}
