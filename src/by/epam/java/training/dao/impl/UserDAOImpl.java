package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.web.util.EncriptionMD5;
import by.epam.java.training.model.user.*;
import org.apache.log4j.Logger;
import static by.epam.java.training.dao.util.SQLRequest.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public boolean updateUser(ProfileForm profile) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean result = false;

        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(UPDATE_USER);
            cstmt.setString(USER_LOGIN, profile.getLogin());
            cstmt.setString(USER_PASSWORD, profile.getPassword());
            cstmt.setString(USER_FIRST_NAME, profile.getFirstName());
            cstmt.setString(USER_LAST_NAME, profile.getLastName());
            cstmt.setString(USER_EMAIL, profile.getEmail());
            cstmt.executeQuery();
            result = true;
        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public boolean isExistLoginAndPassword(SignInForm signInForm) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean result = false;

        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(IS_EXIST_USER_WITH_LOGIN_AND_PASSWORD);
            cstmt.setString(USER_LOGIN, signInForm.getLogin());
            cstmt.setString(USER_PASSWORD, signInForm.getPassword());
            cstmt.registerOutParameter(RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(RESULT);

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public User getUserByLogin(String login) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        User user = null;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_USER_BY_LOGIN);
            cstmt.setString(USER_LOGIN, login);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(USER_ID));
                user.setLogin(rs.getString(USER_LOGIN));
                user.setEmail(rs.getString(USER_EMAIL));
                user.setFirstName(rs.getString(USER_FIRST_NAME));
                user.setLastName(rs.getString(USER_LAST_NAME));
                user.setRegistrationDate(rs.getDate(USER_REGISTRATION_DATE));
                Role role = new Role(rs.getInt(USER_ROLE_ID),rs.getString(USER_ROLE_NAME));
                user.setRole(role);
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        }  catch (Exception ex){
            logger.warn("Database query error",ex);
        }
        finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return user;
    }

    @Override
    public ActiveUser getActiveUser(String login) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ActiveUser activeUser = null;

        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_USER_BY_LOGIN);
            cstmt.setString(USER_LOGIN, login);
            rs = cstmt.executeQuery();

            if (rs.next()) {
                activeUser = new ActiveUser();
                activeUser.setId(rs.getInt(USER_ID));
                activeUser.setLogin(rs.getString(USER_LOGIN));
            }

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return activeUser;
    }

    @Override
    public ActiveUser addUser(SignUpForm signUpForm) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ActiveUser activeUser = null;

        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(ADD_USER);
            cstmt.setString(USER_LOGIN, signUpForm.getLogin());
            cstmt.setString(USER_PASSWORD, signUpForm.getLogin());
            cstmt.setString(USER_FIRST_NAME, signUpForm.getFirstName());
            cstmt.setString(USER_LAST_NAME, signUpForm.getLastName());
            cstmt.setString(USER_EMAIL, signUpForm.getEmail());
            cstmt.setString(USER_ROLE_NAME, signUpForm.getRole().getName());
            cstmt.execute();

            activeUser = getActiveUser(signUpForm.getLogin());
        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return activeUser;
    }

    @Override
    public boolean isFreeLogin(String login) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean result = false;

        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(IS_FREE_LOGIN);
            cstmt.setString(USER_LOGIN, login);
            cstmt.registerOutParameter(RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(RESULT);

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public List<User> findUsers() {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        List<User> userList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(FIND_USER_LIST);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(USER_ID));
                user.setLogin(rs.getString(USER_LOGIN));
                user.setEmail(rs.getString(USER_EMAIL));
                user.setFirstName(rs.getString(USER_FIRST_NAME));
                user.setLastName(rs.getString(USER_LAST_NAME));
                user.setRegistrationDate(rs.getDate(USER_REGISTRATION_DATE));
                Role role = new Role(rs.getInt(USER_ROLE_ID),rs.getString(USER_ROLE_NAME));
                user.setRole(role);

                userList.add(user);
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        }  catch (Exception ex){
            logger.warn("Database query error",ex);
        }
        finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return userList;
    }
}
