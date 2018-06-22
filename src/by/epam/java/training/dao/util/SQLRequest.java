package by.epam.java.training.dao.util;

public class SQLRequest {

    public final static String LOCALE = "lang";
    public final static String RESULT = "result";

    //USERS
    public final static String IS_EXIST_USER_WITH_LOGIN_AND_PASSWORD = "{call is_exist_user_with_login_and_password(?,?,?)}";
    public final static String IS_FREE_LOGIN = "{call is_free_login(?,?)}";
    public final static String ADD_USER = "{call get_all_books_by_lang(?,?,?,?,?,?)}";
    public final static String GET_USER_BY_LOGIN = "{call get_user_by_login(?)}";

    //Column labels
    public final static String USER_ID = "user_id";
    public final static String USER_LOGIN = "login";
    public final static String USER_PASSWORD = "password";
    public final static String USER_FIRST_NAME = "first_name";
    public final static String USER_LAST_NAME = "last_name";
    public final static String USER_EMAIL = "email";
    public final static String USER_REGISTRATION_DATE = "registration_date";
    public final static String USER_ROLE_ID = "role_id";
    public final static String USER_ROLE_NAME = "role_name";

    //BOOKS
    public final static String GET_BOOK_BY_ID = "{call get_book_by_id_and_lang(?,?)}";
    public final static String GET_ALL_BOOKS = "{call get_all_books_by_lang(?)}";

    //Column labels
    public final static String BOOK_ID = "book_id";
    public final static String BOOK_NAME = "name";
    public final static String BOOK_DESCRIPTION = "description";
    public final static String BOOK_PUBLISH_YEAR = "publish_year";
    public final static String BOOK_PRICE = "price";
    public final static String BOOK_PAGES = "pages";
    public final static String BOOK_PUBLISHING_HOUSE_ID = "publishing_house_id";
    public final static String BOOK_PUBLISHING_HOUSE_NAME = "publishing_house_name";
    public final static String BOOK_COVER = "cover_url";
    public final static String BOOK_PDF_FILE = "pdf_file_url";

    //NEWS
    public final static String GET_NEWS_BY_ID = "{call get_news_by_id_and_lang(?,?)}";
    public final static String GET_ALL_NEWS = "{call get_all_news_by_lang(?,?,?)}";

    //Column labels
    public final static String NEWS_ID = "news_id";
    public final static String NEWS_TITLE = "title";
    public final static String NEWS_TEXT = "text";
    public final static String NEWS_PUBLISH_DATE = "publish_date";
    public final static String NEWS_PHOTO_URL = "photo_url";

    public final static String COUNT_NEWS_ON_PAGE = "count_news_on_page";
    public final static String NUMBER_OF_PAGE = "number_of_page";


}
