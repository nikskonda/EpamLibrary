package by.epam.java.training.model.book;

import java.io.Serializable;

public class BookCover implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private Integer publishYear;
    private Double price;
    private String coverUrl;


    public BookCover() {
    }

    public BookCover(Integer id, String name, Integer publishYear, Double price, String coverUrl) {
        this.id = id;
        this.name = name;
        this.publishYear = publishYear;
        this.price = price;
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

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
