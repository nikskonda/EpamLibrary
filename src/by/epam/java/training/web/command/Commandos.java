package by.epam.java.training.web.command;

public enum Commandos {
    SIGN_IN("sign_in"), SIGN_UP("sign_up"),
    OPEN_SIGN_IN("open_sign_in"), OPEN_SIGN_UP("open_sign_up"),
    RU("ru"), EN("en"),
    OPEN_CATALOG("open_catalog")

    ;

    private String value;

    Commandos(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

}
