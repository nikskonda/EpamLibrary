package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.dao.util.EncriptionMD5;
import by.epam.java.training.model.user.*;
import org.apache.log4j.Logger;
import static by.epam.java.training.dao.util.SQLRequest.*;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private void closeResultSetAndStatement(ResultSet resultSet, Statement statement){
        try{
            if (resultSet!=null){
                resultSet.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close ResultSet",ex);
        }

        try{
            if (statement!=null){
                statement.close();
            }
        }
        catch (SQLException ex){
            logger.warn("I can not close Statement",ex);
        }
    }





    @Override
    public boolean isExistLoginAndPassword(AuthorizationForm authorizationForm) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        boolean result = false;
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            String query = FIND_USER_BY_LOGIN_AND_PASSWORD.replaceFirst(PLACE_FOR_LOGIN, authorizationForm.getLogin())
                    .replaceFirst(PLACE_FOR_PASSWORD, EncriptionMD5.encrypt(authorizationForm.getPassword()));
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSetAndStatement(resultSet, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return result;
    }

    private Role getRoleById(Integer id){

        return new Role(id,"ADMIN");
    }


    @Override
    public User getUserByLogin(String login) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            String query = FIND_USER_BY_LOGIN.replaceFirst(PLACE_FOR_LOGIN, login);
            rs = statement.executeQuery(query);

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(ID_USER));
                user.setLogin(rs.getString(LOGIN));
                user.setEmail(rs.getString(EMAIL));
                user.setFirstName(rs.getString(FIRST_NAME));
                user.setLastName(rs.getString(LAST_NAME));
                user.setRegistrationDate(rs.getDate(REGISTRATION_DATE));
                user.setAddress(new Address());
                user.setRole(getRoleById(1));
            }

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSetAndStatement(rs, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return user;
    }

    @Override
    public ActiveUser getActiveUser(String login) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        ActiveUser activeUser = null;
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            String query = FIND_ACTIVE_USER_BY_LOGIN.replaceFirst(PLACE_FOR_LOGIN, login);
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                activeUser = new ActiveUser();
                activeUser.setId(resultSet.getInt(ID_USER));
                activeUser.setLogin(resultSet.getString(LOGIN));
            }

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSetAndStatement(resultSet, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return activeUser;
    }

    @Override
    public ActiveUser addUser(RegistrationForm registrationForm) {
        ActiveUser activeUser = null;

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            String query = ADD_USER.replaceFirst(PLACE_FOR_LOGIN, registrationForm.getLogin())
                    .replaceFirst(PLACE_FOR_PASSWORD, EncriptionMD5.encrypt(registrationForm.getPassword()))
                    .replaceFirst(PLACE_FOR_EMAIL, registrationForm.getEmail())
                    .replaceFirst(PLACE_FOR_FIRST_NAME, registrationForm.getFirstName())
                    .replaceFirst(PLACE_FOR_SECOND_NAME, registrationForm.getLastName());

            int result = statement.executeUpdate(query);

            if (result>0){
                activeUser =getActiveUser(registrationForm.getLogin());
            }

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSetAndStatement(resultSet, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return activeUser;
    }

    @Override
    public boolean isFreeLogin(String login) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ConnectionPool connectionPool = DAOFactory.getInstance().getConnectionPool();
        boolean result = false;
        try {
            connection = connectionPool.retrieve();
            statement = connection.createStatement();
            String query = FIND_USER_BY_LOGIN.replaceFirst(PLACE_FOR_LOGIN, login);

            resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                result = true;
            }

            connectionPool.putback(connection);

        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSetAndStatement(resultSet, statement);
            try{
                connectionPool.putback(connection);
            } catch (NullPointerException ex){
                logger.warn("Connection was not received", ex);
            }
        }
        return result;
    }
}
