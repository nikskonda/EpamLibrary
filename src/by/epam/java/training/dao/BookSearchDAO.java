package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookSearchDAO {

    List<BookCover> getBooksByPage(String search, LordOfPages pageData) throws DAOException;

    Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage) throws DAOException;

}
