package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookSearchDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class BookSearchDAOImpl extends AbstractDAO implements BookSearchDAO {

    private static final Logger logger = Logger.getLogger(BookSearchDAOImpl.class);

    @Override
    public List<BookPreview> getBooksByPage(String search, PageAttributes pageData) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<BookPreview> booksList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(FIND_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
            cstmt.setString(LOCALE, pageData.getLocale());
            cstmt.setString(SEARCH, search);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                BookPreview book = new BookPreview();
                book.setId(rs.getInt(BOOK_ID));
                book.setName(rs.getString(BOOK_NAME));
                book.setPublishYear(rs.getInt(BOOK_PUBLISH_YEAR));
                book.setPrice(rs.getDouble(BOOK_PRICE));
                book.setCoverUrl(rs.getString(BOOK_COVER_URL));

                booksList.add(book);
            }
        }catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        }  finally {
            closeAll(rs, cstmt, con);
        }
        return booksList;
    }

    @Override
    public Integer calcTotalPages(String locale, String search, Integer countBooksOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CALC_TOTAL_PAGES_BOOKS_SEARCH);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, countBooksOnOnePage);
            cstmt.setString(LOCALE, locale);
            cstmt.setString(SEARCH, search);
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
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

}
