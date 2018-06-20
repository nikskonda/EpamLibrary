package by.epam.java.training.model.book;

import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private Integer publishYear;
    private Double price;
    private Integer pages;
    private PublishingHouse publishingHouse;
    private String pdfFileUrl;
    private String coverUrl;


    public Book() {
    }

    public Book(Integer id, String name, String description, Integer publishYear, Double price, Integer pages,
                PublishingHouse publishingHouse, String pdfFileUrl, String coverUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.publishYear = publishYear;
        this.price = price;
        this.pages = pages;
        this.publishingHouse = publishingHouse;
        this.pdfFileUrl = pdfFileUrl;
        this.coverUrl = coverUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
