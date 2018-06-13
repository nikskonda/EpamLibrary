package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.UserDAO;
import by.epam.java.training.model.*;
import by.epam.java.training.printer.LogPrinter;
import org.apache.log4j.Logger;


import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    private final String LOGIN = "root";
    private final String PASSWORD = "password";
    private final String URL = "jdbc:mysql://localhost:3306/library";

    private final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id FROM users WHERE users.login='placeForLogin' AND users.password='placeForPassword';";
    private final static String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE users.login='placeForLogin';";
    private final static String ADD_USER = "INSERT INTO `library`.`users` (`login`, `password`, `first_name`, `last_name`, `email`, `adresses_address_id`, `roles_role_id`) VALUES ('placeForLogin', 'placeForPassword', 'placeForFirstName', 'placeForSecondName', 'placeForEmail', '1', '4');";
    private final static String PLACE_FOR_LOGIN = "placeForLogin";
    private final static String PLACE_FOR_PASSWORD = "placeForPassword";
    private final static String PLACE_FOR_FIRSTNAME = "placeForFirstName";
    private final static String PLACE_FOR_SECONDNAME = "placeForSecondName";
    private final static String PLACE_FOR_EMAIL = "placeForEmail";


    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (ClassNotFoundException ex){
            LogPrinter.printLogError(ex.getMessage(), LOGGER);
        }
        catch (SQLException ex) {
            LogPrinter.printLogError(ex.getMessage(), LOGGER);
        }
        return connection;
    }


    @Override
    public boolean isExistLoginAndPassword(AuthorizationUser authorizationUser) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        boolean result = false;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = FIND_USER_BY_LOGIN_AND_PASSWORD.replaceFirst(PLACE_FOR_LOGIN, authorizationUser.getLogin())
                    .replaceFirst(PLACE_FOR_PASSWORD, authorizationUser.getPassword());
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                result = true;
            }


        } catch (SQLException ex) {
            LogPrinter.printLogError(ex.getMessage(), LOGGER);
        } finally {
//            try{
//                resultSet.close();
//            }
//            catch (SQLException ex){
//                LogPrinter.printLogError(ex.getMessage(), LOGGER);
//            }
//
//            try{
//                statement.close();
//            }
//            catch (SQLException ex){
//                LogPrinter.printLogError(ex.getMessage(), LOGGER);
//            }
//            try{
//                connection.close();
//            }
//            catch (SQLException ex){
//                LogPrinter.printLogError(ex.getMessage(), LOGGER);
//            }
        }
        return result;
    }

    private Role getRoleById(Integer id){
//
        return new Role(id,"ADMIN");
    }


    @Override
    public User getUserByLogin(String login) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = FIND_USER_BY_LOGIN.replaceFirst(PLACE_FOR_LOGIN, login);
            rs = statement.executeQuery(query);

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("idUsers"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setRegistrationDate(rs.getDate("registration_date"));
                user.setAddress(new Address());
                user.setRole(getRoleById(1));
            }

        } catch (SQLException ex) {

        } finally {
            try{
                rs.close();
            }
            catch (SQLException ex){

            }
            try{
                statement.close();
            }
            catch (SQLException ex){

            }
            try{
                connection.close();
            }
            catch (SQLException ex){

            }
        }
        return user;
    }

    @Override
    public User addUser(RegistrationUser registrationUser) {
        User user = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = ADD_USER.replaceFirst(PLACE_FOR_LOGIN, registrationUser.getLogin())
                    .replaceFirst(PLACE_FOR_PASSWORD, registrationUser.getPassword())
                    .replaceFirst(PLACE_FOR_EMAIL, registrationUser.getEmail())
                    .replaceFirst(PLACE_FOR_FIRSTNAME, registrationUser.getFirstName())
                    .replaceFirst(PLACE_FOR_SECONDNAME, registrationUser.getLastName());
            rs = statement.executeQuery(query);

            user = getUserByLogin(registrationUser.getLogin());

        } catch (SQLException ex) {

        } finally {
            try{
                rs.close();
            }
            catch (SQLException ex){

            }
            try{
                statement.close();
            }
            catch (SQLException ex){

            }
            try{
                connection.close();
            }
            catch (SQLException ex){

            }
        }
        return user;
    }

    @Override
    public boolean isFreeLogin(String login) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        boolean result = false;
        try {
            connection = getConnection();
            statement = connection.createStatement();
            String query = FIND_USER_BY_LOGIN.replaceFirst(PLACE_FOR_LOGIN, login);

            resultSet = statement.executeQuery(query);

            if (!resultSet.next()) {
                result = true;
            }

        } catch (SQLException ex) {

        } finally {
            try{
                resultSet.close();
            }
            catch (SQLException ex){

            }
            try{
                statement.close();
            }
            catch (SQLException ex){

            }
            try{
                connection.close();
            }
            catch (SQLException ex){

            }
        }
        return result;
    }
}
