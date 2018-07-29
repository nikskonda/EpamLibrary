package by.epam.training.model.book;

import by.epam.training.model.book.constituents.Genre;
import by.epam.training.model.book.constituents.PublishingHouse;
import by.epam.training.web.command.util.FieldNameConstant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Used to store information about Book.
 *
 * @author Nikita Shkonda
 * @see java.io.Serializable
 */
public class Book extends BookPreview implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String COMMA_AND_SPACE = ", ";
    private static final int FIRST_CHAR = 0;
    private static final int STEP = 1;

    /**
     * Description of Book.
     */
    private String description;
    /**
     * Pages of Book.
     */
    private Integer pages;
    /**
     * PublishingHouse of Book.
     */
    private PublishingHouse publishingHouse;
    /**
     * Pdf file of Book.
     */
    private String pdfFileUrl;
    /**
     * Text file of Book.
     */
    private String textFileUrl;
    /**
     * Authors of Book.
     */
    private String authors;

    /**
     * List of Genres.
     */
    private List<Genre> genres;


    public Book() {
        genres = new ArrayList<>();
    }

    public Book(BookPreview bookPreview) {
        super.setId(bookPreview.getId());
        super.setName(bookPreview.getName());
        super.setCoverUrl(bookPreview.getCoverUrl());
        super.setPrice(bookPreview.getPrice());
        super.setPublishYear(bookPreview.getPublishYear());
        genres = new ArrayList<>();
    }

    /**
     * Return description of Book.
     *
     * @return description of Book
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set new description to Book
     *
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return pages of Book.
     *
     * @return pages of Book
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * Set new description to Book
     *
     * @param description new description
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * Return Publishing House of Book.
     *
     * @return Publishing House of Book
     */
    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    /**
     * Set new Publishing House to Book
     *
     * @param publishingHouse new Publishing House
     */
    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    /**
     * Return pdf file of Book.
     *
     * @return pdf file of Book
     */
    public String getPdfFileUrl() {
        return pdfFileUrl;
    }

    /**
     * Set new pdf file to Book
     *
     * @param pdfFileUrl new pdf file
     */
    public void setPdfFileUrl(String pdfFileUrl) {
        this.pdfFileUrl = pdfFileUrl;
    }

    /**
     * Return genres of Book.
     *
     * @return genres of Book
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Set new genres to Book
     *
     * @param genres new genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Add new genre to Book
     *
     * @param genre new genre
     */
    public void addGenre(Genre genre){
        this.genres.add(genre);
    }

    /**
     * Return string genres of Book.
     *
     * @return string genres of Book
     */
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

    /**
     * Return text file of Book.
     *
     * @return text file of Book
     */
    public String getTextFileUrl() {
        return textFileUrl;
    }

    /**
     * Set new text file to Book
     *
     * @param textFileUrl new text file
     */
    public void setTextFileUrl(String textFileUrl) {
        this.textFileUrl = textFileUrl;
    }

    /**
     * Return authors of Book.
     *
     * @return authors of Book
     */
    public String getAuthors() {
        return authors==null ? FieldNameConstant.EMPTY_STRING : authors;
    }

    /**
     * Set new authors to Book
     *
     * @param authors new authors
     */
    public void setAuthors(String authors) {
        this.authors = authors;
    }

    /**
     * Override {@link Object#equals(Object)}<br>
     * Check all fields of objects<br>
     * All checks allow for null reference
     *
     * @param obj object to compare
     * @return Boolean equality of input and current objects
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;

        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (pages != null ? !pages.equals(book.pages) : book.pages != null) return false;
        if (publishingHouse != null ? !publishingHouse.equals(book.publishingHouse) : book.publishingHouse != null)
            return false;
        if (pdfFileUrl != null ? !pdfFileUrl.equals(book.pdfFileUrl) : book.pdfFileUrl != null) return false;
        if (textFileUrl != null ? !textFileUrl.equals(book.textFileUrl) : book.textFileUrl != null) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        return genres != null ? genres.equals(book.genres) : book.genres == null;
    }

    /**
     * Override {@link Object#hashCode()}<br>
     * All calculations allow for null reference
     *
     * @return integer hashCode of object/
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = super.hashCode();
        hashCode = prime * hashCode + (description != null ? description.hashCode() : 0);
        hashCode = prime * hashCode + (pages != null ? pages.hashCode() : 0);
        hashCode = prime * hashCode + (publishingHouse != null ? publishingHouse.hashCode() : 0);
        hashCode = prime * hashCode + (pdfFileUrl != null ? pdfFileUrl.hashCode() : 0);
        hashCode = prime * hashCode + (textFileUrl != null ? textFileUrl.hashCode() : 0);
        hashCode = prime * hashCode + (authors != null ? authors.hashCode() : 0);
        hashCode = prime * hashCode + (genres != null ? genres.hashCode() : 0);
        return hashCode;
    }
}
