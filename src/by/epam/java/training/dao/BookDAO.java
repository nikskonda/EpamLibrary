package by.epam.java.training.dao;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookDAO {

    List<BookCover> getAllBooks(String locale);

    Book getBook(Integer bookId, String locale);

    String getUrlToTextOfBook(Integer bookId, String locale);
}
