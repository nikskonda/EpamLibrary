package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.BookSearchDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.book.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class BookSearchDAOImpl extends AbstractDAO implements BookSearchDAO {

    private static final Logger logger = Logger.getLogger(BookSearchDAOImpl.class);

    @Override
    public List<BookCover> getBooksByPage(String locale, String search, Integer countOnPage, Integer numberOfPage) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<BookCover> booksList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(FIND_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, countOnPage);
            cstmt.setInt(NUMBER_OF_PAGE, numberOfPage);
            cstmt.setString(LOCALE, locale);
            cstmt.setString(SEARCH, search);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                BookCover book = new BookCover();
                book.setId(rs.getInt(BOOK_ID));
                book.setName(rs.getString(BOOK_NAME));
                book.setPublishYear(rs.getInt(BOOK_PUBLISH_YEAR));
                book.setPrice(rs.getDouble(BOOK_PRICE));
                book.setCoverUrl(rs.getString(BOOK_COVER));
                booksList.add(book);
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return booksList;
    }

    @Override
    public Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CALC_TOTAL_PAGES_BOOKS_SERCH);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, countBooksOnOnePage);
            cstmt.setString(LOCALE, locale);
            cstmt.setString(SEARCH, search);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (SQLException ex) {
            logger.warn("Вatabase query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return result;
    }

}
