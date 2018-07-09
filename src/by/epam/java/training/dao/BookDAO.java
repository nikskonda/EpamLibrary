package by.epam.java.training.dao;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookDAO {

    List<BookCover> getAllBooks(String locale);

    Book getBook(Integer bookId, String locale);

    String getUrlToTextOfBook(Integer bookId, String locale);

    List<BookCover> getBooksByPage(String locale, Integer countOnPage, Integer numberOfPage);

    Integer calcTotalPages(String locale, Integer countBooksOnOnePage);

    Integer getBookmark(Integer userId, Integer bookId, String locale);
}
