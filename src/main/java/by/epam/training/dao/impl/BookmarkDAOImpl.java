package by.epam.training.dao.impl;

import by.epam.training.dao.AbstractDAO;
import by.epam.training.dao.BookmarkDAO;
import by.epam.training.dao.exception.ConnectionPoolException;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.dao.util.SQLRequestConstant;
import by.epam.training.model.book.Bookmark;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement an interface that defines different activities with bookmarks in database.
 *
 * @author  Nikita Shkonda
 */
public class BookmarkDAOImpl extends AbstractDAO implements BookmarkDAO {

    private static final Logger logger = Logger.getLogger(BookmarkDAOImpl.class);

    private static final Integer DEFAULT_PAGE_NUMBER = 1;

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
    @Override
    public Integer getBookmark(Bookmark bookmark) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer page = DEFAULT_PAGE_NUMBER;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_BOOKMARK);
            cstmt.setInt(SQLRequestConstant.USER_ID, bookmark.getUserId());
            cstmt.setInt(SQLRequestConstant.BOOK_ID, bookmark.getBookId());
            cstmt.setString(SQLRequestConstant.LOCALE, bookmark.getLocale());
            rs = cstmt.executeQuery();

            while (rs.next()) {
                page = rs.getInt(SQLRequestConstant.PAGE_NUMBER);
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return page;
    }

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
    @Override
    public boolean setBookmark(Bookmark bookmark) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.SET_BOOKMARK);
            cstmt.setInt(SQLRequestConstant.USER_ID, bookmark.getUserId());
            cstmt.setInt(SQLRequestConstant.BOOK_ID, bookmark.getBookId());
            cstmt.setString(SQLRequestConstant.LOCALE, bookmark.getLocale());
            cstmt.setInt(SQLRequestConstant.PAGE_NUMBER, bookmark.getPageNumber());
            rs = cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return result;
    }

    /**
     * Return list of books in which bookmarks was made.
     *
     * @param userId - Id of the user.
     * @param pageAttribute - Information about page.
     *
     * @return list of books in which bookmarks was made.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Bookmark
     *
     */
    @Override
    public List<Bookmark> getBookmarksOfUser(Integer userId, PageAttribute pageAttribute) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Bookmark> bookmarks = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_LIST_OF_BOOKMARKS);
            cstmt.setInt(SQLRequestConstant.USER_ID, userId);
            cstmt.setString(SQLRequestConstant.LOCALE, pageAttribute.getLocale());
            cstmt.setInt(SQLRequestConstant.COUNT_BOOKMARKS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(SQLRequestConstant.NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            rs = cstmt.executeQuery();

            while (rs.next()){
                Bookmark bookmark = new Bookmark();
                bookmark.setBookId(rs.getInt(SQLRequestConstant.BOOK_ID));
                bookmark.setPageNumber(rs.getInt(SQLRequestConstant.PAGE_NUMBER));
                bookmark.setUserId(userId);
                bookmark.setLocale(pageAttribute.getLocale());

                bookmarks.add(bookmark);
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return bookmarks;
    }

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
    @Override
    public boolean deleteBookmark(Bookmark bookmark) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.DELETE_BOOKMARK);
            cstmt.setInt(SQLRequestConstant.USER_ID, bookmark.getUserId());
            cstmt.setInt(SQLRequestConstant.BOOK_ID, bookmark.getBookId());
            cstmt.setString(SQLRequestConstant.LOCALE, bookmark.getLocale());
            rs = cstmt.executeQuery();

            result = true;
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return result;
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
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public Integer calcPagesCountBookmarks(Integer userId, String locale, Integer countBookmarksOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.CALC_TOTAL_PAGES_IN_BOOKMARKS);
            cstmt.setInt(SQLRequestConstant.COUNT_BOOKMARKS_ON_PAGE, countBookmarksOnOnePage);
            cstmt.setString(SQLRequestConstant.LOCALE, locale);
            cstmt.setInt(SQLRequestConstant.USER_ID, userId);
            cstmt.registerOutParameter(SQLRequestConstant.RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(SQLRequestConstant.RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }
}
