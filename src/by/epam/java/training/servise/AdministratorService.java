package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.SignInForm;

import java.util.List;

public interface AdministratorService {

    boolean isAdministrator(String login) throws DAOException;

    List<User> getUsersByPages(LordOfPages pageData);

    Integer calcTotalPagesWithUsers(Integer countUsersOnOnePage) throws DAOException;

    List<User> FindUsersByPages(String search, LordOfPages pageData);

    Integer calcTotalPagesWithUsersSearch(String search, Integer countUsersOnOnePage) throws DAOException;

    List<Role> getRoles();

    boolean changeRole(Integer userId, String roleName);

    boolean deleteUser(Integer userId);

    User getUser(Integer userId) throws DAOException;


}
