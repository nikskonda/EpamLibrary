package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.impl.UserDAOImpl;
import by.epam.java.training.model.ActiveUser;
import by.epam.java.training.model.AuthorizationForm;
import by.epam.java.training.model.RegistrationForm;
import by.epam.java.training.model.User;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final ValidatorManager validator = new ValidatorManager();

    @Override
    public boolean isExistLoginAndPassword(AuthorizationForm authorizationForm) {
        if (!validator.isValid(ValidatorType.AUTHORIZATION_FORM_VALIDATOR, authorizationForm)){
            return false;
        }
        return userDAO.isExistLoginAndPassword(authorizationForm);

    }

    @Override
    public User getUserByLogin(String login) {
        if (!validator.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return null;
        }
        return userDAO.getUserByLogin(login);
    }

    @Override
    public ActiveUser addUser(RegistrationForm registrationForm) {
        if (!validator.isValid(ValidatorType.REGISTRATION_FORM_VALIDATOR, registrationForm)){
            logger.warn("Unvalid Registration Form");
            return null;
        }
        return userDAO.addUser(registrationForm);
    }

    @Override
    public boolean isFreeLogin(String login) {
        if (!validator.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return false;
        }
        return userDAO.isFreeLogin(login);
    }

    @Override
    public ActiveUser getActiveUser(String login) {
        if (!validator.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return null;
        }
        return userDAO.getActiveUser(login);
    }
}
