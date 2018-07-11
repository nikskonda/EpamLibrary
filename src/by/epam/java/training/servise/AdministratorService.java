package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.user.*;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.SignInForm;

import java.util.List;

public interface AdministratorService {

    boolean isAdministrator(SignInForm signInForm) throws ConnectionPoolException;

    List<User> getUsers() throws DAOException;

    List<Role> getRoles();

    boolean changeRole(Integer userId, String roleName);

    boolean deleteUser(Integer userId);

    User getUser(Integer userId) throws DAOException;


}
