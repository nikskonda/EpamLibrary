package by.epam.java.training.web.command;

public enum Pages {
    START_PAGE("index.jsp"),
    SIGN_IN("jsp/user/SignIn.jsp"),
    SIGN_UP("jsp/user/SignUp.jsp"),
    PROFILE("jsp/user/Profile.jsp"),
    CATALOG("jsp/Catalog.jsp")
    ;

    private String page;

    Pages(String page) {
        this.page = page;
    }

    public String getPage() {
        return this.page;
    }

}
