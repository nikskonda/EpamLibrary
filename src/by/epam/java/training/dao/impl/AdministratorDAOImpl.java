package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class AdministratorDAOImpl extends AbstractDAO implements AdministratorDAO {

    private static final Logger logger = Logger.getLogger(AdministratorDAOImpl.class);

    @Override
    public boolean isAdministrator(String login) throws ConnectionPoolException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean result = false;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(IS_ADMINISTRATOR);
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
    public List<User> getUsersByPages(PageAttributes pageData) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<User> userList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_LIST_OF_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
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

    @Override
    public Integer calcTotalPagesWithUsers(Integer countUsersOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, countUsersOnOnePage);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public List<User> FindUsersByPages(String search, PageAttributes pageData) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<User> userList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(FIND_LIST_OF_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
            cstmt.setString(SEARCH, search);
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

    @Override
    public Integer calcTotalPagesWithUsersSearch(String search, Integer countUsersOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_USERS_SEARCH);
            cstmt.setString(SEARCH, search);
            cstmt.setInt(COUNT_USERS_ON_PAGE, countUsersOnOnePage);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public User getUser(Integer userId) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        User user = null;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_USER_BY_ID);
            cstmt.setInt(USER_ID, userId);
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
    public List<Role> getRoles() {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<Role> roleList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_LIST_OF_ROLES);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt(USER_ROLE_ID),rs.getString(USER_ROLE_NAME));
                roleList.add(role);
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
        return roleList;
    }

    @Override
    public boolean changeRole(Integer userId, String roleName) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        boolean result = false;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CHANGE_USER_ROLE);
            cstmt.setInt(USER_ID, userId);
            cstmt.setString(USER_ROLE_NAME, roleName);
            rs = cstmt.executeQuery();
            result = true;
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
        return result;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        boolean result = false;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(DELETE_USER);
            cstmt.setInt(USER_ID, userId);
            rs = cstmt.executeQuery();
            result = true;
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
        return result;
    }
}
