package by.epam.java.training.dao;

import by.epam.java.training.model.book.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getBooks();

    List<Book> getBooks(String locale);

}
