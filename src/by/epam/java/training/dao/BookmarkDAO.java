package by.epam.java.training.dao;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Bookmark;

import java.util.List;

public interface BookmarkDAO {

    Integer getBookmark(Bookmark bookmark) throws DAOException;

    boolean setBookmark(Bookmark bookmark) throws DAOException;

    List<Bookmark> getBookmarksOfUser(Integer userId, PageAttributes pageAttributes)
            throws DAOException;

    boolean deleteBookmark(Bookmark bookmark) throws DAOException;

    Integer calcTotalPages(Integer userId, String locale, Integer countBookmarksOnOnePage) throws DAOException;
}
