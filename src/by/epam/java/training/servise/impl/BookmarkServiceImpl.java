package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookmarkDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.BookmarkService;

import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

public class BookmarkServiceImpl implements BookmarkService{
    private static final Logger logger = Logger.getLogger(BookmarkServiceImpl.class);

    private final BookmarkDAO bookmarkDAO = DAOFactory.getBookmarkDAO();

    @Override
    public Integer getBookmark(Bookmark bookmark) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.BOOKMARK_VALIDATOR, bookmark)){
            return null;
        }
        return bookmarkDAO.getBookmark(bookmark);
    }

    @Override
    public boolean setBookmark(Bookmark bookmark)throws DAOException  {
        if (!ValidatorManager.isValid(ValidatorType.NEW_BOOKMARK_VALIDATOR, bookmark)){
            return false;
        }
        return bookmarkDAO.setBookmark(bookmark);
    }
}
