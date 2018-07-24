package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.AdministratorDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserSearchDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.user.User;
import by.epam.java.training.model.user.constituents.Role;
import by.epam.java.training.servise.UserSearchService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class UserSearchDAOImpl extends AbstractDAO implements UserSearchDAO {

    private static final Logger logger = Logger.getLogger(UserSearchDAOImpl.class);


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
    public List<User> findUsersPerPage(String search, PageAttributes pageAttributes) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(FIND_LIST_OF_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, pageAttributes.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageAttributes.getNumberOfPage());
            cstmt.setString(SEARCH, search);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                userList.add(buildUser(rs));
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
        return userList;
    }

    @Override
    public Integer calcPagesCountUserSearchResults(String search, Integer countUsersOnPage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_USERS_SEARCH);
            cstmt.setString(SEARCH, search);
            cstmt.setInt(COUNT_USERS_ON_PAGE, countUsersOnPage);
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
}
