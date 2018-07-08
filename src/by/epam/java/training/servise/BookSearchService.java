package by.epam.java.training.servise;

import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookSearchService {

    List<BookCover> getBooksByPage(String locale, String search, Integer countOnPage, Integer numberOfPage);

    Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage);

}
