package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookmarkDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.BookmarkService;

import org.apache.log4j.Logger;

public class BookmarkServiceImpl implements BookmarkService{
    private static final Logger logger = Logger.getLogger(BookmarkServiceImpl.class);

    private final BookmarkDAO bookmarkDAO = DAOFactory.getBookmarkDAO();

    @Override
    public Integer getBookmark(Bookmark bookmark) {
        return bookmarkDAO.getBookmark(bookmark);
    }

    @Override
    public boolean setBookmark(Bookmark bookmark) {
        return bookmarkDAO.setBookmark(bookmark);
    }
}
