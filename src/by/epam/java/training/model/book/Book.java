package by.epam.java.training.model.book;

import java.io.Serializable;

public class Book extends BookCover implements Serializable {
    private static final long serialVersionUID = 1L;


    private String description;
    private Integer pages;
    private PublishingHouse publishingHouse;
    private String pdfFileUrl;


    public Book() {
    }


    public Book(Integer id, String name, Integer publishYear, Double price, String coverUrl, String description, Integer pages, PublishingHouse publishingHouse, String pdfFileUrl) {
        super(id, name, publishYear, price, coverUrl);
        this.description = description;
        this.pages = pages;
        this.publishingHouse = publishingHouse;
        this.pdfFileUrl = pdfFileUrl;
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


}
