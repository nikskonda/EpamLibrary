package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;

import java.util.List;

public interface BookService {

    Book getBook(Integer bookId, String locale) throws DAOException;

    List<String> getTextOfBook(Integer bookId, String locale, String path, Integer page) throws DAOException;

    List<BookCover> getBooksByPage(LordOfPages pageData) throws DAOException;

    Integer calcTotalPages(String locale, Integer countBooksOnOnePage) throws DAOException;
}

