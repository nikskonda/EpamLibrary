package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.BookmarkDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class BookmarkDAOImpl extends AbstractDAO implements BookmarkDAO {

    private static final Logger logger = Logger.getLogger(BookmarkDAOImpl.class);

    private static final Integer DEFAULT_PAGE_NUMBER = new Integer(1);

    @Override
    public Integer getBookmark(Bookmark bookmark) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        Integer page = new Integer(DEFAULT_PAGE_NUMBER);
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_BOOKMARK);
            cstmt.setInt(USER_ID, bookmark.getUserId());
            cstmt.setInt(BOOK_ID, bookmark.getBookId());
            cstmt.setString(LOCALE, bookmark.getLocale());
            rs = cstmt.executeQuery();
            while (rs.next()) {
                page = rs.getInt(PAGE_NUMBER);
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return page;
    }

    @Override
    public boolean setBookmark(Bookmark bookmark) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        boolean result = false;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(SET_BOOKMARK);
            cstmt.setInt(USER_ID, bookmark.getUserId());
            cstmt.setInt(BOOK_ID, bookmark.getBookId());
            cstmt.setString(LOCALE, bookmark.getLocale());
            cstmt.setInt(PAGE_NUMBER, bookmark.getPageNumber());
            rs = cstmt.executeQuery();
            result = true;
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        }  finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public List<Bookmark> getBookmarksOfUser(Integer userId, PageAttributes pageAttributes) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<Bookmark> bookmarks = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_LIST_OF_BOOKMARKS);
            cstmt.setInt(USER_ID, userId);
            cstmt.setString(LOCALE, pageAttributes.getLocale());
            cstmt.setInt(COUNT_BOOKMARKS_ON_PAGE, pageAttributes.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageAttributes.getNumberOfPage());
            rs = cstmt.executeQuery();
            while (rs.next()){
                Bookmark bookmark = new Bookmark();
                bookmark.setBookId(rs.getInt(BOOK_ID));
                bookmark.setPageNumber(rs.getInt(PAGE_NUMBER));
                bookmark.setUserId(userId);
                bookmark.setLocale(pageAttributes.getLocale());

                bookmarks.add(bookmark);
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        }  finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return bookmarks;
    }

    @Override
    public boolean deleteBookmark(Bookmark bookmark) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        boolean result = false;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(DELETE_BOOKMARK);
            cstmt.setInt(USER_ID, bookmark.getUserId());
            cstmt.setInt(BOOK_ID, bookmark.getBookId());
            cstmt.setString(LOCALE, bookmark.getLocale());
            rs = cstmt.executeQuery();
            result = true;
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        }  finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

    @Override
    public Integer calcTotalPages(Integer userId, String locale, Integer countBookmarksOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_BOOKMARKS);
            cstmt.setInt(COUNT_BOOKMARKS_ON_PAGE, countBookmarksOnOnePage);
            cstmt.setString(LOCALE, locale);
            cstmt.setInt(USER_ID, userId);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);

        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }
}
