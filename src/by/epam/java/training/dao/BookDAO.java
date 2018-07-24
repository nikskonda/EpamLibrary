package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.model.book.constituents.Genre;

import java.util.List;

public interface BookDAO {

    Book getBook(Integer bookId, String locale) throws DAOException;

    String getUrlToTextOfBook(Integer bookId, String locale) throws DAOException;

    List<BookPreview> getBooksPerPage(PageAttributes pageAttributes) throws DAOException;

    Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws DAOException;

    List<Genre> getGenres(String lang) throws DAOException;

}
