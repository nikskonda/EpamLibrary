package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.model.user.ActiveUser;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;

import java.util.List;

public interface AdministratorDAO {

    boolean isAdministrator(SignInForm signInForm) throws ConnectionPoolException;

    User getUser(Integer userId);

    List<User> getUsers();

    List<Role> getRoles();

    boolean changeRole(Integer userId, String roleName);

    boolean deleteUser(Integer userId);
}
