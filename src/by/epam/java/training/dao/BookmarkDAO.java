package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.Bookmark;

import java.util.List;

public interface BookmarkDAO {

    Integer getBookmark(Bookmark bookmark) throws DAOException;

    boolean setBookmark(Bookmark bookmark) throws DAOException;

    List<Bookmark> getBookmarksOfUser(Integer userId, String lang, Integer countBookmarks, Integer numberOfPage)
            throws DAOException;

    boolean deleteBookmark(Bookmark bookmark) throws DAOException;
}
