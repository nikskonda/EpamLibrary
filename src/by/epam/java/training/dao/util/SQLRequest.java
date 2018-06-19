package by.epam.java.training.dao.util;

public class SQLRequest {

    public final static String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id FROM users WHERE users.login='placeForLogin' AND users.password='placeForPassword';";
    public final static String FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE users.login='placeForLogin';";
    public final static String FIND_ACTIVE_USER_BY_LOGIN = "SELECT user_id, login FROM users WHERE users.login='placeForLogin';";
    public final static String ADD_USER = "INSERT INTO `library`.`users` (`login`, `password`, `first_name`, `last_name`, `email`, `role_id`, `registration_date`) VALUES ('placeForLogin', 'placeForPassword', 'placeForFirstName', 'placeForSecondName', 'placeForEmail', '1', NOW());";
    public final static String PLACE_FOR_LOGIN = "placeForLogin";
    public final static String PLACE_FOR_PASSWORD = "placeForPassword";
    public final static String PLACE_FOR_FIRST_NAME = "placeForFirstName";
    public final static String PLACE_FOR_SECOND_NAME = "placeForSecondName";
    public final static String PLACE_FOR_EMAIL = "placeForEmail";

    //Column labels

    public final static String ID_USER = "user_id";
    public final static String LOGIN = "login";
    public final static String FIRST_NAME = "first_name";
    public final static String LAST_NAME = "last_name";
    public final static String EMAIL = "email";
    public final static String REGISTRATION_DATE = "registration_date";

}
