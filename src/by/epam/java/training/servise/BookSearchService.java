package by.epam.java.training.servise;

import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.servise.exception.ServiceException;

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
     * @param pageAttribute - Information about page.
     *
     * @return list of book previews for specified page.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the database
     *
     * @see BookPreview
     * @see PageAttribute
     *
     */
    List<BookPreview> findBooksPerPage(String search, PageAttribute pageAttribute) throws ServiceException;

    /**
     * Return total count of pages with books in which match the search query.
     *
     * @param search - Search word.
     * @param locale - Language.
     * @param countBooksOnOnePage - Count books on one page.
     *
     * @return Total count of pages with books in which match the search query.
     *
     * @throws ServiceException  if there was an error executing the query
     * in the database
     *
     */
    Integer calcPagesCountBookSearchResult(String locale, String search, Integer countBooksOnOnePage) throws ServiceException;

}
