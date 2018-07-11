package by.epam.java.training.model.book.constituents;

import java.io.Serializable;

public class Genre implements Serializable{

    private static final long serialVersionUID = -7727031108910163406L;

    private Integer id;
    private String name;

    public Genre() {
    }

    public Genre(Integer id, String name) {
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
