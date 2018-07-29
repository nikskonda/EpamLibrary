package by.epam.java.training.dao.impl;

import by.epam.java.training.dao.AbstractDAO;
import by.epam.java.training.dao.BookSearchDAO;
import by.epam.java.training.dao.exception.ConnectionPoolException;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.*;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.java.training.dao.util.SQLRequestConstant.*;

/**
 * Implement an interface that defines different activities with book search in database.
 *
 * @author  Nikita Shkonda
 */
public class BookSearchDAOImpl extends AbstractDAO implements BookSearchDAO {

    private static final Logger logger = Logger.getLogger(BookSearchDAOImpl.class);

    /**
     * Find list of books in which match the search query.
     *
     * @param search - Search word.
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
    public List<BookPreview> findBooksPerPage(String search, PageAttribute pageAttribute) throws DAOException {
        Connection con = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<BookPreview> booksList = new ArrayList<>();
        try {
            con = retrieveConnection();

            cstmt = con.prepareCall(FIND_BOOKS);
            cstmt.setInt(COUNT_BOOKS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            cstmt.setString(LOCALE, pageAttribute.getLocale());
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
     * Return total count of pages with books in which match the search query.
     *
     * @param search - Search word.
     * @param locale - Language.
     * @param countBooksOnOnePage - Count books on one page.
     *
     * @return Total count of pages with books in which match the search query.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public Integer calcPagesCountBookSearchResults(String locale, String search, Integer countBooksOnOnePage) throws DAOException {
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
            throw new DAOException(ex);
        } catch (SQLException ex) {
            throw new DAOException("Database query error", ex);
        } finally {
            closeStatementAndConnection(cstmt, con);
        }
        return result;
    }

}
