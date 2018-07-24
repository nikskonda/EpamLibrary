package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;

import java.util.List;

public interface UserSearchDAO {


    List<User> findUsersPerPage(String search, PageAttributes pageAttributes) throws DAOException;

    Integer calcPagesCountUserSearchResults(String search, Integer countUsersOnPage) throws DAOException;

}
