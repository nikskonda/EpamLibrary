package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;
import by.epam.java.training.servise.exception.ServiceException;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with bookmarks.
 *
 * @author  Nikita Shkonda
 */
public interface BookmarkService {

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
    Integer getBookmark(Bookmark bookmark) throws ServiceException;

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
    boolean setBookmark(Bookmark bookmark) throws ServiceException;

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
    List<Book> getBooksWithBookmark(Integer userId, PageAttribute pageAttribute) throws ServiceException;

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
    boolean deleteBookmark(Bookmark bookmark) throws ServiceException;

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
    Integer calcPagesCountBookmarks(Integer userId, String locale, Integer countBookmarksOnOnePage) throws ServiceException;
}

