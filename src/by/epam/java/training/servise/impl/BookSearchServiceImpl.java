package by.epam.java.training.servise.impl;

import by.epam.java.training.dao.BookSearchDAO;
import by.epam.java.training.dao.DAOFactory;
import by.epam.java.training.dao.exception.DAOException;
import by.epam.java.training.model.PageAttribute;
import by.epam.java.training.model.book.BookPreview;
import by.epam.java.training.servise.BookSearchService;
import by.epam.java.training.servise.exception.ServiceException;
import by.epam.java.training.servise.validation.ValidatorManager;
import by.epam.java.training.servise.validation.ValidatorType;
import org.apache.log4j.Logger;

import java.util.List;

public class BookSearchServiceImpl implements BookSearchService {
    private static final Logger logger = Logger.getLogger(BookSearchServiceImpl.class);

    private final BookSearchDAO bookSearchDAO = DAOFactory.getBookSearchDAO();


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
