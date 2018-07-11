package by.epam.java.training.model.book.constituents;

import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID = -7727031108910163406L;

    private Integer id;
    private String firstName;
    private String lastName;

    public Author() {
    }

    public Author(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
