package by.epam.training.dao.impl;

import by.epam.training.dao.AbstractDAO;
import by.epam.training.dao.UserSearchDAO;
import by.epam.training.dao.exception.ConnectionPoolException;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.user.User;
import by.epam.training.model.user.constituents.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.training.dao.util.SQLRequestConstant.*;

/**
 * Implement an interface that defines different
 * activities with user search in database.
 *
 * @author  Nikita Shkonda
 */
public class UserSearchDAOImpl extends AbstractDAO implements UserSearchDAO {

    private static final Logger logger = Logger.getLogger(UserSearchDAOImpl.class);

    /**
     * Find list of users in which match the search query.
     *
     * @param search - Search word.
     * @param pageAttribute - Information about page.
     *
     * @return list of users for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see User
     * @see PageAttribute
     *
     */
    @Override
    public List<User> findUsersPerPage(String search, PageAttribute pageAttribute) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(FIND_LIST_OF_USERS);
            cstmt.setInt(COUNT_USERS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            cstmt.setString(SEARCH, search);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                userList.add(buildUser(rs));
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return userList;
    }

    /**
     * Return total count of pages with users in which match the search query.
     *
     * @param search - Search word.
     * @param countUsersOnPage - Count users on one page.
     *
     * @return Total count of pages with users in which match the search query.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
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
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
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
}
