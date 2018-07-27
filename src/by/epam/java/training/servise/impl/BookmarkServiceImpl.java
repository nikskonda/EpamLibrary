package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookmarkDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.BookService;
import by.epam.java.training.servise.BookmarkService;

import by.epam.java.training.servise.ServiceFactory;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookmarkServiceImpl implements BookmarkService{
    private static final Logger logger = Logger.getLogger(BookmarkServiceImpl.class);

    private final BookmarkDAO bookmarkDAO = DAOFactory.getBookmarkDAO();

    @Override
    public Integer getBookmark(Bookmark bookmark) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.BOOKMARK_VALIDATOR, bookmark)){
            return null;
        }

        try{
            return bookmarkDAO.getBookmark(bookmark);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public boolean setBookmark(Bookmark bookmark)throws ServiceException  {
        if (!ValidatorManager.isValid(ValidatorType.NEW_BOOKMARK_VALIDATOR, bookmark)){
            return false;
        }

        try{
            return bookmarkDAO.setBookmark(bookmark);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public List<Book> getBooksWithBookmark(Integer userId, PageAttribute pageAttribute) throws ServiceException{
        if (!ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)
                || !ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute)){
            return null;
        }

        try{
            BookService service = ServiceFactory.getBookService();
            List<Bookmark> bookmarks = bookmarkDAO.getBookmarksOfUser(userId, pageAttribute);
            List<Book> books = new ArrayList<>();

            for (Bookmark bookmark : bookmarks){
                books.add(service.getBook(bookmark.getBookId(), bookmark.getLocale()));
            }

            return books;
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
     }

    @Override
    public boolean deleteBookmark(Bookmark bookmark) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.BOOKMARK_VALIDATOR, bookmark)){
            return false;
        }

        try{
            return bookmarkDAO.deleteBookmark(bookmark);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

    @Override
    public Integer calcPagesCountBookmarks(Integer userId, String locale, Integer countBookmarksOnOnePage) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBookmarksOnOnePage)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, userId)){
            return null;
        }

        try{
            return bookmarkDAO.calcPagesCountBookmarks(userId, locale, countBookmarksOnOnePage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }
}
