package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;

import java.util.List;

public interface AdministratorService {

    boolean isAdministrator(String login) throws DAOException;

    List<User> getUsersByPages(PageAttributes pageData) throws DAOException;

    Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws DAOException;

    List<User> FindUsersByPages(String search, PageAttributes pageData) throws DAOException;

    Integer calcPagesCountUserSearchResult(String search, Integer countUsersOnOnePage) throws DAOException;

    List<Role> getRoles() throws DAOException;

    boolean changeRole(Integer userId, String roleName) throws DAOException;

    boolean deleteUser(Integer userId) throws DAOException;

    User getUser(Integer userId) throws DAOException;


}
