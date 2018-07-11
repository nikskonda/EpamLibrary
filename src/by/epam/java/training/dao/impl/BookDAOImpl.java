package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.dao.util.ConnectionPool;
import by.epam.java.training.model.LordOfPages;
import by.epam.java.training.model.book.*;
import by.epam.java.training.model.book.constituents.Author;
import by.epam.java.training.model.book.constituents.Genre;
import by.epam.java.training.model.book.constituents.PublishingHouse;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequest.*;

public class BookDAOImpl extends AbstractDAO implements BookDAO {

    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);


    @Override
    public Book getBook(Integer bookId, String locale) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        Book book = new Book();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_BOOK);
            cstmt.setInt(BOOK_ID, bookId);
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
                book.setPdfFileUrl(rs.getString(BOOK_PDF_FILE_URL));
                book.setCoverUrl(rs.getString(BOOK_COVER_URL));
                book.setAuthors(findAuthors(bookId));
                book.setGenres(findGenres(bookId));
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
        return book;
    }

    private List<Author> findAuthors(Integer bookId) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<Author> authors = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_BOOK_AUTHORS);
            cstmt.setInt(BOOK_ID, bookId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt(AUTHOR_ID));
                author.setFirstName(rs.getString(AUTHOR_FIRST_NAME));
                author.setLastName(rs.getString(AUTHOR_LAST_NAME));
                authors.add(author);
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
        return authors;
    }

    private List<Genre> findGenres(Integer bookId) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<Genre> genres = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_BOOK_GENRES);
            cstmt.setInt(BOOK_ID, bookId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Genre genre = new Genre();
                genre.setId(rs.getInt(GENRE_ID));
                genre.setName(rs.getString(GENRE_NAME));
                genres.add(genre);
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
        return genres;
    }

    @Override
    public String getUrlToTextOfBook(Integer bookId, String locale) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        String textUrl = null;
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_URL_TO_TEXT_OF_BOOK);
            cstmt.setString(LOCALE, locale);
            cstmt.setInt(BOOK_ID, bookId);
            rs = cstmt.executeQuery();
            while (rs.next()) {
                textUrl = rs.getString(BOOK_TEXT_FILE_URL);
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
        return textUrl;
    }

    @Override
    public List<BookCover> getListOfBooksByPage(LordOfPages pageData) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        ConnectionPool conPool = DAOFactory.getConnectionPool();
        List<BookCover> booksList = new ArrayList<>();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(GET_LIST_OF_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, pageData.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageData.getNumberOfPage());
            cstmt.setString(LOCALE, pageData.getLocale());
            rs = cstmt.executeQuery();
            while (rs.next()) {
                BookCover book = new BookCover();
                book.setId(rs.getInt(BOOK_ID));
                book.setName(rs.getString(BOOK_NAME));
                book.setPublishYear(rs.getInt(BOOK_PUBLISH_YEAR));
                book.setPrice(rs.getDouble(BOOK_PRICE));
                book.setCoverUrl(rs.getString(BOOK_COVER_URL));
                booksList.add(book);
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
        return booksList;
    }

    @Override
    public Integer calcTotalPagesWithBooks(String locale, Integer countBooksOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Integer result = null;

        ConnectionPool conPool = DAOFactory.getConnectionPool();
        try {
            con = conPool.retrieve();
            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, countBooksOnOnePage);
            cstmt.setString(LOCALE, locale);
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
