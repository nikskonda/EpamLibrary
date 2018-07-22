package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;

import java.util.List;

public interface AdministratorDAO {

    boolean isAdministrator(String login) throws DAOException;

    User getUser(Integer userId) throws DAOException;

    List<User> getUsersPerPage(PageAttributes pageAttributes) throws DAOException;

    Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws DAOException;

    List<User> findUsersByPages(String search, PageAttributes pageAttributes);

    Integer calcPagesCountUserSearchResults(String search, Integer countUsersOnPage) throws DAOException;

    List<Role> getRoles() throws DAOException;

    boolean changeRole(Integer userId, String roleName) throws DAOException;

    boolean deleteUser(Integer userId) throws DAOException;


}
