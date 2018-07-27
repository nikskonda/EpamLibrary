package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.model.user.form.ProfileForm;
import by.epam.java.training.model.user.form.SignInForm;
import by.epam.java.training.model.user.form.SignUpForm;
import by.epam.java.training.model.user.*;
import org.apache.log4j.Logger;
import static by.epam.java.training.dao.util.SQLRequest.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public boolean updateUser(ProfileForm profileForm) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(UPDATE_USER);
            cstmt.setString(USER_LOGIN, profileForm.getLogin());
            cstmt.setString(USER_PASSWORD, profileForm.getPassword());
            cstmt.setString(USER_FIRST_NAME, profileForm.getFirstName());
            cstmt.setString(USER_LAST_NAME, profileForm.getLastName());
            cstmt.setString(USER_EMAIL, profileForm.getEmail());
            cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    @Override
    public boolean isUserExist(SignInForm signInForm) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(IS_EXIST_USER_WITH_LOGIN_AND_PASSWORD);
            cstmt.setString(USER_LOGIN, signInForm.getLogin());
            cstmt.setString(USER_PASSWORD, signInForm.getPassword());
            cstmt.registerOutParameter(RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }


    @Override
    public User getUser(Integer userId) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_USER_BY_ID);
            cstmt.setInt(USER_ID, userId);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                user = buildUser(rs);
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return user;
    }

    @Override
    public ActiveUser getActiveUser(String login) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ActiveUser activeUser = null;
        try {
            con = retrieveConnection();
            cstmt = con.prepareCall(GET_USER_BY_LOGIN);
            cstmt.setString(USER_LOGIN, login);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                activeUser = buildActiveUser(rs);
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return activeUser;
    }

    @Override
    public ActiveUser addUser(SignUpForm signUpForm) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ActiveUser activeUser = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(ADD_USER);
            cstmt.setString(USER_LOGIN, signUpForm.getLogin());
            cstmt.setString(USER_PASSWORD, signUpForm.getLogin());
            cstmt.setString(USER_FIRST_NAME, signUpForm.getFirstName());
            cstmt.setString(USER_LAST_NAME, signUpForm.getLastName());
            cstmt.setString(USER_EMAIL, signUpForm.getEmail());
            cstmt.setString(USER_ROLE_NAME, signUpForm.getRole().getName());
            cstmt.execute();

            activeUser = getActiveUser(signUpForm.getLogin());
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return activeUser;
    }

    @Override
    public boolean isFreeLogin(String login) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(IS_FREE_LOGIN);
            cstmt.setString(USER_LOGIN, login);
            cstmt.registerOutParameter(RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    private ActiveUser buildActiveUser(ResultSet rs) throws SQLException{
        ActiveUser activeUser = new ActiveUser();

        activeUser.setId(rs.getInt(USER_ID));
        activeUser.setLogin(rs.getString(USER_LOGIN));
        activeUser.setRole(buildRole(rs));

        return activeUser;
    }

    private User buildUser(ResultSet rs) throws SQLException{
        User user = new User(buildActiveUser(rs));

        user.setEmail(rs.getString(USER_EMAIL));
        user.setFirstName(rs.getString(USER_FIRST_NAME));
        user.setLastName(rs.getString(USER_LAST_NAME));
        user.setRegistrationDate(rs.getDate(USER_REGISTRATION_DATE));

        return user;
    }

    private Role buildRole(ResultSet rs) throws SQLException{
        Role role = new Role();

        role.setId(rs.getInt(USER_ROLE_ID));
        role.setName(rs.getString(USER_ROLE_NAME));

        return role;
    }
}
