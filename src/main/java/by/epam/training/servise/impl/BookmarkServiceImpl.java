package by.epam.training.servise.impl;

import by.epam.training.dao.BookmarkDAO;
import by.epam.training.dao.DAOFactory;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.Book;
import by.epam.training.model.book.Bookmark;
import by.epam.training.servise.BookService;
import by.epam.training.servise.BookmarkService;

import by.epam.training.servise.ServiceFactory;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementing the {@link BookmarkService} interface for different activities with bookmarks.
 *
 * @author  Nikita Shkonda
 */
public class BookmarkServiceImpl implements BookmarkService{
    private static final Logger logger = Logger.getLogger(BookmarkServiceImpl.class);

    private final BookmarkDAO bookmarkDAO = DAOFactory.getBookmarkDAO();

    /**
     * Return saved number of page for specific book, user and language.
     *
     * @param bookmark - information about bookmark.
     *
     * @return saved number of page.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Bookmark
     */
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

    /**
     * Save number of page for specific book, user and language.
     *
     * @param bookmark - information about bookmark.
     *
     * @return <tt>true</tt> if the added was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the service.
     *
     * @see Bookmark
     */
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

    /**
     * Return list of books in which bookmarks was made.
     *
     * @param userId - Id of the user.
     * @param pageAttribute - Information about page.
     *
     * @return list of books in which bookmarks was made.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Bookmark
     * @see Book
     *
     */
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

    /**
     * Delete bookmark for specific book, user and language.
     *
     * @param bookmark - Information about bookmark.
     *
     * @return <tt>true</tt> if the deleted was successful.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     * @see Bookmark
     *
     */
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

    /**
     * Return total number of pages with bookmarks.
     *
     * @param userId - Id of the user.
     * @param locale - Language.
     * @param countBookmarksOnOnePage - Count of bookmarks displayed on one page.
     *
     * @return Total number of pages with bookmarks.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the service.
     *
     */
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
