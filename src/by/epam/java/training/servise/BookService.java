package by.epam.java.training.servise;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookService {

    List<BookCover> getAllBooks(String locale);

    Book getBook(Integer bookId, String locale);

    String getTextOfBook(Integer bookId, String locale, String path);

}
