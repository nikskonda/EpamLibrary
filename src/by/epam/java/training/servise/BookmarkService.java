package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.Bookmark;

import java.util.List;

public interface BookmarkService {

    Integer getBookmark(Bookmark bookmark) throws DAOException;

    boolean setBookmark(Bookmark bookmark) throws DAOException;

    List<Book> getListOfBooksWithBookmark(Integer userId, PageAttributes pageAttributes) throws DAOException;

    boolean deleteBookmark(Bookmark bookmark) throws DAOException;

    Integer calcTotalPages(Integer userId, String locale, Integer countBookmarksOnOnePage) throws DAOException;
}

