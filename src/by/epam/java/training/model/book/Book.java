package by.epam.java.training.model.book;

import org.apache.commons.codec.binary.StringUtils;

import java.io.Serializable;
import java.util.List;

public class Book extends BookCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String COMMA_AND_SPACE = ", ";
    private static final String SPACE = " ";
    private static final int FIRST_CHAR = 0;
    private static final int STEP = 1;

    private String description;
    private Integer pages;
    private PublishingHouse publishingHouse;
    private String pdfFileUrl;

    private List<Author> authors;
    private List<Genre> genres;


    public Book() {
    }


    public Book(Integer id, String name, Integer publishYear, Double price, String coverUrl, String description, Integer pages, PublishingHouse publishingHouse, String pdfFileUrl) {
        super(id, name, publishYear, price, coverUrl);
        this.description = description;
        this.pages = pages;
        this.publishingHouse = publishingHouse;
        this.pdfFileUrl = pdfFileUrl;
    }

    public Book(Integer id, String name, Integer publishYear, Double price, String coverUrl, String description, Integer pages, PublishingHouse publishingHouse, String pdfFileUrl, List<Author> authors, List<Genre> genres) {
        super(id, name, publishYear, price, coverUrl);
        this.description = description;
        this.pages = pages;
        this.publishingHouse = publishingHouse;
        this.pdfFileUrl = pdfFileUrl;
        this.authors = authors;
        this.genres = genres;
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


    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
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
        return sb.toString().substring(FIRST_CHAR, sb.length() - COMMA_AND_SPACE.length()-STEP);
    }

    public String getAuthorsAsString(){
        StringBuilder sb = new StringBuilder();
        for (Author author : authors){
            sb.append(author.getFirstName()).append(SPACE).append(author.getLastName()).append(COMMA_AND_SPACE);
        }
        return sb.toString().substring(FIRST_CHAR, sb.length() - COMMA_AND_SPACE.length()-STEP);
    }
}
