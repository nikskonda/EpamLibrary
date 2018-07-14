package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.constituents.Genre;

import java.util.List;

public interface BookDAO {

    Book getBook(Integer bookId, String locale) throws DAOException;

    String getUrlToTextOfBook(Integer bookId, String locale) throws DAOException;

    List<BookCover> getListOfBooksByPage(LordOfPages pageData) throws DAOException;

    Integer calcTotalPagesWithBooks(String locale, Integer countBooksOnOnePage) throws DAOException;

    List<Genre> getListOfGenre(String lang) throws DAOException;

}
