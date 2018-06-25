package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.book.Book;
import by.epam.java.training.model.book.BookCover;
import by.epam.java.training.model.book.PublishingHouse;
import org.apache.log4j.Logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class BookDAOImpl extends AbstractDAO implements BookDAO {

    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

    @Override
    public List<BookCover> getAllBooks(String locale) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        List<BookCover> booksList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_ALL_BOOKS);
            cstmt.setString(LOCALE, locale);
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
    public Book getBook(Integer id, String locale) {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getInstance().getConnectionPool();
        Book book = new Book();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_BOOK_BY_ID);
            cstmt.setInt(BOOK_ID, id);
            cstmt.setString(LOCALE, locale);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                book.setId(rs.getInt(BOOK_ID));
                book.setName(rs.getString(BOOK_NAME));
                book.setDescription(rs.getString(BOOK_DESCRIPTION));
                book.setPublishYear(rs.getInt(BOOK_PUBLISH_YEAR));
                book.setPrice(rs.getDouble(BOOK_PRICE));
                book.setPages(rs.getInt(BOOK_PAGES));
                PublishingHouse ph = new PublishingHouse(rs.getInt(BOOK_PUBLISHING_HOUSE_ID),
                        rs.getString(BOOK_PUBLISHING_HOUSE_NAME));
                book.setPublishingHouse(ph);
                book.setPdfFileUrl(rs.getString(BOOK_PDF_FILE));
                book.setCoverUrl(rs.getString(BOOK_COVER));
            }
        } catch (SQLException ex) {
            logger.warn("Database query error",ex);
        } finally {
            closeResultSet(rs);
            closeCallableStatement(cstmt);
            putbackConnection(con, conPool);
        }
        return book;

    }
}
