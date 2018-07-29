package by.epam.training.dao.impl;

import by.epam.training.dao.AbstractDAO;
import by.epam.training.dao.BookDAO;
import by.epam.training.dao.exception.ConnectionPoolException;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.constituents.Genre;
import by.epam.training.model.book.constituents.PublishingHouse;
import by.epam.training.dao.util.SQLRequestConstant;
import by.epam.training.model.book.Book;
import by.epam.training.model.book.BookPreview;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement an interface that defines different activities with book in database.
 *
 * @author  Nikita Shkonda
 */
public class BookDAOImpl extends AbstractDAO implements BookDAO {

    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

    /**
     * Return information about book by id and language
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     *
     * @return information about book.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    @Override
    public Book getBook(Integer bookId, String locale) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Book book = new Book();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_BOOK);
            cstmt.setInt(SQLRequestConstant.BOOK_ID, bookId);
            cstmt.setString(SQLRequestConstant.LOCALE, locale);
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

    /**
     * Return url text of the book for specific language.
     *
     * @param bookId - Id of the book.
     * @param locale - Language.
     *
     * @return list of paragraphs of text.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see Book
     *
     */
    @Override
    public String getUrlToTextOfBook(Integer bookId, String locale) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        String textUrl = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_URL_TO_TEXT_OF_BOOK);
            cstmt.setString(SQLRequestConstant.LOCALE, locale);
            cstmt.setInt(SQLRequestConstant.BOOK_ID, bookId);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                textUrl = rs.getString(SQLRequestConstant.BOOK_TEXT_FILE_URL);
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

    /**
     * Return list of book previews for specified page.
     *
     * @param pageAttribute - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see BookPreview
     * @see PageAttribute
     *
     */
    @Override
    public List<BookPreview> getBooksPerPage(PageAttribute pageAttribute) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<BookPreview> booksList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_LIST_OF_BOOKS);
            cstmt.setInt(SQLRequestConstant.COUNT_BOOKS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(SQLRequestConstant.NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            cstmt.setString(SQLRequestConstant.LOCALE, pageAttribute.getLocale());
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

    /**
     * Return total count of page with books.
     *
     * @param locale - Language.
     * @param countBooksOnOnePage - Count books on one page.
     *
     * @return Total count of page with book.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public Integer calcPagesCountBooks(String locale, Integer countBooksOnOnePage) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        Integer result = null;
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.CALC_TOTAL_PAGES_IN_BOOKS);
            cstmt.setInt(SQLRequestConstant.COUNT_BOOKS_ON_PAGE, countBooksOnOnePage);
            cstmt.setString(SQLRequestConstant.LOCALE, locale);
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

    /**
     * Return list of genres for specified language.
     *
     * @param lang - Language.
     *
     * @return List of genres.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public List<Genre> getGenres(String lang) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Genre> genres = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(SQLRequestConstant.GET_LIST_OF_GENRES);
            cstmt.setString(SQLRequestConstant.LOCALE, lang);
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

        genre.setId(rs.getInt(SQLRequestConstant.GENRE_ID));
        genre.setName(rs.getString(SQLRequestConstant.GENRE_NAME));

        return genre;
    }

    private BookPreview buildBookCover(ResultSet rs) throws SQLException{
        BookPreview book = new BookPreview();

        book.setId(rs.getInt(SQLRequestConstant.BOOK_ID));
        book.setName(rs.getString(SQLRequestConstant.BOOK_NAME));
        book.setPublishYear(rs.getInt(SQLRequestConstant.BOOK_PUBLISH_YEAR));
        book.setPrice(rs.getDouble(SQLRequestConstant.BOOK_PRICE));
        book.setCoverUrl(rs.getString(SQLRequestConstant.BOOK_COVER_URL));

        return book;
    }

    private Book buildBook(ResultSet rs, String locale) throws DAOException, SQLException{
        Book book = new Book(buildBookCover(rs));

        book.setDescription(rs.getString(SQLRequestConstant.BOOK_DESCRIPTION));
        book.setPages(rs.getInt(SQLRequestConstant.BOOK_PAGES));
        PublishingHouse ph = new PublishingHouse(rs.getInt(SQLRequestConstant.BOOK_PUBLISHING_HOUSE_ID),
                rs.getString(SQLRequestConstant.BOOK_PUBLISHING_HOUSE_NAME));
        book.setPublishingHouse(ph);
        book.setPdfFileUrl(rs.getString(SQLRequestConstant.BOOK_PDF_FILE_URL));
        book.setAuthors(rs.getString(SQLRequestConstant.BOOK_AUTHORS));
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

            cstmt = con.prepareCall(SQLRequestConstant.GET_BOOK_GENRES);
            cstmt.setInt(SQLRequestConstant.BOOK_ID, bookId);
            cstmt.setString(SQLRequestConstant.LOCALE, lang);
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
