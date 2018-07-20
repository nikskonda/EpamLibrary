package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;

import java.util.List;

public interface AdministratorDAO {

    boolean isAdministrator(String login) throws DAOException;

    User getUser(Integer userId);

    List<User> getUsersByPages(PageAttributes pageData);

    Integer calcTotalPagesWithUsers(Integer countUsersOnOnePage) throws DAOException;

    List<User> FindUsersByPages(String search, PageAttributes pageData);

    Integer calcTotalPagesWithUsersSearch(String search, Integer countUsersOnOnePage) throws DAOException;

    List<Role> getRoles();

    boolean changeRole(Integer userId, String roleName);

    boolean deleteUser(Integer userId);


}
