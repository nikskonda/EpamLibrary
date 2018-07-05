package by.epam.java.training.web.command;

public enum Pages2 {
    START_PAGE("jsp/Main.jsp"),
    SIGN_IN("jsp/user/SignIn.jsp"),
    SIGN_UP("jsp/user/SignUp.jsp"),
    PROFILE("jsp/user/Profile.jsp"),
    CATALOG("jsp/Catalog.jsp"),
    ERROR("jsp/Error.jsp"),
    NEWS("jsp/News.jsp"),
    USER_LIST("jsp/user/UserList.jsp"),
    BOOK("jsp/Book.jsp"),
    READING_ROOM("jsp/ReadingRoom.jsp"),

    ;

    private String page;

    Pages2(String page) {
        this.page = page;
    }

    public String getPage() {
        return this.page;
    }

}
