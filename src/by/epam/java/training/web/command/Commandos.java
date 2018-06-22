package by.epam.java.training.web.command;

public enum Commandos {
    SIGN_IN("sign_in"), SIGN_UP("sign_up"),
    RU("ru"), EN("en"),
    OPEN_CATALOG("open_catalog"),
    OPEN_NEWS("open_news"),
    ADD_NEWS("add_news"),

    ;

    private String value;

    Commandos(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
