package by.epam.java.training.servise;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookService {

    Book getBook(Integer bookId, String locale);

    List<String> getTextOfBook(Integer bookId, String locale, String path, Integer page);

    List<BookCover> getBooksByPage(String locale, Integer countOnPage, Integer numberOfPage);

    Integer calcTotalPages(String locale, Integer countBooksOnOnePage);

    Integer getBookmark(Integer userId, Integer bookId, String locale);
}

