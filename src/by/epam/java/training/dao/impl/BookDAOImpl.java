package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.*;
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
        Book book = new Book();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_BOOK);
            cstmt.setInt(BOOK_ID, bookId);
            cstmt.setString(LOCALE, locale);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                book = buildBook(rs, locale);
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return book;
    }

    @Override
    public String getUrlToTextOfBook(Integer bookId, String locale) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String textUrl = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_URL_TO_TEXT_OF_BOOK);
            cstmt.setString(LOCALE, locale);
            cstmt.setInt(BOOK_ID, bookId);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                textUrl = rs.getString(BOOK_TEXT_FILE_URL);
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return textUrl;
    }

    @Override
    public List<BookPreview> getBooksPerPage(PageAttribute pageAttribute) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<BookPreview> booksList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_LIST_OF_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            cstmt.setString(LOCALE, pageAttribute.getLocale());
            rs = cstmt.executeQuery();

            while (rs.next()) {
                booksList.add(buildBookCover(rs));
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return booksList;
    }

    @Override
    public Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(CALC_TOTAL_PAGES_IN_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, countBooksOnOnePage);
            cstmt.setString(LOCALE, locale);
            cstmt.registerOutParameter(RESULT, Types.SMALLINT);
            cstmt.executeQuery();

            result = cstmt.getInt(RESULT);
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

    @Override
    public List<Genre> getGenres(String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Genre> genres = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_LIST_OF_GENRES);
            cstmt.setString(LOCALE, lang);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                genres.add(buildGenre(rs));
            }
        } catch (ConnectionPoolException ex){
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeAll(rs, cstmt, con);
        }
        return genres;
    }

    private Genre buildGenre(ResultSet rs) throws SQLException{
        Genre genre = new Genre();

        genre.setId(rs.getInt(GENRE_ID));
        genre.setName(rs.getString(GENRE_NAME));

        return genre;
    }

    private BookPreview buildBookCover(ResultSet rs) throws SQLException{
        BookPreview book = new BookPreview();

        book.setId(rs.getInt(BOOK_ID));
        book.setName(rs.getString(BOOK_NAME));
        book.setPublishYear(rs.getInt(BOOK_PUBLISH_YEAR));
        book.setPrice(rs.getDouble(BOOK_PRICE));
        book.setCoverUrl(rs.getString(BOOK_COVER_URL));

        return book;
    }

    private Book buildBook(ResultSet rs, String locale) throws DAOException, SQLException{
        Book book = new Book(buildBookCover(rs));

        book.setDescription(rs.getString(BOOK_DESCRIPTION));
        book.setPages(rs.getInt(BOOK_PAGES));
        PublishingHouse ph = new PublishingHouse(rs.getInt(BOOK_PUBLISHING_HOUSE_ID),
                rs.getString(BOOK_PUBLISHING_HOUSE_NAME));
        book.setPublishingHouse(ph);
        book.setPdfFileUrl(rs.getString(BOOK_PDF_FILE_URL));
        book.setAuthors(rs.getString(BOOK_AUTHORS));
        book.setGenres(getGenres(book.getId(), locale));

        return book;
    }

    private List<Genre> getGenres(Integer bookId, String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Genre> genres = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(GET_BOOK_GENRES);
            cstmt.setInt(BOOK_ID, bookId);
            cstmt.setString(LOCALE, lang);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                genres.add(buildGenre(rs));
            }
        } catch (ConnectionPoolException ex){
            logger.warn("Database connection failed.",ex);
            throw new DAOException();
        }catch (SQLException ex) {
            logger.warn("Database query error",ex);
            throw new DAOException();
        } finally {
            closeAll(rs, cstmt, con);
        }
        return genres;
    }
}
