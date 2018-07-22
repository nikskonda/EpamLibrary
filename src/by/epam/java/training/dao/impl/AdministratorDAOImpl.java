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
    public boolean isAdministrator(String login) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;

        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(IS_ADMINISTRATOR);
            cstmt.setString(USER_LOGIN, login);
            cstmt.registerOutParameter(RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(RESULT);
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error", ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    @Override
    public List<User> getUsersByPages(PageAttributes pageData) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_LIST_OF_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
            rs = cstmt.executeQuery();

            while (rs.next()) {
                userList.add(buildUser(rs));
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error", ex);
            throw new DAOException();
        } finally {
            closeAll(rs, cstmt, con);
        }
        return userList;
    }

    private User buildUser(ResultSet rs) throws SQLException{
        User user = new User();

        user.setId(rs.getInt(USER_ID));
        user.setLogin(rs.getString(USER_LOGIN));
        user.setEmail(rs.getString(USER_EMAIL));
        user.setFirstName(rs.getString(USER_FIRST_NAME));
        user.setLastName(rs.getString(USER_LAST_NAME));
        user.setRegistrationDate(rs.getDate(USER_REGISTRATION_DATE));

        user.setRole(buildRole(rs));

        return user;
    }

    private Role buildRole(ResultSet rs) throws SQLException{
        Role role = new Role();

        role.setId(rs.getInt(USER_ROLE_ID));
        role.setName(rs.getString(USER_ROLE_NAME));

        return role;
    }

    @Override
    public Integer calcTotalPagesWithUsers(Integer countUsersOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, countUsersOnOnePage);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    @Override
    public List<User> FindUsersByPages(String search, PageAttributes pageData) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(FIND_LIST_OF_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
            cstmt.setString(SEARCH, search);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                userList.add(buildUser(rs));
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database query error",ex);
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        }
        finally {
            closeAll(rs, cstmt, con);
        }
        return userList;
    }

    @Override
    public Integer calcTotalPagesWithUsersSearch(String search, Integer countUsersOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_USERS_SEARCH);
            cstmt.setString(SEARCH, search);
            cstmt.setInt(COUNT_USERS_ON_PAGE, countUsersOnOnePage);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
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
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeAll(rs, cstmt, con);
        }
        return user;
    }

    @Override
    public List<Role> getRoles() throws DAOException{
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
                roleList.add(buildRole(rs));
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeAll(rs, cstmt, con);
        }
        return roleList;
    }

    @Override
    public boolean changeRole(Integer userId, String roleName) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CHANGE_USER_ROLE);
            cstmt.setInt(USER_ID, userId);
            cstmt.setString(USER_ROLE_NAME, roleName);
            cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    @Override
    public boolean deleteUser(Integer userId) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(DELETE_USER);
            cstmt.setInt(USER_ID, userId);
            cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }
}
