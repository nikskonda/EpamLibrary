package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookmarkDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.BookmarkService;

import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Book> getBooksWithBookmark(Integer userId, PageAttributes pageAttributes) throws DAOException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)
                || !ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttributes)){
            return null;
        }
        BookService service = ServiceFactory.getBookService();
        List<Bookmark> bookmarks = bookmarkDAO.getBookmarksOfUser(userId, pageAttributes);
        List<Book> books = new ArrayList<>();

        for (Bookmark bookmark : bookmarks){
            books.add(service.getBook(bookmark.getBookId(), bookmark.getLocale()));
        }

        return books;
     }

    @Override
    public boolean deleteBookmark(Bookmark bookmark) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.BOOKMARK_VALIDATOR, bookmark)){
            return false;
        }
        return bookmarkDAO.deleteBookmark(bookmark);
    }

    @Override
    public Integer calcPagesCountBookmarks(Integer userId, String locale, Integer countBookmarksOnPage) throws DAOException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBookmarksOnPage)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }

        return bookmarkDAO.calcTotalPages(userId, locale, countBookmarksOnPage);
    }
}
