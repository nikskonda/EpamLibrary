package by.epam.training.dao.impl;

import by.epam.training.dao.AbstractDAO;
import by.epam.training.dao.BookSearchDAO;
import by.epam.training.dao.exception.ConnectionPoolException;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.dao.util.SQLRequestConstant;
import by.epam.training.model.book.BookPreview;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

            cstmt = con.prepareCall(SQLRequestConstant.FIND_BOOKS);
            cstmt.setInt(SQLRequestConstant.COUNT_BOOKS_ON_PAGE, pageAttribute.getCountOnPage());
            cstmt.setInt(SQLRequestConstant.NUMBER_OF_PAGE, pageAttribute.getNumberOfPage());
            cstmt.setString(SQLRequestConstant.LOCALE, pageAttribute.getLocale());
            cstmt.setString(SQLRequestConstant.SEARCH, search);
            rs = cstmt.executeQuery();

            while (rs.next()) {
                BookPreview book = new BookPreview();
                book.setId(rs.getInt(SQLRequestConstant.BOOK_ID));
                book.setName(rs.getString(SQLRequestConstant.BOOK_NAME));
                book.setPublishYear(rs.getInt(SQLRequestConstant.BOOK_PUBLISH_YEAR));
                book.setPrice(rs.getDouble(SQLRequestConstant.BOOK_PRICE));
                book.setCoverUrl(rs.getString(SQLRequestConstant.BOOK_COVER_URL));

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

            cstmt = con.prepareCall(SQLRequestConstant.CALC_TOTAL_PAGES_BOOKS_SEARCH);
            cstmt.setInt(SQLRequestConstant.COUNT_BOOKS_ON_PAGE, countBooksOnOnePage);
            cstmt.setString(SQLRequestConstant.LOCALE, locale);
            cstmt.setString(SQLRequestConstant.SEARCH, search);
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
