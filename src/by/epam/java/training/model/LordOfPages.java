package by.epam.java.training.model;

import java.io.Serializable;

public class LordOfPages implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer numberOfPage;
    private Integer countOnPage;
    private String locale;

    public LordOfPages() {
    }

    public LordOfPages(Integer numberOfPage, Integer countOnPage, String locale) {
        this.numberOfPage = numberOfPage;
        this.countOnPage = countOnPage;
        this.locale = locale;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public Integer getCountOnPage() {
        return countOnPage;
    }

    public void setCountOnPage(Integer countOnPage) {
        this.countOnPage = countOnPage;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
