package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.constituents.Genre;

import java.util.List;

public interface BookService {

    Book getBook(Integer bookId, String locale) throws DAOException;

    List<String> getTextOfBook(Integer bookId, String locale, String path, Integer page) throws DAOException;

    List<BookCover> getBooksByPage(PageAttributes pageData) throws DAOException;

    Integer calcTotalPages(String locale, Integer countBooksOnOnePage) throws DAOException;

    List<Genre> getListOfGenre(String lang) throws DAOException;
}

