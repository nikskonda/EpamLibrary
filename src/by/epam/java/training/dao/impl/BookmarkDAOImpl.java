package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.BookmarkDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.util.ConnectionPool;
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
    public Integer getBookmark(Bookmark bookmark){
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
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return page;
    }

    @Override
    public boolean setBookmark(Bookmark bookmark) {
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
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }
}
