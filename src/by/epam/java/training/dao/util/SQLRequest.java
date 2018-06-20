package by.epam.java.training.dao.util;

public class SQLRequest {

    //USER
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


    //BOOK
    public final static String FIND_ALL_BOOKS = "SELECT * FROM books;";
    public final static String FIND_ALL_BOOKS_BY_LOCALE = "SELECT books.book_id, name, description, publish_year, price, pages, publishing_house_id, tbooks.pdf_file_url, cover_url FROM books right join tbooks on books.book_id = tbooks.book_id where tbooks.lang = 'placeForLanguage';";

    public final static String BOOK_ID = "book_id";
    public final static String BOOK_DEF_NAME = "def_name";
    public final static String BOOK_DEF_DESCRIPTION = "def_description";
    public final static String BOOK_PUBLISH_YEAR = "publish_year";
    public final static String BOOK_PRICE = "price";
    public final static String BOOK_PAGES = "pages";
    public final static String BOOK_PUBLISHING_HOUSE_ID = "publishing_house_id";
    public final static String BOOK_DEF_PDF_FILE = "def_pdf_file_url";
    public final static String BOOK_COVER = "cover_url";
    public final static String BOOK_NAME = "name";
    public final static String BOOK_DESCRIPTION = "description";
    public final static String BOOK_PDF_FILE = "pdf_file_url";

    public final static String PLACE_FOR_LANGUAGE = "placeForLanguage";
}
