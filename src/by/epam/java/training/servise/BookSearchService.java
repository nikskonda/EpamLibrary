package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttributes;
import by.epam.java.training.model.book.BookPreview;

import java.util.List;

/**
 * The interface defines methods for implementing different
 * activities with book search.
 *
 * @author  Nikita Shkonda
 */
public interface BookSearchService {

    /**
     * Find list of books in which match the search query.
     *
     * @param search - Search word.
     * @param pageAttributes - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     * @see BookPreview
     * @see PageAttributes
     *
     */
    List<BookPreview> findBooksPerPage(String search, PageAttributes pageAttributes) throws  DAOException;

    /**
     * Return total count of pages with books in which match the search query.
     *
     * @param search - Search word.
     * @param locale - Language.
     * @param countBooksOnPage - Count books on one page.
     *
     * @return Total count of pages with books in which match the search query.
     *
     * @throws DAOException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountBookSearchResult(String locale, String search, Integer countBooksOnPage) throws DAOException;

}
