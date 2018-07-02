package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.model.user.*;
import by.epam.java.training.servise.UserService;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final ValidatorManager validator = new ValidatorManager();

    @Override
    public boolean isExistLoginAndPassword(SignInForm signInForm) {
        if (!validator.isValid(ValidatorType.AUTHORIZATION_FORM_VALIDATOR, signInForm)){
            return false;
        }
        return userDAO.isExistLoginAndPassword(signInForm);

    }

    @Override
    public User getUserByLogin(String login) {
        if (!validator.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
            return null;
        }
        return userDAO.getUserByLogin(login);
    }

    @Override
    public ActiveUser addUser(SignUpForm signUpForm) {
        if (!validator.isValid(ValidatorType.REGISTRATION_FORM_VALIDATOR, signUpForm)){
            logger.warn("Unvalid Registration Form");
            return null;
        }
        return userDAO.addUser(signUpForm);
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

    @Override
    public boolean updateUser(ProfileForm profile) {
//        if (!validator.isValid(ValidatorType.LOGIN_VALIDATOR, login)){
//            return null;
//        }
        return userDAO.updateUser(profile);
    }

    @Override
    public List<User> findUsers() {
        return userDAO.findUsers();
    }
}
