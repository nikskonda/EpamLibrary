package by.epam.java.training.model.book;

import java.io.Serializable;

public class PublishingHouse implements Serializable {
   private static final long serialVersionUID = 1L;

   private Integer id;
   private String name;

    public PublishingHouse() {
    }

    public PublishingHouse(String name) {
        this.name = name;
    }

    public PublishingHouse(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
