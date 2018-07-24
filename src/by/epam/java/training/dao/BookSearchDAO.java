package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.BookPreview;

import java.util.List;

public interface BookSearchDAO {

    List<BookPreview> getBooksByPage(String search, PageAttributes pageData) throws DAOException;

    Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage) throws DAOException;

}
