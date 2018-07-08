package by.epam.java.training.dao;

import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookSearchDAO {

    List<BookCover> getBooksByPage(String locale, String search, Integer countOnPage, Integer numberOfPage);

    Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage);

}
