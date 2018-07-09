package by.epam.java.training.dao.util;

public class SQLRequest {

    public final static String LOCALE = "lang";
    public final static String RESULT = "result";

    //USERS
    public final static String IS_EXIST_USER_WITH_LOGIN_AND_PASSWORD = "{call is_exist_user_with_login_and_password(?,?,?)}";
    public final static String IS_FREE_LOGIN = "{call is_free_login(?,?)}";
    public final static String ADD_USER = "{call add_user(?,?,?,?,?,?)}";
    public final static String GET_USER_BY_LOGIN = "{call get_user_by_login(?)}";
    public final static String GET_USER_BY_ID = "{call get_user_by_id(?)}";
    public final static String UPDATE_USER = "{call update_user(?,?,?,?,?)}";
    public final static String GET_ROLE_LIST = "{call get_role_list()}";
    public final static String GET_USER_LIST = "{call get_user_list()}";
    public final static String CHANGE_USER_ROLE = "{call change_user_role(?,?)}";
    public final static String DELETE_USER = "{call delete_user(?)}";
    public final static String IS_ADMINISTRATOR = "{call is_administrator(?,?,?)}";


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
    public final static String GET_ALL_BOOKS = "{call get_all_books_by_lang(?,?,?)}";
    public final static String CALC_TOTAL_PAGES_BOOKS = "{call calc_pages_in_books_by_lang(?,?,?)}";
    public final static String GET_URL_TO_TEXT_OF_BOOK = "{call get_url_to_text_of_book(?,?)}";

    public final static String FIND_BOOKS = "{call find_book(?,?,?,?)}";
    public final static String CALC_TOTAL_PAGES_BOOKS_SERCH = "{call calc_pages_in_book_search(?,?,?,?)}";

    public final static String GET_BOOKMARK = "{call get_bookmark(?,?,?)}";
    public final static String SET_BOOKMARK = "{call set_bookmark(?,?,?,?)}";

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
    public final static String BOOK_TEXT_FILE = "text_file_url";
    public final static String COUNT_BOOKS_ON_PAGE = "count_books_on_page";
    public final static String SEARCH = "search";
    public final static String PAGE_NUMBER = "page_number";

    //AUTHORS
    public final static String FIND_BOOK_AUTHORS = "{call find_book_authors(?)}";
    //Column labels
    public final static String AUTHOR_ID = "author_id";
    public final static String AUTHOR_FIRST_NAME = "first_name";
    public final static String AUTHOR_LAST_NAME = "last_name";

    //GENRES
    public final static String FIND_BOOK_GENRES = "{call find_book_genres(?)}";
    //Column labels
    public final static String GENRE_ID = "genre_id";
    public final static String GENRE_NAME = "genre_name";

    //NEWS
    public final static String GET_NEWS_BY_ID = "{call get_news_by_id_and_lang(?,?)}";
    public final static String GET_ALL_NEWS = "{call get_all_news_by_lang(?,?,?)}";
    public final static String CALC_TOTAL_PAGES_NEWS = "{call calc_pages_in_news_by_lang(?,?,?)}";
    public final static String ADD_NEWS = "{call add_news(?,?,?,?,?,?)}";
    public final static String ADD_NEWS_LANG = "{call add_news_lang(?,?,?,?)}";

    //Column labels
    public final static String NEWS_ID = "news_id";
    public final static String NEWS_TITLE = "title";
    public final static String NEWS_TEXT = "text";
    public final static String NEWS_PUBLISH_DATE = "publish_date";
    public final static String NEWS_PHOTO_URL = "photo_url";
    public final static String NEWS_SMALL_PHOTO_URL = "small_photo_url";

    public final static String COUNT_NEWS_ON_PAGE = "count_news_on_page";
    public final static String NUMBER_OF_PAGE = "number_of_page";


}
