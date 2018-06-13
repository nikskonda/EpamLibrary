package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.impl.UserDAOImpl;
import by.epam.java.training.model.AuthorizationUser;
import by.epam.java.training.model.RegistrationUser;
import by.epam.java.training.model.User;
import by.epam.java.training.servise.UserService;

public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean isExistLoginAndPassword(AuthorizationUser authorizationUser) {
//        if (Validator.isValid(authorizationUser)){
//            return false;
//        }
        return userDAO.isExistLoginAndPassword(authorizationUser);

    }

    @Override
    public User getUserByLogin(String login) {
        //        if (Validator.isValid(authorizationUser)){
//            return null;
//        }
        return userDAO.getUserByLogin(login);
    }

    @Override
    public User addUser(RegistrationUser registrationUser) {
        //        if (Validator.isValid(authorizationUser)){
//            return null;
//        }
        return userDAO.addUser(registrationUser);
    }

    @Override
    public boolean isFreeLogin(String login) {
        //        if (Validator.isValid(login)){
//            return false;
//        }
        return userDAO.isFreeLogin(login);
    }
}
