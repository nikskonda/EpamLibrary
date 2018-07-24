package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.Bookmark;

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
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Bookmark
     */
    Integer getBookmark(Bookmark bookmark) throws DAOException;

    /**
     * Save number of page for specific book, user and language.
     *
     * @param bookmark - information about bookmark.
     *
     * @return <tt>true</tt> if the added was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Bookmark
     */
    boolean setBookmark(Bookmark bookmark) throws DAOException;

    /**
     * Return list of books in which bookmarks was made.
     *
     * @param userId - Id of the user.
     * @param pageAttributes - Information about page.
     *
     * @return list of books in which bookmarks was made.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Bookmark
     * @see Book
     *
     */
    List<Book> getBooksWithBookmark(Integer userId, PageAttributes pageAttributes) throws DAOException;

    /**
     * Delete bookmark for specific book, user and language.
     *
     * @param bookmark - Information about bookmark.
     *
     * @return <tt>true</tt> if the deleted was successful.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Bookmark
     *
     */
    boolean deleteBookmark(Bookmark bookmark) throws DAOException;

    /**
     * Return total number of pages with bookmarks.
     *
     * @param userId - Id of the user.
     * @param locale - Language.
     * @param countBookmarksOnPage - Count of bookmarks displayed on one page.
     *
     * @return Total number of pages with bookmarks.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountBookmarks(Integer userId, String locale, Integer countBookmarksOnPage) throws DAOException;
}

