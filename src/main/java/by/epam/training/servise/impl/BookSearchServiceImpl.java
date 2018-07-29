package by.epam.training.servise.impl;

import by.epam.training.dao.BookSearchDAO;
import by.epam.training.dao.DAOFactory;
import by.epam.training.dao.exception.DAOException;
import by.epam.training.model.PageAttribute;
import by.epam.training.model.book.BookPreview;
import by.epam.training.servise.BookSearchService;
import by.epam.training.servise.exception.ServiceException;
import by.epam.training.servise.validation.ValidatorManager;
import by.epam.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Implementing the {@link BookSearchService} interface for different activities with book's search.
 *
 * @author  Nikita Shkonda
 */
public class BookSearchServiceImpl implements BookSearchService {
    private static final Logger logger = Logger.getLogger(BookSearchServiceImpl.class);

    private final BookSearchDAO bookSearchDAO = DAOFactory.getBookSearchDAO();

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
    @Override
    public List<BookPreview> findBooksPerPage(String search, PageAttribute pageAttribute) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.PAGES_VALIDATOR, pageAttribute) ||
                !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)){
            return null;
        }
        try{
            return bookSearchDAO.findBooksPerPage(search, pageAttribute);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
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
     * @throws ServiceException  if there was an error executing the query
     * in the database
     *
     */
    @Override
    public Integer calcPagesCountBookSearchResult(String locale, String search, Integer countBooksOnOnePage) throws ServiceException {
        if (!ValidatorManager.isValid(ValidatorType.LOCALE_VALIDATOR, locale)
                || !ValidatorManager.isValid(ValidatorType.STRING_VALIDATOR, search)
                || !ValidatorManager.isValid(ValidatorType.NATURAL_NUMBER_VALIDATOR, countBooksOnOnePage)){
            return null;
        }
        try{
            return  bookSearchDAO.calcPagesCountBookSearchResults(locale, search, countBooksOnOnePage);
        } catch (DAOException ex){
            throw new ServiceException(ex);
        }
    }

}
