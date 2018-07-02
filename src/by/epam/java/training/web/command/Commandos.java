package by.epam.java.training.web.command;

public enum Commandos {
    SIGN_IN("sign_in"), SIGN_UP("sign_up"),
    RU("ru"), EN("en"),
    OPEN_CATALOG("open_catalog"),
    OPEN_NEWS("open_news"),
    ADD_NEWS("add_news"),
    OPEN_PROFILE("open_profile"),
    UPDATE_PROFILE("update_profile"),
    SHOW_NEWS_BY_ID("open_news_by_id"),
    SHOW_USER_LIST("show_user_list"),
    SHOW_BOOK_BY_ID("open_book_by_id"),
    READ_BOOK_BY_ID("read_book_by_id"),
    ;

    private String value;

    Commandos(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
