package by.epam.training.dao.impl;

import by.epam.training.dao.AbstractDAO;
import by.epam.training.dao.AdministratorDAO;
import by.epam.training.dao.DAOFactory;
import by.epam.training.dao.exception.ConnectionPoolException;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.dao.util.ConnectionPool;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.user.User;
import by.epam.training.model.user.constituents.Role;
import by.epam.training.dao.util.SQLRequestConstant;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement an interface that defines the administrator's actions in the database.
 *
 * @author  Nikita Shkonda
 */
public class AdministratorDAOImpl extends AbstractDAO implements AdministratorDAO {

    private static final Logger logger = Logger.getLogger(AdministratorDAOImpl.class);

    /**
     * Return true if the login belongs to the administrator.
     *
     * @param login - Login of the user.
     *
     * @return <tt>true</tt> if the login belongs to the administrator.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public boolean isAdministrator(String login) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;

        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.IS_ADMINISTRATOR);
            cstmt.setString(SQLRequestConstant.USER_LOGIN, login);
            cstmt.registerOutParameter(SQLRequestConstant.RESULT, Types.BOOLEAN);
            cstmt.executeQuery();

            result = cstmt.getBoolean(SQLRequestConstant.RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    /**
     * List of users for a specific page.
     * The page is indicated in @Param
     *
     * @param pageAttribute - information about page.
     *
     * @return List of users for a specific page
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see PageAttribute
     *
     */
    @Override
    public List<User> getUsersPerPage(PageAttribute pageAttribute) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_LIST_OF_USERS);
            cstmt.setInt(SQLRequestConstant.COUNT_USERS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(SQLRequestConstant.NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
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
     * Return total number of pages with users.
     *
     *
     * @param countUsersOnOnePage - Count of users displayed on one page
     *
     * @return Total number of pages with users.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public Integer calcPagesCountUsers(Integer countUsersOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.CALC_TOTAL_PAGES_IN_USERS);
            cstmt.setInt(SQLRequestConstant.COUNT_USERS_ON_PAGE, countUsersOnOnePage);
            cstmt.registerOutParameter(SQLRequestConstant.RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(SQLRequestConstant.RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    /**
     * Return information about user by id.
     *
     * @param userId - Id of the user.
     *
     * @return User if the query was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see User
     *
     */
    @Override
    public User getUser(Integer userId) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_USER_BY_ID);
            cstmt.setInt(SQLRequestConstant.USER_ID, userId);
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

    /**
     * Return list of roles.
     *
     *
     * @return List of roles.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public List<Role> getRoles() throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<Role> roleList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(SQLRequestConstant.GET_LIST_OF_ROLES);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                roleList.add(buildRole(rs));
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return roleList;
    }

    /**
     * Change the user role by id.
     *
     * @param userId - Id of the user.
     * @param roleName - New role of the user.
     *
     * @return <tt>true</tt> if the change was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public boolean changeRole(Integer userId, String roleName) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.CHANGE_USER_ROLE);
            cstmt.setInt(SQLRequestConstant.USER_ID, userId);
            cstmt.setString(SQLRequestConstant.USER_ROLE_NAME, roleName);
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

    /**
     * Delete the user by id.
     *
     * @param userId - Id of the user.
     *
     * @return <tt>true</tt> if the delete was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public boolean deleteUser(Integer userId) throws DAOException{
        Connection con = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.DELETE_USER);
            cstmt.setInt(SQLRequestConstant.USER_ID, userId);
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

    private User buildUser(ResultSet rs) throws SQLException{
        User user = new User();

        user.setId(rs.getInt(SQLRequestConstant.USER_ID));
        user.setLogin(rs.getString(SQLRequestConstant.USER_LOGIN));
        user.setEmail(rs.getString(SQLRequestConstant.USER_EMAIL));
        user.setFirstName(rs.getString(SQLRequestConstant.USER_FIRST_NAME));
        user.setLastName(rs.getString(SQLRequestConstant.USER_LAST_NAME));
        user.setRegistrationDate(rs.getDate(SQLRequestConstant.USER_REGISTRATION_DATE));

        user.setRole(buildRole(rs));

        return user;
    }

    private Role buildRole(ResultSet rs) throws SQLException{
        Role role = new Role();

        role.setId(rs.getInt(SQLRequestConstant.USER_ROLE_ID));
        role.setName(rs.getString(SQLRequestConstant.USER_ROLE_NAME));

        return role;
    }
}
