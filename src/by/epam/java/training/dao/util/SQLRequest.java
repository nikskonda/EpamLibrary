package by.epam.java.training.dao.util;

public class SQLRequest {

    public final static String LOCALE = "lang";
    public final static String RESULT = "result";

    //USER_LIST
    public final static String IS_EXIST_USER_WITH_LOGIN_AND_PASSWORD = "{call is_exist_user(?,?,?)}";
    public final static String IS_FREE_LOGIN = "{call is_free_login(?,?)}";
    public final static String ADD_USER = "{call add_user(?,?,?,?,?,?)}";
    public final static String GET_USER_BY_LOGIN = "{call get_user_by_login(?)}";
    public final static String GET_USER_BY_ID = "{call get_user_by_id(?)}";
    public final static String UPDATE_USER = "{call update_user(?,?,?,?,?)}";
    public final static String GET_LIST_OF_ROLES = "{call get_list_of_roles()}";
    public final static String GET_LIST_OF_USERS = "{call get_list_of_users(?,?)}";
    public final static String FIND_LIST_OF_USERS = "{call find_user(?,?,?)}";
    public final static String CALC_TOTAL_PAGES_IN_USERS = "{call calc_pages_in_users(?,?)}";
    public final static String CALC_TOTAL_PAGES_IN_USERS_SEARCH = "{call calc_pages_in_users_search(?,?,?)}";
    public final static String CHANGE_USER_ROLE = "{call change_user_role(?,?)}";
    public final static String DELETE_USER = "{call delete_user(?)}";
    public final static String IS_ADMINISTRATOR = "{call is_administrator(?,?)}";
    public final static String IS_MODERATOR = "{call is_moderator(?,?)}";


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

    public final static String COUNT_USERS_ON_PAGE = "count_users_on_page";


    //BOOKS
    public final static String GET_BOOK = "{call get_book(?,?)}";
    public final static String GET_LIST_OF_BOOKS = "{call get_list_of_books(?,?,?)}";
    public final static String CALC_TOTAL_PAGES_IN_BOOKS = "{call calc_pages_in_books(?,?,?)}";
    public final static String GET_URL_TO_TEXT_OF_BOOK = "{call get_url_to_text_of_book(?,?)}";
    public final static String ADD_BOOK = "{call add_book(?,?,?,?,?,?,?,?,?,?)}";
    public final static String ADD_TRANSLATED_BOOK = "{call add_translated_book(?,?,?,?,?,?)}";
    public final static String UPDATE_BOOK = "{call update_book(?,?,?,?,?,?,?,?,?,?)}";
    public final static String UPDATE_TRANSLATED_BOOK = "{call update_translated_book(?,?,?,?,?,?)}";
    public final static String DELETE_BOOK = "{call delete_book(?)}";

    public final static String FIND_BOOKS = "{call find_book(?,?,?,?)}";
    public final static String CALC_TOTAL_PAGES_BOOKS_SEARCH = "{call calc_pages_in_book_search(?,?,?,?)}";

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
    public final static String BOOK_AUTHORS = "authors";
    public final static String BOOK_COVER_URL = "cover_url";
    public final static String BOOK_PDF_FILE_URL = "pdf_file_url";
    public final static String BOOK_TEXT_FILE_URL = "text_file_url";
    public final static String COUNT_BOOKS_ON_PAGE = "count_books_on_page";
    public final static String SEARCH = "search";
    public final static String PAGE_NUMBER = "page_number";


    //GENRES
    public final static String GET_BOOK_GENRES = "{call get_book_genres(?,?)}";
    public final static String GET_LIST_OF_GENRES = "{call get_list_of_genres(?)}";
    public final static String ADD_BOOK_GENRES = "{call add_book_genre(?,?)}";
    public final static String DELETE_BOOK_GENRES = "{call delete_book_genre(?)}";
    //Column labels
    public final static String GENRE_ID = "genre_id";
    public final static String GENRE_NAME = "genre_name";

    //NEWS
    public final static String GET_NEWS = "{call get_news(?,?)}";
    public final static String GET_LIST_OF_NEWS = "{call get_list_of_news(?,?,?)}";
    public final static String CALC_TOTAL_PAGES_IN_NEWS = "{call calc_pages_in_news(?,?,?)}";
    public final static String ADD_NEWS = "{call add_news(?,?,?,?,?,?)}";
    public final static String ADD_TRANSLATED_NEWS = "{call add_translated_news(?,?,?,?)}";
    public final static String UPDATE_NEWS = "{call update_news(?,?,?,?,?,?)}";
    public final static String UPDATE_TRANSLATED_NEWS = "{call update_translated_news(?,?,?,?)}";
    public final static String DELETE_NEWS = "{call delete_news(?)}";

    //Column labels
    public final static String NEWS_ID = "news_id";
    public final static String NEWS_TITLE = "title";
    public final static String NEWS_TEXT = "text";
    public final static String NEWS_PUBLISH_DATE = "publish_date";
    public final static String NEWS_PHOTO_URL = "photo_url";
    public final static String NEWS_THUMBS_URL = "thumbs_url";

    public final static String COUNT_NEWS_ON_PAGE = "count_news_on_page";
    public final static String NUMBER_OF_PAGE = "number_of_page";


}
