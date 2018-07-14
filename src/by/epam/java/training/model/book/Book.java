package by.epam.java.training.model.book;

import by.epam.java.training.model.book.constituents.Author;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.book.constituents.PublishingHouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book extends BookCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String COMMA_AND_SPACE = ", ";
    private static final int FIRST_CHAR = 0;
    private static final int STEP = 1;

    private String description;
    private Integer pages;
    private PublishingHouse publishingHouse;
    private String pdfFileUrl;
    private String textFileUrl;
    private String authors;

    private List<Genre> genres;


    public Book() {
        genres = new ArrayList<>();
    }




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre){
        this.genres.add(genre);
    }

    public String getGenresAsString(){
        StringBuilder sb = new StringBuilder();
        for (Genre genre : genres){
            sb.append(genre.getName()).append(COMMA_AND_SPACE);
        }
        if (sb.length()==0){
            return sb.toString();
        }
        return sb.toString().substring(FIRST_CHAR, sb.length() - COMMA_AND_SPACE.length()-STEP);
    }

    public String getTextFileUrl() {
        return textFileUrl;
    }

    public void setTextFileUrl(String textFileUrl) {
        this.textFileUrl = textFileUrl;
    }

    public String getAuthors() {
        return authors==null?"":authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
