package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookSearchService {

    List<BookCover> findBooksByPage(String search, PageAttributes pageData) throws  DAOException;

    Integer calcPagesCountBookSearchResult(String locale, String search, Integer countBooksOnOnePage) throws DAOException;

}
