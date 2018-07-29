package by.epam.training.dao;

import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.BookPreview;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with book search in database.
 *
 * @author  Nikita Shkonda
 */
public interface BookSearchDAO {

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
    List<BookPreview> findBooksPerPage(String search, PageAttribute pageAttribute) throws DAOException;

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
    Integer calcPagesCountBookSearchResults(String locale, String search, Integer countBooksOnOnePage) throws DAOException;

}
